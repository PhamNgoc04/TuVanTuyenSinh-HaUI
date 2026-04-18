 
 
NHẬN XÉT CỦA GIẢNG VIÊN PHẢN BIỆN 
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
........................................................................................................................................... ...........................................................................................................................................
............................................................ 
Điểm: ….. (Bằng chữ                         ) 
Ngày ….. tháng……năm 20.... 
Giảng viên phản biện
NHẬN XÉT CỦA GIẢNG VIÊN HƯỚNG DẪN 
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
...........................................................................................................................................
........................................................................................................................................... ...........................................................................................................................................
............................................................ 
Điểm: ….. (Bằng chữ                          ) 
Ngày ….. tháng……năm 20... 
Giảng viên hướng dẫn
LỜI CẢM ƠN 
Đầu tiên, em xin gửi lời cảm ơn đến các thầy cô trong Học viện Công nghệ Bưu chính Viễn thông nói chung và các thầy cô trong khoa Công nghệ thông tin nói riêng. Cảm ơn các thầy cô đã dạy cho em rất nhiều kiến thức và kinh nghiệm trong suốt những năm đại học, giúp em có được cơ sở lý thuyết vững vàng và tạo một môi trường học tập chuyên nghiệp để em có thể phát triển bản thân và vững bước trên con đường phát triển sự nghiệp. 
Em xin gửi lời cảm ơn chân thành đến cô Đỗ Thị Liên đã tận tình hướng dẫn, dẫn dắt cũng như tạo điều kiện giúp em hoàn thành đồ án này, em xin chúc cô và gia đình thật nhiều sức khỏe và thành công. 
Cuối cùng, em xin chân thành cảm ơn gia đình và bạn bè, đã luôn tạo điều kiện, quan tâm, giúp đỡ, động viên em trong suốt quá trình học tập và hoàn thành khóa luận tốt nghiệp. 
Vì thời gian có hạn, trình độ hiểu biết của bản thân còn nhiều hạn chế. Cho nên trong đồ án này không tránh khỏi những thiếu sót, em rất mong nhận được sự đóng góp ý kiến của tất cả các thầy cô giáo cũng như các bạn bè để đồ án em được hoàn thiện hơn nữa. 
Em xin chân thành cảm ơn!  
MỤC LỤC
Chương 1: Tổng quan về hệ thống tư vấn thông tin tuyển sinh PTIT.........................................................................................7
1.1 Tổng quan hệ thống.........................................................................................................................................................7
1.1.1 Mục đích hệ thống...................................................................................................................................................7
1.1.2 Khảo sát các sản phẩm tương tự..............................................................................................................................7
1.1.3 Yêu cầu hoạt động của ứng dụng.............................................................................................................................8
1.2 Thiết kế tương tác.........................................................................................................................................................13
1.3 Phương pháp tiếp cận và giải quyết vấn đề...................................................................................................................14
1.3.1 Mô hình tổng quát hệ thống...................................................................................................................................14
1.3.2 Phương pháp xây dựng phần mềm.........................................................................................................................14
1.3.3 Kiến trúc phần mềm..............................................................................................................................................16
1.3.4 Công nghệ triển khai hệ thống...............................................................................................................................19
1.4 Tổng kết chương...........................................................................................................................................................23
Chương 2: Công nghệ tư vấn thông tin tuyển sinh tự động......................................................................................................24
2.1 Yêu cầu tư vấn tuyển sinh tự động................................................................................................................................24
2.2 Nguyên lý hoạt động.....................................................................................................................................................24
2.2.1 Nguyên lý hoạt động của speech to text................................................................................................................24
2.2.2 Nguyên lý tư vấn tự động......................................................................................................................................25
2.3 Xử lý bài toán với mô hình seq2seq và cơ chế attention................................................................................................26
2.3.1 Giới thiệu mô hình Sequence-to-Sequence............................................................................................................26
2.3.2 Giới thiệu cơ chế attention.....................................................................................................................................27
2.4 Công nghệ áp dụng.......................................................................................................................................................28
2.5 Kết luận chương............................................................................................................................................................32
Chương 3: Phân tích thiết kế hệ thống.....................................................................................................................................35
3.	1 Phân tích hệ thống........................................................................................................................................................35
3.1.1 Biểu đồ usecase.....................................................................................................................................................35
3.1.2 Kịch bản của các usecase.......................................................................................................................................45
3.1.3 Biểu đồ lớp phân tích.............................................................................................................................................54
3.2 Thiết kế hệ thống tư vấn tuyển sinh tự động..................................................................................................................56
Chương 4: Thiết kế cơ sở dữ liệu.............................................................................................................................................66
Kết luận chung.........................................................................................................................................................................71
Phụ lục: Cài đặt và triển khai...................................................................................................................................................72
DANH MỤC CÁC HÌNH
Hình 1. 1: Tổng quát hệ thống	14
Hình 1. 2 Mô hình tổng quát hệ thống	15
Hình 1. 3 Kiến trúc Clean Architecture	17
Hình 1. 4 Mô hình MVVM	     19
Hình 2. 1 Mô hình xử lý...........................................................................................................................................................24
Hình 2. 2 Nguyên lý hoạt động speech to text..........................................................................................................................25
Hình 2. 3 Ví dụ về nguyên lý hoạt động của mô hình ngôn ngữ..............................................................................................25
Hình 2. 4 Mô tả về việc huấn luyện trước................................................................................................................................26
Hình 2. 5 Mô hình Encoder - Decoder.....................................................................................................................................27
Hình 2. 6 Cơ chế extension......................................................................................................................................................27
Hình 2. 7 Mô hình transformer................................................................................................................................................28
Hình 2. 8 Cơ chế self – extension của transformer...................................................................................................................30
Hình 2. 9 Mô tả về attension vector.........................................................................................................................................30
Hình 2. 10 Kiến trúc transformer.............................................................................................................................................31
Hình 2. 11 Sơ đồ kiến trúc của OpenAi Whisper.....................................................................................................................32
Hình 2. 12 Một số thông tin từ học viện...................................................................................................................................33
Hình 2. 13 Biểu đồ training loss...............................................................................................................................................34
Hình 3. 1 Usecase tổng quan hệ thống	35
Hình 3. 2 Chức năng xem thông tin trường	36
Hình 3. 3 Chức năng xem thông tin ngành	36
Hình 3. 4 Chức năng tư vấn tự động	37
Hình 3. 5 Chức năng xem thông tin học phí	37
Hình 3. 6 Chức năng xem thông tin nghề nghiệp	38
Hình 3. 7 Chức năng xem phương thức xét tuyển	38
Hình 3. 8 Chức năng xem thông tin học bổng	39
Hình 3. 9 Chức năng xem tin tức	39
Hình 3. 10 Chức năng xem quy trình nhập học	40
Hình 3. 11 Chức năng chỉnh sửa thông tin trường	40
Hình 3. 12 Chức năng chỉnh sửa thông tin ngành	41
Hình 3. 13 Chức năng chỉnh sửa thông tin nghề nghiệp	41
Hình 3. 14 Chức năng chỉnh sửa thông tin học bổng	42
Hình 3. 15 Chức năng chỉnh sửa tin tức	42
Hình 3. 16 Usecase chỉnh sửa quy trình nhập học	43
Hình 3. 17 Usecase chỉnh sửa thông tin học phí	43
Hình 3. 18 Usecase chỉnh sửa phương thức xét tuyển	44
Hình 3. 19 Biểu đồ lớp phân tích	55
Hình 3. 20 Biểu đồ tuần tự chức năng xem thông tin trường	55
Hình 3. 21 Biểu đồ tuần tự chức năng xem thông tin ngành	56
Hình 3. 22 Biểu đồ tuần tự chức năng tư vấn tự động	56
Hình 3. 23 Biểu đồ tuần tự chức năng xem thông tin học bổng	57
Hình 3. 24 Biểu đồ tuần tự chức năng xem thông tin học phí	57
Hình 3. 25 Biểu đồ tuần tự chức năng xem thông tin nghề nghiệp	58
Hình 3. 26 Biểu đồ tuần tự chức năng xem tin tức	58
Hình 3. 27 Biểu đồ tuần tự chức năng xem quy trình nhập học	59
Hình 3. 28 Biểu đồ tuần tự chức năng xem phương thức xét tuyển	59
Hình 3. 29 Biểu đồ tuần tự chức năng chỉnh sửa thông tin trường	60
Hình 3. 30 Biểu đồ tuần tự chức năng chỉnh sửa thông tin ngành	60
Hình 3. 31 Biểu đồ tuần tự chức năng chỉnh sửa thông tin nghề nghiệp	61
Hình 3. 32 Biểu đồ tuần tự chức năng chỉnh sửa thông tin học bổng	61
Hình 3. 33 Biểu đồ tuần tự chức năng chỉnh sửa tin tức	62
Hình 3. 34 Biểu đồ tuần tự chức năng chỉnh sửa quy trình nhập học	62
Hình 3. 35 Biểu đồ lớp thực thể	63
Hình 3. 36 Biểu đồ lớp thiết kế	64
Hình 3. 37 Biểu đồ thiết kế cơ sở dữ liệu	65
Chương 1: Tổng quan về hệ thống tư vấn thông tin tuyển sinh PTIT
Tại chương 1: Tổng quan về hệ thống tư vấn thông tin tuyển sinh PTIT, em sẽ trình bày tổng quan về yêu cầu cho hệ thống tư vấn tuyển sinh đồng thời cũng khảo sát hiện trạng và so sánh với những hệ thống tư vấn hiện có. Điều này nhằm mục đích xác định những yếu điểm cũng như những ưu điểm của các hệ thống đối thủ, từ đó đề xuất những cải tiến cho hệ thống của mình. 
Đồng thời em sẽ giới thiệu khái quát qua về yêu cầu phần mềm cho hệ thống tư vấn, bao gồm những chức năng cụ thể mà người dùng có thể mong đợi từ sản phẩm. Ngoài ra, chương này sẽ đưa ra thiết kế tương tác để minh họa diện mạo đầu ra của hệ thống, giúp dễ dàng hình dung về ứng dụng. Trên cơ sở đó em tiến hành xác định phương pháp tiếp cận và giải quyết vấn đề cho bài toán đặt ra.
1.1 Tổng quan hệ thống
1.1.1 Mục đích hệ thống
Hệ thống phục vụ cho công tác tư vấn tuyển sinh, định hướng nghề nghiệp và xem chỉ tiêu xét tuyển nhằm cung cấp những thông tin cần thiết về Học viện Công nghệ Bưu chính viễn thông ví dụ như các thông tin về chương trình học, yêu cầu đầu vào và cơ hội việc làm sau khi tốt nghiệp,....  
Học sinh, sinh viên cũng có thể nắm bắt được thông tin về các quy trình cần thiết lúc nhập học như là hướng dẫn về quy trình nộp hồ sơ xét tuyển, cách chuẩn bị cho các kỳ thi đầu vào và các vấn đề liên quan đến đăng ký tuyển sinh hay những thông tin chi tiết về học bổng, hỗ trợ tài chính và các nguồn tài trợ khác để giúp học sinh đạt được mục tiêu học tập của mình. 
Ngoài ra, hệ thống còn có chức năng tư vấn tự động (người dùng có thể chat hoặc sử dụng giọng nói để đưa ra yêu cầu) để có thể nắm thông tin về các khóa học, chương trình học, ngành nghề phù hợp với từng học sinh.
1.1.2 Khảo sát các sản phẩm tương tự
Một số hệ thống tư vấn tuyển sinh: 
•	Website ketnoigiaoduc.vn: là một cổng thông tin điện tử về tuyển sinh đào tạo của Công ty Cổ phần Cổng thông tin Giáo dục Việt Nam. Trang web cung cấp các thông tin về quy chế, quy định về thi và tuyển sinh các cấp, các chính sách mới nhất của nhà nước về giáo dục đào tạo, những tư vấn lời khuyên chuyên gia về hướng nghiệp, chọn trường, thi cử, các bí quyết về tuyển sinh hiệu quả, vv. giúp thí sinh tìm hiểu những thông tin tuyển sinh, thông tin đầy đủ và cập nhật của các trường, các đơn vị giáo dục đào tạo, các nhà tuyển sinh cả nước trước khi đưa ra các quyết định đăng ký tuyển sinh nhập học
•	Website tuyensinh.ufm.edu.vn: Đại học tài chính, Marketing: là cổng thông tin tuyển sinh của Đại học tài chính - Marketing: Cung cấp các thông tin như hướng nghiệp, thông tin các chuyên ngành đào tạo, thông tin tuyển sinh liên kết quốc tế, thông tin nhập học, … 
•	Học viện Công nghệ Bưu chính Viễn thông (PTIT): PTIT Slink, PTIT Slink tuyển sinh - là các ứng dụng cung cấp đầy đủ các thông tin về nhà trường ví dụ như thông tin nhà trường, thông tin các ngành học, ứng dụng được Học viện Công nghệ Bưu chính Viễn thông phát triển nhằm hỗ trợ thí sinh và phụ huynh trong quá trình tuyển sinh vào trường. Ứng dụng PTIT S-Link Tuyển sinh là một công cụ hữu ích giúp thí sinh và phụ huynh thuận tiện hơn trong quá trình tuyển sinh vào Học viện Công nghệ Bưu chính Viễn thông. Ứng dụng cung cấp thông tin tuyển sinh đầy đủ và chính xác về các phương thức tuyển sinh, chỉ tiêu, tổ hợp xét tuyển,... của Học viện. đồng thời cung cấp các thông tin hỗ trợ tuyển sinh như lịch thi, hướng dẫn thí sinh,...
Nhìn chung, các trường đại học tại Việt Nam đều có hệ thống tư vấn tuyển sinh đa dạng, bao gồm tư vấn trực tuyến, tư vấn qua điện thoại và tư vấn trực tiếp. Các hệ thống tư vấn này nhằm cung cấp thông tin tuyển sinh và hỗ trợ thí sinh trong quá trình lựa chọn ngành nghề phù hợp hay nắm thêm các thông tin chi tiết. 
Do đó các bạn học sinh, sinh viên có thể có nhiều cách tiếp cận thông tin và có rất nhiều thông tin có thể nắm bắt. Tuy nhiên đối với việc tư vấn trực tiếp, đôi khi người tư vấn viên lại không có mặt ngay tức thì để hỗ trợ giải đáp kịp thời. Từ điều này, có thể cải thiện thêm chức năng tư vấn tuyển sinh tự động không cần tới nhân viên tư vấn. Đặc biệt trong trường hợp các bạn không tiện sử dụng bàn phím, một chức năng nhận diện giọng nói sẽ giúp các bạn có thể dễ dàng đưa ra câu hỏi.
1.1.3 Yêu cầu hoạt động của ứng dụng
Sau khi khảo sát các hệ thống trên, có thể nhận thấy các hệ thống tư vấn tuyển sinh đều có các điểm mạnh, bao gồm đa dạng thông tin, thông tin chính xác và cập nhật, cũng như khả năng hỗ trợ thí sinh trong việc lựa chọn ngành nghề và quá trình tuyển sinh. 
Mỗi khi tới kỳ tuyển sinh, việc cung cấp thông tin giải đáp thắc mắc cho các bạn học sinh, sinh viên là rất quan trọng. Với số lượng sinh viên mỗi năm là cực kỳ lớn, việc cung cấp thông tin chính xác và kịp thời là một việc rất quan trọng. Ngoài ra, với số lượng lớn như vậy, việc nhận về số lượng các câu hỏi và các cách thức hỏi khác nhau cho một mục đích là rất nhiều. Vậy làm sao để có thể nhận biết được đâu là mục đích mà câu hỏi hướng tới để đưa ra câu trả lời chính xác? Ngoài ra, để đem lại sự thuận tiện cho những thời điểm học sinh, sinh viên không tiện để có thể soạn và nhắn ra câu hỏi, làm thế nào để có thể hỗ trợ họ? Tuy nhiên các hệ thống trên đang triển khai tư vấn dạng tư vấn trực tiếp, cần người tư vấn cho sinh viên. Trường hợp người tư vấn trực tiếp đang không thể trả lời, sinh viên có thể nhận được thông tin chậm trễ. Để giúp các bạn có thể dễ dàng hơn trong việc tìm kiếm thông tin, bên cạnh các chức năng thông tin, hệ thống xây dựng chức năng hỗ trợ giải đáp thắc mắc tự động, các bạn học sinh và sinh viên có thể nhận được thông tin cần thiết một cách nhanh chóng.
Trong một số trường hợp khi các bạn học sinh, sinh viên không tiện sử dụng bàn phím, hệ thống có thể hỗ trợ các bạn bằng chức năng nhận diện giọng nói, dễ dàng đặt câu hỏi bằng lời nói.
 
