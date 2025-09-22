package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_STATUS_MOTO")
@Getter
@Setter
public class StatusMotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_moto_seq_generator")
    @SequenceGenerator(name = "status_moto_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_STATUS_MOTO", allocationSize = 1)
    @Column(name = "ID_STATUS")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 20)
    private String descricao;

    @Column(name = "DISPONIVEL", length = 1)
    private String disponivel;
}