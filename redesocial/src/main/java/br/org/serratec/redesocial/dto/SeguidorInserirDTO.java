package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Seguidor;

public class SeguidorInserirDTO {

	private Long id;
	private LocalDate dataInicioSeguimento;
	private Long idUsuarioSeguidor;
	private Long idUsuarioSeguido;

	public SeguidorInserirDTO() {
	}

	public SeguidorInserirDTO(Long id, LocalDate dataInicioSeguimento, Long idUsuarioSeguidor, Long idUsuarioSeguido) {
		this.id = id;
		this.dataInicioSeguimento = dataInicioSeguimento;
		this.idUsuarioSeguidor = idUsuarioSeguidor;
		this.idUsuarioSeguido = idUsuarioSeguido;
	}

	public SeguidorInserirDTO(Seguidor seguidor) {
		this.id = seguidor.getId();
		this.dataInicioSeguimento = seguidor.getDataInicioSeguimento();
		this.idUsuarioSeguidor = seguidor.getUsuarioSeguidor().getId();
		this.idUsuarioSeguido = seguidor.getUsuarioSeguido().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(LocalDate dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public Long getIdUsuarioSeguidor() {
		return idUsuarioSeguidor;
	}

	public void setIdUsuarioSeguidor(Long idUsuarioSeguidor) {
		this.idUsuarioSeguidor = idUsuarioSeguidor;
	}

	public Long getIdUsuarioSeguido() {
		return idUsuarioSeguido;
	}

	public void setIdUsuarioSeguido(Long idUsuarioSeguido) {
		this.idUsuarioSeguido = idUsuarioSeguido;
	}

}
