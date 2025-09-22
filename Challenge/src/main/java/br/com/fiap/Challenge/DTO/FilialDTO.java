package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FilialDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 14)
    private String cnpj;

    @Size(max = 20)
    private String telefone;

    @Size(max = 100)
    private String email;

    @Size(max = 1)
    private String ativo;

    @NotNull(message = "ID do endereço é obrigatório")
    private Long idEndereco;

    private EnderecoDTO endereco;
}