package modelo;

import java.time.LocalDateTime;

import javax.swing.ImageIcon;

public class Mensaje {
	private final String texto;
	private final LocalDateTime hora;
	private final ImageIcon emogi;
	
	public Mensaje(String texto) {
		super();
		this.texto = texto;
		this.hora = LocalDateTime.now();
		this.emogi = null;
	}
	
	public Mensaje(ImageIcon emoji) {
		super();
		this.texto = "";
		this.hora = LocalDateTime.now();
		this.emogi = emoji;
	}

	public String getTexto() {
		return texto;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public ImageIcon getEmogi() {
		return emogi;
	}
	
	
	

}
