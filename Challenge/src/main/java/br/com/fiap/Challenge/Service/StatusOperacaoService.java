package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.StatusOperacaoDTO;
import br.com.fiap.Challenge.Entity.StatusOperacaoEntity;
import br.com.fiap.Challenge.Repository.StatusOperacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusOperacaoService {

    private final StatusOperacaoRepository repo;

    public StatusOperacaoService(StatusOperacaoRepository repo) {
        this.repo = repo;
    }

    public List<StatusOperacaoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public StatusOperacaoDTO buscarPorId(Long id) {
        StatusOperacaoEntity status = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status de operação não encontrado: id=" + id));
        return toDTO(status);
    }

    public StatusOperacaoDTO salvar(StatusOperacaoDTO dto) {
        StatusOperacaoEntity statusSalvo = repo.save(toEntity(dto));
        return toDTO(statusSalvo);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Status de operação não encontrado para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // Métodos de mapeamento entre DTO e Entity
    private StatusOperacaoDTO toDTO(StatusOperacaoEntity entity) {
        StatusOperacaoDTO dto = new StatusOperacaoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setTipoMovimentacao(entity.getTipoMovimentacao());
        return dto;
    }

    private StatusOperacaoEntity toEntity(StatusOperacaoDTO dto) {
        StatusOperacaoEntity entity = new StatusOperacaoEntity();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setTipoMovimentacao(dto.getTipoMovimentacao());
        return entity;
    }
}