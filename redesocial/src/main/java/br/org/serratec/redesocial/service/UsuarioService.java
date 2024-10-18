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
	
	public List<UsuarioDTO> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO :: new).toList();
		return usuariosDTO;
	}
	
	public Optional<Usuario> buscar(Long id){
		return usuarioRepository.findById(id);
	}
	
	
	public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSobrenome(usuarioDTO.getSobrenome());
		
		usuario = usuarioRepository.save(usuario);
		
		return usuarioDTO;
	}
	
// atualizar  o inserir no usuario controller pra puxar do Service 
// 		- comecar a fazer tratamento de erro e exceptions pra prosegir com essa parte
// confirmar se precisa fazer o service pro put e pro delete tbm

}


