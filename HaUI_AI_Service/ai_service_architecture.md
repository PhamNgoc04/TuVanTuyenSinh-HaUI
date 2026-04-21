# Tài liệu thiết kế: Nguyên lý hoạt động của HaUI AI Service

Tài liệu này mô tả chi tiết kiến trúc, luồng đi của dữ liệu và cách thức hoạt động của vi dịch vụ **HaUI AI Service**, chịu trách nhiệm cho trợ lý ảo "Mộc Lan" chuyên tư vấn tuyển sinh cho trường Đại học Công nghiệp Hà Nội.

---

## 1. Tổng quan Kiến trúc Hệ thống

HaUI AI Service là một **Microservice độc lập** được xây dựng bằng Python (FastAPI). Vi dịch vụ này giao tiếp với ứng dụng Android qua RESTful API, hoàn toàn tách biệt với Core Backend (Ktor Kotlin).

Sức mạnh của AI Service dựa trên 3 khối xử lý chính:
1. **Khối Xử lý Giọng Nói (Speech-to-Text):** Sử dụng mô hình xử lý ngôn ngữ mô phỏng sự nghe của AI (`OpenAI Whisper`).
2. **Khối Tri thức Cục bộ (Local Knowledge Base):** Sử dụng bộ quy tắc và dữ liệu cố định định dạng JSON (`knowledge_base.json`).
3. **Khối Tư duy Trả lời (Language Model - Hybrid Fallback):** Vận hành dự phòng theo 2 lớp:
   - **Lớp 1:** Mô hình học sâu (Deep Learning) cục bộ dựng bằng TensorFlow Keras (mô phỏng mô hình `Seq2Seq` + cơ chế `Attention`).
   - **Lớp 2 (Fallback):** LLM điện toán đám mây Google Gemini 2.0 Flash Lite.

---

## 2. Luồng xử lý chi tiết (Data Flow)

Có hai luồng vào dữ liệu chính cho AI Service từ App Android: **Nhập Text** và **Gửi thu âm Giọng nói**.

### 2.1. Luồng 1: Truy vấn bằng Văn bản (Text Query)

**Endpoint:** `POST /api/v1/ai/ask`

1. Người dùng nhập câu hỏi vào ứng dụng (VD: "Điểm chuẩn ngành CNTT là bao nhiêu?").
2. Ứng dụng Android đóng gói chuỗi này vào định dạng JSON và gọi HTTP POST đến AI Service.
3. FastAPI controller nhận request, bóc tách lấy `request.text` để truyền vào hàm trung tâm `find_answer(user_query: str)`.
4. Hàm `find_answer` sẽ trực tiếp chuyển giao cho **Cơ chế Hybrid Fallback** (Xem hình mô tả bên dưới).
5. Sau khi AI Service nảy sinh câu trả lời, FastAPI đóng gói vào HTTP Response và gửi JSON back về cho App hiển thị dưới dạng tin nhắn của "Mộc Lan".

### 2.2. Luồng 2: Truy vấn bằng Giọng nói (Voice Query)

**Endpoint:** `POST /api/v1/ai/voice`

1. App Android ghi âm giọng nói của người dùng và gửi đi dưới dạng **Multipart File** đính kèm âm thanh đến endpoint.
2. Tại Server FastAPI:
    - **Bước 1 (Lưu tạm):** Mở file nhận được và lưu tạm ngay vào ổ cứng dưới tên `temp_X`.
    - **Bước 2 (Speech-to-Text):** Nạp file `temp_` đó cho AI siêu thính giác `Whisper` (phiên bản mô hình `tiny`) với tham số chỉ định dịch ra tiếng Việt (`language="vi"`).
    - **Bước 3 (Tháo dỡ):** Sau khi `Whisper` trích xuất thành chuỗi ký tự kết quả (`transcribed_text`), file `temp_` bị xoá ngay lập tức để tiết kiệm ổ chứa server.
3. Ký tự `transcribed_text` này được lấy để truyền vào luồng xử lý văn bản ở hàm trung tâm `find_answer(...)`.
4. Server trả lời kết quả trong JSON, bao gồm luôn cả đoạn `transcription` (Text được dịch từ giọng nói) để Android Update hiển thị giao diện UI cho người người (tức là User đã nói gì).

