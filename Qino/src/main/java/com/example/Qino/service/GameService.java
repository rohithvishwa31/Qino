package com.example.Qino.service;

import com.example.Qino.entity.Game;
import com.example.Qino.repository.GameRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public  void createGame()
    {
        Game game=new Game();
        game.setGameId("Qino"+System.currentTimeMillis());
        game.setStatus(1);
        game.setDate(new Date(System.currentTimeMillis()));
        game.setBetLimit(new BigDecimal(100));
        gameRepository.save(game);
    }
}
