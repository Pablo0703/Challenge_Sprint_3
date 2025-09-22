package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.HistoricoLocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoLocalizacaoRepository extends JpaRepository<HistoricoLocalizacaoEntity, Long> {
}
