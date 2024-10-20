package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.serratec.redesocial.domain.Seguidor;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.SeguidoUsuarioDTO;
import br.org.serratec.redesocial.dto.SeguidorDTO;
import br.org.serratec.redesocial.dto.SeguidorInserirDTO;
import br.org.serratec.redesocial.exception.NotFoundException;
import br.org.serratec.redesocial.repository.SeguidorRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class SeguidorService {
	
	@Autowired
	private SeguidorRepository seguidorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<SeguidorDTO> findAll(Pageable pageable){
		Page<Seguidor> seguidores = seguidorRepository.findAll(pageable);
		List<SeguidorDTO> seguidoresDTO = seguidores.stream().map(SeguidorDTO :: new).collect(Collectors.toList());
		return new PageImpl<>(seguidoresDTO, pageable, seguidores.getTotalElements());
	}
	
	@Transactional
	public SeguidorInserirDTO seguir(SeguidorInserirDTO seguidorInserirDTO) {
		Optional<Usuario> seguidoOpt = usuarioRepository.findById(seguidorInserirDTO.getIdUsuarioSeguido());
		Optional<Usuario> seguidorOpt = usuarioRepository.findById(seguidorInserirDTO.getIdUsuarioSeguidor());

		if (!seguidoOpt.isPresent()) {
			throw new NotFoundException("Usuario Seguido não encontrado, ID: " + seguidorInserirDTO.getIdUsuarioSeguido());
		}
		if (!seguidorOpt.isPresent()) {
			throw new NotFoundException("Usuario Seguidor não encontrado, ID: " + seguidorInserirDTO.getIdUsuarioSeguidor());
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
			throw new NotFoundException("Relacionamento não encontrado, ID: " + id);
		}
		seguidorRepository.deleteById(id);
		return 1;
	}
	
	public SeguidoUsuarioDTO seguidoresPorUsuario(Long id) {
		  Optional<Seguidor> seguidorOpt = seguidorRepository.findById(id);
		  if (!seguidorOpt.isPresent()) {
				throw new NotFoundException("Usuario Seguido não encontrado, ID: " + id);
			}
		  
		return new SeguidoUsuarioDTO(seguidorOpt.get());
		
	}

}
