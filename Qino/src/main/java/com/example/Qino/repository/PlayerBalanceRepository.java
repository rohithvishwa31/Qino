package com.example.Qino.repository;

import com.example.Qino.entity.Player;
import com.example.Qino.entity.PlayerBalance;
import org.springframework.data.repository.CrudRepository;

public interface PlayerBalanceRepository extends CrudRepository<PlayerBalance, Integer> {
  public  PlayerBalance findByPlayer(Player player);

}
