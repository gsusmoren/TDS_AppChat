package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import modelo.Mensaje;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorMensajeTDS implements IAdaptadorMensajeDAO {
	
	private static ServicioPersistencia servPersistencia;
	//HORA?????
	private SimpleDateFormat hourFormat;

	private static AdaptadorMensajeTDS unicaInstancia;
	
	public static AdaptadorMensajeTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorMensajeTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorMensajeTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		//HORAAAAAAAAAAAA
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
		
		eMensaje = new Entidad();
		
		eMensaje.setNombre("mensaje");
		eMensaje.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("texto",m.getTexto()), new Propiedad("hora",hourFormat.format(m.getHora())), new Propiedad("emoji", m.getEmoji(
						))));
		
	}

	public void borrarMensajes() {
		// TODO Auto-generated method stub
		
	}

	public Mensaje recuperarMensaje(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

}
