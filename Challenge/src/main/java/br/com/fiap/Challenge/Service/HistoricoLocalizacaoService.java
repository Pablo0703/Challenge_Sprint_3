package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.*;
import br.com.fiap.Challenge.Entity.*;
import br.com.fiap.Challenge.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoLocalizacaoService {

    private final HistoricoLocalizacaoRepository repo;
    private final MotoRepository motoRepo;
    private final MotociclistaRepository motociclistaRepo;
    private final ZonaPatioRepository zonaPatioRepo;
    private final StatusOperacaoRepository statusOperacaoRepo;

    // Construtor para injeção de dependências
    public HistoricoLocalizacaoService(HistoricoLocalizacaoRepository repo, MotoRepository motoRepo, MotociclistaRepository motociclistaRepo, ZonaPatioRepository zonaPatioRepo, StatusOperacaoRepository statusOperacaoRepo) {
        this.repo = repo;
        this.motoRepo = motoRepo;
        this.motociclistaRepo = motociclistaRepo;
        this.zonaPatioRepo = zonaPatioRepo;
        this.statusOperacaoRepo = statusOperacaoRepo;
    }

    @Transactional(readOnly = true)
    public List<HistoricoLocalizacaoDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HistoricoLocalizacaoDTO buscarPorId(Long id) {
        return repo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado: id=" + id));
    }

    /**
     * DOCUMENTAÇÃO: Salva um novo histórico ou atualiza um existente.
     * Inclui a validação de negócio para as datas.
     */
    @Transactional
    public HistoricoLocalizacaoDTO salvar(HistoricoLocalizacaoDTO dto) {
        // 1. VALIDAÇÃO DA REGRA DE NEGÓCIO: Data de entrada não pode ser anterior à de saída.
        if (dto.getDataHoraEntrada() != null && dto.getDataHoraEntrada().isBefore(dto.getDataHoraSaida())) {
            throw new IllegalArgumentException("Erro: A data de entrada não pode ser anterior à data de saída.");
        }

        // 2. Converte o DTO para uma Entidade, buscando as associações pelos IDs.
        HistoricoLocalizacaoEntity entity = toEntity(dto);

        // 3. Salva a entidade no banco de dados.
        HistoricoLocalizacaoEntity historicoSalvo = repo.save(entity);

        // 4. Retorna o DTO da entidade salva para o controller.
        return toDTO(historicoSalvo);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Histórico não encontrado para excluir: id=" + id);
        }
        repo.deleteById(id);
    }

    // --- MÉTODOS DE CONVERSÃO ---

    /**
     * Converte uma Entidade para DTO, populando os objetos aninhados para exibição.
     */
    // Dentro de HistoricoLocalizacaoService.java

    private HistoricoLocalizacaoDTO toDTO(HistoricoLocalizacaoEntity entity) {
        HistoricoLocalizacaoDTO dto = new HistoricoLocalizacaoDTO();
        dto.setId(entity.getId());
        dto.setDataHoraSaida(entity.getDataHoraSaida());
        dto.setDataHoraEntrada(entity.getDataHoraEntrada());
        dto.setKmRodados(entity.getKmRodados());

        if (entity.getMoto() != null) {
            dto.setMotoId(entity.getMoto().getId());

            // Esta lógica de conversão manual está correta.
            MotoDTO motoDTO = new MotoDTO();
            motoDTO.setId(entity.getMoto().getId());
            motoDTO.setPlaca(entity.getMoto().getPlaca());
            dto.setMoto(motoDTO);
        }

        if (entity.getMotociclista() != null) {
            dto.setMotociclistaId(entity.getMotociclista().getId());
            MotociclistaDTO motociclistaDTO = new MotociclistaDTO();
            motociclistaDTO.setId(entity.getMotociclista().getId());
            motociclistaDTO.setNome(entity.getMotociclista().getNome());
            dto.setMotociclista(motociclistaDTO);
        }

        if (entity.getZonaPatio() != null) {
            dto.setZonaPatioId(entity.getZonaPatio().getId());
            ZonaPatioDTO zonaPatioDTO = new ZonaPatioDTO();
            zonaPatioDTO.setId(entity.getZonaPatio().getId());
            zonaPatioDTO.setNomeZona(entity.getZonaPatio().getNomeZona());
            dto.setZonaPatio(zonaPatioDTO);
        }

        if (entity.getStatusOperacao() != null) {
            dto.setStatusOperacaoId(entity.getStatusOperacao().getId());
            StatusOperacaoDTO statusDTO = new StatusOperacaoDTO();
            statusDTO.setId(entity.getStatusOperacao().getId());
            statusDTO.setDescricao(entity.getStatusOperacao().getDescricao());
            dto.setStatusOperacao(statusDTO);
        }

        return dto;
    }

    /**
     * Converte um DTO para uma Entidade, buscando as entidades relacionadas pelos IDs.
     */
    private HistoricoLocalizacaoEntity toEntity(HistoricoLocalizacaoDTO dto) {
        // Se for uma atualização, busca a entidade existente. Se for novo, cria uma nova.
        HistoricoLocalizacaoEntity entity = dto.getId() != null ? repo.findById(dto.getId()).orElse(new HistoricoLocalizacaoEntity()) : new HistoricoLocalizacaoEntity();

        entity.setDataHoraSaida(dto.getDataHoraSaida());
        entity.setDataHoraEntrada(dto.getDataHoraEntrada());
        entity.setKmRodados(dto.getKmRodados());

        // Busca e associa as entidades relacionadas usando os IDs do DTO
        MotoEntity moto = motoRepo.findById(dto.getMotoId())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada: id=" + dto.getMotoId()));
        entity.setMoto(moto);

        if (dto.getMotociclistaId() != null) {
            MotociclistaEntity motociclista = motociclistaRepo.findById(dto.getMotociclistaId())
                    .orElseThrow(() -> new EntityNotFoundException("Motociclista não encontrado: id=" + dto.getMotociclistaId()));
            entity.setMotociclista(motociclista);
        } else {
            entity.setMotociclista(null);
        }

        if (dto.getZonaPatioId() != null) {
            ZonaPatioEntity zona = zonaPatioRepo.findById(dto.getZonaPatioId())
                    .orElseThrow(() -> new EntityNotFoundException("Zona de pátio não encontrada: id=" + dto.getZonaPatioId()));
            entity.setZonaPatio(zona);
        } else {
            entity.setZonaPatio(null);
        }

        if (dto.getStatusOperacaoId() != null) {
            StatusOperacaoEntity status = statusOperacaoRepo.findById(dto.getStatusOperacaoId())
                    .orElseThrow(() -> new EntityNotFoundException("Status de operação não encontrado: id=" + dto.getStatusOperacaoId()));
            entity.setStatusOperacao(status);
        } else {
            entity.setStatusOperacao(null);
        }

        return entity;
    }
}