1.1.3.1 Phần dành cho người dùng cuối
●	Đăng nhập: Người dùng thực hiện truy cập vào hệ thống → hệ thống hiển thị màn hình đăng nhập → Người dùng nhập thông tin tài khoản và nhấn nút đăng nhập → Hệ thống sẽ chuyển sang màn hình trang chủ
●	Đăng ký: Người dùng thực hiện truy cập vào hệ thống → hệ thống hiển thị màn hình đăng nhập → Người dùng nhấn chọn đăng ký →  Hệ thống hiển thị màn hình đăng ký → Người dùng nhập thông tin tài khoản và nhấn nút đăng ký → Hệ thống sẽ hiển thị trạng thái đăng ký và  chuyển về màn hình đăng nhập
●	Đăng xuất: Người dùng đã đăng nhập thành công và thực hiện di chuyển qua màn thông tin → hệ thống hiển thị giao diện thông tin → Người dùng nhấn vào nút Đăng xuất → hệ thống hiển thị thông báo bạn có muốn đăng xuất → Người dùng xác nhận → hệ thống di chuyển về màn hình đăng nhập
●	Xem thông tin trường: sinh viên đăng nhập vào hệ thống → chọn chức năng giới thiệu về trường → hệ thống hiển thị thông tin về trường, sinh viên nhấn chọn lịch sử → hệ thống hiển thị thông tin về lịch sử hình thành
●	Xem thông tin ngành: sinh viên chọn xem thông tin ngành → hệ thống hiển thị danh sách các ngành học và chỉ tiêu tuyển sinh đi kèm → sinh viên nhấn chọn một ngành → hệ thống hiển thị các thông tin về ngành bao gồm: mã ngành, thời gian học, ngày nhập học, cơ sở và chương trình đào tạo của ngành 
●	Tư vấn tuyển sinh tự động: sinh viên đăng nhập vào hệ thống, sinh viên chọn icon bot → hệ thống hiển thị màn hình chat tư vấn → sinh viên thực hiện chat hoặc sử dụng giọng nói và chat bot sẽ trả lời dựa theo nội dung sinh viên vừa nhập
●	Định hướng nghề nghiệp: sinh viên bấm vào chức năng định hướng nghề nghiệp → hệ thống hiển thị danh sách các nghề nghiệp hiện có
●	Xem thông tin học bổng, hỗ trợ tài chính:  tại màn hình trang chủ, sinh viên nhấn vào xem thêm → hệ thống hiển thị màn hình danh sách chức năng → sinh viên nhấn vào xem thông tin học bổng, hỗ trợ tài chính → hệ thống hiển thị màn hình thông tin học bổng, hỗ trợ tài chính
●	Xem tin tức: Tại màn hình trang chủ, sinh viên nhấn vào phần tin tức tại bottom nav, hệ thống hiển thị màn hình danh sách tin tức → sinh viên nhấn chọn một tin tức → hệ thống hiển thị màn hình tin tức vừa được chọn
●	Xem quy trình nhập học: tại màn hình trang chủ, sinh viên nhấn vào xem thêm → hệ thống hiển thị màn hình danh sách chức năng → sinh viên nhấn vào xem quy trình nhập học → hệ thống hiển thị màn hình quy trình nhập học
●	Xem thông tin học phí:  tại màn hình trang chủ, sinh viên nhấn vào xem thêm → hệ thống hiển thị màn hình danh sách chức năng → sinh viên nhấn chọn xem học phí → hệ thống hiển thị thông tin về học phí
Xem phương thức xét tuyển: tại màn hình trang chủ, sinh viên nhấn vào cụm xem thêm → hệ thống hiển thị màn hình danh sách chức năng → sinh viên nhấn vào chức năng xem phương thức xét tuyển → hệ thống hiển thị thông tin xem phương thức xét tuyển
1.1.3.2 Phần dành cho người quản trị
●	Người quản trị có chức năng xem thông tin tương tự người dùng cuối
●	Xem danh sách tài khoản người dùng: sau khi đăng nhập thành công, người quản trị bấm vào icon profile → hệ thống hiển thị màn hình profile → người quản trị bấm vào Quản lý danh sách tài khoản → Hệ thống chuyển sang màn danh sách tài khoản.
●	Xoá tài khoản người dùng: sau khi đăng nhập thành công, người quản trị bấm vào icon profile → hệ thống hiển thị màn hình profile → người quản trị bấm vào Quản lý danh sách tài khoản → Hệ thống chuyển sang màn danh sách tài khoản. → người dùng bấm
●	Chỉnh sửa thông tin của trường: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin trường → hệ thống hiển thị giao diện thông tin chi tiết gồm tên, địa chỉ, … → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin→  người quản trị chỉnh sửa thông tin trường và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình thông tin trường
●	Chỉnh sửa thông tin ngành: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin ngành→ hệ thống hiển thị giao diện thông tin chi tiết gồm tên ngành, thông tin ngành, … → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin ngành và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin ngành
●	Chỉnh sửa thông tin nghề nghiệp: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin ngành nghề nghiệp → hệ thống hiển thị giao diện thông tin chi tiết gồm tên ngành nghề, thông tin ngành nghề, … → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin ngành và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin ngành nghề.
●	Chỉnh sửa thông tin học phí: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin học phí → hệ thống hiển thị giao diện thông tin chi tiết gồm số tiền, hạn thanh toán… → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin ngành và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin học phí.
Chỉnh sửa thông tin học bổng, hỗ trợ tài chính: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin học bổng → hệ thống hiển thị giao diện thông tin chi tiết gồm số tiền, hạn thanh toán… → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin học phí và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin học phí
●	Chỉnh sửa thông tin tin tức: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin tin tức → hệ thống hiển thị giao diện thông tin chi tiết gồm ảnh, tiêu đề, nội dung tin tức… → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin tin tức và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin tin tức.
●	Chỉnh sửa thông tin quy trình nhập học: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin tin tức → hệ thống hiển thị giao diện thông tin chi tiết gồm các bước hướng dẫn nhập học… → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin về quy trình nhập học và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin quy trình nhập học.
●	Chỉnh sửa thông tin phương thức xét tuyển: người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng →  người dùng kéo xuống phần chức năng quản lý và chọn chỉnh sửa thông tin phương thức xét tuyển → hệ thống hiển thị giao diện thông tin chi tiết gồm các phương thức xét tuyển… → người quản trị bấm chỉnh sửa → hệ thống cho phép thay đổi thông tin →  người quản trị chỉnh sửa thông tin phương thức xét tuyển và bấm lưu thay đổi → hệ thống báo lưu thành công và quay về màn hình hiển thị thông tin phương thức xét tuyển.
●	Xóa thông tin trường: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin trường → hệ thống hiển thị danh sách thông tin trường → người quản trị chọn thông tin trường cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin trường → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin trường.
●	Xóa thông tin ngành: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin ngành → hệ thống hiển thị danh sách thông tin ngành → người quản trị chọn thông tin ngành cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xoá thông tin ngành → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin ngành.
Xóa thông tin ngành nghề nghiệp: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin ngành nghề nghiệp → hệ thống hiển thị danh sách thông tin ngành nghề nghiệp → người quản trị chọn 
 
thông tin ngành nghề nghiệp cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin ngành nghề nghiệp → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin ngành nghề nghiệp.
●	Xóa thông tin học phí: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin học phí → hệ thống hiển thị danh sách thông tin học phí → người quản trị chọn thông tin học phí cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin học phí → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin học phí.
●	Xóa thông tin học bổng, hỗ trợ tài chính: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin học bổng → hệ thống hiển thị danh sách thông tin học bổng → người quản trị chọn thông tin học bổng cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin học bổng → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin học bổng.
●	Xóa thông tin tin tức: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin tin tức → hệ thống hiển thị danh sách thông tin tin tức → người quản trị chọn thông tin tin tức cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin tin tức → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin tin tức.
●	Xóa thông tin quy trình nhập học: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin quy trình nhập học → hệ thống hiển thị danh sách thông tin quy trình nhập học → người quản trị chọn thông tin quy trình nhập học cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin quy trình nhập học → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin quy trình nhập học.
●	Xóa thông tin phương thức xét tuyển: Người quản trị sau khi đăng nhập, bấm vào thêm chức năng → hệ thống hiển thị màn hình các chức năng → người dùng kéo xuống phần chức năng quản lý và chọn xoá thông tin phương thức xét tuyển → hệ thống hiển thị danh sách thông tin phương thức xét tuyển → người quản trị chọn thông tin phương thức xét tuyển cần xoá → hệ thống cho phép xác nhận xóa → người quản trị xác nhận xóa thông tin phương thức xét tuyển → hệ thống báo xóa thành công và cập nhật lại danh sách thông tin phương thức xét tuyển
1.2 Thiết kế tương tác
 
Hình 1. 1: Tổng quát hệ thống
1.3 Phương pháp tiếp cận và giải quyết vấn đề
1.3.1 Mô hình tổng quát hệ thống
 
