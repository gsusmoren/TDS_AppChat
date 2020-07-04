package modelo;


/*
 * Descuento aplicable a los alumnos de la UMU.
 */
public class DescuentoEstudiante implements Descuento {

	@Override
	public double calcDescuento(Usuario usuario) {
		if (usuario.getEmail().contains("@um.es"))
			return 6.00;
		else
			return 10;
		
	}

}
