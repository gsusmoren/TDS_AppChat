package modelo;

import java.sql.Date;

import javax.swing.ImageIcon;

public class Usuario {

	private String nombre;
	private Date fechaNacimiento;
	private int movil;
	private String contrasena;
	private ImageIcon imagen;
	private boolean premium;

	public Usuario(String nombre, Date fechaNacimiento, int movil, String contrasena, ImageIcon imagen) {

		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.contrasena = contrasena;
		this.imagen = imagen;
		this.premium = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getMovil() {
		return movil;
	}

	public void setMovil(int movil) {
		this.movil = movil;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	

}
