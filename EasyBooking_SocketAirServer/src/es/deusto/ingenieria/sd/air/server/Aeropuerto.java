package es.deusto.ingenieria.sd.air.server;

import java.io.Serializable;

public class Aeropuerto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Nombre;
	private String Codigo;
	
	
    public Aeropuerto(String nombre, String codigo) {
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
