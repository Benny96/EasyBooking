package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Inicio() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidoAEasybooking = new JLabel("Bienvenido a EasyBooking");
		lblBienvenidoAEasybooking.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblBienvenidoAEasybooking.setBounds(58, 50, 418, 81);
		contentPane.add(lblBienvenidoAEasybooking);
		
		JButton btnDarseDeAlta = new JButton("Darse de alta");
		btnDarseDeAlta.setBounds(175, 186, 191, 46);
		contentPane.add(btnDarseDeAlta);
		btnDarseDeAlta.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Alta f = new Alta();
				f.setVisible(true);
				dispose();
		    }
		});
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setBounds(175, 248, 191, 46);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Login f1 = new Login();
				f1.setVisible(true);
				dispose();
		    }
		});
		
	}
}
