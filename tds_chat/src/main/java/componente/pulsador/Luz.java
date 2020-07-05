package componente.pulsador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

public class Luz extends Canvas implements Serializable{
	private Color color=Color.YELLOW;
	private boolean encendido=false;
	private String nombre;
	
	private Vector encendidoListeners=new Vector();
	private boolean bPulsado=false;
	
	private static Color colorBoton1=new Color(160,160,160);
	private static Color colorBoton2=new Color(200,200,200);
	
	public synchronized void addEncendidoListener(IEncendidoListener listener) {
		encendidoListeners.addElement(listener);
	}
	
	public synchronized void removeEncendidoListener(IEncendidoListener listener) {
		encendidoListeners.removeElement(listener);
	}
	
	public Luz() {
		setSize(60,60);
		setMinimumSize(new Dimension(60,60));
		repaint();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				luzPressed(e);
				repaint();
			}
			public void mouseReleased(MouseEvent e) {
				luzReleased(e);
			}
		});
			
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color newColor) {
		this.color = newColor;
		repaint();
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean nencendido) {
		boolean oldEncendido=encendido;
		this.encendido = nencendido;
		if(oldEncendido!=nencendido) {
			EncendidoEvent event=new EncendidoEvent(this, oldEncendido, nencendido);
			notificarCambioEncendido(event);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}
	
	public void paint(Graphics g) {
		int ancho=getSize().width;
		int alto=getSize().height;
		
		if(ancho!=alto) {
			if(ancho<alto) alto=ancho;
			else ancho=alto;
			setSize(ancho,alto);
			invalidate();
		}
		
		int grosor=3;
		int anchuraBoton=ancho-grosor;
		int bordeBoton=anchuraBoton/5;
		int anchuraLuz=anchuraBoton-2*bordeBoton;
		
		int x;
		
		if(!bPulsado) { x=0;
			
		}else { x=grosor; }
		
		g.setColor(colorBoton1);
		g.fillOval(grosor, grosor, anchuraBoton, anchuraBoton);
		g.setColor(Color.BLACK);
		g.drawOval(grosor, grosor, anchuraBoton-1, anchuraBoton-1);
		g.setColor(colorBoton2);
		g.fillOval(x, x, anchuraBoton, anchuraBoton);
		
		if(encendido) g.setColor(color);
		else g.setColor(getBackground());
		
		g.fillOval(x+bordeBoton, x+bordeBoton, anchuraLuz, anchuraLuz);
		g.setColor(Color.BLACK);
		g.drawOval(x, x, anchuraBoton-1, anchuraBoton-1);
		g.drawOval(x+bordeBoton, x+bordeBoton, anchuraLuz-1, anchuraLuz-1);
		g.setColor(getForeground());
	}
	
	public void luzPressed(MouseEvent e) { 
		bPulsado=true; 
	}
	
	
	public void luzReleased(MouseEvent e) {
		if(bPulsado) {
			bPulsado = false;
			if(encendido) setEncendido(false);
			else setEncendido(true);
			repaint();
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(60,60);
	}
	
	public Dimension getMinimumSize() {return getPreferredSize();}
	
	private void notificarCambioEncendido(EncendidoEvent evento) {
		Vector lista;
		synchronized (this) {
			lista=(Vector)encendidoListeners.clone();
		}
		for(int i=0;i<lista.size();i++) {
			IEncendidoListener listener=(IEncendidoListener)lista.elementAt(i);
			listener.enteradoCambioEncendido(evento);
		}
	}
	
}
