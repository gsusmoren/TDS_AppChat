package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorGrupoTDS implements IAdaptadorGrupoDAO{
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorGrupoTDS unicaInstancia = null;
	
	public static AdaptadorGrupoTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorGrupoTDS();
		else
			return unicaInstancia;
	}
	
	public AdaptadorGrupoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
		
	public void registrarGrupo(Grupo grupo) {
		
		Entidad eGrupo;
		boolean existe = true;
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if (existe) return;
		
		// Registrar primero los atributos que son objetos
		AdaptadorMensajeTDS aMensaje = AdaptadorMensajeTDS.getUnicaInstancia();
		for (Mensaje m : grupo.getListaMensajes()){
			aMensaje.registrarMensaje(m);
		}
		
		AdaptadorUsuarioTDS aUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		aUsuario.registrarUsuario(grupo.getAdmin());
	
		AdaptadorContactoIndividualTDS aContacto = AdaptadorContactoIndividualTDS.getUnicaInstancia();
		for (ContactoIndividual c : grupo.getContactos()){
			aContacto.registrarContactoIndividual(c);
		}
		
		eGrupo = new Entidad();
		
		eGrupo.setNombre("grupo");
		eGrupo.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", grupo.getNombre()),
						new Propiedad("mensajes", obtenerIdListaMensajes(grupo.getListaMensajes())),
						new Propiedad("admin", String.valueOf(grupo.getAdmin().getId())),
						new Propiedad("contactos", obtenerIdContactos(grupo.getContactos())))));
		eGrupo = servPersistencia.registrarEntidad(eGrupo);
		grupo.setId(eGrupo.getId());
	}

	public void borrarGrupo(Grupo grupo) {
		Entidad eGrupo;
		AdaptadorMensajeTDS aMensaje = AdaptadorMensajeTDS.getUnicaInstancia();
		for (Mensaje m : grupo.getListaMensajes()){
			aMensaje.borrarMensaje(m);
		}
		eGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		servPersistencia.borrarEntidad(eGrupo);
	}

	public void modificarGrupo(Grupo grupo) {
		Entidad eGrupo;
		
		eGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "nombre");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "nombre", grupo.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "mensajes");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "mensajes", obtenerIdListaMensajes(grupo.getListaMensajes()));
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "admin");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "admin", String.valueOf(grupo.getAdmin().getId()));
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "contactos");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "contactos", obtenerIdContactos(grupo.getContactos()));

	}

	public Grupo recuperarGrupo(int id) {
		if (PoolDAO.getUnicaInstancia().contiene(id))
			return (Grupo) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad eGrupo = servPersistencia.recuperarEntidad(id);
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");
		
		Grupo grupo = new Grupo(nombre);
		
		PoolDAO.getUnicaInstancia().addObjeto(id, grupo);
		
		grupo.setId(id);
		
		List<Mensaje> mensajes = obtenerMensajesCodigo(
				servPersistencia.recuperarPropiedadEntidad(eGrupo, "mensajes"));
		grupo.setListaMensajes(mensajes);
		
		AdaptadorUsuarioTDS aUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codU = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eGrupo, "admin"));
		Usuario admin = aUsuario.recuperarUsuario(codU);
		grupo.setAdmin(admin);
		
		List<ContactoIndividual> contactos = obtenerContactosCodigo(
				servPersistencia.recuperarPropiedadEntidad(eGrupo, "contactos"));
		grupo.setContactos(contactos);
		
		return grupo;
	}

	

	public List<Grupo> recuperarTodosGrupos() {
		List<Grupo> grupos = new LinkedList<Grupo>();
		List<Entidad> eGrupos= servPersistencia.recuperarEntidades("grupo");
		
		for (Entidad e : eGrupos){
			grupos.add(recuperarGrupo(e.getId()));
		}
		return grupos;
	}

	private String obtenerIdListaMensajes(List<Mensaje> listaMensajes){
		String mensajes="";
		for(Mensaje m : listaMensajes){
			mensajes += m.getId() + " ";
		}
		return mensajes.substring(0, mensajes.length()-1);
	}
	
	private String obtenerIdContactos(List<ContactoIndividual> contactos) {
		String cont="";
		for(ContactoIndividual c : contactos){
			cont += c.getId() + " ";
		}
		return cont.substring(0, cont.length()-1);
	}
	
	private List<Mensaje> obtenerMensajesCodigo(String m){
		List<Mensaje> mensajes = new LinkedList<Mensaje>();
		AdaptadorMensajeTDS aMensaje = AdaptadorMensajeTDS.getUnicaInstancia();
		StringTokenizer strTok = new StringTokenizer(m, " ");
		while(strTok.hasMoreTokens()){
			mensajes.add( aMensaje.recuperarMensaje(
							Integer.valueOf((String)strTok.nextElement())));
		}
		return mensajes;
	}
	
	private List<ContactoIndividual> obtenerContactosCodigo(String c) {
		List<ContactoIndividual> contactos = new LinkedList<ContactoIndividual>();
		AdaptadorContactoIndividualTDS aContacto = AdaptadorContactoIndividualTDS.getUnicaInstancia();
		StringTokenizer strTok = new StringTokenizer(c, " ");
		while(strTok.hasMoreTokens()){
			contactos.add(aContacto.recuperarContactoIndividual(
					Integer.valueOf((String)strTok.nextElement())));
		}
		return contactos;
	}
}
