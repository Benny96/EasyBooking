package es.deusto.ingenieria.sd.eb.client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.airmi.server.data.dto.RMIAeropuertoDTO;
import es.deusto.ingenieria.sd.eb.client.gui.Inicio;
import es.deusto.ingenieria.sd.eb.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.eb.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.PersonaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public class EasyBookingController {
	private static EasyBookingController instance;
	private RMIServiceLocator rsl;

	private EasyBookingController(String[] args) throws RemoteException {
		instance = this;
		rsl = new RMIServiceLocator();
		rsl.setService(args);
		Inicio frame = new Inicio();
		frame.setVisible(true);
	}
	
	public static EasyBookingController getInstance() {
		return instance;
	}

	public void nuevaReserva(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<PersonaDTO> personas, int pago) throws RemoteException {
			rsl.getReservaService().nuevaReserva(codigoReserva, email, codigoVuelo, fecha, personas, pago);
	}
	
	public int numeroReservas() throws RemoteException {
		try {
			return rsl.getReservaService().numeroReservas();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<UsuarioDTO> getUsuarios() throws RemoteException {
		return rsl.getUsuarioService().getUsuariosDTO();
	}

	public void crearNuevoUsuarioGoogle (String email) throws RemoteException, NullPointerException {
		rsl.getUsuarioService().generarNuevoUsuarioGoogle(email);
	}
	
	public void crearNuevoUsuarioFacebook (String email) throws RemoteException, NullPointerException {
		rsl.getUsuarioService().generarNuevoUsuarioFacebook(email);
	}
	
	public List<RMIAeropuertoDTO> getRMIAeropuertos() {
		List<RMIAeropuertoDTO> aeropuertos = new ArrayList<>();
		try {
			aeropuertos = rsl.getRMIAirlineService().getAeropuertosDTO();
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
		return aeropuertos;
	}
	
	public List<AeropuertoDTO> getSocketAeropuertos() throws RemoteException {
		return rsl.getReservaService().getAeropuertosSocketDTO();
	}
	
	//TODO: Finalmente no se ha utilizado en esta implementación. Se podría tratar de asignar esta función en un futuro.
	public List<ReservaDTO> getReservas() {
		List<ReservaDTO> reservas = new ArrayList<>();
		try {
			reservas = rsl.getReservaService().getReservasDTO();
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
		return reservas;
	}
	
	//TODO: Finalmente no se ha utilizado en esta implementación. Se podría tratar de asignar esta función en un futuro.
	public void cancelarReserva(int codigoReserva) throws RemoteException{
		try {
			rsl.getReservaService().cancelarReserva(codigoReserva);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	//TODO: Finalmente no se ha utilizado en esta implementación. Se podría tratar de asignar esta función en un futuro.
	public void eliminarUsuario (String email) {
		try {
			rsl.getUsuarioService().eliminarUsuario(email);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		System.exit(0);
	}

	public static void main(String[] args) throws RemoteException {
		new EasyBookingController(args);
	}
}