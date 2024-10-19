package br.org.serratec.redesocial.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.serratec.redesocial.domain.Seguidor;
import br.org.serratec.redesocial.domain.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private Integer qntSeguidores = 0;
	private List<SeguidorUsuarioDTO> seguidores;

	public UsuarioDTO() {
	}



	public UsuarioDTO(Long id, String nome, String sobrenome, Integer qntSeguidores) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.qntSeguidores = qntSeguidores;
	}



	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.seguidores = new ArrayList<>();
		for (Seguidor seguidor : usuario.getSeguidores()) {
			this.seguidores.add(new SeguidorUsuarioDTO(seguidor));
			this.qntSeguidores++;
		}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SeguidorUsuarioDTO> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<SeguidorUsuarioDTO> seguidores) {
		this.seguidores = seguidores;
	}



	public Integer getQntSeguidores() {
		return qntSeguidores;
	}



	public void setQntSeguidores(Integer qntSeguidores) {
		this.qntSeguidores = qntSeguidores;
	}
	
	

}