package modelo;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
		if (!contactos.contains(c)) {
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

	public boolean comprobarContacto(Usuario u) {
		for (Contacto c : contactos) {
			if (c instanceof ContactoIndividual) {
				if (((ContactoIndividual) c).getUsuario().movil.equals(u.getMovil()))
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
		for (Grupo g1 : l) {
			if (g1.getNombre().equals(nombre))
				return null;
		}
		Grupo g = new Grupo(nombre);
		for (ContactoIndividual ci : contactos) {
			g.addContacto(ci);
		}
		this.contactos.add(g);
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
		for (Grupo g : getGrupos()) {

			if (g.getNombre().equals(nombre)) {
				return g;
			}

		}
		return null;
	}

	public boolean borrarContacto(Contacto c) {
		return this.contactos.remove(c);

	}

	// Método para obtener los mensajes del ultimo mes
	public int getNMensajesUltimoMes() {

		int mesActual = LocalDateTime.now().getMonthValue();
		long nMensajes = contactos.stream().flatMap(cont -> cont.getListaMensajes().stream())
				.filter(m -> m.getHora().getMonthValue() == (mesActual)).count();
		return (int) nMensajes;
	}

	// Método para calcular los descuento de los que dispone el usuario
	public double getDescuento() {
		DescuentoEstudiante dis1 = new DescuentoEstudiante();
		DescuentoFijo dis2 = new DescuentoFijo();

		if (dis1.calcDescuento(this) < dis2.calcDescuento(this)) {
			return dis1.calcDescuento(this);
		} else {
			return dis2.calcDescuento(this);
		}

	}

	// Método para contar el número de mensajes que manda el usuario en cada
	// mes del año
	public Integer[] getMensajesPorMeses() {
		Integer[] meses = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		this.contactos.stream().flatMap(cont -> cont.getListaMensajes().stream())
				.filter(msg -> msg.getEmisor().equals(this)).forEach(m -> {
					int nMes = m.getHora().getMonthValue();
					meses[nMes - 1]++;
				});

		return meses;
	}

	// Método para encontar los grupos en los que el usuario es más activo
	public HashMap<Grupo, Double> getGruposMasActivos() {
		HashMap<Grupo, Double> msgRate = new HashMap<Grupo, Double>();
		List<Grupo> gruposOrd = getGrupos().stream().sorted((g1, g2) -> {
			return (g1.getListaMensajes().size() - g2.getListaMensajes().size());
		}).collect(Collectors.toList());

		int maxGrupos = 6;
		if (gruposOrd.size() < 6)
			maxGrupos = gruposOrd.size();

		for (int i = 0; i < maxGrupos; i++) {
			double percent = gruposOrd.get(i).getPorcentajeUsuario(this);
			msgRate.put(gruposOrd.get(i), percent);
		}
		return msgRate;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (premium ? 1231 : 1237);
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
		Usuario other = (Usuario) obj;
		if (contrasena == null) {
			if (other.contrasena != null)
				return false;
		} else if (!contrasena.equals(other.contrasena))
			return false;
		if (id != other.id)
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (premium != other.premium)
			return false;
		return true;
	}

}