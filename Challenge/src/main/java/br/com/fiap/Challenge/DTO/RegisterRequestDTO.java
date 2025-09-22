package br.com.fiap.Challenge.DTO;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
