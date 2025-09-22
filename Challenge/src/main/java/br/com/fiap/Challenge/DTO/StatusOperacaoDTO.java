package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StatusOperacaoDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String descricao;

    @NotBlank(message = "O tipo de movimentação é obrigatório.")
    @Pattern(regexp = "ENTRADA|SAIDA|TRANSFERENCIA", message = "Tipo de movimentação inválido. Valores permitidos: ENTRADA, SAIDA, TRANSFERENCIA.")
    private String tipoMovimentacao;
}