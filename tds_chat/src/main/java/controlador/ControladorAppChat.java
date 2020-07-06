package controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import componente.cargador.CargadorMensajes;
import componente.cargador.MensajesEvent;
import componente.cargador.MensajesListener;
import componente.cargador.modelo.MensajeWhatsApp;
import componente.cargador.modelo.Plataforma;
import dao.DAOException;
import dao.FactoriaDAO;
import dao.IAdaptadorContactoInidivualDAO;
import dao.IAdaptadorGrupoDAO;
import dao.IAdaptadorMensajeDAO;
import dao.IAdaptadorUsuarioDAO;
import modelo.CatalogoUsuarios;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;

public class ControladorAppChat implements MensajesListener {

	private static ControladorAppChat unicaInstancia = null;

	private IAdaptadorUsuarioDAO adapU;
	private IAdaptadorContactoInidivualDAO adapCI;
	private IAdaptadorGrupoDAO adapGP;
	private IAdaptadorMensajeDAO adapMS;
	private CatalogoUsuarios catalogoUsuarios;
	private Usuario usuarioActual;

	private ControladorAppChat() {
		inicializarAdaptadores();
		inicializarCatalogo();
	}

	public static ControladorAppChat getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppChat();
		return unicaInstancia;
	}

	// Método para registrar a un usuario en la aplicación
	public boolean registrarUsuario(String nombre, Date fechaNacimiento, String movil, String email, String contrasena,
			String nick, String imagen, String saludo) {
		// Comprobar usuarios unicos
		if (!isUsuarioUnico(movil, nick)) {
			return false;
		}

		Usuario u = new Usuario(nombre, fechaNacimiento, movil, email, contrasena, nick, imagen, saludo);
		adapU.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
		return true;

	}

	public boolean isUsuarioUnico(String movil, String nick) {
		if ((catalogoUsuarios.getUsuarioMovil(movil) == null) && (catalogoUsuarios.getUsuarioNick(nick) == null)) {
			return true;
		}
		return false;
	}

	public Usuario getUsuarioActual() {

		return usuarioActual;
	}

	// Método para logear a un usaurio.....Posibilidad de login con movil
	public boolean loginUsuario(String login, String passwd) {
		Usuario usuario = catalogoUsuarios.getUsuarioNick(login);

		if (usuario != null && usuario.getContrasena().equals(passwd)) {
			// Establecemos el usuario principal actual al hacer login
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	// Método para comprobar credecnciales al usar paypal
	public boolean loginPayPal(String correo, String passwd) {
		System.out.println(usuarioActual.getEmail());
		if (usuarioActual.getEmail().equals(correo) && usuarioActual.getContrasena().equals(passwd)) {
			return true;
		} else {
			return false;
		}
	}

	// Método para actualizar un usuario pues se han cambiado sus atributos
	public void actualizarUsuario(Usuario u) {
		this.adapU.modificarUsuario(u);
		catalogoUsuarios.actualizarUsuario(u);

	}

	// Registramos un nuevo Contacto Individual y actualizamos el usuario actual.
	public boolean addContactoIndividual(String nombre, String numero) {

		Usuario ciUser = catalogoUsuarios.getUsuarioMovil(numero);
		if (ciUser != null) {
			ContactoIndividual cc = usuarioActual.addContactoI(nombre, numero, ciUser);
			if (cc == null)
				return false;
			adapCI.registrarContactoIndividual(cc);
			adapU.modificarUsuario(usuarioActual);
			catalogoUsuarios.actualizarUsuario(usuarioActual);
			return true;

		}
		return false;
	}

	// Método para haceer premium a un usaurio
	public void setPremium() {
		usuarioActual.setPremium(true);
		actualizarUsuario(usuarioActual);
	}

	public boolean comprobarContacto(String nombre, String numero, ContactoIndividual c) {
		List<ContactoIndividual> contactos = usuarioActual.getContactosIndividuales();
		for (ContactoIndividual cont : contactos) {
			if ((cont.getNombre().equals(nombre) && !cont.equals(c))
					// ese equals está mal
					|| (cont.getMovil().equals(numero) && !cont.equals(numero)))
				return false;
		}
		return true;
	}

	public boolean addContacto(String login, Contacto c) {
		Usuario u = catalogoUsuarios.getUsuarioNick(login);

		if (u.addContacto(c)) {
			if (c instanceof ContactoIndividual) {
				ContactoIndividual ci = (ContactoIndividual) c;
				adapCI.registrarContactoIndividual(ci);
			} else {
				Grupo g = (Grupo) c;
				adapGP.registrarGrupo(g);
			}
			adapU.modificarUsuario(u);
			catalogoUsuarios.actualizarUsuario(u);
			return true;
		}
		return false;
	}

	// Metodo para obtener los grupos comunes ambos
	public String getGruposComunes(ContactoIndividual ci) {
		List<Grupo> userG = usuarioActual.getGrupos();
		List<Grupo> ciG = ci.getUsuario().getGrupos();

		List<Grupo> comunes = userG.stream().filter(ciG::contains).collect(Collectors.toList());

		String gps = comunes.stream().map(c -> c.getNombre()).collect(Collectors.joining(","));
		return new String("[" + gps + "]");
	}

	// Método que devuelve el contacto dado el nick
	public ContactoIndividual getContactoIndividual(String nick) {

		ContactoIndividual ci = usuarioActual.getContactoIndividual(nick);
		if (ci != null) {
			return ci;
		} else {
			return null;
		}
	}

	// Método para crear un nuevo grupo.
	public Grupo crearGrupo(String nombre, List<ContactoIndividual> ctcs) {
		Grupo grupo = usuarioActual.crearGrupo(nombre, ctcs);

		if (grupo != null) {
			grupo.setAdmin(usuarioActual);
			adapGP.registrarGrupo(grupo);
			adapU.modificarUsuario(usuarioActual);
			catalogoUsuarios.actualizarUsuario(usuarioActual);

			for (ContactoIndividual ci : ctcs) {
				Usuario u = ci.getUsuario();
				u.addContacto(grupo);
				adapU.modificarUsuario(u);
				catalogoUsuarios.actualizarUsuario(u);
			}
			return grupo;
		}
		return null;

	}

	public void cMensajeEmoji(int e, Contacto c) {
		Mensaje m1 = new Mensaje(e);
		m1.setEmisor(usuarioActual);
		m1.setReceptor(c);

		if (c instanceof ContactoIndividual) {
			Usuario r = ((ContactoIndividual) c).getUsuario();

			ContactoIndividual ci = null;
			// Comprobamos
			if (!r.comprobarContacto(usuarioActual)) {
				ci = new ContactoIndividual(usuarioActual.getMovil(), usuarioActual.getMovil(), usuarioActual);
				addContacto(r.getNick(), ci);
			} else {
				ci = r.getContactoIndividual(usuarioActual);
			}

			Mensaje m2 = new Mensaje(e);
			m2.setEmisor(usuarioActual);
			m2.setReceptor(c);

			c.addMensaje(m1);
			adapMS.registrarMensaje(m1);
			adapCI.modificarContactoIndividual((ContactoIndividual) c);
			ci.addMensaje(m2);
			adapMS.registrarMensaje(m2);
			adapCI.modificarContactoIndividual(ci);
		} else {
			Grupo g = (Grupo) c;
			g.addMensaje(m1);
			adapMS.registrarMensaje(m1);
			adapGP.modificarGrupo(g);
		}
	}

	public void cMensajeTexto(String t, Contacto c) {
		Mensaje m1 = new Mensaje(t);
		m1.setEmisor(usuarioActual);
		m1.setReceptor(c);

		if (c instanceof ContactoIndividual) {
			Usuario r = ((ContactoIndividual) c).getUsuario();

			ContactoIndividual ci = null;
			if (!r.comprobarContacto(usuarioActual)) {
				ci = new ContactoIndividual(usuarioActual.getMovil(), usuarioActual.getMovil(), usuarioActual);
				addContacto(r.getNick(), ci);
			} else {
				ci = r.getContactoIndividual(usuarioActual);
			}

			Mensaje m2 = new Mensaje(t);
			m2.setEmisor(usuarioActual);
			m2.setReceptor(c);

			c.addMensaje(m1);
			adapMS.registrarMensaje(m1);
			adapCI.modificarContactoIndividual((ContactoIndividual) c);
			ci.addMensaje(m2);
			adapMS.registrarMensaje(m2);
			adapCI.modificarContactoIndividual(ci);
		} else {
			Grupo g = (Grupo) c;
			g.addMensaje(m1);
			adapMS.registrarMensaje(m1);
			adapGP.modificarGrupo(g);
		}
	}

	// GetGRupo nick
	public Grupo getGrupo(String nombre) {
		Grupo g = usuarioActual.getGrupo(nombre);

		if (g != null) {
			return g;
		} else {
			return null;
		}
	}

	//Método para editar un grupo
	public Grupo modificarGrupo(String grupo, String nombre, List<ContactoIndividual> l) {
		Grupo g = usuarioActual.getGrupo(grupo);
		for (ContactoIndividual c : g.getContactos()) {
			c.getUsuario().borrarContacto(g);
			adapU.modificarUsuario(c.getUsuario());
			catalogoUsuarios.actualizarUsuario(c.getUsuario());
		}
		g.setNombre(nombre);
		g.setContactos(l);
		adapGP.modificarGrupo(g);
		for (ContactoIndividual c : l) {
			c.getUsuario().addContacto(g);
			adapU.modificarUsuario(c.getUsuario());
			catalogoUsuarios.actualizarUsuario(c.getUsuario());
		}
		return g;
	}

	// Método para exportar un PDF con los contactos del usuario
	public void exportarContactosPDF() throws FileNotFoundException, DocumentException {
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("Contactos_de_" + usuarioActual.getNick() + ".pdf"));

		doc.open();

		Paragraph parrafo = new Paragraph("CONTACTOS DE " + usuarioActual.getNombre().toUpperCase() + "\n",
				FontFactory.getFont(FontFactory.COURIER, 36, BaseColor.BLACK));
		parrafo.setAlignment(Chunk.ALIGN_CENTER);
		doc.add(parrafo);

		try {
			Image foto = Image.getInstance(usuarioActual.getImagen());
			foto.scaleToFit(100, 100);
			foto.setAlignment(Chunk.ALIGN_MIDDLE);
			doc.add(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		parrafo = new Paragraph("Contactos Individuales\n",
				FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK));
		doc.add(parrafo);
		for (ContactoIndividual ci : usuarioActual.getContactosIndividuales()) {
			parrafo = new Paragraph("		[Nick: " + ci.getNombre() + "-  Móvil: " + ci.getMovil() + "]\n");
			doc.add(parrafo);
		}

		parrafo = new Paragraph("\nGrupos\n", FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK));
		doc.add(parrafo);

		for (Grupo g : usuarioActual.getGrupos()) {
			parrafo = new Paragraph("\n		[Nombre: " + g.getNombre() + "]\n");
			doc.add(parrafo);
			parrafo = new Paragraph("			-Participantes:\n");
			doc.add(parrafo);
			for (ContactoIndividual ci : g.getContactos()) {
				parrafo = new Paragraph("			   (Nick: " + ci.getNombre() + "- Móvil: " + ci.getMovil() + ")\n");
				doc.add(parrafo);

			}
		}
		doc.close();

	}

	// Método para eliminar a un Contacto
	public boolean eliminarContacto(Contacto c) {
		// TODO Comprobar usuario como admin
		if (usuarioActual.getContactos().contains(c)) {
			if (c instanceof Grupo) {
				Grupo gp = (Grupo) c;
				((Grupo) c).setAdmin(null);
				adapGP.modificarGrupo(gp);
			}
			usuarioActual.borrarContacto(c);
			adapU.modificarUsuario(usuarioActual);
			catalogoUsuarios.actualizarUsuario(usuarioActual);
			return true;
		}
		return false;

	}

	// Método para eliminar los mensajes con un Contacto
	public void eliminarMensajes(Contacto c) {
		c.borrarMensajes();
		if (c instanceof ContactoIndividual) {
			adapCI.modificarContactoIndividual((ContactoIndividual) c);

		} else {
			adapGP.modificarGrupo((Grupo) c);
		}
	}

	// Método para obtener el número de mensajes que manda el usuario en cada
	// mes del año
	public Integer[] getMensajesPorMeses() {
		return usuarioActual.getMensajesPorMeses();
	}

	// Método para obtener los grupos más activos y la participacion del usuario en
	// ellos
	public HashMap<Grupo, Double> getGruposMasActivos() {
		return usuarioActual.getGruposMasActivos();
	}

	public void ficheroImportado(String rutaFichero, String formato, Plataforma p) {
		CargadorMensajes cargadorm = new CargadorMensajes();
		cargadorm.addMensajeListener(ControladorAppChat.getUnicaInstancia());
		cargadorm.setFichero(rutaFichero, formato, p);
	}

	@Override
	public void nuevosMensajes(MensajesEvent e) {

		List<MensajeWhatsApp> listaMensajes = e.obtenerLista();
		String contacto = "";
		for (MensajeWhatsApp m : listaMensajes) {
			if (!m.getAutor().equals(usuarioActual.getNombre())) {
				contacto = m.getAutor();
				break;
			}
		}
		Contacto cont = null;
		for (Contacto c : usuarioActual.getContactos()) {
			if (c.getNombre().equals(contacto)) {
				cont = c;
				break;
			}
		}

		if (cont != null) {
			for (MensajeWhatsApp m : listaMensajes) {
				String autor = m.getAutor();
				if (autor.equals(usuarioActual.getNombre())) {
					Mensaje m1 = new Mensaje(m.getTexto());
					m1.setEmisor(usuarioActual);
					m1.setReceptor(cont);
					LocalDateTime f = m.getFecha();
					m1.setHora(f);

					if (cont instanceof ContactoIndividual) {
						cont.addMensaje(m1);
						adapMS.registrarMensaje(m1);
						adapCI.modificarContactoIndividual((ContactoIndividual) cont);
					} else {
						cont.addMensaje(m1);
						adapMS.registrarMensaje(m1);
						adapGP.modificarGrupo((Grupo) cont);
					}

				} else {
					Usuario u = ((ContactoIndividual) cont).getUsuario();
					ContactoIndividual c_actual = new ContactoIndividual(usuarioActual.getNombre(),
							usuarioActual.getMovil());
					c_actual.setUsuario(usuarioActual);
					Mensaje m1 = new Mensaje(m.getTexto());
					m1.setEmisor(u);
					m1.setReceptor(c_actual);
					LocalDateTime f = m.getFecha();
					m1.setHora(f);

					cont.addMensaje(m1);
					adapMS.registrarMensaje(m1);
					adapCI.modificarContactoIndividual((ContactoIndividual) cont);
				}
			}
		}

	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);

		} catch (DAOException e) {
			e.printStackTrace();
		}
		adapCI = factoria.getContactoInidivualDAO();
		adapGP = factoria.getGrupoDAO();
		adapMS = factoria.getMensajeDAO();
		adapU = factoria.getUsuarioDAO();
	}

	private void inicializarCatalogo() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
}