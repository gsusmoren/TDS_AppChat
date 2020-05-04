package dao;

import beans.Entidad;
import modelo.ContactoIndividual;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorContactoIndividualTDS implements IAdaptadorContactoInidivualDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorContactoIndividualTDS unicaInstancia;

	public static AdaptadorContactoIndividualTDS getUnicaInstancia() {
		// patron singleton
		if (unicaInstancia == null)
			return new AdaptadorContactoIndividualTDS();
		else
			return unicaInstancia;
	}
	//¿Como funciona FactoríaServPErsistencia?
	private AdaptadorContactoIndividualTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	
	
	public void registrarContactoIndividual(ContactoIndividual c) {
		Entidad eCIndividual;
		boolean existe = true;
		try {
			eCIndividual = servPersistencia.recuperarEntidad(c.getMovil());
			
		} catch (NullPointerException e) {
			existe = false;
		}
		if(existe) return;
		
		//registrar primero los atributos que son objetos
		

	}

	public void borrarContactoIdividual(ContactoIndividual c) {
		// TODO Auto-generated method stub

	}

	public void modificarContactoIndividual(ContactoIndividual c) {
		// TODO Auto-generated method stub

	}

	public ContactoIndividual recuperarContactoIndividual(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
