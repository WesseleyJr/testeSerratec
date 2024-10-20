package br.org.serratec.redesocial.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.serratec.redesocial.domain.Comentario;
import br.org.serratec.redesocial.domain.Postagem;

public class PostagemDTO {

	private Long id;
	private String conteudo;
	private String usuarioNome;
	private List<ComentarioDTO> comentarios;

	public PostagemDTO() {

	}

	public PostagemDTO(Long id, String conteudo) {
		this.id = id;
		this.conteudo = conteudo;
	}

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.usuarioNome = postagem.getUsuario().getNome();
		this.comentarios = new ArrayList<>();
		for (Comentario comentario : postagem.getComentarios()) {
			this.comentarios.add(new ComentarioDTO(comentario));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}

}
