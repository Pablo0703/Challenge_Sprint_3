package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.EnderecoDTO;
import br.com.fiap.Challenge.DTO.FilialDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Entity.FilialEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import br.com.fiap.Challenge.Repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilialService {

    private final FilialRepository repo;
    private final EnderecoRepository enderecoRepository;

    public FilialService(FilialRepository repo, EnderecoRepository enderecoRepository) {
        this.repo = repo;
        this.enderecoRepository = enderecoRepository;
    }

    public List<FilialDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FilialDTO buscarPorId(Long id) {
        FilialEntity filial = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada: id=" + id));
        return toDTO(filial);
    }

    @Transactional
    public FilialDTO salvar(FilialDTO dto) {
        FilialEntity filialEntity;
        if (dto.getId() != null) {
            filialEntity = repo.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada: id=" + dto.getId()));
            filialEntity.setNome(dto.getNome());
            filialEntity.setCnpj(dto.getCnpj());
            filialEntity.setTelefone(dto.getTelefone());
            filialEntity.setEmail(dto.getEmail());
            filialEntity.setAtivo(dto.getAtivo());
        } else {
            filialEntity = new FilialEntity();
            filialEntity.setNome(dto.getNome());
            filialEntity.setCnpj(dto.getCnpj());
            filialEntity.setTelefone(dto.getTelefone());
            filialEntity.setEmail(dto.getEmail());
            filialEntity.setAtivo(dto.getAtivo());
        }

        if (dto.getIdEndereco() != null) {
            EnderecoEntity enderecoEntity = enderecoRepository.findById(dto.getIdEndereco())
                    .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado para o ID: " + dto.getIdEndereco()));
            filialEntity.setEndereco(enderecoEntity);
        } else {
            filialEntity.setEndereco(null);
        }

        FilialEntity filialSalva = repo.save(filialEntity);
        return toDTO(filialSalva);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Filial não encontrada para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    private FilialDTO toDTO(FilialEntity entity) {
        FilialDTO dto = new FilialDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setAtivo(entity.getAtivo());

        if (entity.getEndereco() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(entity.getEndereco().getId());
            enderecoDTO.setLogradouro(entity.getEndereco().getLogradouro());
            enderecoDTO.setNumero(entity.getEndereco().getNumero());
            enderecoDTO.setComplemento(entity.getEndereco().getComplemento());
            enderecoDTO.setBairro(entity.getEndereco().getBairro());
            enderecoDTO.setCep(entity.getEndereco().getCep());
            enderecoDTO.setCidade(entity.getEndereco().getCidade());
            enderecoDTO.setEstado(entity.getEndereco().getEstado());
            enderecoDTO.setPais(entity.getEndereco().getPais());
            dto.setEndereco(enderecoDTO);
        }
        return dto;
    }
}