package br.org.serratec.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.redesocial.domain.Postagem;
import br.org.serratec.redesocial.domain.Usuario;
import br.org.serratec.redesocial.dto.PostagemDTO;
import br.org.serratec.redesocial.dto.PostagemInserirDTO;
import br.org.serratec.redesocial.repository.PostagemRepository;
import br.org.serratec.redesocial.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class PostagemService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	
	public List<PostagemDTO> findAll() {
		List<Postagem> postagens = postagemRepository.findAll();
		List<PostagemDTO> postagensDTO = postagens.stream().map(PostagemDTO::new).toList();
		return postagensDTO;
	}

	public PostagemDTO buscar(Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		return new PostagemDTO(postagemOpt.get());
	}

	@Transactional
	public PostagemInserirDTO inserir(PostagemInserirDTO postagemInserirDTO) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(postagemInserirDTO.getIdUsuario());
		if (!usuarioOpt.isPresent()) {
			return null;
		}

		Postagem postagem = new Postagem();
		postagem.setConteudo(postagemInserirDTO.getConteudo());
		postagem.setDataCriacao(postagemInserirDTO.getDataCriacao());
		postagem.setUsuario(usuarioOpt.get());
		
		postagem = postagemRepository.save(postagem);
		return new PostagemInserirDTO(postagem);
	}

	@Transactional
	public PostagemInserirDTO att(PostagemInserirDTO postagemInserirDTO, Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(postagemInserirDTO.getIdUsuario());
		if (!usuarioOpt.isPresent() || !postagemOpt.isPresent()) {
			return null;
		}

		Postagem postagem = postagemOpt.get();
		postagem.setConteudo(postagemInserirDTO.getConteudo());
		postagem.setDataCriacao(postagemInserirDTO.getDataCriacao());
		postagem.setUsuario(usuarioOpt.get());
		
		postagem = postagemRepository.save(postagem);
		return new PostagemInserirDTO(postagem);
	}

	@Transactional
	public Integer del(Long id) {
		if (!postagemRepository.existsById(id)) {
			return null;
		}
		postagemRepository.deleteById(id);
		return 1;
	}
}
