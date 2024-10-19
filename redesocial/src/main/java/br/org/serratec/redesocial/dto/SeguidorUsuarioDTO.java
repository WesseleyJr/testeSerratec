package br.org.serratec.redesocial.dto;

import br.org.serratec.redesocial.domain.Seguidor;

public class SeguidorUsuarioDTO {
	private String nomeUsuarioSeguidor;
	private Long idUsuarioSeguidor;
	
	
	
	public SeguidorUsuarioDTO() {
	}
	
	public SeguidorUsuarioDTO(String nomeUsuarioSeguidor, Long idUsuarioSeguidor) {
		this.nomeUsuarioSeguidor = nomeUsuarioSeguidor;
		this.idUsuarioSeguidor = idUsuarioSeguidor;
	}
	
	public SeguidorUsuarioDTO(Seguidor seguidor) {
		this.nomeUsuarioSeguidor = seguidor.getUsuarioSeguidor().getNome();
		this.idUsuarioSeguidor = seguidor.getUsuarioSeguidor().getId();
	}
	
	public String getNomeUsuarioSeguidor() {
		return nomeUsuarioSeguidor;
	}
	public void setNomeUsuarioSeguidor(String nomeUsuarioSeguidor) {
		this.nomeUsuarioSeguidor = nomeUsuarioSeguidor;
	}
	public Long getIdUsuarioSeguidor() {
		return idUsuarioSeguidor;
	}
	public void setIdUsuarioSeguidor(Long idUsuarioSeguidor) {
		this.idUsuarioSeguidor = idUsuarioSeguidor;
	}
	
	
}
