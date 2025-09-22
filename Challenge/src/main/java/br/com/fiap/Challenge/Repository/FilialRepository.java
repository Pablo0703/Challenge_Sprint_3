package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.FilialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<FilialEntity, Long> {
}
