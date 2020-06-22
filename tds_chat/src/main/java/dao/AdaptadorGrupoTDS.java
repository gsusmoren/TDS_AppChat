package dao;

import java.util.List;

import modelo.Grupo;
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
	
		
	public void registrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
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
