package modelo;

import java.util.Map;

public class CatalogoUsuarios {
	private Map<String,Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	
	
	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}
}
