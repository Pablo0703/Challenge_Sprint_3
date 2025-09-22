package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.NotaFiscalDTO;
import br.com.fiap.Challenge.Entity.NotaFiscalEntity;
import br.com.fiap.Challenge.Repository.NotaFiscalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaFiscalService {

    private final NotaFiscalRepository repo;

    public NotaFiscalService(NotaFiscalRepository repo) {
        this.repo = repo;
    }

    public List<NotaFiscalDTO> listarTodos() {
        return repo.findAllByOrderByIdAsc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public NotaFiscalDTO buscarPorId(Long id) {
        NotaFiscalEntity nf = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota Fiscal não encontrada: id=" + id));
        return toDTO(nf);
    }

    public NotaFiscalDTO salvar(NotaFiscalDTO dto) {
        NotaFiscalEntity nfSalva = repo.save(toEntity(dto));
        return toDTO(nfSalva);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Nota Fiscal não encontrada para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // Métodos de mapeamento entre DTO e Entity
    private NotaFiscalDTO toDTO(NotaFiscalEntity entity) {
        NotaFiscalDTO dto = new NotaFiscalDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setSerie(entity.getSerie());
        dto.setModelo(entity.getModelo());
        dto.setChaveAcesso(entity.getChaveAcesso());
        dto.setDataEmissao(entity.getDataEmissao());
        dto.setValorTotal(entity.getValorTotal());
        dto.setFornecedor(entity.getFornecedor());
        dto.setCnpjFornecedor(entity.getCnpjFornecedor());
        return dto;
    }

    private NotaFiscalEntity toEntity(NotaFiscalDTO dto) {
        NotaFiscalEntity entity = new NotaFiscalEntity();
        entity.setId(dto.getId());
        entity.setNumero(dto.getNumero());
        entity.setSerie(dto.getSerie());
        entity.setModelo(dto.getModelo());
        entity.setChaveAcesso(dto.getChaveAcesso());
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setValorTotal(dto.getValorTotal());
        entity.setFornecedor(dto.getFornecedor());
        entity.setCnpjFornecedor(dto.getCnpjFornecedor());

        return entity;
    }
}