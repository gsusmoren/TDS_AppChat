package dao;

import modelo.ContactoIndividual;

public interface IAdaptadorContactoInidivualDAO {
	public void registrarContactoIndividual(ContactoIndividual c);
	public void borrarContactoIdividual(ContactoIndividual c);
	public void modificarContactoIndividual(ContactoIndividual c);
	public ContactoIndividual recuperarContactoIndividual(int codigo);
	
}
