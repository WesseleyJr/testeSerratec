package br.org.serratec.redesocial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.redesocial.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE UPPER (u.nome) LIKE UPPER(CONCAT('%', :paramNome, '%'))")
	Page<Usuario> buscarPorNome(String paramNome, Pageable pegeable);

	@Query("SELECT u FROM Usuario u WHERE UPPER (u.email) LIKE UPPER(CONCAT('%', :paramEmail, '%'))")
	Page<Usuario> buscarPorEmail(String paramEmail, Pageable pegeable);

	Usuario findByEmail(String email);

}
