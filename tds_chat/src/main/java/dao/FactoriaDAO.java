package dao;

import dao.DAOException;
import dao.FactoriaDAO;

public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "dao.TDSFactoriaDAO";
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
			} 
		return unicaInstancia;
	}
	
	public static FactoriaDAO getInstancia() throws DAOException{
		if (unicaInstancia == null) return getInstancia (FactoriaDAO.DAO_TDS);
				else return unicaInstancia;
	}
	
	/* Constructor */
	protected FactoriaDAO (){}
	
	
	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	public abstract IAdaptadorGrupoDAO getGrupoDAO();
	public abstract IAdaptadorContactoInidivualDAO getContactoInidivualDAO();
	public abstract IAdaptadorMensajeDAO getMensajeDAO();
	
}
