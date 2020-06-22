package dao;

public class TDSFactoriaDAO extends FactoriaDAO {

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {

		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorGrupoDAO getGrupoDAO() {
		return AdaptadorGrupoTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorContactoInidivualDAO getContactoInidivualDAO() {
		return AdaptadorContactoIndividualTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorMensajeDAO getMensajeDAO() {

		return AdaptadorMensajeTDS.getUnicaInstancia();
	}

}
