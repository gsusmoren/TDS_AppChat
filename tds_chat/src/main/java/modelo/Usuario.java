package modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public class Usuario {
	private int codigo;
	private String nombre;
	private LocalDate fechaNacimiento;
	private int movil;
	private String nick;
	private String contrasena;
	private ImageIcon imagen;
	private String email;
	private boolean premium;
	private List<Contacto> contactos;

	public Usuario(String nombre, LocalDate fechaNacimiento, int movil, String email , String contrasena, String nick,ImageIcon imagen) {
		this.codigo = 0;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String usuario) {
		this.nick = usuario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int id) {
		this.codigo = id;
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

	public String getEmail() {
		return email;
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
