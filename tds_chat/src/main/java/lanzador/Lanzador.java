package lanzador;

import java.awt.EventQueue;

import gui.LoginView;
/**
 * Lanzador de la aplicación
 * @author Jesus
 *
 */
public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView ventana = new LoginView();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}