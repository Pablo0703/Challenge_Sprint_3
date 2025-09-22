package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StatusMotoDTO {

    private Long id;

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @Size(max = 1)
    private String disponivel;
}