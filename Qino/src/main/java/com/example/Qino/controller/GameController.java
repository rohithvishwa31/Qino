package com.example.Qino.controller;


import com.example.Qino.entity.Game;
import com.example.Qino.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class GameController {


    @Autowired
    GameRepository gameRepository;

    @GetMapping("/start")
    private String startGame()
    {
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {

                System.out.println("timer called");
                List<Game> all = gameRepository.findAll();
                Optional<Game> first = all.stream().filter(a -> a.getStatus() == 1).findFirst();
                 if(first.isEmpty())
                 {
                     createGame();
                 }
                 else{
                     Game game= first.get();
                    game.setStatus(2);

                    gameRepository.save(game);
                    createGame();


                 }

            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,100000);
        return null;
    }

    public  void createGame()
    {
        Game game=new Game();
        game.setGameId("Qino"+System.currentTimeMillis());
        game.setStatus(1);
        game.setDate(new Date(System.currentTimeMillis()));
        gameRepository.save(game);

    }
}
