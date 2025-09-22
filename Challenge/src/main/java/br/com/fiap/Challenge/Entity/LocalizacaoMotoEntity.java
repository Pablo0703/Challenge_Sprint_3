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