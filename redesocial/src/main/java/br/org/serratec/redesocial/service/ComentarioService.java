package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.redesocial.domain.Comentario;
import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.ComentarioDTO;
import br.org.serratec.redesocial.dto.ComentarioInserirDTO;
import br.org.serratec.redesocial.exception.NotFoundException;
import br.org.serratec.redesocial.repository.ComentarioRepository;
import br.org.serratec.redesocial.repository.PostagemRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	public Page<ComentarioDTO> findAll(Pageable pageable) {
		Page<Comentario> comentarios = comentarioRepository.findAll(pageable);
		List<ComentarioDTO> comentariosDTO = comentarios.stream().map(ComentarioDTO::new).toList();
		return new PageImpl<>(comentariosDTO, pageable, comentarios.getTotalElements());
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
