package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Menu(UsuarioDTO aux) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(Color.white);
		setTitle("Reserva de vuelos");
		
		JLabel label = new JLabel("Bienvenido, " + aux.getEmail().toUpperCase());
		label.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		label.setBounds(40, 30, 287, 37);
		contentPane.add(label);
		
		JButton btnReservar = new JButton("Reservar vuelo");
		btnReservar.setBounds(150, 130, 175, 29);
		contentPane.add(btnReservar);
		
		btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Vuelos vuelos = new Vuelos(aux);
            	vuelos.setVisible(true);
            	dispose();
            }
        });
	}
}