Hình 1. 2 Mô hình tổng quát hệ thống
1.3.2 Phương pháp xây dựng phần mềm
Dựa vào mô hình tổng quát của hệ thống, em lựa chọn phương pháp hướng dịch vụ để phát triển module server và sử dụng phương pháp hướng đối tượng để phát triển module ứng dụng/ mobile. Backend và Frontend sẽ được giao tiếp với nhau thông qua 
Restful API
1.3.2.1 Phương pháp hướng dịch vụ - Khối server
SOA hay Service - Oriented Architecture là một thuật ngữ được hiểu nôm na là kiến trúc hướng dịch vụ. Nơi đây tập hợp mọi dịch vụ được kết nối “mềm dẻo” với nhau. Tuy nhiên, chúng vẫn có giao tiếp và được định nghĩa độc lập, rõ ràng với nền tảng hệ thống.
Có thể nói, SOA thuộc một cấp độ cao hơn ở phát triển ứng dụng, nó chú trọng nhiều đến quy trình nghiệp vụ. Đồng thời dùng chính giao tiếp chuẩn của mình nhằm che đi sự phức tạp kỹ thuật bên dưới. Nói theo một cách dễ hiểu hơn, thì SOA được định nghĩa là kiểu kiến trúc trong phần mềm bao gồm nhiều thành phần đơn lẻ. Chúng tập trung lại và tạo thành những dịch vụ, mỗi dịch vụ thực hiện một nhiệm vụ, quy trình khác nhau.
Sự tập hợp những thành phần được kết nối qua cổng giao tiếp, chúng có tính kế thừa các thành phần đang tồn tại. Chúng tương tác với nhau mà không cần quan tâm đến việc mình được phát triển trên nền tảng công nghệ nào. Điều này có tác dụng giúp hệ thống mở rộng hơn và tích hợp một cách dễ dàng.
1.3.2.2 Phương pháp hướng đối tượng - Khối ứng dụng/mobile
Xây dựng phần mềm hướng đối tượng (Object-Oriented Software Development) là một quá trình phát triển phần mềm sử dụng phương pháp lập trình hướng đối tượng (OOP). 
Khái niệm cơ bản của OOP: OOP là một phương pháp lập trình mà dữ liệu và mã nguồn được tổ chức thành các "đối tượng." Mỗi đối tượng bao gồm dữ liệu (thuộc tính) và mã nguồn (phương thức) liên quan đến nó. Đối tượng được sử dụng để biểu diễn thực thể trong thế giới thực.
Các bước chính trong quá trình xây dựng phần mềm hướng đối tượng:
•	Phân tích yêu cầu: Trước hết, bạn cần hiểu rõ yêu cầu của dự án phần mềm và xác định các đối tượng và quan hệ giữa chúng.
•	Thiết kế: Sau khi đã hiểu yêu cầu, bạn sẽ thiết kế kiến trúc của hệ thống, bao gồm việc xác định các lớp, đối tượng, thuộc tính và phương thức. Bạn cũng sẽ quyết định về cách các đối tượng tương tác với nhau.
•	Lập trình: Tạo mã nguồn theo thiết kế đã xác định, bao gồm việc viết các lớp, phương thức, và triển khai các đối tượng.
•	Kiểm thử: Thực hiện kiểm thử để đảm bảo rằng phần mềm hoạt động như mong đợi và đáp ứng yêu cầu.
•	Bảo trì và mở rộng: Sau khi triển khai, phần mềm cần được bảo trì và có thể mở rộng để thêm tính năng mới hoặc sửa lỗi.
Lợi ích của xây dựng phần mềm hướng đối tượng:
•	Tái sử dụng mã nguồn: Các đối tượng có thể được tái sử dụng trong các dự án khác, giúp tiết kiệm thời gian và công sức lập trình.
•	Dễ bảo trì: Các đối tượng tạo ra một cấu trúc dễ bảo trì, vì bạn có thể tập trung vào từng phần riêng lẻ thay vì phải quản lý một mã nguồn lớn và phức tạp.
•	Mở rộng dễ dàng: Bạn có thể thêm tính năng mới bằng cách mở rộng hoặc thay đổi các lớp và đối tượng mà không ảnh hưởng đến các phần khác trong hệ thống.
•	Quản lý dự án tốt hơn: OOP giúp tạo ra cấu trúc dự án rõ ràng và mối quan hệ giữa các thành phần, giúp quản lý dự án dễ dàng hơn.
•	Xây dựng phần mềm hướng đối tượng giúp tạo ra các ứng dụng có cấu trúc, hiệu quả, và dễ bảo trì. Nó là một trong những phương pháp phát triển phần mềm quan trọng và được ưa chuộng trong ngành công nghiệp phần mềm
1.3.3 Kiến trúc phần mềm
1.3.3.1 Kiến trúc Clean Architecture (Dùng cho khối Ứng dụng - App)
Clean Architecture là kiến trúc ứng nổi tiếng dựa trên nguyên lý loại bỏ sự lệ thuộc giữa các đối tượng cũng như các layer trong ứng dụng. Trong kiến trúc Clean Architecture bao gồm bốn layer được đại diện thông qua các vòng tròn đồng tâm. Các vòng tròn ở trong sẽ không hề biết gì về các vòng tròn bên ngoài. Nguyên tắc này được minh hoạ như sau:
 
Hình 1. 3 Kiến trúc Clean Architecture
Entities là layer trong cùng, cũng là layer quan trọng nhất. Entity chính là các thực thể hay từng đối tượng cụ thể và các rule business login của nó. Trong OOP, đây chính là Object cùng với các method và properties tuân thủ nguyên tắc Encapsulation – chỉ bên trong Object mới có thể thay đổi trạng thái (State) của chính nó. 
Use Cases là layer chứa các business logic ở cấp độ cụ thể từng Use Case. Interface Adapters chính là các layer phụ trách việc chuyển đổi các format dữ liệu để phù hợp với từng Use Case và Entities. Các format dữ liệu này có thể dùng cả bên trong hoặc bên ngoài ứng dụng. 
Framework & Drivers là tầng ngoài cùng, tổ hợp các công cụ cụ thể phục vụ cho từng nhu cầu của người dùng cuối như: thiết bị (devices), web, application, databases…
Ưu và Nhược điểm của Clean Architecture Ưu điểm:
●	Chia để trị rất hiệu quả trong ứng dụng lớn: Trong Clean Architecture thì code tầng nào thì ở đúng tầng nấy. Hạn chế được việc "code ở đâu cũng là code, chạy được là được". 
●	Rất dễ maintain và mở rộng: Việc tìm kiếm bug và lỗi logic sẽ trở nên dễ dàng và nhanh hơn. Vì các tầng độc lập với nhau thông qua các Interfaces nên việc mở rộng hoặc thay đổi các tầng sẽ không ảnh hưởng tới nhau. Điều này hạn chế các breaking change cũng như phải viết lại code (refactoring). 
●	Tách biệt biểu đồ phụ thuộc: Mô hình này tạo ra các lớp hoàn toàn độc lập với nhau, giúp tách biệt business logic, UI và các yếu tố kỹ thuật khác. Điều này giúp cho việc thay đổi từng phần của ứng dụng dễ dàng hơn và không ảnh hưởng đến các phần khác.
●	Dễ kiểm thử: Clean Architecture khuyến khích việc viết các unit test dựa trên nguyên tắc "Single Responsibility" dễ dàng hơn và bám sát hơn với yêu cầu.
●	Đảm bảo tính độc lập với nền tảng: Clean Architecture giúp đảm bảo ứng dụng của bạn không phụ thuộc quá nhiều vào các công nghệ hoặc nền tảng cụ thể, điều này làm cho việc chuyển đổi giữa các nền tảng hoặc nâng cấp công nghệ dễ dàng hơn.
Nhược điểm
•	Tính trừu tượng cao: Trừu tượng càng cao thì tiện cho các developers nhưng sẽ gây ảnh hưởng không nhỏ tới tốc độ thực thi (performance). 
•	Phức tạp cho các ứng dụng nhỏ: Mô hình Clean Architecture có thể trở nên quá phức tạp cho các ứng dụng nhỏ và đơn giản. Việc triển khai một mô hình như vậy có thể tốn thời gian và công sức không cần thiết.
•	Yêu cầu kiến thức sâu: Để thiết kế và triển khai Clean Architecture, đòi hỏi kiến thức sâu về thiết kế phần mềm và khả năng phân tích yêu cầu của dự án một cách tỉ mỉ.
•	Tăng độ phức tạp cho các dự án thay đổi thường xuyên: Đối với các dự án mà yêu cầu sự thay đổi thường xuyên, việc tuân theo mô hình Clean Architecture có thể làm tăng độ phức tạp và tốn nhiều thời gian.
•	Yêu cầu quản lý cẩn thận: Để triển khai Clean Architecture thành công, bạn cần có quản lý dự án tốt và đảm bảo rằng toàn bộ nhóm phát triển hiểu rõ và tuân theo các quy tắc và nguyên tắc của mô hình.
1.3.3.2	Mô hình MVVM (triển khai kết hợp với Clean Architecture trong khối ứng dụng Android)
Bên cạnh việc sử dụng phân chia ứng dụng thành các lớp với nhiệm vụ cụ thể, em kết hợp triểm khai với mô hình MVVM để có thể tách biệt logic giao diện người dùng và logic xử lý dữ liệu. MVVM là viết tắt của Model - View - ViewModel, ở đó View (giao diện người dùng) sẽ được cập nhật bởi ViewModel và việc xử lý Logic hoặc trình bày dữ liệu sẽ do Model đảm nhận
 
