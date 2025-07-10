package com.example.Qino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Data
public class Game {

    @Id
    private String gameId;
    private Date date;
    private Integer status;//1 - act 2- completed
    private BigDecimal betLimit;

}
