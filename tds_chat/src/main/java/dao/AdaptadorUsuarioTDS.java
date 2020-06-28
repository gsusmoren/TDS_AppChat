package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import modelo.Contacto;
import modelo.ContactoIndividual;

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
			} else {
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
				new Propiedad("nick", u.getNick()), 
				new Propiedad("password", u.getContrasena()),
				new Propiedad("email", u.getEmail()),
				new Propiedad("imagen", u.getImagen()),
				new Propiedad("saludo",u.getSaludo()),
				//premium como string
				new Propiedad("premium", String.valueOf(u.isPremium())),
				new Propiedad("contactos", getCodigosContactoInd(u.getContactos())),
				new Propiedad("grupos", getCodigosGrupos(u.getContactos()))
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
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "saludo", u.getSaludo());
		//Eliminar y añadir Contactos
		
		String ctcs = getCodigosContactoInd(u.getContactos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contactos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contactos", ctcs);
		
		String grps = getCodigosGrupos(u.getContactos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "grupos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "grupos", grps);
		
		
		
	}

	public Usuario recuperarUsuario(int codigo) {
	
		// Comprobar si ya se ha recuperado el usuario con POOL
		if (PoolDAO.getUnicaInstancia().contiene(codigo)) {
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		}

		Entidad eUsuario;
		
		String nombre;
		Date fechaNacimiento = null;
		String movil;
		String nick;
		String password;
		String imagen;
		String saludo;
		String email;
		String premium;
		List<Contacto> conInd = new LinkedList<Contacto>();
		List<Contacto> grupos = new LinkedList<Contacto>();
		

		eUsuario = servPersistencia.recuperarEntidad(codigo);

		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		try {
		fechaNacimiento =  new SimpleDateFormat("yyyy-mm-dd").parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		}catch (ParseException e) {
			e.printStackTrace();
		}

		movil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "movil");
		nick = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nick");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		imagen = servPersistencia.recuperarPropiedadEntidad(eUsuario, "imagen");
		saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, "saludo");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium");
		
		
		Usuario u = new Usuario(nombre,fechaNacimiento, movil, email, password, nick, imagen, saludo);
		u.setId(codigo);
		
		if(premium.equals("true")) {
			u.setPremium(true);
		}else u.setPremium(false);
		
		//Añadir el usuario al pool antes de llamar a los demás adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		//Recuperar propiedades que son objetos 
		//Uso de método auxiliar usando códigos devuelve contacto
		
		conInd = obtenerContactoIndDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "contactos"));
		grupos = obtenerGruposDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "grupos"));
		
		for(Contacto ind : conInd) {
			u.addContacto(ind);
		}
		for(Contacto g : grupos) {
			u.addContacto(g);
		}
		
		return u;
	}

	public List<Usuario> recuperarTodosUsuarios() {
	List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("Usuario");
	List<Usuario> usuarios = new LinkedList<Usuario>();
	
	for(Entidad eU : eUsuarios) {
		usuarios.add(recuperarUsuario(eU.getId()));
	}
		return usuarios;
	}
	
	
	
	
	/*
	 * -------------------Métodos auxiliares----------------------------
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
	
	//Obtener Grupo desde Codigos
	private List<Contacto> obtenerGruposDesdeCodigos(String gps){
		List<Contacto> grupos = new LinkedList<Contacto>()	;
		
		if (gps == null || gps.equals(""))
			return grupos;
		
		StringTokenizer strTok = new StringTokenizer(gps," ");
		AdaptadorGrupoTDS adapGP = AdaptadorGrupoTDS.getUnicaInstancia();
		
		while(strTok.hasMoreTokens())
			grupos.add(adapGP.recuperarGrupo(Integer.valueOf((String) strTok.nextElement())));
		return grupos;
			
		
	}
	
	//Obtener Grupo desde Codigos
	private List<Contacto> obtenerContactoIndDesdeCodigos(String cts){
		List<Contacto> conts = new LinkedList<Contacto>();
		
		if (cts == null || cts.equals("")) return conts;
		
		StringTokenizer strTok = new StringTokenizer(cts," ");
		AdaptadorContactoIndividualTDS adapCI = AdaptadorContactoIndividualTDS.getUnicaInstancia();
		
		while(strTok.hasMoreTokens())
			conts.add(adapCI.recuperarContactoIndividual(Integer.valueOf((String) strTok.nextElement())));
		return conts;
	}
	

}
