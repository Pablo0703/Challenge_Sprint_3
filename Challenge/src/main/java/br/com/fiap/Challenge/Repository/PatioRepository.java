package br.com.fiap.Challenge.Repository;


import br.com.fiap.Challenge.Entity.PatioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<PatioEntity, Long> {

}
