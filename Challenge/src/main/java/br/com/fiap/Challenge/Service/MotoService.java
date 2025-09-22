package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.Entity.MotoEntity;
import br.com.fiap.Challenge.Entity.NotaFiscalEntity;
import br.com.fiap.Challenge.Entity.StatusMotoEntity;
import br.com.fiap.Challenge.Entity.TipoMotoEntity;
import br.com.fiap.Challenge.Repository.MotoRepository;
import br.com.fiap.Challenge.Repository.TipoMotoRepository;
import br.com.fiap.Challenge.Repository.StatusMotoRepository;
import br.com.fiap.Challenge.Repository.NotaFiscalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoService {

    private final MotoRepository repo;
    private final TipoMotoRepository tipoMotoRepo;
    private final StatusMotoRepository statusMotoRepo;
    private final NotaFiscalRepository notaFiscalRepo;

    public MotoService(MotoRepository repo, TipoMotoRepository tipoMotoRepo, StatusMotoRepository statusMotoRepo, NotaFiscalRepository notaFiscalRepo) {
        this.repo = repo;
        this.tipoMotoRepo = tipoMotoRepo;
        this.statusMotoRepo = statusMotoRepo;
        this.notaFiscalRepo = notaFiscalRepo;
    }

    public List<MotoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MotoDTO buscarPorId(Long id) {
        MotoEntity moto = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada: id=" + id));
        return toDTO(moto);
    }

    // Dentro da classe MotoService
    public MotoDTO salvar(MotoDTO dto) {
        // 1. Verifica a Placa
        Optional<MotoEntity> motoExistenteComPlaca = repo.findByPlaca(dto.getPlaca());
        if (motoExistenteComPlaca.isPresent() && !motoExistenteComPlaca.get().getId().equals(dto.getId())) {
            // Se encontrou uma moto com a mesma placa E o ID é diferente do que estamos a salvar,
            // então é uma duplicata inválida.
            throw new DataIntegrityViolationException("Placa '" + dto.getPlaca() + "' já cadastrada!");
        }

        // 2. Verifica o Chassi
        Optional<MotoEntity> motoExistenteComChassi = repo.findByChassi(dto.getChassi());
        if (motoExistenteComChassi.isPresent() && !motoExistenteComChassi.get().getId().equals(dto.getId())) {
            // A mesma lógica para o chassi
            throw new DataIntegrityViolationException("Chassi '" + dto.getChassi() + "' já cadastrado!");
        }

        // 3. Se passou nas validações, converte para entidade e salva
        MotoEntity entity = toEntity(dto);
        MotoEntity motoSalva = repo.save(entity);
        return toDTO(motoSalva);
    }

    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Moto não encontrada para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    private MotoDTO toDTO(MotoEntity entity) {
        MotoDTO dto = new MotoDTO();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setAnoFabricacao(entity.getAnoFabricacao());
        dto.setAnoModelo(entity.getAnoModelo());
        dto.setChassi(entity.getChassi());
        dto.setCilindrada(entity.getCilindrada());
        dto.setCor(entity.getCor());
        dto.setDataAquisicao(entity.getDataAquisicao());
        dto.setValorAquisicao(entity.getValorAquisicao());

        if (entity.getTipo() != null) {
            dto.setTipo(entity.getTipo().getDescricao());
        }
        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().getDescricao());
        }
        if (entity.getNotaFiscal() != null) {
            dto.setNotaFiscalId(entity.getNotaFiscal().getId());
        }

        return dto;
    }

    private MotoEntity toEntity(MotoDTO dto) {
        MotoEntity entity = new MotoEntity();
        entity.setId(dto.getId());
        entity.setPlaca(dto.getPlaca());
        entity.setModelo(dto.getModelo());
        entity.setAnoFabricacao(dto.getAnoFabricacao());
        entity.setAnoModelo(dto.getAnoModelo());
        entity.setChassi(dto.getChassi());
        entity.setCilindrada(dto.getCilindrada());
        entity.setCor(dto.getCor());
        entity.setDataAquisicao(dto.getDataAquisicao());
        entity.setValorAquisicao(dto.getValorAquisicao());

        // CORREÇÃO FINAL ESTÁ AQUI: Usando o campo "tipo" (String) do DTO
        if (dto.getTipo() != null) {
            TipoMotoEntity tipo = tipoMotoRepo.findByDescricao(dto.getTipo())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de moto não encontrado: " + dto.getTipo()));
            entity.setTipo(tipo);
        }

        if (dto.getStatus() != null) {
            StatusMotoEntity status = statusMotoRepo.findByDescricao(dto.getStatus())
                    .orElseThrow(() -> new EntityNotFoundException("Status de moto não encontrado: " + dto.getStatus()));
            entity.setStatus(status);
        }
        if (dto.getNotaFiscalId() != null) {
            NotaFiscalEntity notaFiscal = notaFiscalRepo.findById(dto.getNotaFiscalId())
                    .orElseThrow(() -> new EntityNotFoundException("Nota fiscal não encontrada: id=" + dto.getNotaFiscalId()));
            entity.setNotaFiscal(notaFiscal);
        }

        return entity;
    }
}