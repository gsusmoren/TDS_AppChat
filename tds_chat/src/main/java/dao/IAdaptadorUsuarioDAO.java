package dao;

import java.util.List;

import modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	public void registrarUsuario(Usuario u);
	public void borrarUsuario(Usuario u);
	public void modificarUsuario(Usuario u);
	public Usuario recuperarUsuario(int cod);
	public List<Usuario> recuperarTodosUsuarios();
}
