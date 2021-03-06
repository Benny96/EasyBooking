package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;

public class AeropuertoAssembler {
	
	private static AeropuertoAssembler instance = new AeropuertoAssembler();
	
	private AeropuertoAssembler() {}

	public static AeropuertoAssembler getInstance() {
		return instance;
	}
	
	public List<AeropuertoDTO> assemble(List<Aeropuerto> Aeropuertos) 
	{
		List<AeropuertoDTO> AeropuertosDTO = new ArrayList<>();
		for (Aeropuerto a : Aeropuertos) 
		{
			AeropuertosDTO.add(new AeropuertoDTO(a.getCodigo(), a.getNombre()));
		}
		System.out.println("* Assembling Aeropuertos ...");
		return AeropuertosDTO;
	}
}
