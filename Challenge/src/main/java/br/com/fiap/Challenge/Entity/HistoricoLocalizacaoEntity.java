package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime; // 1. Importado o tipo de data moderno

@Entity
@Table(name = "MOTTU_HISTORICO_LOCALIZACAO")
@Getter
@Setter
public class HistoricoLocalizacaoEntity {

    @Id
    // 2. Adicionado para o banco de dados gerar o ID automaticamente
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_seq_generator")
    @SequenceGenerator(name = "historico_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_HISTORICO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOTO", nullable = false)
    private MotoEntity moto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOTOCICLISTA")
    private MotociclistaEntity motociclista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ZONA_PATIO")
    private ZonaPatioEntity zonaPatio;

    @Column(name = "DATA_HORA_SAIDA", nullable = false)
    private LocalDateTime dataHoraSaida; // 3. Tipo alterado para LocalDateTime

    @Column(name = "DATA_HORA_ENTRADA")
    private LocalDateTime dataHoraEntrada; // 4. Tipo alterado para LocalDateTime

    @Column(name = "KM_RODADOS", precision = 10, scale = 2)
    private BigDecimal kmRodados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATUS_OPERACAO")
    private StatusOperacaoEntity statusOperacao;
}