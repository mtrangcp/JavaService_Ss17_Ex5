Để tối ưu hóa tính bảo mật và khả năng mở rộng cho hệ thống Game Arcade, giải pháp tốt nhất là không 
phụ thuộc hoàn toàn vào một phương pháp đơn lẻ, mà áp dụng chiến lược kết hợp bổ trợ lẫn nhau:

1. Phân quyền tập trung dựa trên URL (requestMatchers tại SecurityConfig)
   Bản chất: Đóng vai trò là "Vòng ngăn chặn vòng ngoài" (Tầng thô - Coarse-grained). Hệ thống sẽ chặn ngay lập 
tức các request đi vào các dải URL nhạy cảm dựa trên cấu trúc đường dẫn cố định.

Ngữ cảnh áp dụng: Áp dụng cho các cụm chức năng có tính tách biệt tuyệt đối về vai trò. Ví dụ: Toàn bộ các API 
có tiền tố /api/admin/ hay /api/moderator/ sẽ được cấu hình chặn đứng tại đây nếu người dùng không có 
vai trò tương ứng.

Lý do lựa chọn: Giúp bảo vệ hệ thống từ sớm tại tầng Filter Chain mà chưa cần tiêu tốn tài nguyên kích hoạt
các Controller/Service bên trong. Code cấu hình tập trung tại một file giúp lập trình viên có cái nhìn tổng quan, 
tránh bỏ sót toàn bộ một phân vùng API.

2. Phân quyền cấp phương thức (@PreAuthorize, @Secured tại Controller)
   Bản chất: Đóng vai trò là "Hệ thống kiểm soát vòng trong" (Tầng tinh - Fine-grained). Kiểm tra điều kiện 
bảo mật ngay trước khi logic của hàm được thực thi.

Ngữ cảnh áp dụng: Áp dụng cho các API mang tính hỗn hợp (nhiều vai trò cùng truy cập nhưng quyền hạn khác nhau) 
hoặc các API có điều kiện ràng buộc động phụ thuộc vào dữ liệu đầu vào.

Lý do lựa chọn:

Dùng @Secured cho các hàm đơn giản, tường minh về vai trò (ví dụ: chỉ đích danh @Secured("ROLE_ADMIN") 
mới được xem báo cáo doanh thu).

Dùng @PreAuthorize với ngôn ngữ biểu thức SpEL cho các logic phức tạp, như kiểm tra tính chính chủ: 
"Người dùng chỉ được xóa/sửa bình luận của chính họ tạo ra hoặc họ phải là Moderator/Admin". Điểm này 
cấu hình URL ở SecurityConfig hoàn toàn không thể làm được vì nó không đọc được dữ liệu nghiệp vụ của Request.

--> Sự kết hợp này tạo nên hệ thống bảo mật 2 lớp. Nếu lập trình viên vô tình quên 
cấu hình ở lớp này, lớp kia vẫn hoạt động như một lưới đỡ bảo mật, triệt tiêu hoàn toàn các lỗ hổng nguy hiểm.