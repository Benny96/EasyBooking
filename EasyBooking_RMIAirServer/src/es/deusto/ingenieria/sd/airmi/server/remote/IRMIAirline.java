package es.deusto.ingenieria.sd.airmi.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.airmi.server.data.dto.RMIAeropuertoDTO;

public interface IRMIAirline extends Remote {
	
	public List<RMIAeropuertoDTO> getAeropuertosDTO() throws RemoteException;
}