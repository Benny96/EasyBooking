package es.deusto.ingenieria.sd.eb.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.PersonaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaDTO;

public interface IReservaAdmin extends Remote {
	
	public List<ReservaDTO> getReservasDTO() throws RemoteException;
	public void nuevaReserva(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<PersonaDTO> personas, int pago)throws RemoteException;
	public void cancelarReserva(int codigoReserva) throws RemoteException;
	public List<AeropuertoDTO> getAeropuertosSocketDTO() throws RemoteException;
	public int numeroReservas() throws RemoteException;
}