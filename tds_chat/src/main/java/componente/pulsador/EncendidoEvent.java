package componente.pulsador;

import java.util.EventObject;

public class EncendidoEvent extends EventObject{
	protected boolean oldEncendido,newEncendido;
	public EncendidoEvent(Object fuente, boolean anterior, boolean nuevo) {
		super(fuente);
		newEncendido=nuevo;
		oldEncendido=anterior;
	}
	
	public boolean getNewEncendido() {return newEncendido;}
	public boolean getOldEncendido() {return oldEncendido;}
}
