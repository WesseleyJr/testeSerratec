package br.org.serratec.redesocial.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "O campo deve ser preenchido!")
	private String nome;

	@Column(nullable = false)
	@NotBlank(message = "O campo deve ser preenchido!")
	private String sobrenome;

	@Column(nullable = false)
	@NotBlank(message = "O campo deve ser preenchido!")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "O campo deve ser preenchido!")
	private String senha;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSeguindo", fetch = FetchType.EAGER)
	private List<Seguidor> seguidores = new ArrayList<>();
	
	

	public Usuario() {
	}

	public Usuario(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNascimento,
			List<Seguidor> seguidores) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.seguidores = seguidores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Seguidor> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Seguidor> seguidores) {
		this.seguidores = seguidores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
