package componente.cargador;

import java.util.EventListener;
import java.util.EventObject;

public interface MensajesListener extends EventListener {
	public void nuevosMensajes(MensajesEvent e);
}
