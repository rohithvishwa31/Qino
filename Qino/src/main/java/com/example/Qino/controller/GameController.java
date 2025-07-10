package com.example.Qino.controller;


import com.example.Qino.entity.Bet;
import com.example.Qino.entity.Game;
import com.example.Qino.entity.Player;
import com.example.Qino.entity.PlayerBalance;
import com.example.Qino.model.Ticket;
import com.example.Qino.repository.BetRepository;
import com.example.Qino.repository.GameRepository;
import com.example.Qino.repository.PlayerBalanceRepository;
import com.example.Qino.repository.PlayerRepository;
import com.example.Qino.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

@RestController("/game")
public class GameController {


    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @Autowired
    BetRepository betRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerBalanceRepository playerBalanceRepository;

    @GetMapping("/start")
    private String startGame() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                System.out.println("timer called");
                List<Game> all = gameRepository.findAll();
                Optional<Game> first = all.stream().filter(a -> a.getStatus() == 1).findFirst();
                if (first.isEmpty()) {
                    gameService.createGame();
                } else {
                    Game game = first.get();
                    List<Bet> byGameId = betRepository.findByGameId(game.getGameId());

                    Collections.shuffle(byGameId);
                    Player winPlayer = playerRepository.findByName(byGameId.get(0).getPlayerId());
                    PlayerBalance playerBalance = playerBalanceRepository.findByPlayer_pid(winPlayer.getPid());
                    playerBalance.setBalance(playerBalance.getBalance().add(game.getBetLimit().multiply(new BigDecimal(byGameId.size()))));
                    playerBalanceRepository.save(playerBalance);
                    System.out.println("winner " + byGameId.get(0));
                    game.setStatus(2);

                    gameRepository.save(game);
                    gameService.createGame();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 100000);
        return null;
    }

    @PostMapping("/bet")
    public String postBet(@RequestBody Ticket ticket) {

        Player player = playerRepository.findByName(ticket.getPlayerId());
        PlayerBalance playerBalance = playerBalanceRepository.findByPlayer_pid(player.getPid());
        List<Game> all = gameRepository.findAll();
        Optional<Game> first = all.stream().filter(a -> a.getStatus() == 1).findFirst();
        if (!first.isEmpty()) {
            Game game = first.get();
            if (playerBalance.getBalance().compareTo(game.getBetLimit()) > 0) {
                Bet bet = new Bet();
                Game gamebyStatus = gameRepository.findByStatus(1);
                if (gamebyStatus != null) {
                    bet.setGameId(gamebyStatus.getGameId());
                    bet.setPlayerId(ticket.getPlayerId());
                    betRepository.save(bet);
                    playerBalance.setBalance(playerBalance.getBalance().subtract(game.getBetLimit()));
                    playerBalanceRepository.save(playerBalance);
                    return "bet posted";
                }
                return "bet not posted ";

            }
            return null;
        }
     return null;
    }
}
