package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Esta es la clase Composite, cuando se cree o use una operacion común a ambos hijo de 
 * contacto, en esta clase se debe de adaptar para llamar a la de todos los hijos hoja
 *
 *Cuando usuario llame a un método de Conctacto, debe tener la misma funcionalidad
 */

public  class Grupo extends Contacto {
	private Usuario admin;
	private List<ContactoIndividual> contactos;
	//¿Como sabemos el usuario actual que está creando el grupo?
	//ver demás recursos
	
	public Grupo(String nombre) {
		super(nombre);
		this.contactos = new LinkedList<ContactoIndividual>();
	}
	
	
	public boolean addContacto(ContactoIndividual c) {
		return this.contactos.add(c);
	}
	
	public boolean removeContacto(ContactoIndividual c) {
		return this.contactos.remove(c);
	}
	
	
	
	public List<ContactoIndividual> getContactos() {
		return contactos;
	}


	public void setContactos(List<ContactoIndividual> contactos) {
		this.contactos = contactos;
	}


	public Usuario getAdmin(){
		return admin;
	}
	
	public void setAdmin(Usuario a){
		this.admin = a;
	}
	
	
}
