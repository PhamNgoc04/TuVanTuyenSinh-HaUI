# CHƯƠNG 3: THIẾT KẾ VÀ TRIỂN KHAI ỨNG DỤNG ANDROID FRONTEND

Trong chương này, báo cáo sẽ trình bày một cách tường minh và sâu sắc về toàn bộ quy trình thiết kế, kiến trúc hệ thống và các giải pháp mã nguồn được áp dụng để thiết lập nên Frontend của Hệ thống Tư vấn Tuyển sinh Đại học Công nghiệp Hà Nội (HaUI). Ứng dụng được sinh viên triển khai bằng ngôn ngữ Kotlin lõi, áp dụng triệt để bộ công cụ Jetpack Libraries nhằm giải quyết bài toán hiệu năng cao.

## 3.1. Phân Tích Kiến Trúc Hệ Thống (Clean Architecture & MVVM)

Để ứng dụng có khả năng duy trì, dễ dàng bảo trì và thuận tiện cho việc nâng cấp mở rộng trong tương lai, đồ án đã loại bỏ phương pháp thiết kế nguyên khối (Monolithic App) kiểu cũ để chuyển đổi hoàn toàn sang mô hình **Clean Architecture** kết hợp với mẫu thiết kế **MVVM (Model - View - ViewModel)**. Sự kết hợp này mang lại 3 ưu điểm sống còn: Tách biệt mối quan tâm (Separation of Concerns), Dễ dàng kiểm thử (Testability), và Tái sử dụng mã nguồn.

### 3.1.1. Khối View (Tầng Giao Diện)
Khối View đại diện cho các `Activity` và `Fragment` trong ứng dụng. Trách nhiệm duy nhất của View là thao tác với UI (vẽ giao diện đồ họa) và bắt các sự kiện người dùng (Click, Scroll).
- **ViewBinding**: Toàn bộ ứng dụng sử dụng cơ chế ViewBinding được kích hoạt từ `build.gradle.kts`, giúp định tuyến và liên kết các file `.xml` trực tiếp vào mã Kotlin an toàn kiểu (Type-safe). Điều này loại bỏ hoàn toàn các lỗi sập ứng dụng (NullPointerException) do `findViewById` gây ra.
- **Quan sát viên (Observer Pattern)**: Tầng View không sở hữu dữ liệu. Nó đóng vai trò là một Observer (người quan sát). Khi nhận được tín hiệu biến đổi dữ liệu (LiveData emit) từ ViewModel, View sẽ lập tức cập nhật lại giao diện.

### 3.1.2. Khối ViewModel (Tầng Nghiệp Vụ Sinh Lực)
ViewModel là trung tâm quyền lực của mọi màn hình. Nó chịu trách nhiệm vận hành toàn bộ Logic nghiệp vụ:
- Các lớp như `HomeViewModel`, `NganhViewModel`, `InfoViewModel` được kế thừa từ lớp `androidx.lifecycle.ViewModel`.
- Các dữ liệu mà hệ thống trả về được đóng gói cẩn thận bên trong `MutableLiveData<T>`.
- **Khắc phục lỗi Sinh tồn (Lifecycle Awareness)**: Nhờ có kiến trúc này, khi thí sinh (người dùng) xoay ngang xoay dọc màn hình điện thoại, `Activity` bị tiêu hủy và hệ điều hành khởi tạo lại (Recreate), phần dữ liệu đang nạp dở vẫn được giữ nguyên vẹn bên trong `ViewModel` mà không cần phát sinh thêm các lệnh tải lại tốn băng thông Mạng.

### 3.1.3. Khối Repository (Tầng Kho Lữu Trữ Dữ Liệu)
Repository (Kho lưu trữ) áp dụng mẫu thiết kế **Repository Pattern**, đóng vai trò là "Nguồn chân lý duy nhất" (Single Source of Truth) của Frontend:
- Tầng này trừu tượng hóa các phương thức giao tiếp mạng. Các ViewModel khi cần dữ liệu chỉ cần gọi lệnh như `NganhRepository.getAllNganhHoc()`.
- Việc tách Repo ra khỏi ViewModel giúp sinh viên trong tương lai dễ dàng cắm cấu hình mô phỏng (Mocking) hoặc cắm thêm Local Database (Room SQLite) làm Cache (bộ nhớ đệm) mà không làm vỡ các cấu trúc code bên trên.
- Nó hoạt động như một bộ đệm cầu nối tới `ApiClient.instance` (Sử dụng Retrofit kết hợp thư viện Gson để bóc tách JSON siêu tốc).

## 3.2. Cấu Trúc Đa Tiến Trình (Multithreading) & Coroutines

Xử lý đa tiến trình trên thiết bị di động mang tính chất sống còn. Mặc định trên Android, nếu ứng dụng chặn luồng chính quá 5 giây sẽ bị hệ điều hành "giết chết" bằng lỗi ANR (Application Not Responding).

### 3.2.1. Tích hợp Kotlin Coroutines thay thế Callbacks
Ban đầu, các Framework gọi mạng cũ sử dụng kỹ thuật Callback `enqueue(object: Callback<T>)`, tạo ra các khối mã lệnh lồng nhau rất rối rắm (Callback Hell). Do đó, ứng dụng đã được refactor toàn diện bằng **Coroutines** kết hợp hàm `awaitResponse()` của bản mở rộng Retrofit-KTX.

