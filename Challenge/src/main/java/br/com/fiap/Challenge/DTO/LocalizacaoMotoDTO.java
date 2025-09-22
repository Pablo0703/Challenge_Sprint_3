package br.com.fiap.Challenge.DTO;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class LocalizacaoMotoDTO {

    private Long id;

    @NotNull
    private Long idMoto;

    @NotNull
    private Long idZona;

    @NotNull
    private Date dataHoraEntrada;

    private Date dataHoraSaida;

    private MotoDTO moto;
    private ZonaPatioDTO zonaPatio;
}