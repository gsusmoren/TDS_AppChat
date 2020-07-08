package modelo;
/*
 * Descuento aplicable a cualquier usuario
 */
public class DescuentoFijo implements Descuento {

	@Override
	public double calcDescuento(Usuario usuario) {
		if(usuario.getNMensajesUltimoMes() >= 50) {
			return 8;
		}
		return 10;
		
	}

}
