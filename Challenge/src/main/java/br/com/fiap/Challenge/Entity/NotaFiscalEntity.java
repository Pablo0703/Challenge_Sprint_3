package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal; // 1. Importe a classe BigDecimal
import java.util.Date;

@Entity
@Table(name = "MOTTU_NF")
@Getter
@Setter
public class NotaFiscalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_seq_generator")
    @SequenceGenerator(name = "nota_fiscal_seq_generator", sequenceName = "RM556834.SEQ_MOTTU_NF", allocationSize = 1)
    @Column(name = "ID_NF")
    private Long id;

    @Column(name = "NUMERO", nullable = false, length = 20)
    private String numero;

    @Column(name = "SERIE", nullable = false, length = 5)
    private String serie;

    @Column(name = "MODELO", nullable = false, length = 2)
    private String modelo;

    @Column(name = "CHAVE_ACESSO", unique = true, length = 44)
    private String chaveAcesso;

    @Column(name = "DATA_EMISSAO", nullable = false)
    private Date dataEmissao;

    @Column(name = "VALOR_TOTAL", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorTotal; // 2. Altere o tipo de Double para BigDecimal

    @Column(name = "FORNECEDOR", nullable = false, length = 100)
    private String fornecedor;

    @Column(name = "CNPJ_FORNECEDOR", nullable = false, length = 14)
    private String cnpjFornecedor;

}