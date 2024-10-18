package br.org.serratec.redesocial.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
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

import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import br.org.serratec.redesocial.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			return ResponseEntity.ok(usuarioOpt.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deletar(@PathVariable Long id) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
