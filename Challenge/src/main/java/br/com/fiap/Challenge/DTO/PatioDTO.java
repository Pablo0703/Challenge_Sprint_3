package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatioDTO {

    private Long id;

    @NotBlank(message = "O nome do pátio é obrigatório.")
    @Size(max = 50)
    private String nome;

    private Double areaM2;

    @NotNull(message = "A capacidade é obrigatória.")
    @Positive(message = "A capacidade deve ser um número positivo.")
    private Integer capacidade;

    private String ativo = "S";

    @NotNull(message = "A filial é obrigatória.")
    private Long idFilial; // Usado para receber o ID do formulário

    private FilialDTO filial; // Usado para exibir os dados da filial
}