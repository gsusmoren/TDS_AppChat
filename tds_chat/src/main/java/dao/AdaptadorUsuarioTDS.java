package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Descuento;
import modelo.Grupo;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat formatoFecha;

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		formatoFecha = new SimpleDateFormat("dd-MM-yy");
	}
	//==================
	// REGISTRAR USUARIO
	//==================
	public void registrarUsuario(Usuario u) {
		Entidad eUsuario;
		boolean existe = true;

		try {
			eUsuario = servPersistencia.recuperarEntidad(u.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// REGISTRAR PRIMERO LOS ATRIBUTOS QUE SON OBJETOS
		AdaptadorContactoIndividualTDS adaptCI = AdaptadorContactoIndividualTDS.getUnicaInstancia();
		AdaptadorGrupoTDS adapGP = AdaptadorGrupoTDS.getUnicaInstancia();
		// Devolver contactos viendo el tipo
		for (Contacto c : u.getContactos()) {
			if (c instanceof ContactoIndividual) {
				ContactoIndividual ci = (ContactoIndividual) c;
				adaptCI.registrarContactoIndividual(ci);
			} else if (c instanceof Grupo) {
				Grupo gp = (Grupo) c;
				adapGP.registrarGrupo(gp);
			}
		}

		eUsuario = new Entidad();
		eUsuario.setNombre("Usuario");

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("nombre", u.getNombre()),
				new Propiedad("fechaNacimiento", formatoFecha.format(u.getFechaNacimiento())),
				new Propiedad("movil", u.getMovil()),
				new Propiedad("password", u.getContrasena()),
				new Propiedad("email", u.getEmail()),
				new Propiedad("imagen", u.getImagen()),
				//premium como string
				new Propiedad("premium", String.valueOf(u.isPremium()))
				
				)));
		
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		//Asignar identificador único
		u.setId(eUsuario.getId());
	}

	//==================
	//  BORRAR USUARIO
	//==================
	public void borrarUsuario(Usuario u) {

		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getId());

		servPersistencia.borrarEntidad(eUsuario);
	}
	
	//==================
	//MODIFICAR USUARIO
	//==================
	
	public void modificarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getId());

		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", u.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNacimiento",
				formatoFecha.format(u.getFechaNacimiento()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "movil");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "movil", u.getMovil());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", u.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", u.getContrasena());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "login");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "login", u.getNick());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "imagen");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "imagen", u.getImagen());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", String.valueOf(u.isPremium()));
		//Eliminar y añadir Contactos
		
		String ctcs = getCodigosContactoInd(u.getContactos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contactos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contactos", ctcs);
		
		String grps = getCodigosContactoInd(u.getContactos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "grupos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "grupos", grps);
		
		
		
	}

	public Usuario recuperarUsuario(int cod) {
		// TODO Auto-generated method stub
		// Comprobar si ya se ha recuperado el usuario con POOL
		// if(PoolDAO.get)
		if (PoolDAO.getUnicaInstancia().contiene(cod)) {
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(cod);
		}

		Entidad eUsuario;
		List<Contacto> contactos = new LinkedList<Contacto>();
		String nombre;
		String fechaNacimiento;
		String movil;
		String nick;
		String contrasena;
		String imagen;
		String email;
		boolean premium;

		eUsuario = servPersistencia.recuperarEntidad(cod);

		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento");
		movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");

		return null;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	/*
	 * Métodos auxiliares
	 */
	
	//Obtener códigos de los contactos por su listas
	private String getCodigosContactoInd(List<Contacto> cts) {
		String cadena = "";
		for(Contacto c : cts) {
			if(c instanceof ContactoIndividual) {
				ContactoIndividual ci = (ContactoIndividual) c;
				cadena += ci.getId()+ " ";		
			}
		}
		return cadena.trim();
	}
	
	//Obtener códigos de los grupos como string dada la lista
	private String getCodigosGrupos(List<Contacto> cts) {
		String cadena = "";
		for(Contacto c : cts) {
			if(c instanceof Grupo) {
				Grupo gr = (Grupo) c;
				cadena += gr.getId()+ " ";
			}
		}
		return cadena.trim();
	}
	

}
