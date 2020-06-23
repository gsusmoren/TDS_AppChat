package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Mensaje;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorMensajeTDS implements IAdaptadorMensajeDAO {

	private static ServicioPersistencia servPersistencia;
	// HORA?????
	private SimpleDateFormat hourFormat;

	private static AdaptadorMensajeTDS unicaInstancia = null;

	public static AdaptadorMensajeTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorMensajeTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorMensajeTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		// HORAAAAAAAAAAAA
		hourFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	}

	public void registrarMensaje(Mensaje m) {
		Entidad eMensaje;

		boolean existe = true;
		try {
			eMensaje = servPersistencia.recuperarEntidad(m.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if(existe) return;
		
		//Añadir RECEPTOR y EMISOR del mensaje
		
		
		eMensaje = new Entidad();
		eMensaje.setNombre("mensaje");
		eMensaje.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("texto",m.getTexto()), new Propiedad("hora",hourFormat.format(m.getHora())), 
						new Propiedad("emoji",String.valueOf(m.getEmoji())))));
			
		//Registrar Entidad Mensaje
		eMensaje = servPersistencia.registrarEntidad(eMensaje);
		//asignar identificador unico
		m.setCodigo(eMensaje.getId());
	}
	
	//¿BORRAR MENSAJE?
	
	public void borrarTodosMensajes() {
		//No estoy seguro de que sea correcto, lo mismo esta operacion la tiene q hacer el contacto
		List<Entidad> entidades = servPersistencia.recuperarEntidades("mensaje");
		for(Entidad e: entidades) {
			servPersistencia.borrarEntidad(e);
		}
	}

	public Mensaje recuperarMensaje(int cod) {
		Entidad eMensaje;
		String texto;
		LocalDateTime hora;
		int emoji;
		
		eMensaje = servPersistencia.recuperarEntidad(cod);
		texto = servPersistencia.recuperarPropiedadEntidad(eMensaje,"texto");
		hora = LocalDateTime.parse(servPersistencia.recuperarPropiedadEntidad(eMensaje, "hora"));
		emoji = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje,"emoji"));
		Mensaje mensaje;
		if(emoji ==-1) {
		mensaje = new Mensaje(texto);
		}else {
			mensaje = new Mensaje(emoji);
		}
		//hay que poner la hora anterior, no la nueva, que se crea con la creacion de cualquier mensaje
		
		mensaje.setHora(hora);
		mensaje.setCodigo(cod);
		return mensaje;
		
	}
	
	public List<Mensaje> recuperarTodosMensajes() {
		List<Mensaje> mensajes = new LinkedList<Mensaje>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("mensaje");

		for (Entidad eMensaje : entidades) {
			mensajes.add(recuperarMensaje(eMensaje.getId()));
		}
		return mensajes;
	}

	


}