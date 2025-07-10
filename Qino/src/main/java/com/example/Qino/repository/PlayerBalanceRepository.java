package com.example.Qino.repository;

import com.example.Qino.entity.Player;
import com.example.Qino.entity.PlayerBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerBalanceRepository extends JpaRepository<PlayerBalance, Integer> {

  @Query(nativeQuery = true,value = "select * from player_balance where player_id=?")
  public  PlayerBalance findByPlayer_pid(Integer player);

}
