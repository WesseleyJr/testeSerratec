package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.UsuarioDTO;
import br.org.serratec.redesocial.dto.UsuarioInserirDTO;
import br.org.serratec.redesocial.exception.EmailException;
import br.org.serratec.redesocial.exception.NotFoundException;
import br.org.serratec.redesocial.exception.SenhaException;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).toList();
		return usuariosDTO;
	}

	public UsuarioDTO buscar(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (!usuarioOpt.isPresent()) {
			throw new NotFoundException("Usuario não encontrado, ID: " + id);
		}

		return new UsuarioDTO(usuarioOpt.get());
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException, SenhaException {
		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		usuario.setDataNascimento(usuarioInserirDTO.getDataNascimento());

		usuario = usuarioRepository.save(usuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

		return usuarioDTO;
	}

	@Transactional
	public Usuario att(Usuario usuario, Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException("Usuario não encontrado, ID: " + id);
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional
	public Integer del(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new NotFoundException("Usuario não encontrado, ID: " + id);
		}
		usuarioRepository.deleteById(id);
		return 1;
	}

}
