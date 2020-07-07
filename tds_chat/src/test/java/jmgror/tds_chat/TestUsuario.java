package jmgror.tds_chat;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import modelo.*;

public class TestUsuario {

	Usuario usuario1, usuario2;
	Contacto contacto;
	
	@Before
	public void inicializar(){
		usuario1 = new Usuario("Antonio",new GregorianCalendar(1998, Calendar.FEBRUARY, 27).getTime(),"12345678", "antonio@gmail.com", "pass", "antonin", null, "");
		usuario2 = new Usuario("Juan",new GregorianCalendar(1995, Calendar.FEBRUARY, 15).getTime(),"87654321", "juan@gmail.com", "passjuan", "juanito", null, "");
		contacto = new ContactoIndividual("Juan", "87654321", usuario2);
	}
	
	@Test
	public void testAddContacto() {
		assertTrue(usuario1.addContacto(contacto));
	}

	@Test
	public void testComprobarContacto() {
		usuario1.addContacto(contacto);
		assertTrue(usuario1.comprobarContacto(usuario2));
	}

	@Test
	public void testBorrarContacto() {
		usuario1.addContacto(contacto);
		assertTrue(usuario1.borrarContacto(contacto));
	}

}
