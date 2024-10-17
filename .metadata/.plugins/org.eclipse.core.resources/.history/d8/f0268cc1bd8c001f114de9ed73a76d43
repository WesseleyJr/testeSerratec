package br.org.serratec.redesocial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.serratec.redesocial.domain.Usuario;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
}
