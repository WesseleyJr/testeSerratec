package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Seguidor;

public class SeguidorDTO {

	private Long idUsuarioSeguido;
	private Long idUsuarioSeguidor;
	private LocalDate dataInicioSeguimento;
	
	public SeguidorDTO() {
	}

	public SeguidorDTO(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
	
	public SeguidorDTO(Seguidor seguidor) {
		this.dataInicioSeguimento = seguidor.getDataInicioSeguimento();
		this.idUsuarioSeguido = seguidor.getUsuarioSeguido().getId();
		this.idUsuarioSeguidor = seguidor.getUsuarioSeguidor().getId();
	}

	public Long getIdUsuarioSeguido() {
		return idUsuarioSeguido;
	}

	public void setIdUsuarioSeguido(Long idUsuarioSeguido) {
		this.idUsuarioSeguido = idUsuarioSeguido;
	}

	public Long getIdUsuarioSeguidor() {
		return idUsuarioSeguidor;
	}

	public void setIdUsuarioSeguidor(Long idUsuarioSeguidor) {
		this.idUsuarioSeguidor = idUsuarioSeguidor;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}
	
	
	
}