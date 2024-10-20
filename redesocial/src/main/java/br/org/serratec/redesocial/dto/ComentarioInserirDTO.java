package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Comentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioInserirDTO {
	
	private Long id;
	
	@NotBlank(message = "Insira o texto")
	private String texto;
	
	@NotNull(message = "Insira a data")
	private LocalDate dataComentario;
	
	@NotNull(message = "Insira o id do usuario")
	private Long idUsuario;
	
	@NotNull(message = "Insira o id da postagem")
	private Long idPostagem;
	
	public ComentarioInserirDTO() {
	}

	public ComentarioInserirDTO(Long id, String texto, LocalDate dataComentario, Long idUsuario, Long idPostagem) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataComentario = dataComentario;
		this.idUsuario = idUsuario;
		this.idPostagem = idPostagem;
	}
	
	public ComentarioInserirDTO(Comentario comentario) {
	
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.dataComentario = comentario.getDataComentario();
		this.idUsuario = comentario.getUsuario().getId();
		this.idPostagem = comentario.getPost().getId();
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

	public LocalDate getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDate dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}
	
	
}
