package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import modelo.Venta;
import persistencia.AdaptadorVentaTDS;
import modelo.Contacto;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarUsuario(Usuario u) {
		Entidad eUsuario;
		boolean existe = true;
		
		try {
			eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;
		
		/*REGISTRAR PRIMERO LOS ATRIBUTOS QUE SON OBJETOS (AMBOS TIPOS DE CONTACTOS?????)*/
		/*AdaptadorContactoIndividualTDS adaptadorVenta = AdaptadorVentaTDS.getUnicaInstancia();
		for (Venta v : cliente.getVentas())
			adaptadorVenta.registrarVenta(v);
		*/
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre",u.getNombre()), new Propiedad("fecha",u.getFechaNacimiento().toString()), new Propiedad("movil",String.valueOf(u.getMovil()))
						, new Propiedad("email", u.getEmail()), new Propiedad("contraseña", u.getContrasena()), new Propiedad("nicku", u.getNick()))));
		//AÑADIR PREMIUM????????????
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		u.setCodigo(eUsuario.getId());
	}

	public void borrarUsuario(Usuario u) {
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		
		servPersistencia.borrarEntidad(eUsuario);
	}
	
	public void modificarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", u.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "movil");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "movil", String.valueOf(u.getMovil()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", u.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contraseña");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contraseña", u.getContrasena());
		//CONTACTOOOOOOOOOOOOOOOOOOOOOSSSSSSS
	}

	public Usuario recuperarUsuario(int cod) {
		// TODO Auto-generated method stub
		//Comprobar si ya se ha recuperado el usuario con POOL
		//if(PoolDAO.get)
		
		Entidad eUsuario;
		List<Contacto> contactos = new LinkedList<Contacto>();
		
		eUsuario = servPersistencia.recuperarEntidad(cod);
		
		
		return null;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
