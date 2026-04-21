Chạy nhanh - mở 3 terminal riêng:

- Terminal 1 - AI Service:

powershell
cd d:\Mobile_Kotlin_DevPro\DACK\HaUI_AI_Service
python main.py


- Terminal 2 - Core Ktor:

powershell
cd d:\Mobile_Kotlin_DevPro\DACK\HaUI_Core_Ktor
.\gradlew run

- Terminal 3 - Android: chỉ cần click ▶️ Run trong Android Studio

Lưu ý: AI Service và Core Ktor phải đang chạy trước khi bấm Run Android. Nếu tắt một trong hai → tính năng tương ứng trên app sẽ báo lỗi kết nối.