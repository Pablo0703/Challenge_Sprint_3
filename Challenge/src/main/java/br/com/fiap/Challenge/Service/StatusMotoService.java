package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.StatusMotoDTO;
import br.com.fiap.Challenge.Entity.StatusMotoEntity;
import br.com.fiap.Challenge.Repository.StatusMotoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusMotoService {

    private final StatusMotoRepository repo;

    public StatusMotoService(StatusMotoRepository repo) {
        this.repo = repo;
    }

    public List<StatusMotoDTO> listarTodos() {
        // Busca todos os registros e os ordena pelo ID em ordem crescente.
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StatusMotoDTO buscarPorId(Long id) {
        StatusMotoEntity status = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status da moto não encontrado: id=" + id));
        return toDTO(status);
    }

    @Transactional
    public StatusMotoDTO salvar(StatusMotoDTO dto) {
        StatusMotoEntity statusEntity;
        if (dto.getId() != null) {
            // Se o ID existe no DTO, buscamos a entidade para atualização.
            statusEntity = repo.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Status da moto não encontrado para atualização: id=" + dto.getId()));
            // Atualizamos os campos da entidade existente com os dados do DTO.
            statusEntity.setDescricao(dto.getDescricao());
            statusEntity.setDisponivel(dto.getDisponivel());
        } else {
            // Se o ID não existe, criamos uma nova entidade para inserção.
            statusEntity = new StatusMotoEntity();
            statusEntity.setDescricao(dto.getDescricao());
            statusEntity.setDisponivel(dto.getDisponivel());
        }

        // Salvamos a entidade (o Spring/Hibernate decide se é INSERT ou UPDATE).
        StatusMotoEntity statusSalvo = repo.save(statusEntity);
        return toDTO(statusSalvo);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Status da moto não encontrado para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    private StatusMotoDTO toDTO(StatusMotoEntity entity) {
        StatusMotoDTO dto = new StatusMotoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setDisponivel(entity.getDisponivel());
        return dto;
    }
}