package com.example.Qino.repository;

import com.example.Qino.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet,String> {

  public List<Bet> findByGameId(String gameId);
}
