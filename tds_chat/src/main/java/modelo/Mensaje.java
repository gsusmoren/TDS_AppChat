package modelo;

import java.time.LocalDateTime;

public class Mensaje {
	private final String texto;
	private int codigo;
	private final LocalDateTime hora;
	private final int emoji;

	public Mensaje(String texto) {
		super();
		this.codigo = 0;
		this.texto = texto;
		this.hora = LocalDateTime.now();
		this.emoji = -1;
	}

	public Mensaje(int emoji) {
		this.texto = "";
		this.hora = LocalDateTime.now();
		this.emoji = emoji;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public int getEmoji() {
		return emoji;
	}

	// emogi excluido
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

}
