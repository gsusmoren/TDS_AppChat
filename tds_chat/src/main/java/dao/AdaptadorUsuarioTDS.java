package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import modelo.Contacto;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia;
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

	public void registrarUsuario(Usuario u) {
		Entidad eUsuario;
		boolean existe = true;
		
		try {
			eUsuario = servPersistencia.recuperarEntidad(u.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;
		
		/*REGISTRAR PRIMERO LOS ATRIBUTOS QUE SON OBJETOS (AMBOS TIPOS DE CONTACTOS?????)*/
		
		
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre",String.valueOf(u.getNombre())), 
						new Propiedad("fechaNacimiento",formatoFecha.format(u.getFechaNacimiento())), 
						new Propiedad("movil",String.valueOf(u.getMovil())), 
						new Propiedad("email", String.valueOf(u.getEmail())), 
						new Propiedad("contraseña", String.valueOf(u.getContrasena())), 
						new Propiedad("login", String.valueOf(u.getNick())))));
		//AÑADIR PREMIUM????????????
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		u.setId(eUsuario.getId());
	}

	public void borrarUsuario(Usuario u) {
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getId());
		
		servPersistencia.borrarEntidad(eUsuario);
	}
	
	public void modificarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getId());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", String.valueOf(u.getNombre()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "movil");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "movil", String.valueOf(u.getMovil()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", String.valueOf(u.getEmail()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "contraseña");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "contraseña", String.valueOf(u.getContrasena()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNacimiento", formatoFecha.format(u.getFechaNacimiento()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "login");
		servPersistencia.anadirPropiedadEntidad(eUsuario,"login", String.valueOf(u.getNick()));
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
