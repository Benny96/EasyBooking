package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.Reserva;

public class ReservaAssembler {
	
	public List<ReservaDTO> assemble(List<Reserva> reservas) 
	{
		List<ReservaDTO> reservasDTO = new ArrayList<>();
		for (Reserva r : reservas) 
		{
			reservasDTO.add(new ReservaDTO(r.getCodigoReserva(), r.getEmail(), r.getCodigoVuelo(),  r.getFecha(), r.getPersonas()));
		}
		System.out.println("* Assembling Reservas ...");
		return reservasDTO;
	}
}
