package org.example.logintest.elasticsearch.service;


import org.example.logintest.elasticsearch.entity.Game;
import org.example.logintest.elasticsearch.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;


    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<String> searchGamesByDescription(String keyword) {
        List<Game> games = gameRepository.findByDescriptionContaining(keyword);
        return games.stream().map(Game::getName).toList();
    }
}
