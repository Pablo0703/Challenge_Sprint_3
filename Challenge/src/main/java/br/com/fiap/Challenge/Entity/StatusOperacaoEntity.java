package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_STATUS_OPERACAO")
@Getter
@Setter
public class StatusOperacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_operacao_seq_generator")
    @SequenceGenerator(name = "status_operacao_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_STATUS_OPERACAO", allocationSize = 1)
    @Column(name = "ID_STATUS_OPERACAO")
    private Long id;

    // ... outros campos


    @Column(name = "DESCRICAO", nullable = false, length = 50)
    private String descricao;

    @Column(name = "TIPO_MOVIMENTACAO", nullable = false, length = 20)
    private String tipoMovimentacao;
}
