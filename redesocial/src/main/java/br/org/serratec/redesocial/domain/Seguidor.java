package br.org.serratec.redesocial.domain;

import java.time.LocalDate;
import java.util.Objects;


import jakarta.persistence.*;

@Entity
@Table(name = "seguidor")
public class Seguidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate dataInicioSeguimento;

	@ManyToOne
	@JoinColumn(name = "idSeguidor", nullable = false)
	private Usuario usuarioSeguidor;

	@ManyToOne
	@JoinColumn(name = "idSeguido", nullable = false)
	private Usuario usuarioSeguido;

	public Seguidor() {
	}

	public Seguidor(LocalDate dataInicioSeguimento, Usuario usuarioSeguidor, Usuario usuarioSeguido) {
		this.dataInicioSeguimento = dataInicioSeguimento;
		this.usuarioSeguidor = usuarioSeguidor;
		this.usuarioSeguido = usuarioSeguido;
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
		Seguidor other = (Seguidor) obj;
		return Objects.equals(id, other.id);
	}
}
