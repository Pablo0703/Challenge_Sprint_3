package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "MOTTU_MOTOCICLISTA")
@Getter
@Setter
public class MotociclistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motociclista_seq_generator")
    @SequenceGenerator(name = "motociclista_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_MOTOCICLISTA", allocationSize = 1)
    @Column(name = "ID_MOTOCICLISTA")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "CPF", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "CNH", nullable = false, length = 20, unique = true)
    private String cnh;

    @Column(name = "DATA_VALIDADE_CNH", nullable = false)
    private LocalDate dataValidadeCnh;

    @Column(name = "TELEFONE", nullable = false, length = 20)
    private String telefone;

    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "ATIVO", length = 1) // Recomendo voltar com o length=1
    private String ativo; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ENDERECO")
    private EnderecoEntity endereco;
}