package br.com.fiap.Challenge.DTO;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LocalizacaoMotoDTO {

    private Long id;

    @NotNull
    private Long idMoto;

    @NotNull
    private Long idZona;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dataHoraEntrada;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dataHoraSaida;

    private MotoDTO moto;
    private ZonaPatioDTO zonaPatio;
}