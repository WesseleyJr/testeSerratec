package br.org.serratec.redesocial.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.serratec.redesocial.domain.Seguidor;

public class SeguidoUsuarioDTO {
	private String nomeUsuarioSeguido;
	private String sobrenomeSeguido;
	private Long idUsuarioSeguido;
	private List<SeguidorUsuarioDTO> seguidores;
	
	
	
	public SeguidoUsuarioDTO() {
	}
	

	public SeguidoUsuarioDTO(String nomeUsuarioSeguido, String sobrenomeSeguido, Long idUsuarioSeguido,
			List<SeguidorUsuarioDTO> seguidores) {
		super();
		this.nomeUsuarioSeguido = nomeUsuarioSeguido;
		this.sobrenomeSeguido = sobrenomeSeguido;
		this.idUsuarioSeguido = idUsuarioSeguido;
		this.seguidores = seguidores;
	}



	public SeguidoUsuarioDTO(Seguidor seguidor) {
		this.nomeUsuarioSeguido = seguidor.getUsuarioSeguido().getNome();
		this.idUsuarioSeguido = seguidor.getUsuarioSeguido().getId();
		this.sobrenomeSeguido = seguidor.getUsuarioSeguido().getSobrenome();
		this.seguidores = new ArrayList<>();
		for (Seguidor seg : seguidor.getUsuarioSeguido().getSeguidores()) {
			
			this.seguidores.add(new SeguidorUsuarioDTO(seg));
		}
	}

	public String getNomeUsuarioSeguido() {
		return nomeUsuarioSeguido;
	}

	public void setNomeUsuarioSeguido(String nomeUsuarioSeguido) {
		this.nomeUsuarioSeguido = nomeUsuarioSeguido;
	}

	public String getSobrenomeSeguido() {
		return sobrenomeSeguido;
	}

	public void setSobrenomeSeguido(String sobrenomeSeguido) {
		this.sobrenomeSeguido = sobrenomeSeguido;
	}

	public Long getIdUsuarioSeguido() {
		return idUsuarioSeguido;
	}

	public void setIdUsuarioSeguido(Long idUsuarioSeguido) {
		this.idUsuarioSeguido = idUsuarioSeguido;
	}

	public List<SeguidorUsuarioDTO> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<SeguidorUsuarioDTO> seguidores) {
		this.seguidores = seguidores;
	}
	
}
