package modelo;

public class ContactoIndividual extends Contacto {
	private int movil; 
	private Usuario u;
	
	public ContactoIndividual(String nombre,int movil) {
		super(nombre);
		this.movil=movil;
		
	}
	
	public ContactoIndividual(String nombre, int movil, Usuario u){
		this(nombre, movil);
		this.u = u;
	}
	
	public int getMovil() {
		return movil;
	}
	public void setMovil(int movil) {
		this.movil = movil;
	}
	
	public Usuario getUsuario(){
		return u;
	}
	
	public void setUsuario(Usuario u){
		this.u = u;
	}

	
}
