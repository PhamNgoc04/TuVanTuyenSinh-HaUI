# Hướng Dẫn Khởi Chạy Bộ Máy AI (HaUI AI Service)

Bộ phận AI Service này là một "bộ não" Xử lý Ngôn ngữ Tự nhiên độc lập (Microservice), chịu trách nhiệm nhận diện Câu Ngữ Nghĩa (NLP) và Giọng Nói (Speech-to-Text).

## 🛠 YÊU CẦU TRƯỚC KHI CHẠY
1. Cần cài đặt **Python 3.10 trở lên** (Hệ thống hiện tại đang xài 3.14).
2. Tắt các ứng dụng chạy ngầm chiếm quá tải GPU/RAM vì AI Model tốn từ 500MB đến 1GB cấu hình bộ nhớ RAM.

---

## 🚀 3 BƯỚC KHỞI ĐỘNG SERVER AI LÊN TRÊN INTELIJ/TERMINAL

### Cực Quan Trọng (Bước 0): Phải trỏ đúng vào thư mục AI
AI Service được tách độc lập thành thư mục riêng khỏi Ktor. Bạn bắt buộc phải di chuyển thư mục trỏ lệnh vào trong nó bằng lệnh sau:
```shell
cd HaUI_AI_Service
```

### Bước 1: Nạp Bộ Tham Số & Công Cụ Học Máy
Chạy lệnh bên dưới để phần mềm tự động cài bộ cỗ máy **Sentence-Transformers, Pytorch và Whisper**.
*(Chỉ cần thực hiện bước này 1 lần duy nhất trong đời)*
```shell
python -m pip install -r requirements.txt
```

### Bước 2: Tải và Khởi động Server API AI
Mở đường hầm truyền API sang cổng **8000** để App Android có thể giao tiếp tới.
```shell
python -m uvicorn main:app --reload
```
> **Lưu ý:** Lần đầu chạy lệnh uvicorn, chương trình sẽ khựng (dừng hình) khoảng 1-2 phút ở bước tải do ổ đĩa của bạn đang tải weights mạng nơ-ron từ HuggingFace về máy.
> Các lần chạy sau Server sẽ bật rẹt rẹt trong vòng 3 giây!

---
## 🧪 HƯỚNG DẪN TEST THỬ AI BẰNG POSTMAN HOẶC CMD

### Test Tính Năng NLP - Chatbot:

Hệ thống KHÔNG match string cứng ngắc mà dựa vào khoảng cách Vector, bạn có thể nhập lệnh sau để thử nghiệm:
**Target API:** `POST http://localhost:8000/api/v1/ai/ask`
**Body JSON:**
```json
{
    "text": "hoc phi nganh cntt cua truong la bn"
}
```
**Nhận được:** Câu trả lời chính xác học phí dao động 18 - 25tr.

### Test Tính Năng AI Nhận Dạng Giọng Nói (Whisper):
**Target API:** `POST http://localhost:8000/api/v1/ai/voice`
**Body Form-data:**
- Key: `audio` (Bật Type từ "Text" sang "File")
- Value: *[Tải lên 1 đoạn ghi âm đuôi .wav hỏi về Trường Công nghiệp]*

**Nhận được:** 
AI tự động Transcribe ghi âm thành Văn bản + Đọc Văn bản chạy vào Bot + Trả về luôn thông tin cần tư vấn liên quan tới ghi âm đó!
