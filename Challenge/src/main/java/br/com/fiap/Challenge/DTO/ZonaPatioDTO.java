package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ZonaPatioDTO {

    private Long id;

    @NotBlank(message = "O nome da zona é obrigatório.")
    private String nomeZona;

    @NotBlank(message = "O tipo da zona é obrigatório.")
    private String tipoZona;

    @NotNull(message = "A capacidade é obrigatória.")
    @Positive(message = "A capacidade deve ser positiva.")
    private Integer capacidade;

    @NotNull(message = "É obrigatório selecionar um pátio.")
    private Long idPatio; // Para receber o ID do pátio do formulário

    private PatioDTO patio; // Para exibir os dados do pátio na listagem
}