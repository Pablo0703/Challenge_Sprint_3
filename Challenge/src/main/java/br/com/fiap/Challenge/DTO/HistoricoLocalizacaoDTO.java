package br.com.fiap.Challenge.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HistoricoLocalizacaoDTO {

    private Long id;

    @NotNull(message = "A data de saída é obrigatória.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHoraSaida;

    // A data de entrada não é obrigatória, conforme o banco de dados
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHoraEntrada;

    private BigDecimal kmRodados;

    // --- CAMPOS SIMPLIFICADOS PARA RECEBER IDs DO FORMULÁRIO ---
    @NotNull(message = "A moto é obrigatória.")
    private Long motoId;

    private Long motociclistaId; // Pode ser nulo
    private Long zonaPatioId;    // Pode ser nulo
    private Long statusOperacaoId; // Pode ser nulo


    // --- OBJETOS DTO PARA EXIBIR DADOS (NÃO RECEBIDOS DO FORMULÁRIO) ---
    private MotoDTO moto;
    private MotociclistaDTO motociclista;
    private ZonaPatioDTO zonaPatio;
    private StatusOperacaoDTO statusOperacao;
}