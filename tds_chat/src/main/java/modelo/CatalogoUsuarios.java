package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import dao.DAOException;
import dao.FactoriaDAO;
import dao.IAdaptadorUsuarioDAO;

/* El catálogo mantiene los objetos en memoria, en una tabla hash
 * para mejorar el rendimiento. Esto no se podría hacer en una base de
 * datos con un número grande de objetos. En ese caso se consultaria
 * directamente la base de datos
 */

public class CatalogoUsuarios {
	private Map<String,Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private IAdaptadorUsuarioDAO adaptUS;
	
	//Contructor
	private CatalogoUsuarios() {
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptUS = factoria.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		}catch(DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia() {
		if(unicaInstancia == null) 
			unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	//Devuelve todos los usuarios
	public List<Usuario> getUsuarios(){
		return new LinkedList<Usuario>(usuarios.values());
	}
	
	//Usuario por movil
	public Usuario getUsuario(int movil) {
		for(Usuario u : usuarios.values()) {
			if(u.getMovil().equals(String.valueOf(movil)))
				return u;
		}
		return null;
	}
	//Usuario por nick
	public Usuario getUsuario(String nick) {
		return usuarios.get(nick);
	}
	
	//Añadir Usuario al Catalogo
	public void addUsuario(Usuario u) {
		usuarios.put(u.getNick(),u);
		
	}
	public void removeUsuario(Usuario u) {
		usuarios.remove(u.getNick());
	}
	//Recupera todos los Usuarios para trabajar con ellos en memoria
	private void cargarCatalogo() throws DAOException{
		List<Usuario> usuariosDB = adaptUS.recuperarTodosUsuarios();
		for(Usuario us : usuariosDB) {
			usuarios.put(us.getNick(), us);
		}
	}
	
	
	
		
}