---

## 3. Cơ chế xử lý truy vấn: Hybrid Fallback

Tim cốt yếu của ứng dụng AI này nằm ở chức năng tự chọn câu trả lời `find_answer(user_query)`. AI kiểm duyệt qua ba chu trình khép kín nhằm bảo đảm sự phản hồi ngay lập tức, tính xác thực cực cao với HaUI và tính dự phòng liên tục nếu câu hỏi quá hóc búa.

### 🟡 Vòng 1: Tìm kiếm trực tiếp trong Local Knowledge Base
Server sẽ bóc tất cả các câu trúc từ file cục bộ `knowledge_base.json` (chứa các cặp Hỏi-Đáp quan trọng nhất của trường).
* Kỹ thuật áp dụng: AI Service quét các tổ hợp câu hỏi trong thư viện tri thức này. Nếu phát hiện thấy có bất kỳ cụm từ khóa chuẩn nào lọt trúng hoặc giống hệt với truy vấn của bạn.
* Trạng thái đầu ra: Trả về trực tiếp chính xác từng chữ cho nội dung câu trả lời `item['answer']`. Điều này ngăn chặn việc LLM "ảo tưởng" (Hallucination) bịa ra học phí sai cho người hỏi.

### 🟢 Vòng 2: Mô Hình Học Sâu Keras Cục Bộ (TensorFlow Local Model)
*(Vòng này chỉ chạy nếu chưa dò thấy Keyword ở phòng một và Thư Viện TF đã load được)*.
* Với những câu hỏi gần giống, viết sai chính tả, hoặc ý nghĩa tương đương không nằm trực diện trong Database JSON.
* Phương pháp Seq2Seq với cơ chế Attention (đã được báo cáo trong khóa luận với sơ đồ kiến trúc Encoder-Decoder) sẽ xem xét ngọn nguồn ngữ cảnh câu (Mô hình sẽ cố gắng tự "quan sát" token). Nếu tìm ra câu hỏi gần nghĩa đã mớm sâu trong quá trình đào tạo `train_transformer.py` => Trả về câu trả lời.

### 🔵 Vòng 3: Fallback LLM (Gemini 2.0 Flash Lite)
Nếu 2 phương pháp trên bó tay hoàn toàn, hệ thống đẩy nhiệm vụ cho AI Vĩ Đại của Google.
1. Tại thời điểm chạy ban đầu, AI service lấy dữ liệu từ `knowledge_base` định dạng nó lại để **Bơm trực tiếp** vào *Phần Mô Tả Cốt Lõi (System Instruction)* của Gemini.
2. Trái tim LLM được lập trình bằng lệnh tinh thần: *"Bạn là nữ chuyên viên Mộc Lan, 24 tuổi, Cố vấn Tư vấn học vụ tại Đại học Công nghiệp Hà Nội... LUÔN LUÔN DỰA VÀO DỮ LIỆU SAU ĐÂY..."*.
3. API yêu cầu Gemini dự đoán và sáng tạo câu trả lời với giọng điệu người lớn, dùng emoji vui vẻ, nhưng tuyệt đối không dựa vào tri thức mở trên mạng khi tư vấn học vụ - chỉ được dựa trên đống Text mớm đầu.
4. Chờ kết quả và trả về cho App.

---

## 4. Middleware & Xử lý bảo mật

- **CORS Middleware:** Hệ thống sử dụng CORS `allow_origins=["*"]`. Tuy nhiên, vì giao tiếp Mobile/Server API được call bằng Retrofit client, phần lớn điều này là để khắc phục các tính năng kết nối Cross-domain với localhost trong quá trình gỡ lỗi Emulator/Điện thoại thật cục bộ.
- **Biến môi trường (`.ENV`):** Chứa khóa bảo mật `GEMINI_API_KEY` của Google Cloud, thiết kế giấu trong file không Public lên git, chặn rò rỉ lộ Key tốn tiền cước dịch vụ.
- **Fail-safe Exception:** Thiết kế mọi lúc cho tất cả Route trả về Message dự phòng. Đảm bảo UI di động bên kia Không bao giờ bị kẹt vòng lặp Loading xoay đều nếu có sự cố chết hàm cục bộ giữa chừng ở Model python.
