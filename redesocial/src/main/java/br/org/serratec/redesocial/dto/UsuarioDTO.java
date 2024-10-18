package br.org.serratec.redesocial.dto;

import br.org.serratec.redesocial.domain.Usuario;

public class UsuarioDTO {

	private String nome;
	private String sobrenome;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}