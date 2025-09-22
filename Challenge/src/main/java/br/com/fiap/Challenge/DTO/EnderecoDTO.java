package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String logradouro;

    @NotBlank
    @Size(max = 10)
    private String numero;

    @Size(max = 50)
    private String complemento;

    @NotBlank
    @Size(max = 50)
    private String bairro;

    @NotBlank
    @Size(max = 10)
    private String cep;

    @NotBlank
    @Size(max = 50)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String estado;

    @Size(max = 50)
    private String pais;
}
