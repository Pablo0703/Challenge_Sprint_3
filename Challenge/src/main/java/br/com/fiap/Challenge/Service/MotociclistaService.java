package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.MotociclistaDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Entity.MotociclistaEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import br.com.fiap.Challenge.Repository.MotociclistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotociclistaService {

    private final MotociclistaRepository motociclistaRepo;
    private final EnderecoRepository enderecoRepo;

    public MotociclistaService(MotociclistaRepository motociclistaRepo, EnderecoRepository enderecoRepo) {
        this.motociclistaRepo = motociclistaRepo;
        this.enderecoRepo = enderecoRepo;
    }

    public List<MotociclistaDTO> listarTodos() {
        // CORREÇÃO AQUI: Use o novo método para ordenar
        return motociclistaRepo.findAllByOrderByIdAsc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MotociclistaDTO buscarPorId(Long id) {
        MotociclistaEntity motociclista = motociclistaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Motociclista não encontrado: id=" + id));
        return toDTO(motociclista);
    }

    public MotociclistaDTO salvar(MotociclistaDTO dto) {
        MotociclistaEntity motociclistaSalvo = motociclistaRepo.save(toEntity(dto));
        return toDTO(motociclistaSalvo);
    }

    public void excluir(Long id) {
        if (!motociclistaRepo.existsById(id)) {
            throw new EntityNotFoundException("Motociclista não encontrado para excluir: id=" + id);
        }
        motociclistaRepo.deleteById(id);
    }

    private MotociclistaDTO toDTO(MotociclistaEntity entity) {
        MotociclistaDTO dto = new MotociclistaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setCnh(entity.getCnh());
        dto.setDataValidadeCnh(entity.getDataValidadeCnh());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setAtivo(entity.getAtivo());

        if (entity.getEndereco() != null) {
            dto.setEnderecoId(entity.getEndereco().getId());
        }

        return dto;
    }

    private MotociclistaEntity toEntity(MotociclistaDTO dto) {
        MotociclistaEntity entity = new MotociclistaEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setCnh(dto.getCnh());
        entity.setDataValidadeCnh(dto.getDataValidadeCnh());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setAtivo(dto.getAtivo());

        if (dto.getEnderecoId() != null) {
            EnderecoEntity endereco = enderecoRepo.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + dto.getEnderecoId()));
            entity.setEndereco(endereco);
        }

        return entity;
    }
}