package controlador;

import java.util.Date;
import java.util.List;

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
			// boolean isReg = usuarioActual.addContacto(ci);
			if (cc == null)
				return false;
			adapCI.registrarContactoIndividual(cc);
			adapU.modificarUsuario(usuarioActual);
			catalogoUsuarios.actualizarUsuario(usuarioActual);
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
		usuarioActual.removeContacto(ci);
		adapU.modificarUsuario(usuarioActual);
		return true;
	}

	//  Método para crear un nuevo grupo.
	public Grupo crearGrupo(String nombre, List<ContactoIndividual> ctcs) {
		Grupo grupo = usuarioActual.crearGrupo(nombre, ctcs);
		
		if(grupo!=null){
			grupo.setAdmin(usuarioActual);
			adapGP.registrarGrupo(grupo);
			adapU.modificarUsuario(usuarioActual);
			catalogoUsuarios.actualizarUsuario(usuarioActual);
			
			for(ContactoIndividual ci : ctcs){
				Usuario u = ci.getUsuario();
				u.addContacto(grupo);
				adapU.modificarUsuario(u);
				catalogoUsuarios.actualizarUsuario(u);
			}
			return grupo;
		}
		return null;

	}
	
	public Mensaje cMensajeGrupo(String m, int e, Grupo g){
		Mensaje msj;
		if(e==-1) msj = new Mensaje(m);
		else msj = new Mensaje(e);
		msj.setEmisor(usuarioActual);
		g.addMensaje(msj);
		
		adapMS.registrarMensaje(msj);
		adapGP.modificarGrupo(g);
		return msj;
	}
	
	public Mensaje cMensajeCI(String m, int e, ContactoIndividual c){
		Mensaje msj;
		if(e==-1) msj = new Mensaje(m);
		else msj = new Mensaje(e);
		msj.setEmisor(usuarioActual);
		c.addMensaje(msj);
		
		adapMS.registrarMensaje(msj);
		adapCI.modificarContactoIndividual(c);
		
		//Comprobar si c tiene a usuarioActual como contacto
		ContactoIndividual c2 = null;
		Usuario u2=c.getUsuario();
		if(u2.comprobarContacto(usuarioActual)){
			c2 = u2.getContactoIndividual(usuarioActual);
		}else{
			c2 = u2.addContactoI(usuarioActual.getMovil(), usuarioActual.getMovil(), usuarioActual);
			adapCI.registrarContactoIndividual(c2);
			adapU.modificarUsuario(u2);
		}
		Mensaje msj1;
		if(e==-1) msj1 = new Mensaje(m);
		else msj1 = new Mensaje(e);
		msj1.setEmisor(usuarioActual);
		c2.addMensaje(msj1);
		
		adapMS.registrarMensaje(msj1);
		adapU.modificarUsuario(u2);
		
		return msj;
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