package br.org.serratec.redesocial.dto;

import java.time.LocalDate;

import br.org.serratec.redesocial.domain.Seguidor;
import br.org.serratec.redesocial.domain.Usuario;

public class SeguidorDTO {

	private Long id;
	private LocalDate dataInicioSeguimento;
	private Usuario usuarioSeguidor;
	private Usuario usuarioSeguido;

	public SeguidorDTO() {
	}

	public SeguidorDTO(Long id, LocalDate dataInicioSeguimento, Usuario usuarioSeguidor, Usuario usuarioSeguido) {
		this.id = id;
		this.dataInicioSeguimento = dataInicioSeguimento;
		this.usuarioSeguidor = usuarioSeguidor;
		this.usuarioSeguido = usuarioSeguido;
	}

	public SeguidorDTO(Seguidor seguidor) {
		this.id = seguidor.getId();
		this.dataInicioSeguimento = seguidor.getDataInicioSeguimento();
		this.usuarioSeguidor = seguidor.getUsuarioSeguidor();
		this.usuarioSeguido = seguidor.getUsuarioSeguido();
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

	public Usuario getUsuarioSeguidor() {
		return usuarioSeguidor;
	}

	public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
		this.usuarioSeguidor = usuarioSeguidor;
	}

	public Usuario getUsuarioSeguido() {
		return usuarioSeguido;
	}

	public void setUsuarioSeguido(Usuario usuarioSeguido) {
		this.usuarioSeguido = usuarioSeguido;
	}

}