package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TipoMotoDTO {

    private Long id;

    @NotBlank
    @Size(max = 60)
    private String descricao;

    @NotBlank
    @Size(max = 30)
    private String categoria;
}
