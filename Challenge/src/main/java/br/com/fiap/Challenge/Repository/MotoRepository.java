package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity, Long> {
    Optional<MotoEntity> findByPlaca(String placa);
    Optional<MotoEntity> findByChassi(String chassi);
}
