package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.NotaFiscalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalEntity, Long> {
    List<NotaFiscalEntity> findAllByOrderByIdAsc();
}
