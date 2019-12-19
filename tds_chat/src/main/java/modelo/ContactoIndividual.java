package modelo;

public class ContactoIndividual extends Contacto {
	private int movil;
	public ContactoIndividual(String nombre, int movil) {
		super(nombre);
		this.movil = movil;
	}
	
	public int getMovil() {
		return movil;
	}
	public void setMovil(int movil) {
		this.movil = movil;
	}
	
	

}
