package com.example.Qino.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSignUp {
    private String name;
    private String password;
    private String email;
}
