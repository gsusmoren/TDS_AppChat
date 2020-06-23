package dao;

import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import modelo.ContactoIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorContactoIndividualTDS implements IAdaptadorContactoInidivualDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorContactoIndividualTDS unicaInstancia = null;

	public static AdaptadorContactoIndividualTDS getUnicaInstancia() {
		// patron singleton
		if (unicaInstancia == null)
			return new AdaptadorContactoIndividualTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorContactoIndividualTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	
	
	public void registrarContactoIndividual(ContactoIndividual c) {
		Entidad eCIndividual;
		boolean existe = true;
		try {
			eCIndividual = servPersistencia.recuperarEntidad(c.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if(existe) return;
		
		AdaptadorMensajeTDS aMensaje = AdaptadorMensajeTDS.getUnicaInstancia();
		for (Mensaje m : c.getListaMensajes()){
			aMensaje.registrarMensaje(m);
		}
		//registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS aUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		aUsuario.registrarUsuario(c.getUsuario());
		eCIndividual = new Entidad();
		eCIndividual.setNombre("ContactoIndividual");
		eCIndividual.setPropiedades(Arrays.asList(
				new Propiedad("nombre", c.getNombre()),
				new Propiedad("movil", String.valueOf(c.getMovil())),
				new Propiedad("usuario", String.valueOf(c.getUsuario())),
				new Propiedad("listaMensajes", String.valueOf(c.getListaMensajes()))));
		
		eCIndividual = servPersistencia.registrarEntidad(eCIndividual);
		c.setId(eCIndividual.getId());
	}

	public void borrarContactoIdividual(ContactoIndividual c) {
		Entidad eCIndividual;
		AdaptadorMensajeTDS aMensaje = AdaptadorMensajeTDS.getUnicaInstancia();
		for (Mensaje m : c.getListaMensajes()){
			//BORRAR CADA UNO DE LOS MENSAJES
		}
		eCIndividual = servPersistencia.recuperarEntidad(c.getId());
		servPersistencia.borrarEntidad(eCIndividual);
	}

	public void modificarContactoIndividual(ContactoIndividual c) {
		Entidad eCIndividual = servPersistencia.recuperarEntidad(c.getId());
		servPersistencia.eliminarPropiedadEntidad(eCIndividual, "nombre");
		servPersistencia.anadirPropiedadEntidad(eCIndividual, "nombre", c.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eCIndividual, "movil");
		servPersistencia.anadirPropiedadEntidad(eCIndividual, "movil", String.valueOf(c.getMovil()));
		servPersistencia.eliminarPropiedadEntidad(eCIndividual, "usuario");
		servPersistencia.anadirPropiedadEntidad(eCIndividual, "usuario", String.valueOf(c.getUsuario()));
		servPersistencia.eliminarPropiedadEntidad(eCIndividual, "listaMensajes");
		servPersistencia.anadirPropiedadEntidad(eCIndividual, "listaMensajes", String.valueOf(c.getListaMensajes()));
	}

	public ContactoIndividual recuperarContactoIndividual(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ContactoIndividual) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		Entidad eCIndividual = servPersistencia.recuperarEntidad(codigo);
		String nombre = servPersistencia.recuperarPropiedadEntidad(eCIndividual, "nombre");
		int movil = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCIndividual, "movil"));
		int usuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCIndividual, "usuario"));
		ContactoIndividual contact = new ContactoIndividual(nombre, movil);
		contact.setId(codigo);
		PoolDAO.getUnicaInstancia().addObjeto(codigo, contact);
		
		AdaptadorUsuarioTDS aUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		Usuario u = aUsuario.recuperarUsuario(usuario);
		contact.setUsuario(u);
		
		//OBTENER LOS MENSAJES E INTRODUCIRLOS EN EL CONTACTO INDIVIDUAL
		
		return contact;
	}

}
