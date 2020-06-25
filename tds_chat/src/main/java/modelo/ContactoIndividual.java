package modelo;

public class ContactoIndividual extends Contacto {
	private String movil; 
	private Usuario u; //usuario asociado al movil
	
	public ContactoIndividual(String nombre,String movil) {
		super(nombre);
		this.movil=movil;
		
	}
	
	public ContactoIndividual(String nombre, String movil, Usuario u){
		this(nombre, movil);
		this.u = u;
	}
	
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	
	
	public Usuario getUsuario(){
		return u;
	}
	
	public void setUsuario(Usuario u){
		this.u = u;
	}

	
}
