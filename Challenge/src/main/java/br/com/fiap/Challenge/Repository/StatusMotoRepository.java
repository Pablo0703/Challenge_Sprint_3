package br.com.fiap.Challenge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.Challenge.Entity.StatusMotoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusMotoRepository extends JpaRepository<StatusMotoEntity, Long> {
    List<StatusMotoEntity> findAllByOrderByIdAsc();
    Optional<StatusMotoEntity> findByDescricao(String descricao);
}
