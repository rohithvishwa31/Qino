package com.example.Qino.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bet {

    @Id
    private String playerId;
    private String gameId;

}
