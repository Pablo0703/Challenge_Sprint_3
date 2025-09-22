package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.MotociclistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MotociclistaRepository extends JpaRepository<MotociclistaEntity, Long> {
    List<MotociclistaEntity> findAllByOrderByIdAsc();
}
