package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioInserirDTO {

	@NotBlank(message = "O nome deve ser preenchido!")
	private String nome;
	
	@NotBlank(message = "O sobrenome deve ser preenchido!")
	private String sobrenome;
	
	@NotBlank(message = "O email deve ser preenchido!")
	private String email;
	
	@NotBlank(message = "O senha deve ser preenchida!")
	private String senha;
	
	@NotBlank(message = "O Confirma senha deve ser preenchido!")
	private String confirmaSenha;
	
	@NotNull(message = "A data deve ser preenchida!")
	private LocalDate dataNascimento;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
