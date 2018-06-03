package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.airmi.server.data.dto.RMIAeropuertoDTO;
import es.deusto.ingenieria.sd.eb.client.controller.EasyBookingController;
import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class Vuelos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame vuelo;
	
	private int numreservas;
	private List<RMIAeropuertoDTO> aeropuertosRMI = new ArrayList<RMIAeropuertoDTO>();
	
	private ArrayList <Aeropuerto> misaeropuertos = new ArrayList<Aeropuerto>();
	
	//TODO: �Hace falta Reserva o ReservaDTO?
	public Vuelos(UsuarioDTO aux) {
		vuelo = this;
		/** TODO: A�ADIR LECTURA DE AEROPUERTOS DE GATEWAY Y RMI**/
		
		/*
		 * Lectura Aeropuertos RMI:
		 */
		aeropuertosRMI = EasyBookingController.getInstance().getRMIAeropuertos();
		for (int i = 0; i < aeropuertosRMI.size(); i++)
		{
			misaeropuertos.add(new Aeropuerto(aeropuertosRMI.get(i).getCodigo(), aeropuertosRMI.get(i).getNombre()));
		}
		
		//pasajeros.add("Pepita");
		//pasajeros.add("Juanito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVuelo = new JLabel(misaeropuertos.get(0).getNombre());
		lblVuelo.setBounds(39, 60, 204, 20);
		contentPane.add(lblVuelo);
		
		JLabel lblVuelo_1 = new JLabel(misaeropuertos.get(1).getNombre());
		lblVuelo_1.setBounds(39, 110, 240, 20);
		contentPane.add(lblVuelo_1);
		
		JLabel lblVuelo_2 = new JLabel(misaeropuertos.get(2).getNombre());
		lblVuelo_2.setBounds(39, 155, 240, 20);
		contentPane.add(lblVuelo_2);
		
		JButton btnReservarVuelo = new JButton("Reservar vuelo 1");
		btnReservarVuelo.setBounds(336, 56, 178, 29);
		contentPane.add(btnReservarVuelo);
		btnReservarVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	AddPersonas personas = new AddPersonas(obtenerNumeroReservas(), aux, misaeropuertos.get(0));
            	personas.setVisible(true);
            }
        });
		
		JButton btnReservarVuelo_1 = new JButton("Reservar vuelo 2");
		btnReservarVuelo_1.setBounds(336, 101, 178, 29);
		contentPane.add(btnReservarVuelo_1);
		btnReservarVuelo_1.addActionListener(new ActionListener() {
			 @Override
	         public void actionPerformed(ActionEvent e) 
			 {
				AddPersonas personas = new AddPersonas(obtenerNumeroReservas(), aux, misaeropuertos.get(1));
	            personas.setVisible(true);
			 }
	     });

		
		
		JButton btnReservarVuelo_2 = new JButton("Reservar vuelo 3");
		btnReservarVuelo_2.setBounds(336, 146, 178, 29);
		contentPane.add(btnReservarVuelo_2);
		btnReservarVuelo_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	AddPersonas personas = new AddPersonas(obtenerNumeroReservas(), aux, misaeropuertos.get(2));
            	personas.setVisible(true);
            }
        });
	}
	private int obtenerNumeroReservas ()
	{
		try 
		{
			numreservas = EasyBookingController.getInstance().numeroReservas();
			System.out.println("Numero de reservas: " + numreservas);
		} 
		catch (RemoteException e2) 
		{
			e2.printStackTrace();
		}
		return numreservas;
	}
}