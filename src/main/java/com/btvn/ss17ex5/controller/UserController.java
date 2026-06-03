package com.btvn.ss17ex5.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    /**
     * [Yêu cầu 1/2 @Secured đơn giản] - Khóa tài khoản người dùng vi phạm.
     * Quy tắc: Chỉ có vai trò ADMIN tối cao mới được quyền thực hiện hành động này.
     * Lưu ý: @Secured bắt buộc phải ghi rõ tiền tố 'ROLE_'
     */
    @PutMapping("/{userId}/ban")
    @Secured("ROLE_ADMIN")
    public String banUser(@PathVariable Long userId) {
        return "Tài khoản người dùng " + userId + " đã bị khóa vĩnh viễn do vi phạm chính sách.";
    }

    /**
     * [Yêu cầu 2/2 @Secured đơn giản] - Xem thống kê doanh thu và báo cáo hệ thống.
     * Quy tắc: Cho phép cả ADMIN và các điều phối viên cao cấp (GAME_MODERATOR) xem dữ liệu tổng quan.
     */
    @GetMapping("/dashboard-statistics")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public String getSystemStatistics() {
        return "Dữ liệu thống kê: CCU hiện tại 250,000 người dùng, tổng doanh thu vật phẩm trong ngày đạt 5,000 USD.";
    }
}