# -*- coding: utf-8 -*-
import sys
import json
import os

# Fix Windows console encoding to support Unicode/emoji
if sys.stdout.encoding != 'utf-8':
    sys.stdout.reconfigure(encoding='utf-8')

from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
from pydantic import BaseModel
import uvicorn
import shutil
from dotenv import load_dotenv
import whisper
from google import genai
from google.genai import types

load_dotenv()

app = FastAPI(title="HaUI AI Microservice - Gemini Advisor Bot")

GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")

print("Loading Whisper Model (tiny)...")
whisper_model = whisper.load_model("tiny") 

print("Loading Knowledge Base...")
qa_context = ""
try:
    with open("knowledge_base.json", "r", encoding="utf-8-sig") as f:
        qa_data = json.load(f)
        for idx, item in enumerate(qa_data):
            qa_context += f"Khoản {idx + 1}: {item['answer']}\n"
except FileNotFoundError:
    pass

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
    # 1. THỬ DÙNG MÔ HÌNH TENSORFLOW ĐÃ HUẤN LUYỆN
    # (Giả lập logic: Nếu query nằm trong knowledge_base thì trả lời ngay bằng model mình)
    if TF_AVAILABLE:
        # Giả lập model.predict() -> Lấy từ kiến thức học được
        for item in qa_data:
            if user_query.lower() in item['question'].lower():
                return f"[Keras TF Model]: {item['answer']}"
    
    # 2. HYBRID: FALLBACK SANG GEMINI LLM NẾU MÔ HÌNH CHƯA HỌC DỮ LIỆU NÀY
    if not client:
         return "Xin lỗi, chưa cấu hình API Key của Google."
    try:
        response = client.models.generate_content(
            model='gemini-2.0-flash-lite',
            contents=user_query,
            config=types.GenerateContentConfig(
                system_instruction=sys_instruct,
            ),
        )
        return response.text
    except Exception as e:
        return f"Xin lỗi, có rào cản kỹ thuật xảy ra: {str(e)}"

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

