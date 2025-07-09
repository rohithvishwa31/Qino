package com.example.Qino.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "idgenrator" ,initialValue = 1,allocationSize = 1)
    private long id;
    private String playerId;
    private String gameId;


}
