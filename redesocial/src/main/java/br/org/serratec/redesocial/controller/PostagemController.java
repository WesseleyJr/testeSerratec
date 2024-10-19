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

import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.PostagemDTO;
import br.org.serratec.redesocial.dto.PostagemInserirDTO;
import br.org.serratec.redesocial.repository.PostagemRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		List<Postagem> postagens = postagemRepository.findAll();
		List<PostagemDTO> postagensDto = postagens.stream().map(PostagemDTO :: new ).collect(Collectors.toList());
		return ResponseEntity.ok(postagensDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		Optional<Postagem> postOpt = postagemRepository.findById(id);
		if (postOpt.isPresent()) {
			return ResponseEntity.ok(postOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PostagemInserirDTO> adicionar(@Valid @RequestBody PostagemInserirDTO postagemInserirDTO) {
		Optional<Usuario> usuario = usuarioRepository.findById(postagemInserirDTO.getIdUsuario());
		
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Postagem postagem = new Postagem();
		
		postagem.setConteudo(postagemInserirDTO.getConteudo());
		postagem.setDataCriacao(postagemInserirDTO.getDataCriacao());
		postagem.setUsuario(usuario.get());
		
		postagem = postagemRepository.save(postagem);
		
		 return ResponseEntity.ok().body(new PostagemInserirDTO(postagem));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> alterar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagem.setId(id);
		postagem = postagemRepository.save(postagem);
		return ResponseEntity.ok(postagem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
