package componente.cargador;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;

import componente.cargador.modelo.*;
import componente.cargador.parser.*;

public class MensajesEvent extends EventObject{

	protected String fichero, format;
	protected Plataforma p;
	
	
	public MensajesEvent(Object fuente, String fichero, String format, Plataforma p){
		super(fuente);
		this.fichero = fichero;
		this.format = format;
		this.p = p;
	}

	public String getFichero() {
		return fichero;
	}

	public String getFormat() {
		return format;
	}

	public Plataforma getPlataforma() {
		return p;
	}
	
	public List<MensajeWhatsApp> obtenerLista(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		List<MensajeWhatsApp> chat = null;
		try{
			chat = SimpleTextParser.parse(this.fichero, this.format, this.p);
		} catch (IOException e){
			e.printStackTrace();
		}
		return chat;
	}
	
	
}
