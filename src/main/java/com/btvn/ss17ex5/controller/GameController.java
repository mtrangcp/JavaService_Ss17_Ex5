package com.btvn.ss17ex5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @GetMapping("/{gameId}/play")
    public String playGame(@PathVariable UUID gameId) {
        return "Đang tải dữ liệu trò chơi: " + gameId;
    }

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN') or @commentService.getCommentAuthor(#commentId) == authentication.name")
    public String deleteComment(@PathVariable Long commentId) {
        return "Bình luận " + commentId + " đã được xóa thành công.";
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('PLAYER') and #targetUsername == authentication.name")
    public String buyItem(@RequestParam String targetUsername, @RequestParam String itemId) {
        return "Vật phẩm " + itemId + " đã được mua và chuyển thẳng vào kho đồ của " + targetUsername;
    }
}