package modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class Contacto {
	private String nombre;
	private List<Mensaje> listaMensajes;

	public Contacto(String nombre) {
		this.nombre = nombre;
		this.listaMensajes = new LinkedList<Mensaje>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}
	public boolean addMensaje(Mensaje m) {
		return this.listaMensajes.add(m);
		
	}
	public boolean removeMensaje(Mensaje m) {
		return this.listaMensajes.remove(m);
		
	}
	
	
}
