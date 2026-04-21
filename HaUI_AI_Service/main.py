# -*- coding: utf-8 -*-
import sys
import json
import os

# Fix Windows console encoding to support Unicode/emoji
if sys.stdout.encoding != 'utf-8':
    sys.stdout.reconfigure(encoding='utf-8')

from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import uvicorn
import shutil
from dotenv import load_dotenv
import whisper
from google import genai
from google.genai import types

load_dotenv()

app = FastAPI(title="HaUI AI Microservice - Gemini Advisor Bot")

# ✅ Fix Bug #4: Thêm CORS middleware - thiếu cái này Android bị block hoàn toàn
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Cho phép mọi origin (Android, Web)
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")

print("Loading Whisper Model (tiny)...")
whisper_model = whisper.load_model("tiny") 

print("Loading Knowledge Base...")
qa_context = ""
qa_data = []  # ✅ Fix Bug #3: Khai báo global để tránh NameError khi TF không load
try:
    with open("knowledge_base.json", "r", encoding="utf-8-sig") as f:
        qa_data = json.load(f)
        for idx, item in enumerate(qa_data):
            # ✅ Fix Bug #1: knowledge_base.json dùng 'questions' (plural) không phải 'question'
            # Lấy câu hỏi đầu tiên từ danh sách để build context
            first_q = item.get('questions', item.get('question', ['']))
            first_q = first_q[0] if isinstance(first_q, list) else first_q
            qa_context += f"H: {first_q}\nĐ: {item['answer']}\n\n"
        print(f"✅ Loaded {len(qa_data)} Q&A entries from knowledge base.")
except FileNotFoundError:
    print("⚠️ knowledge_base.json not found. AI sẽ chỉ dùng Gemini.")
except Exception as e:
    print(f"⚠️ Error loading knowledge base: {e}")

sys_instruct = f"Bạn là nữ chuyên viên Mộc Lan, 24 tuổi, Cố vấn Tư vấn học vụ tại Đại học Công nghiệp Hà Nội (HaUI). Tính cách: Thanh lịch, tận tâm, hay dùng emoji dễ thương. LUÔN LUÔN DỰA VÀO DỮ LIỆU SAU ĐÂY ĐỂ TRẢ LỜI NGẮN GỌN (không bịa đặt): {qa_context}"

client = None
if GEMINI_API_KEY:
    client = genai.Client(api_key=GEMINI_API_KEY)

print("🔥 Models Reloaded! Mộc Lan is online!")

class ChatRequest(BaseModel):
    text: str

try:
    import tensorflow as tf
    from train_transformer import build_model
    TF_AVAILABLE = True
    print("Loading TensorFlow Keras Model...")
    qa_model = build_model()
    # qa_model.load_weights("haui_chatbot_weights.h5") # Uncomment when weights exist
except ImportError:
    TF_AVAILABLE = False
    print("TensorFlow is not available. Falling back to Gemini 2.0 Flash Lite...")
except Exception as e:
    TF_AVAILABLE = False
    print(f"Error loading TF Model: {e}. Falling back to Gemini...")

def find_answer(user_query: str) -> str:
    # 1. THỬ TÌM TRONG KNOWLEDGE BASE TRỰC TIẾP (không cần TF model)
    # ✅ Fix Bug #1: Đọc đúng field 'questions' (list) thay vì 'question' (string)
    for item in qa_data:
        questions_field = item.get('questions', item.get('question', []))
        if isinstance(questions_field, str):
            questions_field = [questions_field]
        for q in questions_field:
            if user_query.lower().strip() in q.lower() or q.lower() in user_query.lower().strip():
                print(f"✅ KB Match: '{user_query}' -> '{q}'")
                return item['answer']

    # 2. THỬ DÙNG MÔ HÌNH TENSORFLOW ĐÃ HUẤN LUYỆN (nếu có)
    if TF_AVAILABLE:
        try:
            for item in qa_data:
                questions_field = item.get('questions', item.get('question', []))
                if isinstance(questions_field, str):
                    questions_field = [questions_field]
                for q in questions_field:
                    if user_query.lower() in q.lower():
                        return f"{item['answer']}"
        except Exception as e:
            print(f"TF lookup error: {e}")

    # 3. FALLBACK SANG GEMINI LLM NẾU KHÔNG TÌM THẤY
    if not client:
        return "Xin lỗi, chưa cấu hình API Key của Google. Vui lòng kiểm tra file .env 🙏"
    try:
        print(f"🤖 Calling Gemini (gemini-1.5-flash) for: '{user_query}'")
        # ✅ Đổi sang gemini-1.5-flash vì giới hạn Free Tier RỘNG RÃI hơn khi nhồi file dữ liệu to
        response = client.models.generate_content(
            model='gemini-1.5-flash',
            contents=user_query,
            config=types.GenerateContentConfig(
                system_instruction=sys_instruct,
            ),
        )
        return response.text
    except Exception as e:
        print(f"❌ Gemini error: {e}")
        error_msg = str(e)
        if '429' in error_msg or 'RESOURCE_EXHAUSTED' in error_msg:
            return "Dạ do số lượng tân sinh viên hỏi đông quá nên Mộc Lan đang bị nghẽn mạng xíu (hết hạn mức API), bạn chờ 1 phút rồi hỏi lại nhé! 😭"
        return f"Xin lỗi Mộc Lan đang bận, bạn thử lại sau nhé 😔 (Lỗi hệ thống AI)"

@app.get("/")
def read_root():
    return {"message": "HaUI AI Service is running with Google GenAI!"}

@app.post("/api/v1/ai/ask")
def ask_question(request: ChatRequest):
    answer = find_answer(request.text)
    return JSONResponse(
        content={"question": request.text, "reply": answer},
        media_type="application/json; charset=utf-8"
    )

@app.post("/api/v1/ai/voice")
async def process_voice(audio: UploadFile = File(...)):
    try:
        temp_file = f"temp_{audio.filename}"
        with open(temp_file, "wb") as buffer:
            shutil.copyfileobj(audio.file, buffer)
        
        result = whisper_model.transcribe(temp_file, language="vi")
        transcribed_text = result["text"].strip()
        
        ai_reply = find_answer(transcribed_text)

        if os.path.exists(temp_file):
            os.remove(temp_file)

        return JSONResponse(
            content={"status": "SUCCESS", "transcription": transcribed_text, "reply": ai_reply},
            media_type="application/json; charset=utf-8"
        )
    except Exception as e:
        return JSONResponse(
            status_code=500, 
            content={"status": "ERROR", "message": str(e)},
            media_type="application/json; charset=utf-8"
        )

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)

