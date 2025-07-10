package com.example.Qino.controller;


import com.example.Qino.entity.Player;
import com.example.Qino.entity.PlayerBalance;
import com.example.Qino.model.request.PlayerLogin;
import com.example.Qino.model.request.PlayerSignUp;
import com.example.Qino.model.response.PlayerLoginResponse;
import com.example.Qino.repository.PlayerBalanceRepository;
import com.example.Qino.repository.PlayerRepository;
import com.example.Qino.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerBalanceRepository playerBalanceRepository;

    @Autowired
    private PlayerService playerService;



    @PostMapping("/create")
    public String createPlayer(@RequestBody PlayerSignUp player) {

        Player player1 = new Player();
        player1.setName(player.getName());
        player1.setPassword(player.getPassword());
        player1.setEmail(player.getEmail());
        PlayerBalance playerBalance = new PlayerBalance();
        playerBalance.setBalance(new BigDecimal(10000));
        playerBalance.setPid(player1);

        playerRepository.save(player1);
        playerBalanceRepository.save(playerBalance);

        return "success";

    }

    @PostMapping("/login")
    public PlayerLoginResponse playerLogin(@RequestBody PlayerLogin playerLogin){
        Player player = playerService.validatePlayer(playerLogin.getEmail(), playerLogin.getPassword());
        PlayerLoginResponse response = new PlayerLoginResponse();
        if (player != null){
            response.setPlayer_id(player.getPid());
            response.setStatus("Successfully logged in!");
            return response;
        }
        response.setPlayer_id(null);
        response.setStatus("Login failed! No player found");

        return response;
    }
}
