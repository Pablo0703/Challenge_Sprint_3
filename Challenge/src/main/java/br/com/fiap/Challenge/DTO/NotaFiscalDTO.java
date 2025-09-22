package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal; // 1. Importação adicionada
import java.util.Date;

@Data
public class NotaFiscalDTO {
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String numero;

    @NotBlank
    @Size(max = 5)
    private String serie;

    @NotBlank
    @Size(max = 2)
    private String modelo;

    @NotBlank
    @Size(max = 44)
    private String chaveAcesso;

    @NotNull(message = "A data de emissão é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataEmissao;

    @NotNull(message = "O valor total é obrigatório.")
    @Positive(message = "O valor total deve ser positivo.") // 3. Validação adicional (recomendado)
    private BigDecimal valorTotal; // 2. Tipo corrigido para BigDecimal

    @NotBlank
    @Size(max = 100)
    private String fornecedor;

    @NotBlank
    @Size(max = 14)
    private String cnpjFornecedor; // Pequena correção no nome do campo (Fornecedor)
}