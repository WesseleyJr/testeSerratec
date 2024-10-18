package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.UsuarioDTO;
import br.org.serratec.redesocial.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	public Optional<Usuario> buscar(Long id){
		return usuarioRepository.findById(id);
	}
	
	
//	public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {
//		Usuario usuario = new Usuario();
//		usuario.setNome(usuarioDTO.getNome());
//		usuario.setSobrenome(usuarioDTO.getSobrenome());
//		
//		usuario = usuarioRepository.save(usuario);
//		
//		return usuarioDTO;
//	}
}


