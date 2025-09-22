package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.FilialDTO;
import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.Entity.FilialEntity;
import br.com.fiap.Challenge.Entity.PatioEntity;
import br.com.fiap.Challenge.Repository.FilialRepository;
import br.com.fiap.Challenge.Repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatioService {

    private final PatioRepository patioRepository;
    private final FilialRepository filialRepository;

    public PatioService(PatioRepository patioRepository, FilialRepository filialRepository) {
        this.patioRepository = patioRepository;
        this.filialRepository = filialRepository;
    }

    @Transactional(readOnly = true)
    public List<PatioDTO> listarTodos() {
        return patioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatioDTO buscarPorId(Long id) {
        PatioEntity entity = patioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado. ID: " + id));
        return toDTO(entity);
    }

    @Transactional
    public PatioDTO salvar(PatioDTO dto) {
        PatioEntity entity = toEntity(dto);
        PatioEntity salvo = patioRepository.save(entity);
        return toDTO(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        if (!patioRepository.existsById(id)) {
            throw new EntityNotFoundException("Pátio não encontrado para exclusão. ID: " + id);
        }
        patioRepository.deleteById(id);
    }

    private PatioDTO toDTO(PatioEntity entity) {
        PatioDTO dto = new PatioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setAreaM2(entity.getAreaM2());
        dto.setCapacidade(entity.getCapacidade());
        dto.setAtivo(entity.getAtivo());
        dto.setIdFilial(entity.getFilial().getId()); // Mantém o ID

        // Lógica para carregar os dados da filial (resolve o Lazy Loading)
        if (entity.getFilial() != null) {
            FilialDTO filialDTO = new FilialDTO();
            filialDTO.setId(entity.getFilial().getId());
            filialDTO.setNome(entity.getFilial().getNome());
            dto.setFilial(filialDTO);
        }
        return dto;
    }

    private PatioEntity toEntity(PatioDTO dto) {
        PatioEntity entity = new PatioEntity();
        // Para edição, busca a entidade existente
        if (dto.getId() != null) {
            entity = patioRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado. ID: " + dto.getId()));
        }
        entity.setNome(dto.getNome());
        entity.setAreaM2(dto.getAreaM2());
        entity.setCapacidade(dto.getCapacidade());
        entity.setAtivo(dto.getAtivo());

        if (dto.getIdFilial() != null) {
            FilialEntity filial = filialRepository.findById(dto.getIdFilial())
                    .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada. ID: " + dto.getIdFilial()));
            entity.setFilial(filial);
        }
        return entity;
    }
}
