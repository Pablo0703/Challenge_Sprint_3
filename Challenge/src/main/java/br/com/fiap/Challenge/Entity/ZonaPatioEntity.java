package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_ZONA_PATIO")
@Getter
@Setter
public class ZonaPatioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zona_patio_seq_generator")
    @SequenceGenerator(name = "zona_patio_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_ZONA_PATIO", allocationSize = 1)
    @Column(name = "ID_ZONA")
    private Long id;

    @Column(name = "NOME_ZONA", nullable = false, length = 30)
    private String nomeZona;

    @Column(name = "TIPO_ZONA", nullable = false, length = 20)
    private String tipoZona;

    @Column(name = "CAPACIDADE", nullable = false)
    private Integer capacidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PATIO", nullable = false)
    private PatioEntity patio;
}