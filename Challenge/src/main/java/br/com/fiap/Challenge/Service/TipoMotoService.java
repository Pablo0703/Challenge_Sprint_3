package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.TipoMotoDTO;
import br.com.fiap.Challenge.Entity.TipoMotoEntity;
import br.com.fiap.Challenge.Repository.TipoMotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoMotoService {

    private final TipoMotoRepository repo;

    public TipoMotoService(TipoMotoRepository repo) {
        this.repo = repo;
    }

    public List<TipoMotoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TipoMotoDTO buscarPorId(Long id) {
        TipoMotoEntity tipo = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de moto não encontrado: id=" + id));
        return toDTO(tipo);
    }

    public TipoMotoDTO salvar(TipoMotoDTO dto) {
        TipoMotoEntity tipoSalvo = repo.save(toEntity(dto));
        return toDTO(tipoSalvo);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Tipo de moto não encontrado para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // Mapping methods between DTO and Entity
    private TipoMotoDTO toDTO(TipoMotoEntity entity) {
        TipoMotoDTO dto = new TipoMotoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    private TipoMotoEntity toEntity(TipoMotoDTO dto) {
        TipoMotoEntity entity = new TipoMotoEntity();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}