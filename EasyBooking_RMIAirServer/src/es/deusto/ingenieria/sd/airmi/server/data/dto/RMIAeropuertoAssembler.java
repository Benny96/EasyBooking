package es.deusto.ingenieria.sd.airmi.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.airmi.server.data.RMIAeropuerto;

public class RMIAeropuertoAssembler {
	
private static RMIAeropuertoAssembler instance = new RMIAeropuertoAssembler();
	
	private RMIAeropuertoAssembler() {}

	public static RMIAeropuertoAssembler getInstance() {
		return instance;
	}
	
	public List<RMIAeropuertoDTO> assemble(List<RMIAeropuerto> RMIAeropuertos) 
	{
		List<RMIAeropuertoDTO> RMIAeropuertosDTO = new ArrayList<>();
		for (RMIAeropuerto a : RMIAeropuertos) 
		{
			RMIAeropuertosDTO.add(new RMIAeropuertoDTO(a.getNombre(), a.getCodigo()));
		}
		System.out.println("* Assembling RMIAeropuertos ...");
		return RMIAeropuertosDTO;
	}
}
