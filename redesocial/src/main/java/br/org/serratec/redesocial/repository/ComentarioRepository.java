package br.org.serratec.redesocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.redesocial.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
