package com.example.Qino.service;

import com.example.Qino.entity.Player;
import com.example.Qino.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player validatePlayer(String email, String password){

        Player player = playerRepository.findByEmailAndPassword(email,password);

        if(player!=null){
            return player;
        }
        return null;

    }
}
