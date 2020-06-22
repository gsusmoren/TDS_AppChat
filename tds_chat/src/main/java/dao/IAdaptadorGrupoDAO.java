package dao;

import java.util.List;

import modelo.Grupo;

public interface IAdaptadorGrupoDAO {

	public void registrarGrupo(Grupo grupo);
	public void borrarGrupo(Grupo grupo);
	public void modificarGrupo(Grupo grupo);
	public Grupo recuperarGrupo (int id);
	public List<Grupo> recuperarTodosGrupos();
}
