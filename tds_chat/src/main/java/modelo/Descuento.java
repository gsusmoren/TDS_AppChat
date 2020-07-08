package modelo;

/**
 * Los diferentes tipos de descuentos no podrán conbinarse, unicamaente puede
 * optarse al que más abarata el coste del Premium
 * 
 * @author Jesus
 *
 */
public interface Descuento {
	public double calcDescuento(Usuario usuario);
}
