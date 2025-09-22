package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.EnderecoDTO;
import br.com.fiap.Challenge.Entity.EnderecoEntity;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository repo;

    public EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    public List<EnderecoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EnderecoDTO buscarPorId(Long id) {
        EnderecoEntity e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: id=" + id));
        return toDTO(e);
    }

    public EnderecoDTO salvar(EnderecoDTO dto) {
        EnderecoEntity salvo = repo.save(toEntity(dto));
        return toDTO(salvo);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // mapeamentos
    private EnderecoDTO toDTO(EnderecoEntity e) {
        EnderecoDTO d = new EnderecoDTO();
        d.setId(e.getId());
        d.setLogradouro(e.getLogradouro());
        d.setNumero(e.getNumero());
        d.setComplemento(e.getComplemento());
        d.setBairro(e.getBairro());
        d.setCep(e.getCep());
        d.setCidade(e.getCidade());
        d.setEstado(e.getEstado());
        d.setPais(e.getPais());
        return d;
    }

    private EnderecoEntity toEntity(EnderecoDTO d) {
        EnderecoEntity e = new EnderecoEntity();
        e.setId(d.getId());
        e.setLogradouro(d.getLogradouro());
        e.setNumero(d.getNumero());
        e.setComplemento(d.getComplemento());
        e.setBairro(d.getBairro());
        e.setCep(d.getCep());
        e.setCidade(d.getCidade());
        e.setEstado(d.getEstado());
        e.setPais(d.getPais());
        return e;
    }
}
