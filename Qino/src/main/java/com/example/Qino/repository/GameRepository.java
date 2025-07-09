package com.example.Qino.repository;

import com.example.Qino.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,String> {
}
