package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_TIPO_MOTO")
@Getter
@Setter
public class TipoMotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_moto_seq_generator")
    @SequenceGenerator(name = "tipo_moto_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_TIPO_MOTO", allocationSize = 1)
    @Column(name = "ID_TIPO")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 60)
    private String descricao;

    @Column(name = "CATEGORIA", nullable = false, length = 30)
    private String categoria;
}