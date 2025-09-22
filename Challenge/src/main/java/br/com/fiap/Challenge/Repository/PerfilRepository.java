package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByNome(String nome);
}