Hình 1. 4 Mô hình MVVM
ViewModel là một abstraction của View. Nó sẽ lấy dữ liệu từ tầng Model, xử lý UI logic sau đó hiển thị data có liên quan tới view. ViewModel sẽ không có bất kỳ behavior nào để tương tác với View. Như vậy để nhận biết khi nào cần hiển thị dữ liệu, View sẽ đăng ký lắng nghe sự kiện từ ViewModel. ViewModels sẽ sử dụng các model nếu cần định nghĩa dữ liệu. Sự liên kết giữa View - ViewModel giúp chúng gửi và nhận dữ liệu, để hiểu rõ ta cần tìm hiểu các khái niệm về Binding, DataContext, Behaviors SDK. Nhờ đó ta tách code-behind của View và đưa xuống ViewModel.
Thành phần giao diện của ứng dụng (View) là thành phần duy nhất mà người dùng có thể tương tác được trong chương trình, nó chính là thành phần mô tả dữ liệu. Trong lập trình android, View là một activity, fragment, hay một custom view… Quan hệ giữa ViewModel và View là 1-n, nghĩa là nhiều View có thể liên kết với 1 
ViewModel
Model là các đối tượng giúp truy xuất và thao tác trên dữ liệu. Model chứa phần data được lấy từ nhiều nguồn khác nhau, ví dụ như: từ remote hoặc từ data local. Tuy nhiên cần phải lưu ý đó là model chỉ lưu giữ thông tin mà thôi, nó không quan tâm đến các hoạt động hay dịch vụ có thể thay đổi, điều khiển các thông tin đó. Ví dụ như nó không có trách nhiệm phải định dạng đoạn văn bản hiển thị như thế nào, hay làm sao để lấy một danh sách các item về từ remote server.
Clean Architecture giúp tạo ra một cấu trúc rõ ràng với các lớp và tầng có trách nhiệm cụ thể, giúp dễ bảo trì và mở rộng mã nguồn. Cùng với đó, MVVM tách biệt logic giao diện người dùng và logic xử lý dữ liệu, giúp kiểm thử dễ dàng và thích ứng với sự thay đổi. Sự kết hợp này cũng giúp tái sử dụng mã nguồn hiệu quả, khiến cho việc quản lý độ phức tạp của ứng dụng trở nên dễ dàng hơn. Mô hình thiết kế này tạo điều kiện cho việc phát triển ứng dụng linh hoạt, đồng thời giữ cho mã nguồn sạch sẽ, dễ đọc và có khả năng mở rộng cao.
1.3.4 Công nghệ triển khai hệ thống
1.3.4.1 Phía server
1.3.4.1.1. Flask
Flask là một framework web siêu nhẹ và dễ sử dụng trong ngôn ngữ lập trình Python, được phát triển để xây dựng ứng dụng web nhanh chóng và hiệu quả. Dựa trên triết học "micro" của nó, Flask tập trung vào cung cấp các công cụ cơ bản như định tuyến URL và xử lý HTTP requests, cho phép bạn xây dựng các ứng dụng web tùy chỉnh mà không bị ràng buộc bởi cấu trúc phức tạp.
Một số điểm nổi bật về Flask:
●	Siêu nhẹ: Flask được thiết kế với mục tiêu là duyệt đơn giản và siêu nhẹ. Nó không yêu cầu nhiều dependencies bên ngoài và cho phép bạn tự do lựa chọn các thành phần bạn muốn sử dụng trong dự án của mình.
●	Dễ học và sử dụng: Flask có cú pháp đơn giản và tài liệu rõ ràng, làm cho việc học và sử dụng nó rất dễ dàng, đặc biệt đối với người mới bắt đầu phát triển web.
●	Định tuyến mạnh mẽ: Flask cho phép bạn xác định các định tuyến URL dễ dàng, đồng thời kết hợp với các hàm xử lý để quản lý các request và response.
●	Thích hợp cho ứng dụng nhỏ và trung bình: Flask thích hợp cho việc xây dựng ứng dụng web vừa và nhỏ, đặc biệt là các ứng dụng prototype hoặc dự án cá nhân.
Flask là một framework có khả năng phát triển các ứng dụng web đáng tin cậy và mạnh mẽ. Điều này làm cho Flask trở thành một sự lựa chọn phổ biến trong cộng đồng phát triển Python cho việc xây dựng các dự án web đơn giản và tùy chỉnh.
Em đã sử dụng Flask để có thể xây dựng các API service phục vụ cho công việc ứng dụng android gửi yêu cầu xử lý và trả về các thông tin cần thiết
1.3.4.1.2 SQLite
SQLite là hệ quả trị cơ sở dữ liệu (DBMS) quan hệ tương tự như Mysql, Đặc điểm nổi bật của SQLite so với các DBMS khác là gọn, nhẹ, đơn giản, đặt biệt không cần mô hình server-client, không cần cài đặt, cấu hình hay khởi động nên không có khái niệm user, password hay quyền hạn trong SQLite Database. Dữ liệu cũng được lưu ở một file duy nhất.
SQLite thường không được sử dụng với các hệ thống lớn nhưng với những hệ thống ở quy mô vừa và nhỏ thì SQLite không thua các DBMS khác về chức năng hay tốc độ. Vì không cần cài đặt hay cấu hình nên SQLite được sử dụng nhiều trong việc phát triển, thử nghiệm … vì tránh được những rắc rối trong quá trình cài đặt. Các ưu điểm của SQLite:
●	Nhỏ gọn: SQLite chỉ là một tệp duy nhất, vì vậy nó dễ dàng tải xuống và cài đặt.
●	Hiệu quả: SQLite được thiết kế để chạy trên các thiết bị có tài nguyên hạn chế.
●	Mã nguồn mở: SQLite là mã nguồn mở, vì vậy nó có thể được sửa đổi và tùy chỉnh theo nhu cầu.
●	Dễ sử dụng: SQLite sử dụng ngôn ngữ SQL tiêu chuẩn, vì vậy nó dễ dàng học và sử dụng.
SQLite được em sử dụng trong đồ án với vai trò là nơi lưu trữ các thông tin thực thể cần thiết cho hệ thống như các thông tin về trường, thông tin về ngành học, … SQLite được đặt tại khối API Service phục vụ cho việc các Service truy vấn dữ liệu và lấy ra các thông tin dữ liệu cần thiết và trả về cho ứng dụng Mobile
1.3.4.2 Phía Client
1.3.4.2.1 Android Studio
Android Studio là môi trường phát triển tích hợp (IDE) chính thức để phát triển ứng dụng Android. Nó được phát triển bởi Google và dựa trên IDE Java IntelliJ của hãng JetBrains. Android Studio cung cấp một loạt các tính năng và công cụ giúp các nhà phát triển tạo ra các ứng dụng Android chất lượng cao.
Các tính năng chính của Android Studio:
●	Giao diện người dùng thân thiện và dễ sử dụng: Android Studio được thiết kế với giao diện người dùng trực quan và dễ sử dụng. Các nhà phát triển có thể dễ dàng tìm thấy các tính năng và công cụ cần thiết để phát triển ứng dụng Android.
●	Hỗ trợ các ngôn ngữ lập trình: Android Studio hỗ trợ các ngôn ngữ lập trình Java, Kotlin, C++, và C. Các nhà phát triển có thể chọn ngôn ngữ lập trình phù hợp với nhu cầu của mình.
●	Các công cụ phát triển tích hợp: Android Studio cung cấp một loạt các công cụ phát triển tích hợp giúp các nhà phát triển tạo ra các ứng dụng Android nhanh hơn và hiệu quả hơn. Các công cụ này bao gồm:
●	Trình soạn thảo mã: Trình soạn thảo mã của Android Studio được tích hợp các tính năng nâng cao như tự động hoàn thành, kiểm tra lỗi, và gợi ý từ khóa.
●	Trình quản lý dự án: Trình quản lý dự án của Android Studio giúp các nhà phát triển dễ dàng quản lý các dự án ứng dụng Android của mình.
●	Trình xây dựng: Trình xây dựng của Android Studio giúp các nhà phát triển nhanh chóng xây dựng và triển khai các ứng dụng Android.
●	Trình mô phỏng: Trình mô phỏng của Android Studio giúp các nhà phát triển kiểm tra và gỡ lỗi các ứng dụng Android của mình trên các thiết bị ảo.
1.3.4.2.2 Android Jetpack
Android Jetpack là một bộ công cụ và thư viện hỗ trợ mạnh mẽ được phát triển bởi Google để giúp nhà phát triển xây dựng ứng dụng Android hiệu quả, ổn định và dễ quản lý hơn. Android Jetpack cung cấp một cơ sở vững chắc cho việc phát triển ứng dụng bằng cách giảm bớt sự phức tạp, tối ưu hóa việc quản lý vòng đời và cung cấp các công cụ mạnh mẽ cho việc làm việc với giao diện người dùng, dữ liệu, và các tính năng chung.
Gói Android Jetpack bao gồm nhiều thành phần khác nhau, một số ví dụ như:
●	AppCompat: Đảm bảo ứng dụng hoạt động mượt mà trên các phiên bản Android khác nhau.
●	ViewModel: Giúp quản lý dữ liệu và trạng thái ứng dụng trong suốt quá trình hoạt động của ứng dụng, giúp tránh rò rỉ bộ nhớ và giúp vượt qua sự hủy bỏ và tái khởi động hoạt động.
●	Room: Thư viện cơ sở dữ liệu dễ sử dụng để làm việc với SQLite, cung cấp một cách thuận tiện để lưu trữ và truy vấn dữ liệu trong ứng dụng.
●	Navigation: Giúp quản lý quá trình điều hướng giữa các màn hình và sự chuyển đổi giữa các phân đoạn (Fragments) trong ứng dụng.
●	LiveData: Thông báo về các thay đổi trong dữ liệu và tự động cập nhật giao diện người dùng khi có thay đổi.
1.3.4.2.3 Coroutine
Coroutines là một khái niệm quan trọng trong lập trình Android hiện đại, cho phép chúng ta thực hiện các tác vụ không đồng bộ một cách hiệu quả và dễ dàng. Nó là một phần của thư viện Kotlin, và được hỗ trợ bởi Android Jetpack. Coroutines cho phép chúng ta viết mã xử lý không đồng bộ một cách tự nhiên, gần gũi với cú pháp Kotlin, giúp tạo ra mã dễ đọc và dễ bảo trì.
Chức năng của Coroutines: Xử Lý bất đồng bộ: Một trong những chức năng quan trọng nhất của coroutines là khả năng xử lý các tác vụ không đồng bộ. Coroutines giúp đơn giản hóa việc xử lý các tác vụ như gọi API, thao tác với cơ sở dữ liệu, và tương tác với các tác vụ I/O.
Luồng Thực Thi: Coroutines cho phép quản lý và điều khiển các luồng thực thi một cách tường minh. Có thể tạo ra và quản lý nhiều luồng trong một coroutine, giúp tận dụng tốt hơn khả năng đa nhiệm của thiết bị.
Coroutine Context và Dispatcher: Mỗi coroutine trong Kotlin đều có một context được thể hiện bằng một instance của interface Coroutine Context. Context này là một tập các element cấu hình cho coroutine. Các loại element trong coroutine context gồm:
●	Job: nắm giữ thông tin về lifecycle của coroutine
●	Dispatcher: Quyết định thread nào mà coroutine sẽ chạy trên đó. Có các loại dispatcher sau:
●	Dispatchers.Main: chạy trên main UI thread
●	Dispatchers.IO: chạy trên background thread của thread pool. Thường được dùng khi Read, write files, Database, Networking
●	Dispatchers.Default: chạy trên background thread của thread pool. Thường được dùng khi sorting a list, parse Json, DiffUtils Điểm Mạnh của Coroutines:
●	Đơn giản và dễ đọc hiểu: Coroutines sử dụng cú pháp Kotlin, giúp viết code dễ đọc, giảm thiểu callback hell và tránh callback spaghetti.
●	Hiệu Năng Cao: Coroutines được tối ưu hóa để làm việc tốt trên Android và giảm độ trễ. Điều này giúp ứng dụng Android hoạt động mượt mà hơn.
●	Quản lý Luồng dễ dàng: Coroutines cho phép bạn quản lý và đồng bộ hóa các luồng thực thi một cách tường minh, giúp tránh các vấn đề liên quan đến đa luồng.
Điểm Yếu của Coroutines:
●	Khả năng rò rỉ bộ nhớ: Sử dụng coroutines một cách sai, ví dụ như không giải phóng tài nguyên, có thể dẫn đến rò rỉ bộ nhớ.
●	Phức tạp khi xử lý lỗi: Xử lý lỗi trong coroutines có thể trở nên phức tạp, đặc biệt khi có nhiều luồng song song. 
1.3.4.2.4 Retrofit + gson
Retrofit là một HTTP client type-safe cho Android và Java. Retrofit giúp dễ dàng kết nối đến một dịch vụ REST trên web bằng cách chuyển đổi API thành Java 
Interface. Retrofit giúp dễ dàng xử lý dữ liệu JSON hoặc XML sau đó phân tích cú pháp thành Plain Old Java Objects (POJOs). Tất cả các yêu cầu GET, POST, PUT, PATCH, và DELETE đều có thể được thực thi.Giống như hầu hết các phần mềm mã nguồn mở khác, Retrofit được xây dựng dựa trên một số thư viện mạnh mẽ và công cụ khác. Đằng sau nó, Retrofit làm cho việc sử dụng OkHttp (từ cùng một nhà phát triển) để xử lý các yêu cầu trên mạng. Ngoài ra, Retrofit không tích hợp bất kỳ một bộ chuyển đổi JSON nào để phân tích từ JSON thành các đối tượng Java. Thay vào đó sẽ đi kèm với các thư viện chuyển đổi JSON để xử lý điều đó
1.3.4.2.5 Databinding
Trong sự kiện Google I/O 2015. Google đã giới thiệu đến một thư viện xử lý dữ liệu giữa tầng hiển thị và tầng dữ liệu có tên là Data Binding. Và cách sử dụng thư viện Data Binding này như sử dụng pattern Model-View-ViewModel (MVVM).
DataBinding là một công nghệ quan trọng trong lập trình ứng dụng Android, giúp tối ưu hóa việc làm việc với giao diện người dùng và dữ liệu trong ứng dụng. DataBinding cho phép kết nối trực tiếp giữa các thành phần giao diện và dữ liệu của ứng dụng, giúp giảm thiểu phần lớn mã lập trình, tăng hiệu suất và sự bảo trì của ứng dụng.
Cách hoạt động của Data Binding là sử dụng một lớp trong XML được gọi là "layout" để định nghĩa giao diện người dùng của ứng dụng. Trong layout này, bạn có thể sử dụng các biểu thức và thẻ để truy cập trực tiếp dữ liệu từ đối tượng Java hoặc Kotlin. DataBinding cũng giúp tạo ra mã lập trình dễ đọc hơn bằng cách tự động tạo ra các lớp Binding cho mỗi layout, cho phép bạn truy cập các thành phần giao diện và dữ liệu bằng cách gọi các phương thức đã được tạo sẵn. Điều này làm giảm sai sót và giúp làm việc với giao diện người dùng và dữ liệu dễ dàng hơn.
1.4 Tổng kết chương
Chương 1 đã giới thiệu khái quát về toàn bộ chức năng cũng như công nghệ được áp dụng trong hệ thống. Trong chương tiếp theo, em sẽ trình bày về bài toán tư vấn thông tin tuyển sinh tự động cũng như các công nghệ được áp dụng và thử nghiệm 
Chương 2: Công nghệ tư vấn thông tin tuyển sinh tự động
Để có thể xây dựng được công nghệ tư vấn thông tin tuyển sinh tự động, em sẽ giới thiệu về những yêu cầu về tư vấn tuyển sinh tự động. Từ đó em sẽ đi tới mô tả về nguyên lý hoạt động của công nghệ tư vấn tuyển sinh và các kỹ thuật được áp dụng
2.1 Yêu cầu tư vấn tuyển sinh tự động
Với bài toán này, em sẽ xử lý như sau, sau khi lắng nghe phần yêu cầu, phần câu hỏi của người dùng nói vào. Sau đó đưa vào phần mô hình nhận dạng giọng nói tự động ASR (Automatic Speech Recognition) để tiến hành xử lý và trả về phần văn bản cuối. Đoạn văn bản này sẽ được trả về cho ứng dụng Android.
Sau khi nhận được phần câu hỏi trả về, phần văn bản yêu cầu sẽ được gửi đến hệ thống xử lý. Đoạn văn bản đó sẽ được được vào mô hình được xây dựng dựa trên xử lý ngôn ngữ tự nhiên NLP được huấn luyện với bộ dữ liệu được chuẩn bị sẵn (bộ dữ liệu gồm các câu hỏi và tương ứng là các câu trả lời phù hợp). Mô hình này sẽ được xây dựng và huấn luyện theo cơ chế seq2seq kết hợp với attention. Từ đó, với mỗi yêu cầu, câu hỏi được đưa vào sẽ có những câu trả lời phù hợp được đưa ra
 
