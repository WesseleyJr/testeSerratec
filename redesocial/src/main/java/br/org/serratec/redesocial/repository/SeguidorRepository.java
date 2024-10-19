package br.org.serratec.redesocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.redesocial.domain.Relacionamento;

@Repository
public interface SeguidorRepository extends JpaRepository<Relacionamento, Long> {

}
