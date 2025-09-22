package br.com.fiap.Challenge.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class MotociclistaDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    private String cnh;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataValidadeCnh;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @Email(message = "Formato de e-mail inválido")
    @Size(max = 100)
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    @Size(max = 1)
    private String ativo;

    @NotNull(message = "O endereço é obrigatório.")
    private Long enderecoId; // <-- CORREÇÃO AQUI: Trocamos o objeto pelo ID

}