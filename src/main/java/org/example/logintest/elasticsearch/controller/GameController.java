package org.example.logintest.elasticsearch.controller;


import org.example.logintest.elasticsearch.entity.Game;
import org.example.logintest.elasticsearch.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/search")
    public List<String> searchGames(@RequestParam String keyword) {
        return gameService.searchGamesByDescription(keyword);
    }
}
