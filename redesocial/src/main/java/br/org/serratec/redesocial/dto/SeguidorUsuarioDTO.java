package br.org.serratec.redesocial.dto;

import br.org.serratec.redesocial.domain.Seguidor;

public class SeguidorUsuarioDTO {
	private String nomeUsuarioSeguidor;
	private String sobrenomeSeguidor;
	private Long idUsuarioSeguidor;
	
	public SeguidorUsuarioDTO() {
	}
	
	public SeguidorUsuarioDTO(String nomeUsuarioSeguidor, String sobrenomeSeguidor, Long idUsuarioSeguidor) {
		this.nomeUsuarioSeguidor = nomeUsuarioSeguidor;
		this.sobrenomeSeguidor = sobrenomeSeguidor;
		this.idUsuarioSeguidor = idUsuarioSeguidor;
	}
	
	public SeguidorUsuarioDTO(Seguidor seguidor) {
		this.nomeUsuarioSeguidor = seguidor.getUsuarioSeguidor().getNome();
		this.sobrenomeSeguidor = seguidor.getUsuarioSeguidor().getSobrenome();
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

	public String getSobrenomeSeguidor() {
		return sobrenomeSeguidor;
	}

	public void setSobrenomeSeguidor(String sobrenomeSeguidor) {
		this.sobrenomeSeguidor = sobrenomeSeguidor;
	}
	
	
	
}
