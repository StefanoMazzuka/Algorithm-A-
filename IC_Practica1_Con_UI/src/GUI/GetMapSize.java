package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GetMapSize extends JFrame{
	public GetMapSize() {

		JTextField rows = new JTextField("", 5);
		JTextField columns = new JTextField("", 5);
		JButton ok = new JButton("Ok");

		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Algorithm A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar el programa al pulsar la X

		JPanel menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.gridy = 1; // Fila
		bag.gridx = 0; // Columna
		bag.gridheight = 1; // Filas
		bag.gridwidth = 1; // Columnas
		bag.weighty = 1.0;
		menu.add(new JLabel("Rows:"), bag);
		bag.weighty = 0.0;

		bag.gridy = 2; // Fila
		bag.gridx = 0; // Columna
		bag.gridheight = 1; // Filas
		bag.gridwidth = 1; // Columnas
		menu.add(new JLabel("Columns:"), bag);

		bag.gridy = 3; // Fila
		bag.gridx = 1; // Columna
		bag.gridheight = 3; // Filas
		bag.gridwidth = 1; // Columnas
		bag.weighty = 1.0;
		menu.add(ok, bag);
		bag.weighty = 0.0;

		bag.gridy = 1; // Fila
		bag.gridx = 2; // Columna
		bag.gridheight = 1; // Filas
		bag.gridwidth = 1; // Columnas
		menu.add(rows, bag);

		bag.gridy = 2; // Fila
		bag.gridx = 2; // Columna
		bag.gridheight = 1; // Filas
		bag.gridwidth = 1; // Columnas
		menu.add(columns, bag);

		add(menu);

		rows.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if ((input < '0' || input > '9') && input != '\b') {
					e.consume();
				}
			}
		});

		columns.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if ((input < '0' || input > '9') && input != '\b') {
					e.consume();
				}
			}
		});

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rows.getText().equals("") || columns.getText().equals(""))
					JOptionPane.showMessageDialog(null, "ERROR: Please enter the values.");
				else {			
					if (Integer.parseInt(rows.getText()) > 50 ||
							Integer.parseInt(rows.getText()) < 1 ||
							Integer.parseInt(columns.getText()) > 50 ||
							Integer.parseInt(columns.getText()) < 1)

						JOptionPane.showMessageDialog(null, "ERROR: Please enter the values "
								+ "between 1 and 50.");

					else {
						setVisible(false);
						Map map = new Map(Integer.parseInt(rows.getText()), 
								Integer.parseInt(columns.getText()));
						map.setVisible(true);}
				}	
			}
		});
	}
}