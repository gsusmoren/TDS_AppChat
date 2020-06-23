package modelo;

import java.time.LocalDateTime;

import javax.swing.ImageIcon;

public class Mensaje {
	private int codigo ;
	private  String texto;
	private Usuario emisor;
	private Contacto receptor;
	private  LocalDateTime hora;
	private  int emoji;
	
	public Mensaje(Usuario e, Contacto r, String texto) {
		super();
		this.texto = texto;
		this.emisor = e;
		this.receptor = r;
		this.hora = LocalDateTime.now();
		this.emoji = -1;
	}
	
	public Mensaje(Usuario e, Contacto r, int emoji) {
		super();
		this.texto = "";
		this.emisor = e;
		this.receptor = r;
		this.hora = LocalDateTime.now();
		this.emoji = emoji;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public void setEmoji(int emoji) {
		this.emoji = emoji;
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Contacto getReceptor() {
		return receptor;
	}

	public void setReceptor(Contacto receptor) {
		this.receptor = receptor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + emoji;
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
		if (codigo != other.codigo)
			return false;
		if (emoji != other.emoji)
			return false;
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
