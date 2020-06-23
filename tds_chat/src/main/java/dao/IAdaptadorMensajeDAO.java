package dao;

import java.util.List;

import modelo.Mensaje;

public interface IAdaptadorMensajeDAO {
	public void registrarMensaje(Mensaje m);
	public void borrarTodosMensajes();
	public Mensaje recuperarMensaje(int cod);
	public List<Mensaje> recuperarTodosMensajes();
	//¿BORRAR MENSAJE?
}
