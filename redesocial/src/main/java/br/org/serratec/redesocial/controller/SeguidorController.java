package br.org.serratec.redesocial.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.redesocial.domain.Seguidor;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.SeguidorDTO;
import br.org.serratec.redesocial.dto.SeguidorInserirDTO;
import br.org.serratec.redesocial.repository.SeguidorRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import br.org.serratec.redesocial.service.PostagemService;
import br.org.serratec.redesocial.service.SeguidorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/seguidor")
public class SeguidorController {

	@Autowired
	private SeguidorService seguidorService;
	
	@GetMapping
	public ResponseEntity<List<SeguidorDTO>> listar(){
		return ResponseEntity.ok(seguidorService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<SeguidorInserirDTO> seguir(@Valid @RequestBody SeguidorInserirDTO seguidorInserirDTO) {
		SeguidorInserirDTO seguidorInserir = seguidorService.seguir(seguidorInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(seguidorInserir.getId()).toUri();
		return ResponseEntity.created(uri).body(seguidorInserir);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SeguidorDTO> deletar(@PathVariable Long id) {
		if (seguidorService.del(id) == null) {
			return ResponseEntity.notFound().build();
		}
		seguidorService.del(id);
		return ResponseEntity.noContent().build();
	}
}
