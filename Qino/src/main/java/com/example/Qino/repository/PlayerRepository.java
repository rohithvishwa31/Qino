package com.example.Qino.repository;

import com.example.Qino.entity.Player;
import com.example.Qino.model.PlayerSignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    public Player findByName(String name);
}
