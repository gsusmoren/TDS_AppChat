package dao;

import modelo.Mensaje;

public interface IAdaptadorMensajeDAO {

	public void registrarMensaje(Mensaje m);
	public void borrarMensajes();
	public Mensaje recuperarMensaje(int cod);
}
