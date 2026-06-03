package com.btvn.ss17ex5.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @PutMapping("/{userId}/ban")
    @Secured("ROLE_ADMIN")
    public String banUser(@PathVariable Long userId) {
        return "Tài khoản người dùng " + userId + " đã bị khóa vĩnh viễn do vi phạm chính sách.";
    }

    @GetMapping("/dashboard-statistics")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public String getSystemStatistics() {
        return "Dữ liệu thống kê: CCU hiện tại 250,000 người dùng, tổng doanh thu vật phẩm trong ngày đạt 5,000 USD.";
    }
}