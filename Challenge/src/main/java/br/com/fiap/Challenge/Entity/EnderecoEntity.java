package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTTU_ENDERECO")
@Getter
@Setter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq_generator")
    @SequenceGenerator(name = "endereco_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_ENDERECO", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "LOGRADOURO", nullable = false, length = 50)
    private String logradouro;

    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    @Column(name = "COMPLEMENTO", length = 50)
    private String complemento;

    @Column(name = "BAIRRO", nullable = false, length = 50)
    private String bairro;

    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    @Column(name = "CIDADE", nullable = false, length = 50)
    private String cidade;

    @Column(name = "ESTADO", nullable = false, length = 2)
    private String estado;

    @Column(name = "PAIS", length = 50)
    private String pais;
}