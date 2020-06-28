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
	private Descuento descuento; //Propiedad calculada
	private List<Contacto> contactos;

	// Constructor de Usuario

	public Usuario(String nombre, Date fechaNacimiento, String movil, String email, String contrasena, String nick,
			String imagen,String saludo) {
		this.id = 0;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.movil = movil;
		this.email = email;
		this.contrasena = contrasena;
		this.nick = nick;
		this.imagen = imagen; //Cambiada a String con la locali
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
	public List<Contacto> getContactos(){
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
	
	public void addContacto(Contacto c){
		contactos.add(c);
	}
	
	public List<ContactoIndividual> getContactosIndividuales(){
		List<ContactoIndividual> l = new LinkedList<ContactoIndividual>();
		for(Contacto c : contactos){
			if(c instanceof ContactoIndividual) 
				l.add((ContactoIndividual)c);
		}
		return l;
	}

	// Metodos Adicionales
	public ContactoIndividual addContactoI(String nombre, String movil, Usuario u) {
		// comprobar si ya existe
		ContactoIndividual c = new ContactoIndividual(nombre, movil, u);
		this.contactos.add(c);
		return c;
	}

	public boolean removeContacto(Contacto c) {
		// comprobar que ya existe
		return this.contactos.remove(c);

	}

}