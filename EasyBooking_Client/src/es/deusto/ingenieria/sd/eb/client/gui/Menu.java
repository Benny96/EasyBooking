package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//public ArrayList <Reserva> reservas;

	public Menu(UsuarioDTO aux) {
		
		//TODO: Pensar añadir caso de uso Mostrar Reservas.
		//reservas = new ArrayList <Reserva>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Bienvenido," + aux.getEmail());
		label.setBounds(40, 30, 167, 37);
		contentPane.add(label);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(229, 157, 115, 29);
		contentPane.add(btnReservar);
		
		btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("12312312312312312312");
            	Vuelos vuelos = new Vuelos(aux);
            	System.out.println("LALALALALLALALALLA");
            	vuelos.setVisible(true);
            	dispose();
            }
        });
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setBounds(379, 50, 190, 57);
		contentPane.add(lblBienvenido);
	}
}
