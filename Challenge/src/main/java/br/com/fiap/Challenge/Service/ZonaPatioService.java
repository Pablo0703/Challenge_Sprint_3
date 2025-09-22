package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Entity.PatioEntity;
import br.com.fiap.Challenge.Entity.ZonaPatioEntity;
import br.com.fiap.Challenge.Repository.PatioRepository;
import br.com.fiap.Challenge.Repository.ZonaPatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaPatioService {

    private final ZonaPatioRepository zonaPatioRepository;
    private final PatioRepository patioRepository;

    public ZonaPatioService(ZonaPatioRepository zonaPatioRepository, PatioRepository patioRepository) {
        this.zonaPatioRepository = zonaPatioRepository;
        this.patioRepository = patioRepository;
    }

    /**
     * DOCUMENTAÇÃO:
     * Lista todas as Zonas de Pátio.
     * Busca todas as entidades do banco e as converte para DTOs.
     * @return Uma lista de ZonaPatioDTO.
     */
    @Transactional(readOnly = true)
    public List<ZonaPatioDTO> listarTodos() {
        return zonaPatioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * DOCUMENTAÇÃO:
     * Busca uma Zona de Pátio específica pelo seu ID.
     * @param id O ID da zona a ser buscada.
     * @return O ZonaPatioDTO correspondente.
     * @throws EntityNotFoundException se a zona não for encontrada.
     */
    @Transactional(readOnly = true)
    public ZonaPatioDTO buscarPorId(Long id) {
        ZonaPatioEntity entity = zonaPatioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona de Pátio não encontrada com o ID: " + id));
        return toDTO(entity);
    }

    /**
     * DOCUMENTAÇÃO:
     * Salva uma nova Zona de Pátio ou atualiza uma existente.
     * Esta é a lógica central que resolve o erro ORA-01400.
     * @param dto O DTO com os dados da zona a ser salva.
     */
    @Transactional
    public void salvar(ZonaPatioDTO dto) {
        // 1. Busca a entidade `PatioEntity` usando o ID fornecido no DTO.
        PatioEntity patio = patioRepository.findById(dto.getIdPatio())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado! ID: " + dto.getIdPatio()));

        // 2. Converte o DTO para uma entidade `ZonaPatioEntity`.
        ZonaPatioEntity entity = toEntity(dto);

        // 3. ✨ AQUI ESTÁ O PONTO-CHAVE: Associa a entidade Pátio completa.
        entity.setPatio(patio);

        // 4. Salva a entidade no banco de dados.
        zonaPatioRepository.save(entity);
    }

    /**
     * DOCUMENTAÇÃO:
     * Exclui uma Zona de Pátio pelo seu ID.
     * @param id O ID da zona a ser excluída.
     * @throws EntityNotFoundException se a zona não for encontrada.
     */
    @Transactional
    public void excluir(Long id) {
        if (!zonaPatioRepository.existsById(id)) {
            throw new EntityNotFoundException("Zona de Pátio não encontrada para exclusão. ID: " + id);
        }
        zonaPatioRepository.deleteById(id);
    }


    // --- MÉTODOS AUXILIARES PARA CONVERSÃO DTO <-> ENTIDADE ---

    /**
     * Converte um ZonaPatioDTO para uma ZonaPatioEntity.
     */
    private ZonaPatioEntity toEntity(ZonaPatioDTO dto) {
        ZonaPatioEntity entity = new ZonaPatioEntity();
        entity.setId(dto.getId()); // Mantém o ID para casos de atualização
        entity.setNomeZona(dto.getNomeZona());
        entity.setTipoZona(dto.getTipoZona());
        entity.setCapacidade(dto.getCapacidade());
        // A associação do Pátio é feita no método `salvar`.
        return entity;
    }

    /**
     * Converte uma ZonaPatioEntity para um ZonaPatioDTO.
     */
    private ZonaPatioDTO toDTO(ZonaPatioEntity entity) {
        ZonaPatioDTO dto = new ZonaPatioDTO();
        dto.setId(entity.getId());
        dto.setNomeZona(entity.getNomeZona());
        dto.setTipoZona(entity.getTipoZona());
        dto.setCapacidade(entity.getCapacidade());

        // Define o ID do pátio e também o DTO do pátio para exibição
        if (entity.getPatio() != null) {
            dto.setIdPatio(entity.getPatio().getId());
            dto.setPatio(toPatioDTO(entity.getPatio()));
        }
        return dto;
    }

    /**
     * Converte uma PatioEntity para um PatioDTO.
     * (Idealmente, isso estaria no PatioService, mas incluímos aqui para simplicidade).
     */
    private PatioDTO toPatioDTO(PatioEntity entity) {
        PatioDTO dto = new PatioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        // Mapeie outros campos se necessário
        return dto;
    }
}