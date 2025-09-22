package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "MOTTU_MOTO")
@Getter
@Setter
public class MotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto_seq_generator")
    @SequenceGenerator(name = "moto_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_MOTO", allocationSize = 1)
    @Column(name = "ID_MOTO")
    private Long id;

    // Relacionamento principal e correto para o Tipo
    @ManyToOne(fetch = FetchType.LAZY) // Adicionado para melhor performance
    @JoinColumn(name = "ID_TIPO")
    private TipoMotoEntity tipo;

    // Relacionamento principal e correto para o Status
    @ManyToOne(fetch = FetchType.LAZY) // Adicionado para melhor performance
    @JoinColumn(name = "ID_STATUS")
    private StatusMotoEntity status;

    // Mapeamento da coluna duplicada, agora apenas para leitura
    @Column(name = "ID_TIPO_MOTO", insertable = false, updatable = false)
    private Long idTipoMoto;

    // Mapeamento da coluna duplicada, agora apenas para leitura
    @Column(name = "ID_STATUS_MOTO", insertable = false, updatable = false)
    private Long idStatusMoto;

    @Column(name = "PLACA", nullable = false, unique = true, length = 8)
    private String placa;

    @Column(name = "MODELO", nullable = false, length = 50)
    private String modelo;

    @Column(name = "ANO_FABRICACAO", nullable = false)
    private Integer anoFabricacao;

    @Column(name = "ANO_MODELO", nullable = false)
    private Integer anoModelo;

    @Column(name = "CHASSI", nullable = false, unique = true)
    private String chassi;

    @Column(name = "CILINDRADA")
    private Integer cilindrada;

    @Column(name = "COR", length = 30)
    private String cor;

    @Column(name = "DATA_AQUISICAO", nullable = false)
    private LocalDate dataAquisicao;

    @Column(name = "VALOR_AQUISICAO")
    private BigDecimal valorAquisicao;

    @ManyToOne(fetch = FetchType.LAZY) // Adicionado para melhor performance
    @JoinColumn(name = "ID_NF")
    private NotaFiscalEntity notaFiscal;
}