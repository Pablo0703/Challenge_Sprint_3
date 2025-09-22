package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.StatusOperacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOperacaoRepository extends JpaRepository<StatusOperacaoEntity, Long> {

}
