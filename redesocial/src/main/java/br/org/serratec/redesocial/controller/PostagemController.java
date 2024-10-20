package br.org.serratec.redesocial.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<PostagemDTO>> listar(@PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 3) Pageable pageable) {
		Page<PostagemDTO> postagem = postagemService.findAll(pageable);
		return ResponseEntity.ok(postagem);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
		PostagemDTO postagemDTO = postagemService.buscar(id);
		return ResponseEntity.ok(postagemDTO);
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
		PostagemInserirDTO p = postagemService.att(postagemInserirDTO, id);
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
		postagemService.del(id);
		return ResponseEntity.ok().build();
	}
}
