package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.io.Serializable;

import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Email;
	private String Aeropuerto;
	
	public UsuarioDTO (String email) {
		super();
		Email = email;
		Aeropuerto = "PP";
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAeropuerto() {
		return Aeropuerto;
	}
	public void setAeropuerto(String aeropuerto) {
		Aeropuerto = aeropuerto;
	}
}