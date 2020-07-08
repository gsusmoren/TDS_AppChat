package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
/**
 * Ventana de diálogo para el filtrado de mensajes dentro de un Grupo
 * @author Jesus
 *
 */
@SuppressWarnings("serial")
public class FiltroMensajesGP extends JDialog {

	private Grupo grupo;

	public FiltroMensajesGP(Grupo gp) {
		this.setResizable(false);
		this.grupo = gp;
		this.setTitle("Filtrar Mensajes");
		JDateChooser fechaInicio = new JDateChooser();
		JDateChooser fechaFinal = new JDateChooser();
		JTextField texto = new JTextField(15);
		JComboBox<String> cntactos = new JComboBox<String>();
		JButton buscarBt = new JButton("Buscar");
		cntactos.addItem("");
		if(gp.getAdmin()!=null)
		cntactos.addItem(gp.getAdmin().getNombre());
		for (ContactoIndividual ci : gp.getContactos()) {
			
			
			cntactos.addItem(ci.getUsuario().getNombre());
			
			
		}

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

		panel.add(new JLabel("Enviado por el Usuario: "));
		cntactos.setMaximumSize(new Dimension(600, 40));
		panel.add(cntactos);

		panel.add(Box.createRigidArea(new Dimension(30, 25)));
		panel.add(buscarBt);

		panel.add(Box.createRigidArea(new Dimension(30, 60)));
		JTextArea mensajesEncontrados = new JTextArea();
		mensajesEncontrados.setEditable(false);
		panel.add(new JLabel("Mensajes coincidentes: "));
		mensajesEncontrados.setMaximumSize(new Dimension(800, 200));
		mensajesEncontrados.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane jsMsg = new JScrollPane(mensajesEncontrados, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(jsMsg);

		buscarBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nomb = (String) cntactos.getSelectedItem();

				if (texto.getText().isEmpty() && (fechaInicio.getDate() == null || fechaFinal.getDate() == null)
						&& nomb.equals("")) {
					JOptionPane.showMessageDialog(panel, "No pueden estar todos los campos vacíos", "Error",
							JOptionPane.ERROR_MESSAGE);

				}else {
					
					List<Mensaje> resultado = gp.filtradorGeneralMensajes(texto.getText(), nomb, fechaInicio.getDate(), fechaFinal.getDate());
					mensajesEncontrados.setText(resultado.toString());
					revalidate();
					repaint();
				}

			}
		});

		add(panel);
		setVisible(true);

	}

	public Grupo getGrupo() {
		return grupo;
	}

}
