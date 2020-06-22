package dao;

import java.util.List;

import beans.Entidad;
import modelo.Grupo;
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
		
		if (existe)
			return;
		// Registrar primero los atributos que son objetos
		
	}

	public void borrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
	}

	public void modificarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
	}

	public Grupo recuperarGrupo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Grupo> recuperarTodosGrupos() {
		// TODO Auto-generated method stub
		return null;
	}

}
