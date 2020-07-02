package controlador;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.eclipse.persistence.internal.sessions.factories.model.platform.NetWeaver_7_1_PlatformConfig;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import dao.AdaptadorContactoIndividualTDS;
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

public class ControladorAppChat {

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
	
	public boolean addContactoIndividual(String login, Contacto c){
		Usuario u = catalogoUsuarios.getUsuarioNick(login);
		
		if (u.addContacto(c)){
			if(c instanceof ContactoIndividual){
				ContactoIndividual ci = (ContactoIndividual)c;
				adapCI.registrarContactoIndividual(ci);
			}else{
				Grupo g = (Grupo)c;
				adapGP.registrarGrupo(g);
		}
			adapU.modificarUsuario(u);
			catalogoUsuarios.actualizarUsuario(u);
			return true;
		}
		return false;
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

	// Método para eliminar contacto individual

	public boolean eliminarContactoIndividual(ContactoIndividual ci) {
		adapCI.borrarContactoIdividual(ci);
		usuarioActual.borrarContacto(ci);
		adapU.modificarUsuario(usuarioActual);
		return true;
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
		Mensaje m = new Mensaje(e);
		m.setEmisor(usuarioActual);
		m.setReceptor(c);
		
		if(c instanceof ContactoIndividual){
			Usuario r=((ContactoIndividual) c).getUsuario();
			
			ContactoIndividual ci = null;
			//Comprobamos 
			if(!r.comprobarContacto(usuarioActual)){
				ci = new ContactoIndividual(usuarioActual.getMovil(), usuarioActual.getMovil(), usuarioActual);
				addContactoIndividual(r.getNick(), ci);
			}else{
				ci = r.getContactoIndividual(usuarioActual);
			}
			
			c.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapCI.modificarContactoIndividual((ContactoIndividual)c);
			ci.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapCI.modificarContactoIndividual(ci);
		}else{
			Grupo g = (Grupo)c;
			g.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapGP.modificarGrupo(g);
		}
	}

	public void cMensajeTexto(String t, Contacto c) {
		Mensaje m = new Mensaje(t);
		m.setEmisor(usuarioActual);
		m.setReceptor(c);
		
		if(c instanceof ContactoIndividual){
			Usuario r=((ContactoIndividual) c).getUsuario();
			
			ContactoIndividual ci = null;
			if(!r.comprobarContacto(usuarioActual)){
				ci = new ContactoIndividual(usuarioActual.getMovil(), usuarioActual.getMovil(), usuarioActual);
				addContactoIndividual(r.getNick(), ci);
			}else{
				ci = r.getContactoIndividual(usuarioActual);
			}
			
			c.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapCI.modificarContactoIndividual((ContactoIndividual)c);
			ci.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapCI.modificarContactoIndividual(ci);
		}else{
			Grupo g = (Grupo)c;
			g.addMensaje(m);
			adapMS.registrarMensaje(m);
			adapGP.modificarGrupo(g);
		}
	}

	// GetGRupo nick (NO VA?)
	public Grupo getGrupo(String nombre) {
		Grupo g = usuarioActual.getGrupo(nombre);

		if (g != null) {
			return g;
		} else {
			return null;
		}
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

		parrafo = new Paragraph("Contactos Individuales\n",FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK));
		doc.add(parrafo);
		for (ContactoIndividual ci : usuarioActual.getContactosIndividuales()) {
			parrafo = new Paragraph("		[Nick: " + ci.getNombre() + "-  Móvil: " + ci.getMovil() + "]\n");
			doc.add(parrafo);
		}

		parrafo = new Paragraph("\nGrupos\n",FontFactory.getFont(FontFactory.COURIER, 26, BaseColor.BLACK));
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

	
	//Método para eliminar a un Contacto
	public boolean eliminarContacto(Contacto c) {
		//TODO Comprobar usuario como admin
		if(usuarioActual.getContactos().contains(c)) {
			if(c instanceof Grupo) {
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