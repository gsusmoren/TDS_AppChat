package modelo;

import java.util.LinkedList;
import java.util.List;


public  class Grupo extends Contacto {
	private Usuario admin;
	private List<ContactoIndividual> contactos;
	
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
