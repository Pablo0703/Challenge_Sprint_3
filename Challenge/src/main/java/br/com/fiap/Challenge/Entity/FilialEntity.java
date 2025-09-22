package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_FILIAL")
@Getter
@Setter
public class FilialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial_seq_generator")
    @SequenceGenerator(name = "filial_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_FILIAL", allocationSize = 1)
    @Column(name = "ID_FILIAL")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "CNPJ", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ATIVO", length = 1)
    private String ativo;

    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO")
    private EnderecoEntity endereco;
}