```kotlin
// Ví dụ Code mẫu áp dụng trong InfoViewModel
fun loadAllInfo() {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val hpRes = repository.getHocPhi().awaitResponse()
            val hbRes = repository.getHocBong().awaitResponse()
            // Dữ liệu được đưa thẳng về LiveData
        } catch(e: Exception) { ... }
    }
}
```
Khối mã lệnh trên sở hữu 2 công nghệ cốt lõi:
- **`viewModelScope`**: Tự động đánh dấu vòng đời của Coroutine. Nếu người dùng đột ngột thoát màn hình Thông tin, tiến trình tải dữ liệu bị hủy lặp tức, giúp thiết bị không bị cạn kiệt bộ nhớ.
- **`Dispatchers.IO`**: Điều phối tiến trình vào trong bể đa luồng (Thread-pool) đặc trị tác vụ Mạng và ghi đọc đĩa (I/O). Nhờ vậy luồng UI (Main Thread) rảnh tay để đáp ứng thao tác cuộn (Scroll) mượt mà 60 Frames-Per-Second.

## 3.3. Tối Ưu Hóa Giao Diện Đa Tầng (Master - Detail PagerFlow)

Bài toán: Màn hình chi tiết Ngành học chứa lượng thông tin khổng lồ chia làm 5 hạng mục: Chi tiết chung, Cơ hội nghề nghiệp, Chỉ tiêu tuyển sinh, Yêu cầu điểm chuẩn đầu vào và Chương trình đào tạo.

### 3.3.1. Kỹ thuật ViewPager2 & TabLayoutMediator
Thay vì nhồi nhét tất cả vào một màn hình cuộn (dễ gây gián đoạn khả năng quan sát của thí sinh), ứng dụng triển khai `ViewPager2` quản lý 5 Sub-Fragments:
- `NganhHocDetailActivity` đóng vai trò là vỏ bọc rỗng (Master Context).
- Adapter `FragmentStateAdapter` vẽ 5 Tab một cách linh hoạt, chỉ khởi tạo Tab kế tiếp khi thí sinh Vuốt qua (Lazy Loading).
- **Tuyệt chiêu Chia Sẻ ViewModel**: Cả 5 Fragment con không nạp Dữ liệu độc lập từ API (điều đó sẽ làm sụp đổ lưu lượng mạng của REST server). Thay vào đó, cả 5 đứa "con" đều móc ngược lên "cha" bằng lệnh `(requireActivity() as NganhHocDetailActivity).nganhViewModel` để sử dụng chung một kho LiveData duy nhất đã nạp từ trước. Một lời giải xuất sắc về phân bổ Tài Nguyên Hệ Thống (Resource Management).

## 3.4. Hệ Thống Trao Quyền Mobile CMS (Content Management System)

Không chỉ phục vụ Thí sinh, Đồ án đẩy mạnh tính năng Quản trị Động (Dynamic Admin) thay vì cấu hình cứng tĩnh (Static Layout).

### 3.4.1. Cơ chế Quản trị Tức Thời (Real-time Management)
- Hệ thống thiết kế các `BottomSheetDialog` cho phép cán bộ tuyển sinh thêm mới hoặc cập nhật Ngành học trực tiếp từ ứng dụng thông qua giao diện thẻ rút từ đuôi thiết bị lên. ViewBinding sẽ lấy Input của cán bộ, đóng gói thành khối mã JSON, bắn lên Ktor API Server.
- Toàn bộ thay đổi sẽ lập tức đổ xuống Backend Ktor -> Lưu xuống SQL và Realtime đẩy về thiết bị người dùng cuối. 
- Tính năng phân quyền hiển thị (Role-based UI): Chỉ những phiên đăng nhập mà Server định tuyến JWT Role là `"ADMIN"` thì khối chức năng CMS này mới xuất hiện, loại bỏ nguy cơ lộ lọt Endpoint tới Thí sinh.

## 3.5. Trí Tuệ Nhân Tạo: Tích hợp Hybrid AI Client

Hệ thống Bot Khảo thí (AiChatActivity) là đỉnh cao công nghệ của phần Frontend di động.
- Giao diện `RecyclerView` được tinh chỉnh tùy biến, đan xen layout Bong bóng Phải (Thí sinh) và Trái (Chatbot). Adapter linh hoạt giúp chuyển đổi hình nền mềm mại (Drawable Shape).
- Khi người dùng gửi lệnh Truy Vấn (nhập bằng text hoặc đọc âm thanh Voice), khối lệnh Background sẽ đẩy thông tin qua API Giao tiếp `/chat` của khối Python Backend (Bao gồm Transformer/GenAI).
- Giao diện được xử lý một Lottie Animation (Hiệu ứng gõ phím) tạo cho thí sinh cảm giác rất tự nhiên và "Sống động" như đang nhắn tin với một giảng viên nhân sự thực thụ của trường.

## 3.6. Tổng Kết Chương
Với việc áp dụng bài bản các nguyên lý thiết kế hệ thống phần mềm hướng đối tượng (SOLID), loại bỏ phương pháp lập trình lộn xộn, kết rễ từ các tiến trình xử lý Mạng bằng Coroutine, Khối Frontend Android đã trở thành một nền tảng "Nồi đồng cối đá" thực sự. Nó đủ vững chãi và chuẩn mực để có thể nâng cấp thành các hệ thống thương mại cấp độ Doanh nghiệp (Enterprise-level) cho Đại Học Công Nghiệp Hà Nội ở thời kỳ Vĩ Mô.
