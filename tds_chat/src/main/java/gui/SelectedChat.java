package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tds.BubbleText;

@SuppressWarnings("serial")
public class SelectedChat extends JPanel {
	private JPanel cright_south;
	private JTextField msgT;
	private JButton emoBt;
	private JButton sendBt;

	public SelectedChat() {

		setLayout(new BorderLayout());
		cright_south = new JPanel();
		cright_south.setLayout(new BoxLayout(cright_south, BoxLayout.X_AXIS));
		add(cright_south, BorderLayout.SOUTH);
		this.setSize(750, 700);
		this.setMaximumSize(new Dimension(750, 700));
		this.setMinimumSize(new Dimension(750, 700));
		ImageIcon icEmo = new ImageIcon("pics/happy.png");
		emoBt = new JButton(icEmo);
		this.setBackground(Color.gray);

		cright_south.add(emoBt);

		msgT = new JTextField();
		msgT.setMinimumSize(new Dimension(300, 30));
		msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
		cright_south.add(msgT);
		ImageIcon icSend = new ImageIcon("pics/right-arrow.png");
		sendBt = new JButton(icSend);

		cright_south.add(sendBt);
		final JPanel cright_center = new JPanel();

		add(cright_center, BorderLayout.CENTER);

		cright_center.setLayout(new BoxLayout(cright_center, BoxLayout.Y_AXIS));

		JScrollPane jsCh = new JScrollPane(cright_center, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(jsCh);

		cright_center.setSize(600, 800);
		cright_center.setMinimumSize(new Dimension(600, 800));
		cright_center.setMaximumSize(new Dimension(600, 800));
		cright_center.setBackground(Color.GRAY);

		// Son 25 emogis

		sendBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (msgT.getText().length() > 0) {
					BubbleText borboja = new BubbleText(cright_center, msgT.getText(), Color.cyan, "Usuario",
							BubbleText.SENT);
					cright_center.add(borboja);
					// cambiar Last
				}
				msgT.setText("");
				msgT.grabFocus();

			}
		});

		emoBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JFrame emos = new JFrame();
				emos.setBounds(emoBt.getLocationOnScreen().x, emoBt.getLocationOnScreen().y - 300, 600, 300);
				emos.setBackground(Color.black);

				emos.setUndecorated(true);
				emos.setVisible(true);

				JPanel emoPanel = new JPanel();
				emos.getContentPane().add(emoPanel);
				emoPanel.setLayout(new GridLayout(5, 5));
				//hay 25 emogis
				JLabel emo[] = new JLabel[25];

				for (int i = 0; i < 25; i++) {
					//Copia del iterado como final para poder usarlo con el mouseListener
					final int i2 = i;
					emo[i] = new JLabel(BubbleText.getEmoji(i));
					emoPanel.add(emo[i]);

					emo[i].addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
							// Introducir Nombre del Usuario que los envía
							BubbleText emoSent = new BubbleText(cright_center, i2, Color.CYAN, "JUANPABLO", BubbleText.SENT,
									15);
							cright_center.add(emoSent);
							cright_center.repaint();
							emos.dispose();
						}
					});

				}

				emos.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						emos.dispose();

					}

					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub

					}
				});

			}

		});

	}

}
