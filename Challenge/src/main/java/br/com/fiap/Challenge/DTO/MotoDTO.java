package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MotoDTO {

    private Long id;

    // Assumindo que Tipo e Status são Strings por enquanto
    @NotBlank
    private String tipo;

    @NotBlank
    private String status;

    @NotBlank
    @Size(min = 7, max = 8)
    private String placa;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(1980)
    private Integer anoFabricacao;

    @NotNull
    @Min(1980)
    private Integer anoModelo;

    @NotBlank
    private String chassi;

    @NotNull
    private Integer cilindrada;

    private String cor;

    @NotNull(message = "A data de aquisição é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // <-- AQUI ESTÁ A CORREÇÃO
    private LocalDate dataAquisicao;

    @NotNull
    @Positive
    private BigDecimal valorAquisicao;

    @NotNull
    private Long notaFiscalId; // Assumindo que o formulário envia o ID da nota fiscal
}