package modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Usuario {
	private int id;
	private String nombre;
	private Date fechaNacimiento;
	private String movil;
	private String nick;
	private String contrasena;
	private String imagen;
	private String email;
	private String saludo;
	private boolean premium;
	private Descuento descuento; // Propiedad calculada
	private List<Contacto> contactos;

	// Constructor de Usuario

	public Usuario(String nombre, Date fechaNacimiento, String movil, String email, String contrasena, String nick,
			String imagen, String saludo) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.email = email;
		this.contrasena = contrasena;
		this.nick = nick;
		this.imagen = imagen; // Cambiada a String con la locali
		this.saludo = saludo;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getMovil() {
		return movil;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getImagen() {
		return imagen;
	}

	public String getSaludo() {
		return saludo;
	}

	public List<Contacto> getContactos() {
		return new LinkedList<Contacto>(contactos);
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

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public void setSaludo(String str) {
		this.saludo = str;
	}

	public boolean addContacto(Contacto c) {
		if(!contactos.contains(c)){
			contactos.add(c);
			return true;
		}
		return false;
	}

	public List<Grupo> getGrupos() {
		List<Grupo> gl = new LinkedList<Grupo>();
		for (Contacto c : contactos) {
			if (c instanceof Grupo)
				gl.add((Grupo) c);
		}
		return gl;
	}
	

	
	public boolean comprobarContacto(Usuario u){
		for(Contacto c : contactos){
			if(c instanceof ContactoIndividual){
				if(((ContactoIndividual) c).getUsuario().movil.equals(u.getMovil()))
					return true;
			}
		}
		return false;
	}
	
	public List<ContactoIndividual> getContactosIndividuales() {
		List<ContactoIndividual> l = new LinkedList<ContactoIndividual>();
		for (Contacto c : contactos) {
			if (c instanceof ContactoIndividual)
				l.add((ContactoIndividual) c);
		}
		return l;
	}

	// Metodos Adicionales
	public ContactoIndividual addContactoI(String nombre, String movil, Usuario u) {
		// comprobar si ya existe

		ContactoIndividual c = new ContactoIndividual(nombre, movil, u);
		if (this.getContactos().contains(c))
			return null;
		this.contactos.add(c);
		return c;
	}

	public Grupo crearGrupo(String nombre, List<ContactoIndividual> contactos) {
		List<Grupo> l = getGrupos();
		for(Grupo g1 : l){
			if(g1.getNombre().equals(nombre)) 
				return null;
		}
		Grupo g = new Grupo(nombre);
		for (ContactoIndividual ci : contactos) {
			g.addContacto(ci);
		}
		return g;
	}

	public ContactoIndividual getContactoIndividual(String nombre) {
		// TODO Stream
		for (ContactoIndividual c : getContactosIndividuales()) {
			if (c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	
	public ContactoIndividual getContactoIndividual(Usuario u) {
		// TODO Stream
		for (ContactoIndividual c : getContactosIndividuales()) {
			if (c.getUsuario().getMovil().equals(u.getMovil())) {
				return c;
			}
		}
		return null;
	}

	public Grupo getGrupo(String nombre) {
		for(Grupo g : getGrupos()) {
		
			if(g.getNombre().equals(nombre)) {
				return g;
			}
			
		}
		return null;
	}

	public boolean borrarContacto(Contacto c) {
		return this.contactos.remove(c);

	}

}