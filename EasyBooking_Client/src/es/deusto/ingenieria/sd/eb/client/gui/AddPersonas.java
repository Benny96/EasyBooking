package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import es.deusto.ingenieria.sd.eb.client.controller.EasyBookingController;
import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.eb.server.data.dto.PersonaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class AddPersonas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private JFrame ventana;
	String[] opciones = {"Sí", "No"};
	int salir = 0;
	int numres = 0;
	private ArrayList<PersonaDTO> pas;
	private UsuarioDTO user;
	private Aeropuerto airport;
	/**
	 * Create the frame.
	 */
	public AddPersonas(int numreservas, UsuarioDTO aux, Aeropuerto aero) 
	{
		pas = new ArrayList <PersonaDTO>();
		ventana = this;
		user = aux;
		airport = aero;
		numres = numreservas;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(44, 74, 83, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido 1");
		lblApellido.setBounds(44, 99, 83, 14);
		contentPane.add(lblApellido);
		
		JLabel lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(44, 124, 83, 14);
		contentPane.add(lblApellido_1);
		
		JLabel lblPasaporte = new JLabel("Pasaporte");
		lblPasaporte.setBounds(44, 44, 83, 14);
		contentPane.add(lblPasaporte);
		
		textField = new JTextField();
		textField.setBounds(220, 41, 126, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(220, 71, 126, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 96, 126, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 121, 126, 20);
		contentPane.add(textField_3);
		
		JButton btnAnadirPersona = new JButton("Añadir Persona");
		btnAnadirPersona.setBounds(152, 185, 132, 23);
		contentPane.add(btnAnadirPersona);
		btnAnadirPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	anadirPersona();
            }
        });
	}
	public void anadirPersona()
	{
		if (textField.getText().length()!=0 && textField_1.getText().length()!=0 && textField_2.getText().length()!=0 && textField_3.getText().length()!=0)
		{
    		PersonaDTO p = new PersonaDTO(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText());
    		//TODO: Comprobar que el pasaporte sea único si eso.
    		pas.add(p);
    		cleanText();
    		salir = JOptionPane.showOptionDialog(ventana, "Se ha añadido una nueva persona satisfactoriamente. ¿Quieres añadir otra?", "Añadir más gente", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    		if (salir == 1)
    		{
    			iniciarReserva(pas, user, airport);
    			JOptionPane.showMessageDialog(ventana, "He realizado la reserva del codigo "+airport.getCodigo());
    			ventana.setVisible(false);
        		ventana.dispose();
    		}
		}
	}
	public void cleanText()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	public void iniciarReserva(ArrayList<PersonaDTO> pasajeros, UsuarioDTO aux, Aeropuerto airport)
	{
		try 
		{
			System.out.println("NUMRES DESPUÉS: "+ numres);
			EasyBookingController.getInstance().nuevaReserva(++numres,aux.getEmail(),airport.getCodigo(),new Date() ,pasajeros);
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
}
