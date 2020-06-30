package modelo;

public class ContactoIndividual extends Contacto {
	private String movil; 
	private Usuario u; //usuario real asociado al movil
	
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
	
	public String getImagen() {
		return u.getImagen();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactoIndividual other = (ContactoIndividual) obj;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		return true;
	}
	
	
}
