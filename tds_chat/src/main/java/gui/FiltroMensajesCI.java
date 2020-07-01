package gui;




import java.awt.Dimension;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import com.toedter.calendar.JDateChooser;

import modelo.ContactoIndividual;

@SuppressWarnings("serial")
public class FiltroMensajesCI extends JDialog{
	
	private ContactoIndividual contacto ;
	
	public FiltroMensajesCI(ContactoIndividual ci) {
		this.setResizable(false);
		this.contacto = ci;
		this.setTitle("Filtrar Mensajes");
	
		JDateChooser fechaInicio = new JDateChooser();
		JDateChooser fechaFinal = new JDateChooser();
		JTextField texto = new JTextField(15);
		JButton buscarBt = new JButton("Buscar");
		
		
		
		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Texto a buscar: "));
		texto.setMaximumSize(new Dimension(600, 40));

		panel.add(texto);
		panel.add(new JLabel("Fecha de Inicio: "));
		fechaInicio.setMaximumSize(new Dimension(600, 40));
		panel.add(fechaInicio);
		
		panel.add(new JLabel("Fecha Fin: "));
		fechaFinal.setMaximumSize(new Dimension(600, 40));
		panel.add(fechaFinal);
		panel.add(Box.createRigidArea(new Dimension(30, 25)) );
		panel.add(buscarBt);
	
		panel.add(Box.createRigidArea(new Dimension(30, 60)) );
		JTextArea mensajesEncontrados = new JTextArea();
		mensajesEncontrados.setEditable(false);
		panel.add(new JLabel("Mensajes coincidentes: "));
		mensajesEncontrados.setMaximumSize(new Dimension(800, 200));
		mensajesEncontrados.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane jsMsg = new  JScrollPane(mensajesEncontrados,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(jsMsg);
		
		
	
		
		
		
		add(panel);
		setVisible(true);

		
	}
	
	

}