Hình 2. 1 Mô hình xử lý
2.2 Nguyên lý hoạt động
Để có thể hiểu rõ hơn về mô hình xử lý phía trên, em sẽ mô tả chi tiết hơn về những nguyên lý hoạt động của từng phần speech to text và tư vấn tự động. Bắt đầu với phần nguyên lý hoạt động của speech to text
2.2.1 Nguyên lý hoạt động của speech to text
Công nghệ chuyển tiếng nói ra văn bản (Speech to Text) hoạt động dựa trên các quy trình xử lý và phân tích âm thanh để nhận dạng giọng nói và trích xuất thông tin, sau đó chuyển đổi âm thanh thành văn bản. Dưới đây là cách hoạt động chi tiết 
a. Quy trình xử lý âm thanh
Bước đầu tiên là thu thập âm thanh: Người dùng cung cấp dữ liệu âm thanh thông qua bản ghi âm, cuộc gọi thoại, hoặc các nguồn âm thanh khác.
Tiếp theo, âm thanh được phân tích: Công nghệ sử dụng các thuật toán xử lý tín hiệu và trí tuệ nhân tạo để phân tích và tách riêng các phần âm thanh quan trọng như giọng nói và từ ngữ từ dữ liệu thu thập
b. Nhận dạng giọng nói và trích xuất thông tin
Khi đã phân tích và tách được giọng nói từ dữ liệu âm thanh, công nghệ nhận dạng giọng nói sử dụng các mô hình và mạng nơ-ron để xác định và nhận dạng từng đoạn giọng nói
c. Xử lý và chuyển đổi âm thanh thành văn bản
Dữ liệu giọng nói đã được nhận dạng và trích xuất sau đó được xử lý bổ sung: Các từ và câu được liên kết, đảm bảo cấu trúc văn bản chính xác và dễ đọc. Cuối cùng, dữ liệu giọng nói đã qua quá trình xử lý sẽ được chuyển đổi thành văn bản tương ứng
 
Hình 2. 2 Nguyên lý hoạt động speech to text
2.2.2 Nguyên lý tư vấn tự động
Yêu cầu của việc tư vấn tự động là đối với mỗi mục đích hỏi sẽ cần đưa ra một câu trả lời chính xác. Tuy nhiên, mỗi mục đích hỏi sẽ có thể có nhiều cách hỏi. Vậy nên cần phải lọc ra những từ khoá chính để xác định được mục đích hỏi từ các câu hỏi đầu vào
Mục tiêu của việc tư vấn tự động là phải hiểu được nội dung của câu hỏi yêu cầu. Đối với con người, thông thường sẽ đọc toàn bộ nội dung để có thể hiểu được ý muốn mà câu hỏi đưa ra là gì. Chính là xác định một tập hợp các từ quan trọng có trong câu hỏi và sử dụng các kỹ thuật để xác định trọng số và xếp hạng chúng dựa trên mức độ quan trọng. Từ đó phân loại và biết được mục đích mà câu hỏi đang muốn hướng tới.
 
Hình 2. 3 Ví dụ về nguyên lý hoạt động của mô hình ngôn ngữ
Để có thể đạt được hiệu suất tốt hơn, em chuẩn bị một lượng dữ liệu để huấn luyện trước đó. Huấn luyện trước là hành động huấn luyện một mô hình từ đầu: các trọng số được khởi tạo ngẫu nhiên và quá trình huấn luyện bắt đầu mà không cần biết trước bất kỳ điều gì.
 
Hình 2. 4 Mô tả về việc huấn luyện trước
2.3 Xử lý bài toán với mô hình seq2seq và cơ chế attention
Yêu cầu của việc tư vấn tự động là đối với mỗi mục đích hỏi sẽ cần đưa ra một câu trả lời chính xác. Tuy nhiên, mỗi mục đích hỏi sẽ có thể có nhiều cách hỏi. Vậy nên cần phải lọc ra những từ khoá chính để xác định được mục đích hỏi từ các câu hỏi đầu vào. Nhưng đối với máy tính thì sẽ khó có thể thực hiện như vậy. Vậy nên cần phải có cách để giúp cho máy tính hiểu và xác định được đâu là thông tin quan trọng cần chú ý. Trong đồ án này, em lựa chọn mô hình seq2seq kết hợp self-attention để giải quyết vấn đề trên.
2.3.1 Giới thiệu mô hình Sequence-to-Sequence
Mô hình seq2seq (sequence-to-sequence) được thiết kế để giải quyết các vấn đề liên quan đến xử lý chuỗi dữ liệu, trong đó có những tác vụ quan trọng như dịch máy, tóm tắt văn bản, và chuyển đổi thông tin từ một định dạng chuỗi sang một định dạng khác. Mục đích chính của seq2seq là học cách ánh xạ một chuỗi dữ liệu vào một chuỗi khác, giúp mô hình nắm bắt cấu trúc và ý nghĩa của thông tin trong chuỗi.
Mô hình seq2seq sử dụng kiến trúc Encoder-Decoder có độ dài đầu vào và đầu ra khác nhau. Kiến trúc Encoder-Decoder được coi là hai khối - Mã hóa (Encoder) và Giải mã (Decoder), hai khối này được kết nối với nhau thông qua Vector trung gian (Context Vector):
●	Bộ mã hóa - Encoder: Bộ mã hóa xử lý từng token trong chuỗi đầu vào, và nó cố gắng nhồi nhét toàn bộ thông tin đầu vào vào một vector có độ dài cố định, tức là "vector trung gian". Sau đó bộ mã hóa sẽ chuyển vector này sang bộ giải mã.
●	Vector trung gian - Context Vector: Vector này có chức năng gói gọn toàn bộ ý nghĩa của chuỗi đầu vào và giúp bộ giải mã đưa ra được quyết định chính xác. Đây là trạng thái ẩn nằm cuối chuỗi và được tính bởi bộ mã hóa, vector này sau đó cũng hoạt động như trạng thái ẩn đầu tiên của bộ giải mã.
●	Bộ giải mã - Decoder: Bộ giả mã sử dụng Vector trung gian và cố gắng dự đoán chuỗi đích.
 
Hình 2. 5 Mô hình Encoder - Decoder
2.3.2 Giới thiệu cơ chế attention
Khi sử dụng mô hình seq2seq truyền thống, bộ mã hóa (Encoder) của nó phải chứa toàn bộ thông tin của chuỗi đầu vào trong một vector ngữ cảnh cố định, điều này có thể dẫn đến mất thông tin quan trọng khi xử lý các chuỗi dài. Cơ chế attention được ra đời để giải quyết các vấn đề của mô hình seq2seq với mạng nơ-ron hồi tiếp bằng cách cho phép mô hình tập trung vào các phần quan trọng của chuỗi đầu vào khi tạo ra chuỗi đầu ra. Thay vì chỉ sử dụng một vector ngữ cảnh cố định, mô hình Attention tạo ra một tập hợp các vector ngữ cảnh có trọng số, trong đó mỗi vector được tập trung vào một phần cụ thể của chuỗi đầu vào
 
Hình 2. 6 Cơ chế extension
Chi tiết các bước
Bước 1: Nhận vector trạng thái ẩn của decoder và tất cả các vector trạng thái ẩn của encoder 
Bước 2: Tính điểm attention. Với mỗi vector trạng thái ẩn của encoder thì ta cần tính điểm thể hiện sự liên quan với vector trạng thái ẩn của decoder. Cụ thể, em sẽ áp dụng phương trình tính điểm "attention" với đầu vào là vector trạng thái ẩn decoder và một vector trạng thái ẩn của encoder và trả về một giá trị vô hướng
Bước 3: Tính trọng số attention. Áp dụng hàm softmax với đầu vào là điểm attention
 
Bước 4: Tính toán vector bối cảnh là tổng của các trọng số attention nhân với vector trạng thái ẩn của decoder tại time-step tương ứng
 
Cuối cùng, các vector attention dùng để đưa ra đầu ra được tính dựa trên vector bối cảnh và vector trạng thái ẩn ở decoder 
     2.4 Công nghệ áp dụng
Trong đồ án này, để triển khai bài toán seq2seq kết hợp với cơ chế attention, em lựa chọn sử dụng các công nghệ để triển khai gồm có Transformer và OpenAi Whisper. Các công nghệ này có ưu điểm là quá trình huấn luyện nhanh và thu về kết quả rất tốt.
2.4.1 Transformer (phục vụ cho tư vấn tự động)
	a.	Giới thiệu chung
Transformer là một kiến trúc mạng nơ-ron sử dụng trong machine learning và deep learning, đặc biệt là trong lĩnh vực xử lý ngôn ngữ tự nhiên (NLP). 
Đây là một mô hình mạng nơ-ron đặc biệt nổi bật trong lĩnh vực xử lý ngôn ngữ tự nhiên và dịch máy. Thay vì sử dụng các lớp tích chập như các mô hình trước đó, Transformer chủ yếu sử dụng cơ chế self-attention để xử lý thông tin đầu vào. Cơ chế này cho phép mô hình tập trung vào các phần quan trọng của dữ liệu đầu vào, giúp cải thiện khả năng hiểu và tạo ra dữ liệu đầu ra chất lượng.
 
Hình 2. 7 Mô hình transformer
Một mô hình Transformer xử lý đầu vào có kích thước biến đổi bằng cách sử dụng các lớp tự chú ý thay vì sử dụng RNNs hoặc CNNs.
Cụ thể hơn một chút, tại sao Transformer lại mang lại sự hiệu quả tốt hơn RNN?. Đối với quá trình encode một câu thì RNNs sẽ cần một khoảng thời gian để đọc lần lượt từng từ trong câu, đối với 1 câu dài quá trình này có thể diễn ra khá lâu, như vậy độ phức tạp của encoder để xử lý một câu có độ dài N là O(N). Ngược lại, trong quá trình encode của transformer, các source tokens sẽ "quan sát" nhau (cơ chế self attention) và cố gắng hiểu nhau trong ngữ cảnh của câu. Và độ phức tạp của quá trình này chỉ là O(1).
Bảng dưới đây sẽ thể hiện sự khác biệt của transformer với các mô hình seq2seq, transformer hoàn toàn hoạt động dựa trên cơ chế attention
	seq2seq	seq2seq với
attention	transformer
encoder	RNN/CNN	RNN/CNN	attention
decoder	RNN/CNN	RNN/CNN	attention
tương tác giữa encoder và decoder	vector	attention	attention
	b.	Cơ chế self - attention
 Self -attention chính là thành phần quan trọng nhất của transformer. Sự khác biệt là, trong khi cơ chế attention sẽ tính toán dựa trên trạng thái của decoder ở time-step hiện tại và tất cả các trạng thái ẩn của encoder. Còn self-attention có thể hiểu là attention trong một câu, khi từng thành phần trong câu sẽ tương tác với nhau. Từng token sẽ "quan sát" các tokens còn lại trong, thu thập ngữ cảnh của câu và cập nhập vector biểu diễn.
 
Hình 2. 8 Cơ chế self – extension của transformer
Để xây dựng cơ chế self-attention ta cần chú ý đến hoạt động của 3 vector biểu diễn cho mỗi từ lần lượt là:
●	Query: hỏi thông tin
●	Key: trả lời rằng nó có một số thông tin
●	Value: trả về thông tin đó
Query được sử dụng khi một token "quan sát" những tokens còn lại, nó sẽ tìm kiếm thông tin xung quanh để hiểu được ngữ cảnh và mối quan hệ của nó với các tokens còn lại. Key sẽ phản hồi yêu cầu của Query và được sử dụng để tính trọng số attention. Cuối cùng, Value được sử dụng trọng số attention vừa rồi để tính ra vector đại diện (attention vector)
 
Hình 2. 9 Mô tả về attension vector
	c.	Kiến trúc của Transformer
Tiếp theo ta sẽ đi tìm hiểu về các thành phần chính cấu tạo nên transformer. Mô hình thực hiện chính xác những gì đã được giới thiệu ở trên. Ở bên trái là encoder, thông thường có Nx = 6 layers chồng lên nhau. Mỗi layer sẽ có multi-head attention như đã tìm hiểu và khối feed-forward. Ngoài ra còn các các kết nối residual giống như trong mạng Resnet. Ở bên phải là decoder, tương tự cũng có Nx = 6 layers chồng lên nhau. Kiến trúc thì khá giống encoder những chỉ có thêm khối masked multi-head attention ở vị trí đầu tiên
 
Hình 2. 10 Kiến trúc transformer
●	Positional encoding: Transformer không có các mạng hồi tiếp hay mạng tích chập nên nó sẽ không biết được thứ tự của các token đầu vào. Vì vậy, cần phải có cách nào đó để cho mô hình biết được thông tin này. Đó chính là nhiệm vụ của positional encoding. Như vậy, sau bước nhúng từ (embedding layers) để thu được các tokens thì ta sẽ cộng nó với các vector thể hiện vị trí của từ trong câu.
●	Lớp Normalization: Trong hình ảnh cấu trúc, có lớp "Add & Norm" thì từ Norm thể hiện cho lớp Normalization. Lớp này đơn giản là sẽ chuẩn hóa lại đầu ra của multi-head attention, mang lại hiệu quả cho việc nâng cao khả năng hội tụ.
●	Kết nối Residual: Kết nối residual: thêm đầu vào của một khối vào đầu ra của nó. Với kết nối này giúp mạng có thể chồng được nhiều layers. Như trên hình, kết nối residual sẽ được sử dụng sau các khối FFN và khối attention. Như trên hình từ "Add" trong "Add & Norm" sẽ thể hiện cho kết nối residual.
●	Khối Feed-Forward: Đây là khối cơ bản, sau khi thực hiện tính toán ở khối attention ở mỗi lớp thì khối tiếp theo là FFN. Có thể hiểu là cơ chế attention giúp thu thập thông tin từ những tokens đầu vào thì FFN là khối xử lý những thông tin đó.
2.4.2 OpenAi Whisper (phục vụ chuyển đổi giọng nói)
OpenAI Whisper là một mô hình nhận dạng giọng nói mã nguồn mở được phát triển bởi OpenAI. Nó được đào tạo trên một tập dữ liệu khổng lồ gồm 680.000 giờ âm thanh đa ngôn ngữ. Whisper có thể nhận dạng giọng nói của con người với độ chính xác cao, ngay cả trong điều kiện tiếng ồn và giọng nói khác nhau. Nó cũng có thể dịch giữa các ngôn ngữ.
Whisper vẫn đang trong quá trình phát triển, nhưng nó đã được chứng minh là có tiềm năng ứng dụng rộng rãi.
 
