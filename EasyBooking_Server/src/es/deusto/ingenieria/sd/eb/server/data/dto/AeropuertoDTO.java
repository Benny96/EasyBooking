package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.io.Serializable;

public class AeropuertoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Codigo;
	private String Nombre;
	
    public AeropuertoDTO(String codigo, String nombre) {
		super();
		Codigo = codigo;
		Nombre = nombre;	
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
}
