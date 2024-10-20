package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Postagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostagemInserirDTO {
	
	private Long id;
	
	@NotBlank(message = "Preencha o conteúdo")
	private String conteudo;
	
	@NotNull(message = "Preencha a data")
	private LocalDate dataCriacao;
	
	@NotNull(message = "Preencha o id do usuario")
	private Long idUsuario;
	
	public PostagemInserirDTO() {

	}

	public PostagemInserirDTO(Long id, String conteudo, LocalDate dataCriacao, Long idUsuario) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.idUsuario = idUsuario;
	}
	
	public PostagemInserirDTO(Postagem postagem) {
		
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.dataCriacao = postagem.getDataCriacao();
		this.idUsuario = postagem.getUsuario().getId();
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

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
