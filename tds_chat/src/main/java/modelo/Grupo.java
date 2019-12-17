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
	
	private List<Contacto> contactos;

	public Grupo(String nombre) {
		super(nombre);
		this.contactos = new LinkedList<Contacto>();
	}
	
	
	public boolean addContacto(Contacto c) {
		return this.contactos.add(c);
	}
	
	public boolean removeContacto(Contacto c) {
		return this.contactos.remove(c);
	}
	
	
	
}
