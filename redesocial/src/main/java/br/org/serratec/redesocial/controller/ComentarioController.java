package br.org.serratec.redesocial.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.redesocial.domain.Comentario;
import br.org.serratec.redesocial.dto.ComentarioDTO;
import br.org.serratec.redesocial.dto.ComentarioInserirDTO;
import br.org.serratec.redesocial.service.ComentarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping("/pagina")
	public ResponseEntity<Page<ComentarioDTO>> listar(@PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable) {
		Page<ComentarioDTO> comentario = comentarioService.findAll(pageable);
		return ResponseEntity.ok(comentario);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComentarioDTO> buscar(@PathVariable Long id) {
		ComentarioDTO comentarioDTO = comentarioService.buscar(id);
		return ResponseEntity.ok(comentarioDTO);

	}

	@PostMapping
	public ResponseEntity<ComentarioDTO> adicionar(@Valid @RequestBody ComentarioInserirDTO comentarioInserirDTO) {
		ComentarioDTO comentarioDTO = comentarioService.inserir(comentarioInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(comentarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComentarioDTO> alterar(@PathVariable Long id,
			@Valid @RequestBody ComentarioInserirDTO comentarioInserirDTO) {

		ComentarioDTO comentarioDTO = comentarioService.att(comentarioInserirDTO, id);
		return ResponseEntity.ok(comentarioDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> deletar(@PathVariable Long id) {

		comentarioService.del(id);
		return ResponseEntity.ok().build();
	}
}
