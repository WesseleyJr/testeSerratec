package br.org.serratec.redesocial.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.redesocial.domain.Comentario;
import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.ComentarioDTO;
import br.org.serratec.redesocial.dto.ComentarioInserirDTO;
import br.org.serratec.redesocial.repository.ComentarioRepository;
import br.org.serratec.redesocial.repository.PostagemRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> listar() {
		List<Comentario> comentarios = comentarioRepository.findAll();
		List<ComentarioDTO> comentariosDto = comentarios.stream().map(ComentarioDTO :: new).collect(Collectors.toList());
		return ResponseEntity.ok(comentariosDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comentario> buscar(@PathVariable Long id) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isPresent()) {
			return ResponseEntity.ok(comentarioOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ComentarioInserirDTO> adicionar(@Valid @RequestBody ComentarioInserirDTO comentarioInserirDTO) {
		Optional<Usuario> usuario = usuarioRepository.findById(comentarioInserirDTO.getIdUsuario());
		Optional<Postagem> postagemOP = postagemRepository.findById(comentarioInserirDTO.getIdPostagem());
		
		if (!usuario.isPresent() || !postagemOP.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Comentario comentario = new Comentario();
		
		comentario.setTexto(comentarioInserirDTO.getTexto());
		comentario.setDataComentario(comentarioInserirDTO.getDataComentario());
		comentario.setUsuario(usuario.get());
		comentario.setPost(postagemOP.get());
		
		comentario = comentarioRepository.save(comentario);
		
		Postagem postagem = postagemOP.get();
		
		postagem.getComentarios().add(comentario);

		
		return ResponseEntity.ok().body(new ComentarioInserirDTO(comentario));
		
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> alterar(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
		if (!comentarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		comentario.setId(id);
		comentario = comentarioRepository.save(comentario);
		return ResponseEntity.ok(comentario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> deletar(@PathVariable Long id) {
		if (!comentarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		comentarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
