package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.org.serratec.redesocial.domain.Comentario;
import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.ComentarioDTO;
import br.org.serratec.redesocial.dto.ComentarioInserirDTO;
import br.org.serratec.redesocial.dto.UsuarioDTO;
import br.org.serratec.redesocial.dto.UsuarioInserirDTO;
import br.org.serratec.redesocial.exception.EmailException;
import br.org.serratec.redesocial.exception.NotFoundException;
import br.org.serratec.redesocial.exception.SenhaException;
import br.org.serratec.redesocial.repository.ComentarioRepository;
import br.org.serratec.redesocial.repository.PostagemRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	public List<ComentarioDTO> findAll() {
		List<Comentario> comentarios = comentarioRepository.findAll();
		List<ComentarioDTO> comentariosDTO = comentarios.stream().map(ComentarioDTO::new).toList();

		return comentariosDTO;
	}

	public ComentarioDTO buscar(Long id) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (!comentarioOpt.isPresent()) {
			throw new NotFoundException("Comentario não encontrado, ID: " + id);
		}
		ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioOpt.get());
		return comentarioDTO;
	}

	@Transactional
	public ComentarioDTO inserir(ComentarioInserirDTO comentarioInserirDTO) {

		Optional<Usuario> usuarioOpt = usuarioRepository.findById(comentarioInserirDTO.getIdUsuario());
		Optional<Postagem> postagemOpt = postagemRepository.findById(comentarioInserirDTO.getIdPostagem());

		if (!postagemOpt.isPresent()) {
			throw new NotFoundException("Postagem não encontrada, ID: " + comentarioInserirDTO.getIdPostagem());
		}
		if (!usuarioOpt.isPresent()) {
			throw new NotFoundException("Usuario não encontrado, ID: " + comentarioInserirDTO.getIdUsuario());
		}
		Comentario comentario = new Comentario();
		comentario.setTexto(comentarioInserirDTO.getTexto());
		comentario.setDataComentario(comentarioInserirDTO.getDataComentario());
		comentario.setUsuario(usuarioOpt.get());
		comentario.setPost(postagemOpt.get());
		comentario = comentarioRepository.save(comentario);

		Postagem postagem = postagemOpt.get();
		postagem.getComentarios().add(comentario);

		ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);

		return comentarioDTO;
	}

	@Transactional
	public ComentarioDTO att(ComentarioInserirDTO comentarioInserirDTO, Long id) {

		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(comentarioInserirDTO.getIdUsuario());
		Optional<Postagem> postagemOpt = postagemRepository.findById(comentarioInserirDTO.getIdPostagem());
		if (!comentarioOpt.isPresent()) {
			throw new NotFoundException("Comentario não encontrado, ID: " + id);
		}
		if (!postagemOpt.isPresent()) {
			throw new NotFoundException("Postagem não encontrada, ID: " + comentarioInserirDTO.getIdPostagem());
		}
		if (!usuarioOpt.isPresent()) {
			throw new NotFoundException("Usuario não encontrado, ID: " + comentarioInserirDTO.getIdUsuario());
		}

		Comentario comentario = comentarioOpt.get();
		comentario.setTexto(comentarioInserirDTO.getTexto());
		comentario.setDataComentario(comentarioInserirDTO.getDataComentario());
		comentario.setUsuario(usuarioOpt.get());
		comentario.setPost(postagemOpt.get());
		comentario = comentarioRepository.save(comentario);
		ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);

		return comentarioDTO;
	}

	@Transactional
	public Integer del(Long id) {
		if (!comentarioRepository.existsById(id)) {
			throw new NotFoundException("Comentario não encontrado, ID: " + id);
		}
		comentarioRepository.deleteById(id);
		return 1;
	}

}
