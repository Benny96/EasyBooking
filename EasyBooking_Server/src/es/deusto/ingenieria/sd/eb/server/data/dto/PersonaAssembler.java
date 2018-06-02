package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.Persona;

public class PersonaAssembler {
	
	//TODO: CREO QUE NO NOS HACE FALTA PARA NADA EN NUESTRO PROGRAMA.
	
	public List<PersonaDTO> assemble(List<Persona> personas) {
		List<PersonaDTO> personasDTO = new ArrayList<>();

		for (Persona r : personas) {
			personasDTO.add(new PersonaDTO(r.getPasaporte(), r.getNombre(), r.getApellido1(), r.getApellido2()));
		}

		System.out.println("* Assembling personas ...");
		
		return personasDTO;
}
}
