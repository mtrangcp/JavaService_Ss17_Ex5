package com.btvn.ss17ex5.controller;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/games")
public class PublicGameController {

    @GetMapping("/{gameId}/play")
    public String playGame(@PathVariable UUID gameId) {
        return "Đang tải dữ liệu trò chơi công khai: " + gameId;
    }
}