Hình 2. 11 Sơ đồ kiến trúc của OpenAi Whisper
2.5 Thực nghiệm
2.5.1 Dữ liệu thực nghiệm
Vì là hệ thống tư vấn tự động liên quan đến học viện Công nghệ Bưu chính Viễn thông vậy nên em sẽ sử dụng các thông tin có sẵn tại website của học viện. Qua đó tổng hợp lại thành các câu hỏi và các câu trả lời tương ứng
 
Hình 2. 12 Một số thông tin từ học viện
2.5.2 Phương pháp thực nghiệm
Để tiến hành huấn luyện dữ liệu, em thực hiện huấn luyện bằng học sâu với 
TensorFlow, Keras. Các thông số đầu vào gồm có NUM_LAYERS, NUM_HEADS, UNITS, DROPOUT, embeddings_dim và VOCAB_SIZE đóng vai trò quan trọng trong việc xác định kiến trúc và đặc điểm hoạt động của encoder và decoder. NUM_LAYERS đại diện cho số lớp trong cả hai thành phần, có ảnh hưởng đến khả năng mô hình học mối quan hệ phức tạp. NUM_HEADS là số đầu attention, cung cấp khả năng tập trung đồng thời vào các phần khác nhau của đầu vào. UNITS là số chiều của các lớp ẩn, quyết định khả năng học biểu diễn phức tạp. DROPOUT là tỷ lệ dropout, giúp ngăn chặn overfitting và cải thiện khả năng tổng quát hóa. Ngoài ra, embeddings_dim là kích thước của không gian nhúng, ảnh hưởng đến khả năng biểu diễn từ ngữ của mô hình. VOCAB_SIZE là kích thước của từ vựng, định rõ phạm vi từ ngữ mà mô hình có thể xử lý. Tất cả các tham số này cần được lựa chọn sao cho phù hợp với đặc điểm của dữ liệu và yêu cầu của bài toán để đảm bảo mô hình hoạt động hiệu quả và có khả năng tổng quát hóa.
Thông số	Giá trị
NUM_LAYERS	2
NUM_HEADS	6
UNITS	512
DROPOUT	0.1
embeddings_dim	300
VOCAB_SIZE	Độ dài của câu hỏi dài nhất
2.5.3 Kết quả thực nghiệm
Sau quá trình huấn luyện cho phần tư vấn tự động, thu về kết quả như biểu đồ dưới đây
 
Hình 2. 13 Biểu đồ training loss
2.6 Kết luận chương
Ở chương 2 này, em đã trình bày chi tiết về yêu cầu bài toán tư vấn tự động và hướng xử lý bài toán em đã chọn. Đồng thời cũng giới thiệu về kỹ thuật và các công nghệ em lựa chọn để xử lý bài toán này.
Ở chương tiếp theo, em sẽ trình bày về cấu trúc của hệ thống, phân tích và thiết kế của hệ thống xây dựng ứng dụng di động
Chương 3: Phân tích thiết kế hệ thống
Trong Chương 3, em sẽ trình bày việc phân tích và thiết kế cho hệ thống tư vấn tuyển sinh trên ứng dụng di động.
3. 1 Phân tích hệ thống
3.1.1 Biểu đồ usecase
3.1.1.1 Các Actor có mặt trong hệ thống
Mô tả về hệ thống: Hệ thống được xây dựng nhằm phục vụ cho công tác tư vấn tuyển sinh, định hướng nghề nghiệp và xem chỉ tiêu xét tuyển nhằm cung cấp những thông tin cần thiết về Học viện Công nghệ Bưu chính viễn thông bao gồm các thông tin về: 
●	Thông tin về trường
●	Thông tin về ngành học, chương trình học, các yêu cầu đầu vào và cơ hội việc làm sau khi tốt nghiệp cẩu ngành học đó,....  
●	Học sinh, sinh viên cũng có thể nắm bắt được thông tin về các quy trình cần thiết lúc nhập học như là hướng dẫn về quy trình nộp hồ sơ, nộp học phí và các vấn đề liên quan đến đăng ký tuyển sinh 
●	Thông tin chi tiết về học bổng, hỗ trợ tài chính và các nguồn tài trợ khác để giúp học sinh đạt được mục tiêu học tập của mình. 
Ngoài ra, hệ thống còn có chức năng tư vấn tự động (người dùng có thể chat hoặc sử dụng giọng nói để đưa ra yêu cầu) để có thể nắm thông tin về các khóa học, chương trình học, ngành nghề phù hợp với từng học sinh.
TT	Tên Tiếng Việt	Giải thích
Nhóm khái niệm liên quan đến con người
1	Người dùng	Người có tài khoản và thẩm quyền để đăng nhập vào hệ thống, thực hiện các chức năng được cho phép.
2	Người quản lý	
3	Sinh viên	Người có tài khoản và thẩm quyền để đăng nhập vào hệ thống. Khách hàng có thể sử dụng các chức năng của một sinh viên trong hệ thống. 
Nhóm khái niệm liên quan đến đối tượng cần xử lý
4	Trường	Là đối tượng chứa thông tin chi tiết về trường
5	Ngành học	Là đối tượng chứa thông tin chi tiết về ngành học 
6	Chương trình học	Là đối tượng chứa thông tin chi tiết về chương trình học
7	chỉ tiêu	là đối tượng chứa thông tin chi tiết về chỉ tiêu tuyển 
		sinh
8	Ngành nghề	Là đối tượng chứa thông tin chi tiết về ngành nghề
9	học bổng	Là đối tượng chứa thông tin chi tiết về học bổng
10	học phí	Là đối tượng chứa thông tin chi tiết về học phí
11	Quy trình nhập học	Là đối tượng chứa thông tin chi tiết về quy trình nhập học
12	Tin tức	Là đối tượng biểu diễn cho tin tức
3.1.1.2 UseCase tổng quan hệ thống
 
Hình 3. 1 Usecase tổng quan hệ thống
3.1.1.3 Chức năng xem thông tin trường
 
Hình 3. 2 Chức năng xem thông tin trường
3.1.1.4 Chức năng xem thông tin ngành
 
Hình 3. 3 Chức năng xem thông tin ngành
3.1.1.5 Chức năng tư vấn tự động
 
Hình 3. 4 Chức năng tư vấn tự động
3.1.1.6 Chức năng xem thông tin học phí
 
Hình 3. 5 Chức năng xem thông tin học phí
3.1.1.7 Chức năng xem thông tin định hướng nghề nghiệp
 
Hình 3. 6 Chức năng xem thông tin nghề nghiệp
3.1.1.8 Chức năng xem phương thức xét tuyển
 
Hình 3. 7 Chức năng xem phương thức xét tuyển
3.1.1.9 Chức năng xem thông tin học bổng
 
Hình 3. 8 Chức năng xem thông tin học bổng
3.1.1.10 Chức năng xem tin tức
 
Hình 3. 9 Chức năng xem tin tức
3.1.1.11 Chức năng xem quy trình nhập học
 
Hình 3. 10 Chức năng xem quy trình nhập học
3.1.1.12 Chức năng chỉnh sửa thông tin trường
 
Hình 3. 11 Chức năng chỉnh sửa thông tin trường
3.1.1.13 Chức năng chỉnh sửa thông tin ngành
 
Hình 3. 12 Chức năng chỉnh sửa thông tin ngành
3.1.1.14 Chức năng chỉnh sửa thông tin nghề nghiệp
 
Hình 3. 13 Chức năng chỉnh sửa thông tin nghề nghiệp
3.1.1.15 Chức năng chỉnh sửa thông tin học bổng
 
Hình 3. 14 Chức năng chỉnh sửa thông tin học bổng
3.1.1.16 Chức năng chỉnh sửa tin tức
 
Hình 3. 15 Chức năng chỉnh sửa tin tức
3.1.1.17 Chức năng chỉnh sửa quy trình nhập học
 
Hình 3. 16 Usecase chỉnh sửa quy trình nhập học
3.1.1.18 Chức năng chỉnh sửa thông tin học phí
 
Hình 3. 17 Usecase chỉnh sửa thông tin học phí
3.1.1.19 Chức năng chỉnh sửa phương thức xét tuyển
 
Hình 3. 18 Usecase chỉnh sửa phương thức xét tuyển
3.1.2 Kịch bản của các usecase
3.1.2.1 Chức năng xem thông tin trường
Actor	Học sinh, sinh viên
Precondition	Học sinh, sinh viên đã truy cập màn trang chủ thành công
Post-condition	Học sinh, sinh viên đã xem thông tin về trường PTIT
Kịch bản chính	1.	Học sinh, sinh viên chọn chức năng "Xem thông tin trường"
2.	Hệ thống hiển thị thông tin về trường PTIT trên giao diện ứng dụng, bao gồm:
-	Lịch sử và thông tin chung về trường.
-	Thông tin liên hệ và địa chỉ trường. -	Các thông tin liên quan khác.
3.	Học sinh, sinh viên đọc và xem thông tin về trường PTIT
Ngoại lệ	2.1 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
3.1.2.2 Chức năng xem thông tin ngành
Actor	Học sinh, sinh viên
Precondition	Học sinh, sinh viên đã truy cập màn trang chủ thành công
Post-condition	Học sinh, sinh viên nhận được thông tin ngành
Kịch bản chính	1.	Sinh viên chọn chức năng "Xem thông tin ngành".
2.	Hệ thống hiển thị danh sách các ngành học trong trường PTIT với các thông tin sau:
-	Tên ngành học.
-	Mô tả ngắn về ngành học.
3.	Sinh viên chọn một ngành học cụ thể từ danh sách để xem thông tin chi tiết.
4.	Hệ thống hiển thị thông tin chi tiết về ngành học được chọn, bao gồm:
-	Tên ngành.
-	Mô tả về ngành.
-	Các thông tin về tuyển sinh của ngành.
-	Cơ hội nghề nghiệp sau tốt nghiệp.
Ngoại lệ	2.2 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
4.2 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
3.1.2.3 Chức năng tư vấn tuyển sinh tự động
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã được hỗ trợ về tư vấn tuyển sinh
Kịch bản chính	1.	Sinh viên nhấn chọn icon chatbot trên màn hình chính.
2.	Hệ thống hiển thị màn hình chatbot cho tư vấn tuyển sinh.
3.	Chatbot chào và hỏi về mục tiêu tuyển sinh.
4.	Sinh viên nhập câu hỏi hoặc mục tiêu tuyển sinh của họ.
5.	Chatbot phân tích câu hỏi hoặc mục tiêu và cung cấp thông tin hoặc gợi ý cụ thể dựa trên dữ liệu có sẵn.
6.	Cuộc trò chuyện giữa người dùng và chatbot có thể kéo dài cho đến khi người dùng đạt được thông tin cần thiết hoặc hài lòng với tư vấn.
Ngoại lệ	5.2 Chatbot không thể cung cấp thông tin yêu cầu
3.1.2.4 Chức năng xem thông tin học bổng
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã được hỗ trợ về tư vấn tuyển sinh
Kịch bản chính	1.	Người dùng bấm chọn “xem thêm chức năng” tại màn hình trang chủ
2.	Hệ thống hiển thị màn hình danh sách các chức năng
3.	Người dùng tìm và chọn tùy chọn "Xem thông tin học bổng" trên màn hình.
4.	Hệ thống hiển thị danh sách các học loại bổng bao gồm:
-	Tên học bổng.
-	Mô tả về học bổng.
-	Giá trị học bổng.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
3.1.2.5 Chức năng xem thông tin định hướng nghề nghiệp
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã nắm được thông tin nghề nghiệp
Kịch bản chính	1.	Người dùng chọn chức năng "Định hướng nghề nghiệp" 
2.	Hệ thống hiển thị danh sách các nghề nghiệp có sẵn bao gồm các thông tin:
-	Tên nghề nghiệp.
-	Mô tả về nghề nghiệp.
3.	Người dùng nhấn chọn một nghề nghiệp cụ thể
4.	Hệ thống hiển thị thông tin yêu cầu chi tiết của nghề nghiệp gồm
-	Tên nghề nghiệp.
-	Mô tả về nghề nghiệp.
-	Yêu cầu về trình độ, kỹ năng và kinh nghiệm.
-	Cơ hội việc làm và triển vọng trong tương lai.

Ngoại lệ	2.2 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
4.2 Lỗi kết nối, hệ thống hiển thị thông báo gián đoạn
 
