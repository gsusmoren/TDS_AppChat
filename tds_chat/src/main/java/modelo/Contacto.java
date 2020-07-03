package modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


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

	public void setListaMensajes(List<Mensaje> mns) {
		this.listaMensajes = mns;
	}

	public boolean addMensaje(Mensaje m) {
		this.listaMensajes.add(m);
		return true;
	}

	public boolean removeMensaje(Mensaje m) {
		return this.listaMensajes.remove(m);
	}

	public int getId() {
		return codigo;
	}

	public void setId(int id) {
		this.codigo = id;
	}

	public void borrarMensajes() {
		listaMensajes.clear();

	}

	// Método para obtener Lista de Mensajes con el Texto introducio
	public List<Mensaje> filtrarMensajePorTexto(String texto) {
		if (texto.isEmpty())
			return new LinkedList<Mensaje>();
		List<Mensaje> lista = listaMensajes.stream().filter(m -> m.getTexto().contains(texto))
				.collect(Collectors.toList());
		return lista;
	}

	// Método para obtener Mensajes dado el nombre del usuario
	public List<Mensaje> filtrarMensajesPorUsuario(String nombre) {
		// TODO la búsqueda es por propio Nick
		if (nombre.isEmpty())
			return new LinkedList<Mensaje>();
		List<Mensaje> lista = listaMensajes.stream().filter(m -> m.getEmisor().getNombre().equals(nombre))
				.collect(Collectors.toList());
		return lista;
	}

	// Método devuelve una lista con los mensajes entre las fechas
	public List<Mensaje> filtrarMensajesEntreFechas(LocalDateTime f1, LocalDateTime f2) {
		if (f1 == null || f2 == null)
			return new LinkedList<Mensaje>();
		List<Mensaje> lista = listaMensajes.stream().filter(m -> (m.getHora().isAfter(f1) && m.getHora().isBefore(f2)
				|| m.getHora().isEqual(f1) || m.getHora().isEqual(f2))).collect(Collectors.toList());
		return lista;

	}

	public List<Mensaje> filtradorGeneralMensajes(String texto, String nombre, Date f1, Date f2) {

		List<Mensaje> listaUsuario = new LinkedList<Mensaje>();
		List<Mensaje> listaTexto = new LinkedList<Mensaje>();
		List<Mensaje> listaFechas = new LinkedList<Mensaje>();
		
		
		if (this instanceof ContactoIndividual) {
			// No opcional
			listaTexto = filtrarMensajePorTexto(texto);
			
			listaFechas = filtrarMensajesEntreFechas(LocalDateTime.ofInstant(f1.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(f2.toInstant(), ZoneId.systemDefault()));

			List<Mensaje> comunes = listaTexto.stream().filter(listaFechas::contains).collect(Collectors.toList());

			return comunes;

		} else {

		
	
			listaTexto = filtrarMensajePorTexto(texto);
			if(f1!=null && f2!=null )
				listaFechas = filtrarMensajesEntreFechas(LocalDateTime.ofInstant(f1.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(f2.toInstant(), ZoneId.systemDefault()));
			else
				listaFechas = new LinkedList<Mensaje>();
			listaUsuario = filtrarMensajesPorUsuario(nombre);
			
			if(listaTexto.isEmpty() && listaFechas.isEmpty()) {
				return listaUsuario;
				
			}
			if(listaTexto.isEmpty() && listaUsuario.isEmpty()) {
				return listaFechas;
				
			}
			if(listaUsuario.isEmpty() && listaFechas.isEmpty()) {
				return listaUsuario;
			}									
			
			if(listaUsuario.isEmpty()) {
				List<Mensaje> comunes = listaFechas.stream().filter(listaFechas::contains)
						.collect(Collectors.toList());
				return comunes;
			}
			if(listaFechas.isEmpty()) {
				List<Mensaje> comunes = listaUsuario.stream().filter(listaTexto::contains)
						.collect(Collectors.toList());
				return comunes;
			}
			if (listaTexto.isEmpty()) {
				List<Mensaje> comunes = listaFechas.stream().filter(listaUsuario::contains)
						.collect(Collectors.toList());
				return comunes;
			}
			List<Mensaje> comunes = listaTexto.stream().filter(listaFechas::contains).collect(Collectors.toList());
			List<Mensaje> comunes2 = listaUsuario.stream().filter(comunes::contains).collect(Collectors.toList());
			
			return comunes2;
				
			
		}

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
