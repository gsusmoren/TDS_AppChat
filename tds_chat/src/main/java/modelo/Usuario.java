package modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public class Usuario {
	private int id;
	private String nombre;
	private LocalDate fechaNacimiento;
	private int movil;
	private String nick;
	private String contrasena;
	private ImageIcon imagen;
	private String email;
	private boolean premium;
	private Descuento descuento;
	private List<Contacto> contactos;

	// Constructor de Usuario

	public Usuario(String nombre, LocalDate fechaNacimiento, int movil, String email, String contrasena, String nick,
			ImageIcon imagen) {
		this.id = 0;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.email = email;
		this.contrasena = contrasena;
		this.nick = nick;
		this.imagen = imagen;
		this.contactos = new LinkedList<Contacto>();
		this.premium = false;
	}

	// Getters
	public String getNick() {
		return nick;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getMovil() {
		return movil;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	// Setters
	public void setNick(String usuario) {
		this.nick = usuario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setMovil(int movil) {
		this.movil = movil;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	// Metodos Adicionales
	public boolean addContacto(Contacto c) {
		// comprobar si ya existe0
		return this.contactos.add(c);
	}

	public boolean removeContacto(Contacto c) {
		// comprobar que ya existe
		return this.contactos.remove(c);

	}

}