3.1.2.6 Chức năng xem thông tin, tin tức
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã xem được thông tin, tin tức cần thiết
Kịch bản chính	1.	Học sinh, sinh viên nhấn chọn dưới bottom nav icon tin tức
2.	Hệ thống hiển thị màn hình danh sách các tin tức và thông tin có sẵn, thường dưới dạng danh sách hoặc lưới, bao gồm:
-	Tiêu đề tin tức hoặc thông tin.
-	Ngày đăng tin tức hoặc thông tin.
-	Tóm tắt ngắn về nội dung.
-	Hình ảnh minh họa (nếu có).
3.	Học sinh, sinh viên chọn một tin tức cụ thể để đọc chi tiết, nếu tin tức đó cung cấp thêm thông tin.
4.	Hệ thống hiển thị nội dung chi tiết của tin tức hoặc thông tin, bao gồm:
-	Tiêu đề tin tức hoặc thông tin.
-	Ngày đăng tin tức hoặc thông tin.
-	nội dung tin tức.
-	Hình ảnh minh họa (nếu có)
Ngoại lệ	2.2 Lỗi kết nối, hệ thống thông báo gián đoạn
4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.7 Chức năng xem quy trình nhập học
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã nắm được thông tin quy trình nhập học
Kịch bản chính	1.	Học sinh, sinh viên nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Học sinh, sinh viên tìm và chọn chức năng "Xem quy trình nhập học".
4.	Hệ thống hiển thị danh sách các bước và quy trình cần thiết để nhập học, bao gồm:
-	Chọn chương trình
-	Kiểm tra điều kiện
-	Chuẩn bị hồ sơ
		-	Nộp hồ sơ
5.	Học sinh, sinh viên có thể chọn một bước cụ thể để xem hướng dẫn chi tiết về cách thực hiện bước đó.
6.	Hệ thống hiển thị hướng dẫn chi tiết cho bước đã chọn, bao gồm thông tin liên quan, thời hạn và liên hệ cho trợ giúp nếu cần.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
6.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.8 Chức năng xem thông tin học phí
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã nắm được thông tin về học phí, số tín chỉ một kỳ, …
Kịch bản chính	1.	Học sinh, sinh viên nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Học sinh, sinh viên tìm và chọn tùy chọn "Xem thông tin học phí"
4.	Hệ thống hiển thị danh sách thông tin học phí hiện tại, mỗi thông tin bao gồm:
-	Khoá
Hệ đào tạo (đại trà/chất lượng cao)
-	Giá tiền một tín chỉ
-	Số tín trung bình một kỳ học -	Tổng số tiền cần thanh toán.
-	Các tùy chọn thanh toán
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.9 Chức năng xem phương thức xét tuyển
Actor	Học sinh, sinh viên
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Sinh viên đã nắm được thông tin các phương thức xét tuyển
Kịch bản chính	1. Sinh viên nhấn chọn “xem thêm chức năng”.
	2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Học sinh, sinh viên tìm và chọn tùy chọn "Xem phương thức xét tuyển".
4.	Hệ thống hiển thị danh sách các phương thức xét tuyển bao gồm:
-	Tên phương thức xét tuyển.
-	Mô tả về quy trình xét tuyển.
5.	Học sinh, sinh viên có thể chọn một phương thức xét tuyển cụ thể để xem thông tin chi tiết
6.	Hệ thống hiển thị thông tin chi tiết và yêu cầu liên quan đến phương thức xét tuyển đã chọn.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
6.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.10 Chức năng chỉnh sửa thông tin trường
Actor	Người quản lý
Precondition	Sinh viên đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin trường đã được cập nhật
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin trường".
4.	Hệ thống hiển thị các thông tin của trường, bao gồm:
-	Lịch sử và thông tin chung về trường.
-	Thông tin liên hệ và địa chỉ trường. -	Các thông tin liên quan khác.
5.	Người quản lý chọn thông tin trường muốn chỉnh sửa.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin đã chọn.
7.	Người quản lý chỉnh sửa thông tin và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin trường sau khi lưu
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.11 Chức năng chỉnh sửa thông tin ngành
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin ngành học đã được cập nhật
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin ngành học".
4.	Hệ thống hiển thị danh sách thông tin của các ngành học, bao gồm:
-	Tên ngành.
-	Mô tả về ngành.
-	Các thông tin về tuyển sinh của ngành. -	Cơ hội nghề nghiệp sau tốt nghiệp.
5.	Người quản lý chọn ngành học mà họ muốn chỉnh sửa thông tin.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của ngành học đã chọn.
7.	Người quản lý chỉnh sửa thông tin theo yêu cầu, bao gồm tên ngành học, mô tả, yêu cầu đầu vào, danh sách môn học, hình ảnh và biểu tượng, bấm xác nhận và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin ngành học sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.12 Chức năng chỉnh sửa thông tin định hướng nghề nghiệp
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin định hướng nghề nghiệp đã được cập nhật
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin định hướng nghề nghiệp".
4.	Hệ thống hiển thị danh sách thông tin về các định hướng 
	nghề nghiệp, bao gồm:
-	Tên định hướng nghề nghiệp. -	Mô tả.
-	Yêu cầu và kỹ năng cần thiết.
-	Các ví dụ về công việc liên quan.
5.	Người quản lý chọn định hướng nghề nghiệp mà họ muốn chỉnh sửa thông tin.
6.	Hệ thống hiển thị biểu mẫu hoặc trình soạn thảo để chỉnh sửa thông tin của định hướng nghề nghiệp đã chọn.
7.	Người quản lý chỉnh sửa thông tin sau đó xác nhận và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin định hướng nghề nghiệp sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn 8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.13 Chức năng chỉnh sửa thông tin học bổng
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin học bổng đã được cập nhật.
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin học bổng".
4.	Hệ thống hiển thị danh sách thông tin về các học bổng, bao gồm:
-	Tên học bổng.
-	Mô tả về học bổng. -	Giá trị học bổng.
5.	Người quản lý chọn thông tin học bổng mà họ muốn chỉnh sửa thông tin.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của học bổng đã chọn.
7.	Người quản lý chỉnh sửa thông tin theo yêu cầu và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin học bổng sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.14 Chức năng chỉnh sửa tin tức
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin tin tức đã được cập nhật.
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người dùng tìm và chọn "Chỉnh sửa thông tin tin tức".
4.	Hệ thống hiển thị danh sách các tin tức có sẵn, bao gồm:
-	Tiêu đề tin tức hoặc thông tin.
-	Ngày đăng tin tức hoặc thông tin. -	Mô tả ngắn gọn về tin tức -	Nội dung tin tức.
-	Hình ảnh minh họa (nếu có).
5.	Người quản lý chọn tin tức mà họ muốn chỉnh sửa thông tin.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của tin tức đã chọn.
7.	Người quản lý chỉnh sửa thông tin sau đó xác nhận và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin tin tức sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.15 Chức năng chỉnh sửa quy trình nhập học
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin về quy trình nhập học đã được cập nhật.
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người dùng tìm và chọn "Chỉnh sửa quy trình nhập học".
	4.	Hệ thống hiển thị các bước quy trình nhập học có sẵn, bao gồm:
-	Chọn chương trình
-	Kiểm tra điều kiện
-	Chuẩn bị hồ sơ
-	Nộp hồ sơ
5.	Người quản lý chọn bước trong quy trình nhập học mà họ muốn chỉnh sửa thông tin.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của quy trình nhập học đã chọn.
7.	Người quản lý chỉnh sửa thông tin sau đó xác nhận và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin quy trình nhập học sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn 8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.16 Chức năng chỉnh sửa thông tin học phí
Actor	Người quản lý
Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin về quy trình nhập học đã được cập nhật.
Kịch bản chính	1.	Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin học phí".
4.	Hệ thống hiển thị danh sách thông tin về học phí, bao gồm:
-	Khoá
-	Hệ đào tạo (đại trà/chất lượng cao)
-	Giá tiền một tín chỉ
-	Số tín trung bình một kỳ học -	Tổng số tiền cần thanh toán.
-	Các tùy chọn thanh toán
5.	Người quản lý chọn thông tin về học phí mà họ muốn chỉnh sửa.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của học phí đã chọn.
7.	Người quản lý chỉnh sửa thông tin sau đó xác nhận và lưu các thay đổi.
	8. Hệ thống cập nhật thông tin học phí sau khi lưu.
Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.2.17 Chức năng chỉnh sửa thông tin phương thức xét tuyển
	Actor	Người quản lý
	Precondition	Người quản lý đã truy cập được màn hình trang chủ
Post-condition	Thay đổi đã được áp dụng và thông tin về xét tuyển đã được cập nhật.
	Kịch bản chính	1. Người quản lý nhấn chọn “xem thêm chức năng”.
2.	Hệ thống hiển thị màn hình danh sách chức năng.
3.	Người quản lý tìm và chọn "Chỉnh sửa thông tin xét tuyển".
4.	Hệ thống hiển thị danh sách thông tin xét tuyển, bao gồm:
-	Tên ngành
-	Tên phương thức xét tuyển.
-	Mô tả về quy trình xét tuyển
5.	Người quản lý chọn thông tin xét tuyển mà họ muốn chỉnh sửa.
6.	Hệ thống hiển thị màn hình chỉnh sửa thông tin của xét tuyển đã chọn.
7.	Người quản lý chỉnh sửa thông tin sau đó xác nhận và lưu các thay đổi.
8.	Hệ thống cập nhật thông tin xét tuyển sau khi lưu.
	Ngoại lệ	4.2 Lỗi kết nối, hệ thống thông báo gián đoạn
8.2 Lỗi kết nối, hệ thống thông báo gián đoạn
3.1.3 Biểu đồ lớp phân tích
3.1.3.1 Trích xuất danh từ 
	a.	Lớp thực thể
●	Tài khoản: tên đăng nhập, mật khẩu
●	Người dùng: mã, họ tên, hình đại diện, email
●	Trường: Tên, địa chỉ, thông tin trường, số điện thoại, mô tả
●	Chương trình học: mã chương trình, tên, mô tả, thời gian 
●	Ngành học: mã ngành, tên, mô tả, thời gian học, cơ sở, tổng quan
●	Chỉ tiêu: số lượng, phương thức xét tuyển, năm
●	Yêu cầu đầu vào: khối xét tuyển, điểm tổng
●	Ngành nghề: mô tả, mức lương trung bình, tình hình thị trường
●	Học bổng: loại học bổng, điểm yêu cầu, hạnh kiểm yêu cầu 
●	học phí: số tiền, năm học
●	Tín chỉ: giá tiền, mô tả
●	Quy trình nhập học: bước thực hiện
●	Thông tin, tin tức: tiêu đề, ảnh, nội dung, thời gian, mô tả
	b.	Mối quan hệ giữa các lớp thực thể
●	1 người dùng có 1 tài khoản: quan hệ kết hợp, Nguoi 1-1 TaiKhoan
●	1 trường học có thể có nhiều tin tức: quan hệ kết hợp, Truong 1-n TinTuc 
●	1 trường học có 1 quy trình nhập học: quan hệ kết hợp, Truong 1-1 
QuyTrinhNhapHoc
●	1 trường học có thể có nhiều chương trình học: quan hệ hợp thành, Truong 1-n 
ChuongTrinhHoc
●	1 chương trình học có 1 mức học phí: quan hệ kết hợp, quan hệ 
ChuongTrinhHoc 1-n HocPhi 
●	1 chương trình học có thể có nhiều học bổng: quan hệ kết hợp, 
ChuongTrinhHoc 1-n HocBong
●	1 chương trình học có thể có nhiều ngành học: quan hệ liên kết, 
ChuongTrinhHoc n-n NganhHoc 
●	1 ngành học có thể có nhiều yêu cầu đầu vào: quan hệ kết hợp, NganhHoc 1-n 
YeuCauDauVao
●	1 ngành học có thể có nhiều tín chỉ: quan hệ kết hợp, NganhHoc 1-n TinChi
●	1 ngành học có thể có nhiều chỉ tiêu: quan hệ kết hợp, NganhHoc 1-n ChiTieu
●	1 ngành học có thể đáp ứng nhiều ngành nghề: quan hệ kết hợp, NganhHoc 1-n NganhNghe
3.1.3.2 Biểu đồ lớp phân tích
 
Hình 3. 19 Biểu đồ lớp phân tích
3.2 Thiết kế hệ thống tư vấn tuyển sinh tự động
3.2.1 Biểu đồ tuần tự
3.2.1.1 Chức năng xem thông tin trường
 
Hình 3. 20 Biểu đồ tuần tự chức năng xem thông tin trường
3.2.1.2 Chức năng xem thông tin ngành
 
Hình 3. 21 Biểu đồ tuần tự chức năng xem thông tin ngành
3.2.1.3 Chức năng tư vấn tự động
 
Hình 3. 22 Biểu đồ tuần tự chức năng tư vấn tự động
3.2.1.4 Chức năng xem thông tin học bổng
 
Hình 3. 23 Biểu đồ tuần tự chức năng xem thông tin học bổng
3.2.1.5 Chức năng xem thông tin học phí
 
Hình 3. 24 Biểu đồ tuần tự chức năng xem thông tin học phí
3.2.1.6 Chức năng xem định hướng nghề nghiệp
 
Hình 3. 25 Biểu đồ tuần tự chức năng xem thông tin nghề nghiệp
3.2.1.7 Chức năng xem tin tức
 
Hình 3. 26 Biểu đồ tuần tự chức năng xem tin tức
3.2.1.8 Chức năng xem quy trình nhập học
 
