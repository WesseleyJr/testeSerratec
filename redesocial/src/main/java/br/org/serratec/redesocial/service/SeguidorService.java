package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.redesocial.domain.Seguidor;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.SeguidorDTO;
import br.org.serratec.redesocial.dto.SeguidorInserirDTO;
import br.org.serratec.redesocial.repository.SeguidorRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class SeguidorService {
	
	@Autowired
	private SeguidorRepository seguidorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<SeguidorDTO> findAll(){
		List<Seguidor> seguidores = seguidorRepository.findAll();
		List<SeguidorDTO> seguidoresDTO = seguidores.stream().map(SeguidorDTO :: new).collect(Collectors.toList());
		return seguidoresDTO;
	}
	
	@Transactional
	public SeguidorInserirDTO seguir(@Valid @RequestBody SeguidorInserirDTO seguidorInserirDTO) {
		Optional<Usuario> seguidoOpt = usuarioRepository.findById(seguidorInserirDTO.getIdUsuarioSeguido());
		Optional<Usuario> seguidorOpt = usuarioRepository.findById(seguidorInserirDTO.getIdUsuarioSeguidor());

		if (!seguidoOpt.isPresent() || !seguidorOpt.isPresent()) {
			return null;
		}

		Seguidor seg = new Seguidor();
		seg.setDataInicioSeguimento(seguidorInserirDTO.getDataInicioSeguimento());
		seg.setUsuarioSeguido(seguidoOpt.get());
		seg.setUsuarioSeguidor(seguidorOpt.get());
		
		seg = seguidorRepository.save(seg);
		
		Usuario usuario = seguidoOpt.get();
		usuario.getSeguidores().add(seg);

		return new SeguidorInserirDTO(seg);
	}
	
	@Transactional
	public Integer del(Long id) {
		if (!seguidorRepository.existsById(id)) {
			return null;
		}
		seguidorRepository.deleteById(id);
		return 1;
	}

}
