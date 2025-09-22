package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.LocalizacaoMotoDTO;
import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Entity.LocalizacaoMotoEntity;
import br.com.fiap.Challenge.Repository.LocalizacaoMotoRepository;
import br.com.fiap.Challenge.Repository.MotoRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoMotoService {

    private final LocalizacaoMotoRepository repo;
    private final MotoRepository motoRepo;
    private final ZonaPatioRepository zonaPatioRepo;

    public LocalizacaoMotoService(LocalizacaoMotoRepository repo, MotoRepository motoRepo, ZonaPatioRepository zonaPatioRepo) {
        this.repo = repo;
        this.motoRepo = motoRepo;
        this.zonaPatioRepo = zonaPatioRepo;
    }

    public List<LocalizacaoMotoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LocalizacaoMotoDTO buscarPorId(Long id) {
        LocalizacaoMotoEntity localizacao = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localização da moto não encontrada: id=" + id));
        return toDTO(localizacao);
    }

    public LocalizacaoMotoDTO salvar(LocalizacaoMotoDTO dto) {
        LocalizacaoMotoEntity localizacaoSalva = repo.save(toEntity(dto));
        return toDTO(localizacaoSalva);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Localização da moto não encontrada para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // Mapping methods between DTO and Entity
    private LocalizacaoMotoDTO toDTO(LocalizacaoMotoEntity entity) {
        LocalizacaoMotoDTO dto = new LocalizacaoMotoDTO();
        dto.setId(entity.getId());
        dto.setDataHoraEntrada(entity.getDataHoraEntrada());
        dto.setDataHoraSaida(entity.getDataHoraSaida());

        if (entity.getMoto() != null) {
            dto.setIdMoto(entity.getMoto().getId());
            // CORREÇÃO: Usando setter methods to populate the DTO object
            MotoDTO motoDTO = new MotoDTO();
            motoDTO.setId(entity.getMoto().getId());
            motoDTO.setPlaca(entity.getMoto().getPlaca());
            dto.setMoto(motoDTO);
        }
        if (entity.getZonaPatio() != null) {
            dto.setIdZona(entity.getZonaPatio().getId());
            // CORREÇÃO: Usando setter methods to populate the DTO object
            ZonaPatioDTO zonaPatioDTO = new ZonaPatioDTO();
            zonaPatioDTO.setId(entity.getZonaPatio().getId());
            zonaPatioDTO.setNomeZona(entity.getZonaPatio().getNomeZona());
            dto.setZonaPatio(zonaPatioDTO);
        }
        return dto;
    }

    private LocalizacaoMotoEntity toEntity(LocalizacaoMotoDTO dto) {
        LocalizacaoMotoEntity entity = new LocalizacaoMotoEntity();
        entity.setId(dto.getId());
        entity.setDataHoraEntrada(dto.getDataHoraEntrada());
        entity.setDataHoraSaida(dto.getDataHoraSaida());

        if (dto.getIdMoto() != null) {
            entity.setMoto(motoRepo.findById(dto.getIdMoto())
                    .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada: id=" + dto.getIdMoto())));
        }
        if (dto.getIdZona() != null) {
            entity.setZonaPatio(zonaPatioRepo.findById(dto.getIdZona())
                    .orElseThrow(() -> new EntityNotFoundException("Zona de pátio não encontrada: id=" + dto.getIdZona())));
        }

        return entity;
    }
}