package com.example.Qino.repository;

import com.example.Qino.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    public Player findByName(String name);
    public Player findByEmailAndPassword(String email, String password);
}
