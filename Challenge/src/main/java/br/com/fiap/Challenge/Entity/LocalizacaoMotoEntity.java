package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "MOTTU_LOCALIZACAO_MOTO")
@Getter
@Setter
public class LocalizacaoMotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localizacao_seq")
    @SequenceGenerator(name = "localizacao_seq", sequenceName = "SEQ_LOCALIZACAO", allocationSize = 1)
    @Column(name = "ID_LOCALIZACAO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_MOTO", nullable = false)
    private br.com.fiap.Challenge.Entity.MotoEntity moto;

    @ManyToOne
    @JoinColumn(name = "ID_ZONA", nullable = false)
    private br.com.fiap.Challenge.Entity.ZonaPatioEntity zonaPatio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_ENTRADA", nullable = false)
    private Date dataHoraEntrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_SAIDA")
    private Date dataHoraSaida;
}