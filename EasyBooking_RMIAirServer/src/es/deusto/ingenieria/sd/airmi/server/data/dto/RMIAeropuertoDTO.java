package es.deusto.ingenieria.sd.airmi.server.data.dto;

import java.io.Serializable;

public class RMIAeropuertoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Nombre;
	private String Codigo;
	
    public RMIAeropuertoDTO(String nombre, String codigo) {
		super();
		Nombre = nombre;
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
}
