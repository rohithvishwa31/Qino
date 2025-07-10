package com.example.Qino.model.response;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class PlayerLoginResponse {
   private Integer player_id;
   private String status;
}
