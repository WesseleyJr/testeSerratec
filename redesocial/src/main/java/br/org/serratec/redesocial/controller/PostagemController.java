package br.org.serratec.redesocial.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.dto.PostagemDTO;
import br.org.serratec.redesocial.dto.PostagemInserirDTO;
import br.org.serratec.redesocial.service.PostagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemService postagemService;
	
	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		return ResponseEntity.ok(postagemService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
		PostagemDTO postagemDTO = postagemService.buscar(id);
		if (postagemDTO != null) {
			return ResponseEntity.ok(postagemDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PostagemInserirDTO> adicionar(@Valid @RequestBody PostagemInserirDTO postagemInserirDTO) {
		PostagemInserirDTO postagemDTO = postagemService.inserir(postagemInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postagemDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(postagemDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostagemInserirDTO> alterar(@PathVariable Long id, @Valid @RequestBody PostagemInserirDTO postagemInserirDTO) {
		if (postagemService.att(postagemInserirDTO, id) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(postagemService.att(postagemInserirDTO, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
		if (postagemService.del(id) == null) {
			return ResponseEntity.notFound().build();
		}
		postagemService.del(id);
		return ResponseEntity.ok().build();
	}
}
