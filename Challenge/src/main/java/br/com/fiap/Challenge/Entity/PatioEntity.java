package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_PATIO")
@Getter
@Setter
public class PatioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patio_seq_generator")
    @SequenceGenerator(name = "patio_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_PATIO", allocationSize = 1)
    @Column(name = "ID_PATIO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "AREA_M2")
    private Double areaM2;

    @Column(name = "CAPACIDADE", nullable = false)
    private Integer capacidade;

    @Column(name = "ATIVO", length = 1)
    private String ativo = "S";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FILIAL", nullable = false)
    private FilialEntity filial;
}