package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.LocalizacaoMotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoMotoRepository extends JpaRepository<LocalizacaoMotoEntity, Long> {
}
