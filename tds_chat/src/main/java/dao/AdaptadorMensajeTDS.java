package dao;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorMensajeTDS implements IAdaptadorMensajeDAO {

	private static ServicioPersistencia servPersistencia;
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
		hourFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	}

	public void registrarMensaje(Mensaje m) {
		Entidad eMensaje;
		boolean existe = true;

		try {
			eMensaje = servPersistencia.recuperarEntidad(m.getId());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// Añadir RECEPTOR y EMISOR del mensaje
		// Atributos que son Objetos (Contacto y Usuario)

		Contacto c = m.getReceptor();
		if (c instanceof ContactoIndividual) {
			AdaptadorContactoIndividualTDS adapCI = AdaptadorContactoIndividualTDS.getUnicaInstancia();
			ContactoIndividual ci = (ContactoIndividual) c;
			adapCI.registrarContactoIndividual(ci);
		} else if (c instanceof Grupo) {
			AdaptadorGrupoTDS adapGP = AdaptadorGrupoTDS.getUnicaInstancia();
			Grupo gr = (Grupo) c;
			adapGP.registrarGrupo(gr);

		}
		//Guardar el tiepo de contacto?
		// Registrar Usuario emisor
		AdaptadorUsuarioTDS adapU = AdaptadorUsuarioTDS.getUnicaInstancia();
		adapU.registrarUsuario(m.getEmisor());

		eMensaje = new Entidad();
		eMensaje.setNombre("Mensaje");
		eMensaje.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						
				new Propiedad("texto", m.getTexto()),
				new Propiedad("hora", hourFormat.format(m.getHora())),
				new Propiedad("emoji", String.valueOf(m.getEmoji())),
				new Propiedad("emidor",String.valueOf(m.getEmisor().getId())),
				new Propiedad("receptor",String.valueOf(m.getReceptor().getId())))));

		// Registrar Entidad Mensaje
		eMensaje = servPersistencia.registrarEntidad(eMensaje);
		// asignar identificador unico
		m.setId(eMensaje.getId());
	}

	// ¿BORRAR MENSAJE? +Si
	public void borrarMensaje(Mensaje m) {
	
		Entidad eMensaje = servPersistencia.recuperarEntidad(m.getId());
		servPersistencia.borrarEntidad(eMensaje);
		
	}

	// No estoy seguro de que sea correcto, lo mismo esta operacion la tiene q hacer
	// el contacto
	public void borrarTodosMensajes() {

		List<Entidad> entidades = servPersistencia.recuperarEntidades("Mensaje");
		for (Entidad e : entidades) {
			servPersistencia.borrarEntidad(e);
		}
	}

	public Mensaje recuperarMensaje(int cod) {
		
		Entidad eMensaje;
		String texto;
		LocalDateTime hora;
		int emoji;

		eMensaje = servPersistencia.recuperarEntidad(cod);
		
		texto 	= servPersistencia.recuperarPropiedadEntidad(eMensaje, "texto");
		hora	= LocalDateTime.parse(servPersistencia.recuperarPropiedadEntidad(eMensaje, "hora"));
		emoji 	= Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, "emoji"));
		Mensaje mensaje;
		if (emoji == -1) {
			//Cambiado el constructor.J
			mensaje = new Mensaje(texto);
		} else {
			mensaje = new Mensaje(emoji);
		}
		//Añadir al Pool para evitar bucles por bidireccionalidad J
		PoolDAO.getUnicaInstancia().addObjeto(cod, mensaje);
		
		//Establecer Emisor y Receptor	J
		AdaptadorUsuarioTDS adapU = AdaptadorUsuarioTDS.getUnicaInstancia();
		Usuario u = adapU.recuperarUsuario(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje,"emisor")));
		mensaje.setEmisor(u);
		
		int idReceptor = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje,"receptor"));
		
		if(servPersistencia.recuperarEntidad(idReceptor).getNombre().equals("Grupo")) {
			AdaptadorGrupoTDS adapGP = AdaptadorGrupoTDS.getUnicaInstancia();
			Grupo gReceptor = adapGP.recuperarGrupo(idReceptor) ;
			mensaje.setReceptor(gReceptor);
		}else {
			//Es un Contacto Indidual, no un Grupo
			AdaptadorContactoIndividualTDS adapCI = AdaptadorContactoIndividualTDS.getUnicaInstancia();
			ContactoIndividual ci = adapCI.recuperarContactoIndividual(idReceptor);
			mensaje.setReceptor(ci);
			
		}
		
		// hay que poner la hora anterior, no la nueva, que se crea con la creacion de
		// cualquier mensaje
		mensaje.setHora(hora);
		mensaje.setId(cod);
		
		return mensaje;

	}

	public List<Mensaje> recuperarTodosMensajes() {
		List<Mensaje> mensajes = new LinkedList<Mensaje>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("Mensaje");

		for (Entidad eMensaje : entidades) {
			mensajes.add(recuperarMensaje(eMensaje.getId()));
		}
		return mensajes;
	}


}