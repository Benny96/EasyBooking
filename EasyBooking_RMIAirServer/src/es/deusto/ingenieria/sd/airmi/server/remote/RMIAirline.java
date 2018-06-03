package es.deusto.ingenieria.sd.airmi.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.deusto.ingenieria.sd.airmi.server.data.RMIAeropuerto;
import es.deusto.ingenieria.sd.airmi.server.data.dto.RMIAeropuertoAssembler;
import es.deusto.ingenieria.sd.airmi.server.data.dto.RMIAeropuertoDTO;

import java.util.Map.Entry;

public class RMIAirline extends UnicastRemoteObject implements IRMIAirline{
	
	private static final long serialVersionUID = 1L;
	private ArrayList <RMIAeropuerto> aerop = new ArrayList <RMIAeropuerto>();
	
	public RMIAirline() throws RemoteException {
		super();
		addAeropuertos();
	}

	@Override
	public List<RMIAeropuertoDTO> getAeropuertosDTO() throws RemoteException {
		List<RMIAeropuertoDTO> aeropuertos = new ArrayList<RMIAeropuertoDTO>();
		RMIAeropuertoAssembler assembler = new RMIAeropuertoAssembler();
		aeropuertos = assembler.assemble(getAeropuertos());
		return aeropuertos;
	}
	
	public synchronized List<RMIAeropuerto> getAeropuertos() {
		List<RMIAeropuerto> aeropuertos = new ArrayList<RMIAeropuerto>();
		System.out.println("* Recuperando Aeropuertos RMI...");
		for (int i = 0; i < aerop.size(); i ++)
		{
			aeropuertos.add(aerop.get(i));
		}
		return aeropuertos;
	}
	private void addAeropuertos()
	{
		aerop.add(new RMIAeropuerto("AERO1", "aaaaaa"));
		aerop.add(new RMIAeropuerto("AERO2", "bbbbbb"));
		aerop.add(new RMIAeropuerto("AERO3", "cccccc"));
	}
}
