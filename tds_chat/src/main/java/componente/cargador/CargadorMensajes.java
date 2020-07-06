package componente.cargador;

import java.io.Serializable;
import java.util.Vector;

import componente.cargador.modelo.Plataforma;
import componente.pulsador.IEncendidoListener;

public class CargadorMensajes implements Serializable{
	
	private Vector mensajesListeners=new Vector();
	private String fichero, format;
	private Plataforma p;
	
	

	public synchronized void addMensajeListener(MensajesListener listener) {
		mensajesListeners.addElement(listener);
	}
	
	public synchronized void removeMensajeListener(MensajesListener listener) {
		mensajesListeners.removeElement(listener);
	}
	
	private void notificarCargadorMensajes(MensajesEvent evento){
		Vector lista;
		synchronized (this) {
			lista=(Vector)mensajesListeners.clone();
		}
		for(int i=0;i<lista.size();i++){
			MensajesListener listener=(MensajesListener)lista.elementAt(i);
			listener.nuevosMensajes(evento);
		}
	}

	public Vector getMensajesListeners() {
		return mensajesListeners;
	}

	public void setMensajesListeners(Vector mensajesListeners) {
		this.mensajesListeners = mensajesListeners;
	}
	
	public String getFormat() {
		return format;
	}

	public Plataforma getP() {
		return p;
	}

	public String getFichero() {
		return fichero;
	}
	
	public void setFichero(String fichero, String formato, Plataforma p) {
		this.fichero = fichero;
		this.format=formato;
		this.p = p;
		MensajesEvent evento = new MensajesEvent(this, fichero, formato, p);
		notificarCargadorMensajes(evento);
	}
}
