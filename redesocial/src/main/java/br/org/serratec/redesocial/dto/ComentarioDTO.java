package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Comentario;

public class ComentarioDTO {

	private Long id;
	private String texto;
	private String nomeUsuario;
	private Long idPostagem;
	private LocalDate dataComentario;

	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.nomeUsuario = comentario.getUsuario().getNome();
		this.idPostagem = comentario.getPost().getId();
		this.dataComentario = comentario.getDataComentario();
	}

	public ComentarioDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public LocalDate getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDate dataComentario) {
		this.dataComentario = dataComentario;
	}

}
