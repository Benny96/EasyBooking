package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.eb.client.controller.EasyBookingController;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class Alta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame alta;
	
	UsuarioDTO myUser=null;
	


	public Alta() {
		

		
		
		alta = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFacebookgoogle = new JLabel("Facebook/Google +");
		lblFacebookgoogle.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblFacebookgoogle.setBounds(209, 40, 249, 72);
		contentPane.add(lblFacebookgoogle);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(46, 123, 101, 37);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(46, 214, 101, 47);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(46, 172, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(232, 334, 155, 29);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		//TODO: Hacer llamada al Gateway.
					EasyBookingController.getInstance().crearNuevoUsuario(textField.getText());
					myUser = new UsuarioDTO(textField.getText());
					Menu m = new Menu(myUser);
	                m.setVisible(true);
	                dispose();
				} 
            	catch (RemoteException e1) 
            	{
					JOptionPane.showMessageDialog(alta, "Este usuario ya existe en la BD", "El usuario ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
            }
        });
	}
}
