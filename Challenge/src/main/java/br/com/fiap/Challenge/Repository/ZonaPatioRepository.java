package br.com.fiap.Challenge.Repository;

import br.com.fiap.Challenge.Entity.ZonaPatioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaPatioRepository extends JpaRepository<ZonaPatioEntity, Long> {
}
