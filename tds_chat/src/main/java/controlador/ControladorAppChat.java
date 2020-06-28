package controlador;

import java.util.Date;
import java.util.List;

import dao.DAOException;
import dao.FactoriaDAO;
import dao.IAdaptadorContactoInidivualDAO;
import dao.IAdaptadorGrupoDAO;
import dao.IAdaptadorMensajeDAO;
import dao.IAdaptadorUsuarioDAO;
import modelo.CatalogoUsuarios;
import modelo.Contacto;
import modelo.ContactoIndividual;
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
	
	public void  actualizarCI(ContactoIndividual ci) {
		adapCI.modificarContactoIndividual(ci);
	}

	// Registramos un nuevo Contacto Individual y actualizamos el usuario actual.
	public boolean addContactoIndividual(String nombre, String numero) {
		/*
		 * quién crea el contacto individual, el usuario o el controlador. Un contacto
		 * está asociado a otro usuario. Si lo crea el usuario debería d conocer los
		 * demás usuarios, eso está mal.
		 */
		
		Usuario ciUser = catalogoUsuarios.getUsuarioMovil(numero);
		if(ciUser!=null){
			ContactoIndividual ci = new ContactoIndividual(nombre, numero, ciUser);
			boolean isReg = usuarioActual.addContacto(ci);
			if (isReg) {
				
				adapCI.registrarContactoIndividual(ci);
				
				catalogoUsuarios.actualizarUsuario(usuarioActual);
				adapU.modificarUsuario(usuarioActual);
				
				return true;
			}
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