Hình 3. 27 Biểu đồ tuần tự chức năng xem quy trình nhập học
3.2.1.9 Chức năng xem thông tin phương thức xét tuyển
 
Hình 3. 28 Biểu đồ tuần tự chức năng xem phương thức xét tuyển
3.2.1.10 Chức năng chỉnh sửa thông tin trường
 
Hình 3. 29 Biểu đồ tuần tự chức năng chỉnh sửa thông tin trường
3.2.1.11 Chức năng chỉnh sửa thông tin ngành
 
Hình 3. 30 Biểu đồ tuần tự chức năng chỉnh sửa thông tin ngành
3.2.1.12 Chức năng chỉnh sửa thông tin định hướng nghề nghiệp
 
Hình 3. 31 Biểu đồ tuần tự chức năng chỉnh sửa thông tin nghề nghiệp
3.2.1.13 Chức năng chỉnh sửa thông tin học bổng
 
Hình 3. 32 Biểu đồ tuần tự chức năng chỉnh sửa thông tin học bổng
3.2.1.14 Chức năng chỉnh sửa tin tức
 
Hình 3. 33 Biểu đồ tuần tự chức năng chỉnh sửa tin tức
3.2.1.15 Chức năng chỉnh sửa quy trình nhập học
 
Hình 3. 34 Biểu đồ tuần tự chức năng chỉnh sửa quy trình nhập học
3.2.2 Biểu đồ lớp thiết kế
3.2.2.1 Biểu đồ lớp thiết kế
Trên cơ sở biểu đồ tuần tự, em thực hiện phương pháp gán trách nhiệm cho lớp để đưa các phương thức tương ứng vào lớp
 
Hình 3. 35 Biểu đồ lớp thiết kế 
3.2.2.2  Biểu đồ gói
 
Hình 3. 36 Biểu đồ gói
Chương 4: Thiết kế cơ sở dữ liệu
Dựa vào biểu đồ lớp thiết kế đã có ở phần 3.1.3.1 Trích xuất danh từ trước đó, em xác định được các đối tượng, thuộc tính và kiểu của thuộc tính được lưu trữ trong cơ sở dữ liệu. Qua đó xây dựng các bảng và thuộc tính của chúng. Dưới đây là danh sách các bảng
4.1 Bảng Truong
Tên trường	Thuộc tính	Ghi chú
maTruong	varchar(10)	khoá chính, id 
tenTruong	varchar(255)	tên trường
moTa	varchar(255)	thông tin về trường
thanhPho	varchar(255)	tên vị trí thành phố
quan 	varchar(255)	tên quận
duong	varchar(255)	số hoặc tên đường
4.2 Bảng NganhHoc
Tên trường	Thuộc tính	Ghi chú
maNganh	varchar(10)	khoá chính, id
tenNganh	varchar(255)	tên ngành học
moTa	varchar(255)	mô tả về ngành học
coSo	varchar(255)	cơ sở dạy ngành học
4.3 Bảng NganhNghe
Tên trường	Thuộc tính	Ghi chú
maNghe	varchar(10)	khoá chính, id
tenNganh	varchar(255)	tên ngành nghề
mucLuong	varchar(255)	mức lương trung bình
tinhTrang	varchar(255)	tình trạng công việc hiện tại
NganhHocmaNganh	varchar(255)	khoá ngoại liên kết với bảng NganhHoc
4.4 Bảng ChiTieu
Tên trường	Thuộc tính	Ghi chú
maChiTieu	varchar(10)	khoá chính, id
nam	varchar(255)	năm đề ra chỉ tiêu
soLuong	varchar(255)	số lượng của chỉ tiêu
phuongThuc	varchar(255)	phương thức 
NganhHocmaNganh	varchar(255)	
4.5 Bảng TinChi
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
giaTien	varchar(255)	giá tiền cho 1 tín
NganhHocmaNganh	varchar(255)	khoá ngoại tới bảng 
NganhHoc
4.6 Bảng YeuCauDauVao
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
khoi	varchar(255)	khối xét tuyển
diemTong	varchar(255)	điểm tổng xét tuyển
NganhHocmaNganh	varchar(255)	khoá ngoại tới bảng 
NganhHoc
4.7 Bảng QuyTrinhNhapHoc
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
noiDung	varchar(255)	nội dung quy trình
TruongmaTruong	varchar(255)	khoá ngoại tới bảng Truong
4.8 Bảng HocPhi
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
soTien	varchar(255)	học phí cần trả
namHoc	varchar(255)	năm học áp dụng học phí
ChuongTrinhHocid	varchar(10)	khoá ngoại liên kết tới bảng ChuongTrinhHoc
4.9 Bảng HocBong
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
loaiHb	varchar(255)	loại học bổng
diemYc	varchar(255)	điểm yêu cầu tối thiẻu
hanhKiemYc	varchar(255)	hạnh kiểm yêu cầu tối thiểu
ChuongTrinhHocid	varchar(255)	khoá ngoại liên kết tới bảng ChuongTrinhHoc
4.10 Bảng KhoaHoc
Tên trường	Thuộc tính	Ghi chú
ChuongTrinhHocid	varchar(10)	khoá ngoại với bảng ChươngTrinhHoc
NganhHocmaNganh	varchar(10)	khoá ngoại với bảng NganhHoc
4.11 Bảng NguoiDung
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
ten	varchar(255)	tên người dùng
avatar	varchar(255)	link ảnh đại diện
email	varchar(255)	email
vaiTro	varchar(255)	vai trò trong app
TaiKhoanid	varchar(255)	khoá ngoại với bảng TaiKhoan
4.12 Bảng TinTuc
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
tieuDe	varchar(255)	tiêu đề tin tức
anh	varchar(255)	link ảnh của tin tức
noiDung	varchar(255)	nội dung của tin tức
moTa	varchar(255)	mô tả ngắn gọn về tin tức
TruongmaTruong	varchar(255)	khoá  ngoại liên kết với bảng Truong
4.13 Bảng TaiKhoan
Tên trường	Thuộc tính	Ghi chú
id	varchar(10)	khoá chính, id
username	varchar(255)	tên đăng nhập
password	varchar(255)	mật khẩu
4.14 Quan hệ giữa các bảng
●	1 NguoiDung có 1 TaiKhoan
●	1 Truong có thể có nhiều TinTuc
●	1 Truong có 1 QuyTrinhNhapHoc
●	1 Truong có thể có nhiều ChuongTrinhHoc
●	1 ChuongTrinhHoc có 1 mức HocPhi
●	1 ChuongTrinhHoc có thể có nhiều HocBong
●	1 ChuongTrinhHoc có thể có nhiều NganhHoc
●	1 NganhHoc có thể có nhiều ChuongTrinhHoc
●	1 NganhHoc có thể có nhiều YeuCauDauVao
●	1 NganhHoc có thể có nhiều TinChi
●	1 NganhHoc có thể có nhiều ChiTieu
●	1 NganhHoc có thể đáp ứng nhiều NganhNghe 
Từ đó xây dựng biểu đồ cơ sở dữ liệu như hình vẽ dưới:
 
Hình 3. 37 Biểu đồ thiết kế cơ sở dữ liệu
Kết luận
Ở Chương 4: Thiết kế cơ sở dữ liệu, em đã trích xuất các bảng và mối quan hệ giữa các bảng từ các thực thể và các mối quan hệ được nêu ra trong Chương 3: Phân tích thiết kế hệ thống tư vấn tuyển sinh tự động.
Từ các bảng trong cơ sở dữ liệu trên, em sẽ tiến hành cài đặt và triển khai hệ thống tư vấn tự động.
Kết luận chung
A.     Kiến thức đạt được
Sau quá trình thực hiện đồ án tốt nghiệp, em đã học hỏi được thêm nhiều điều mới, có thêm nhiều tiến bộ cho bản thân. Tiêu biểu một số kiến thức:
●	Quy trình thực tế khi xây dựng một hệ thống từ đầu đến cuối bao gồm các bước như: Lên ý tưởng, tìm hiểu công nghệ, phân tích thiết kế, áp dụng công nghệ, phát triển và triển khai hệ thống trên ứng dụng di động android.
●	Nâng cao khả năng tìm hiểu, nghiên cứu và giải quyết vấn đề
●	Tìm hiểu và áp dụng các kiến trúc, các thư viện mới
+ Thư viện xử lý ngôn ngữ Underthesea
+ Mô hình học máy Transformer
+ Xây dựng server đơn giản với Flask
+ Thư viện Android Jetpack
+ Áp dụng kiến trúc Clean Architecture kết hợp MVVM ● Cải thiện kỹ năng viết và trình bày tài liệu
B. Hạn chế
Hiện tại, hệ thống tư vấn tuyển sinh tự động còn nhiều hạn chế cần khắc phục như:
●	Giao diện còn sơ sài, đơn giản
●	Số chức năng hoàn thiện còn ít, chưa dễ tiếp cận cho người dùng
●	Chưa có chức năng quản lý
●	Bộ dữ liệu chưa có tính mở rộng
●	Mô hình tư vấn vẫn còn nhiều thiếu sót
C. Hướng phát triển trong tương lai
Trong tương lai, để có thể áp dụng được hệ thống vào thực tiễn, một số đề xuất được thực hiện:
●	Phát triển thêm các chức năng
●	Nâng cấp giao diện và nâng cao trải nghiệm người dùng
●	Hoàn thiện chức năng quản lý
●	Xây dựng lại mô hình tư vấn tự động và cập nhật bộ dữ liệu
Phụ lục: Cài đặt và triển khai
	1.1.	Thiết lập môi trường
	1.1.1.	Cài đặt python
Trong đồ án này, em sẽ cài đặt và sử dụng python3, sử dụng câu lệnh sudo apt install python3 trên Ubuntu hoặc tải về python3 tại trang chủ
 
Python3 được cài đặt thành công, có thể kiểm tra phiên bản python3 bằng câu lệnh python3 sẽ hiện ra như sau
 
	1.1.2.	Cài đặt Visual Studio Code
Với việc cài đặt Visual Studio Code, em sẽ tải trực tiếp từ trang chủ.
Sau khi tải xong và thực hiện chạy, giao diện Visual Studio Code sẽ hiện lên như sau
 
	1.1.3.	Cài đặt Android Studio
Vì là hệ thống được triển khai trên ứng dụng di động Android, vậy nên việc triển khai hệ thống này sẽ cần có Android Studio. Có thể tải trực tiếp trên trang chủ.
 
	1.2.	Triển khai hệ thống
	1.2.1.	Cài đặt Flask
Để cài đặt  Flask phục vụ xây dựng các API service, em sẽ tạo một môi trường giúp tách biệt Flask. 
Đầu tiên, em sẽ tạo một thư mục dành cho Flask bằng câu lệnh mkdir flask-app && cd flask-app.
Bước tiếp theo là thiết lập môi trường ảo và kích hoạt (activate) môi trường ảo này bằng 2 câu lệnh dưới đây python3 -m venv venv source venv/bin/activate
Sau khi activate môi trường ảo, em sẽ tiến hành cài đăt Flask với lệnh pip3 install Flask 
Để biết được Flask đã được cài đặt hay chưa hoặc kiểm tra phiên bản hiện tại của
Flask, em sẽ chạy câu lệnh python3 -m flask --version Khi đó màn hình sẽ hiển thị
 
1.2.2. Cài đặt Transformer và UnderTheSea Để cài đặt Transfomer, em sẽ thực hiện câu lệnh pip install transformers
Hoặc có thể cài đặt từ repo
git clone https://github.com/huggingface/transformers.git
cd transformers
pip install -e .
Tiếp đến, em sẽ cài đặt một thư viện hỗ trợ cho việc xử lý dữ liệu phục vụ huấn luyện mô hình, đó là UnderTheSea - thư viện xử lý ngôn ngữ tự nhiên có hỗ trợ xử lý tiếng Viêt.
 
  
	1.2.3.	Cài đặt Whisper
Cài đăt Whisper, em sẽ sử dụng lệnh
pip install -U openai-whisper
 
Nếu cần cập nhật phiên bản mới nhất, em sẽ sử dụng lệnh pip install --upgrade --no-deps --force-reinstall     git+https://github.com/openai/whisper.git
 
	1.3.	Cấu trúc code
 
	1.4.	Một số màn hình
●	Màn hình trang chủ
 
●	Màn hình thông tin trường
 
●	Màn hình chi tiết một tin tức
  
Tài liệu tham khảo
1.	https://flask.palletsprojects.com/en/3.0.x/api/ 
2.	https://github.com/google/gson 
3.	https://github.com/usefulsensors/openai-whisper 
4.	https://blog.vinbigdata.org/transformer-neural-network-mo-hinh-hoc- may-bien-doi-the-gioi-nlp/
5.	https://openai.com/research/whisper 
6.	https://github.com/openai/whisper
7.	https://viblo.asia/p/transformers-nguoi-may-bien-hinh-bien-doi-the-gioi- 
nlp-924lJPOXKPM
8.	https://www.airdroid.com/ai-insights/transformers-chatbot/ 
9.	https://viblo.asia/p/tim-hieu-ve-clean-architecture-trong-android- 
Az45b0vOZxY
10.	https://vncoder.vn/bai-viet/clean-architecture-la-gi-loi-ich-va-han-checua-architecture-so-sanh-mvp-va-mvvm
