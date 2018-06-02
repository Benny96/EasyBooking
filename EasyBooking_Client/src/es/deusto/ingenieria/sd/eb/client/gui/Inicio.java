package es.deusto.ingenieria.sd.eb.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.ingenieria.sd.eb.client.controller.EasyBookingController;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Inicio frame;
	
	private List<ReservaDTO> reservaDTO = new ArrayList<ReservaDTO>();
	private List<UsuarioDTO> usuarioDTO = new ArrayList<UsuarioDTO>();
	
	public Inicio() 
	{
		initialUsuarioListLoad(EasyBookingController.getInstance().getUsuarios());
		initialReservaListLoad(EasyBookingController.getInstance().getReservas());
		
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
		        //your actions
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
	private void initialUsuarioListLoad(List<UsuarioDTO> usu) {
		usuarioDTO = usu;
		//tvProgsList.clear();
		
		for (int i = 0; i < usuarioDTO.size(); i++) {
			UsuarioDTO p = (UsuarioDTO) usu.get(i);
			System.out.println(usu.get(i).getEmail());
			//tvProgsList.addElement(p.getAcronym() + " - " + p.getDescription());
		}
		
		/*if (!tvProgsList.isEmpty()) {
			tvProgsList1.setSelectedIndex(0);
		}*/
	}
	private void initialReservaListLoad(List<ReservaDTO> res) {
		reservaDTO = res;
		//tvProgsList.clear();
		
		for (int i = 0; i < reservaDTO.size(); i++) {
			ReservaDTO p = (ReservaDTO) res.get(i);
			//tvProgsList.addElement(p.getAcronym() + " - " + p.getDescription());
		}
		
		/*if (!tvProgsList.isEmpty()) {
			tvProgsList1.setSelectedIndex(0);
		}*/
	}
}
