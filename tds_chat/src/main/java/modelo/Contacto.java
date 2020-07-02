package modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class Contacto {
	private int codigo;
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
	public void setListaMensajes(List<Mensaje> mns){
		this.listaMensajes = mns;
	}
	
	public boolean addMensaje(Mensaje m) {
		this.listaMensajes.add(m);
		return true;
	}
	public boolean removeMensaje(Mensaje m) {
		return this.listaMensajes.remove(m);
	}
	
	public int getId(){
		return codigo;
	}
	
	public void setId(int id){
		this.codigo = id;
	}

	public void borrarMensajes() {
		listaMensajes = new LinkedList<Mensaje>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
