package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.Usuario;

public class UsuarioAssembler {
	
private static UsuarioAssembler instance = new UsuarioAssembler();
	
	private UsuarioAssembler() {}

	public static UsuarioAssembler getInstance() {
		return instance;
	}
	
	public List<UsuarioDTO> assemble(List<Usuario> usuarios) 
	{
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for (Usuario r : usuarios) {
			usuariosDTO.add(new UsuarioDTO(r.getEmail()));
		}
		System.out.println("* Assembling Usuarios ...");		
		return usuariosDTO;
	}
}
