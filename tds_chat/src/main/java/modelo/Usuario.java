package modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public class Usuario {

	private String nombre;
	private LocalDate fechaNacimiento;
	private int movil;
	private String contrasena;
	private ImageIcon imagen;
	private boolean premium;
	private List<Contacto> contactos;

	public Usuario(String nombre, LocalDate fechaNacimiento, int movil, String contrasena, ImageIcon imagen) {

		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.contrasena = contrasena;
		this.imagen = imagen;
		this.contactos = new LinkedList<Contacto>();
		this.premium = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
	
	public boolean addContacto(Contacto c) {
		return this.contactos.add(c);
	}
	public boolean removeContacto(Contacto c) {
		return this.contactos.remove(c);
		
	}
	

}
