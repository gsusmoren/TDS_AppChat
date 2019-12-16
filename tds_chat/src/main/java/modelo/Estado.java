package modelo;

import javax.swing.ImageIcon;

public class Estado {
	private String mensaje;
	private ImageIcon imagen;
	
	public Estado(String mensaje, ImageIcon imagen) {
		this.mensaje = mensaje;
		this.imagen = imagen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	

}
