package com.example.Qino.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "player_id",sequenceName = "player_id",initialValue = 1000,allocationSize=1)
    private Integer pid;

    private String name;
    private String password;
    private String email;
}
