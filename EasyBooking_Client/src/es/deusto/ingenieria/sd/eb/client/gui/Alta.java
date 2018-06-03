package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.Font;
import java.awt.Color;
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
	private JFrame alta;
	
	UsuarioDTO myUser=null;

	public Alta() 
	{
		alta = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(Color.white);
		setTitle("Proceso de Alta");
		
		JLabel lblFacebookgoogle = new JLabel("Facebook/Google +");
		lblFacebookgoogle.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblFacebookgoogle.setBounds(40, 40, 280, 72);
		contentPane.add(lblFacebookgoogle);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(46, 173, 101, 37);
		contentPane.add(lblUsuario);
		
			
		textField = new JTextField();
		textField.setBounds(46, 202, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnRegistrarseGoogle = new JButton("Registrarse Google+");
		btnRegistrarseGoogle.setBounds(230, 214, 175, 29);
		contentPane.add(btnRegistrarseGoogle);
		btnRegistrarseGoogle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try 
            	{
					EasyBookingController.getInstance().crearNuevoUsuarioGoogle(textField.getText());
					myUser = new UsuarioDTO(textField.getText());
					Menu m = new Menu(myUser);
	                m.setVisible(true);
	                dispose();
				} 
            	catch (RemoteException e1) 
            	{
					JOptionPane.showMessageDialog(alta, "Este usuario ya existe en la BD", "El usuario ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
            	catch (NullPointerException e1)
            	{
            		JOptionPane.showMessageDialog(alta, "Este usuario no existe en Google+", "El usuario no existe", JOptionPane.INFORMATION_MESSAGE);
            	}
            }
        });
		
		JButton btnRegistrarseFacebook = new JButton("Registrarse Facebook");
		btnRegistrarseFacebook.setBounds(230, 174, 175, 29);
		contentPane.add(btnRegistrarseFacebook);
		btnRegistrarseFacebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try 
            	{
					EasyBookingController.getInstance().crearNuevoUsuarioFacebook(textField.getText());
					myUser = new UsuarioDTO(textField.getText());
					Menu m = new Menu(myUser);
	                m.setVisible(true);
	                dispose();
				} 
            	catch (RemoteException e1) 
            	{
					JOptionPane.showMessageDialog(alta, "Este usuario ya existe en la BD", "El usuario ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
            	catch (NullPointerException e1)
            	{
            		JOptionPane.showMessageDialog(alta, "Este usuario no existe en Facebook", "El usuario no existe", JOptionPane.INFORMATION_MESSAGE);
            	}
            }
        });
	}
}
