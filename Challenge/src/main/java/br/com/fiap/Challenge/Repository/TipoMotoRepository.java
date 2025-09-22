package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.TipoMotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoMotoRepository extends JpaRepository<TipoMotoEntity, Long> {

    Optional<TipoMotoEntity> findByDescricao(String descricao);
}