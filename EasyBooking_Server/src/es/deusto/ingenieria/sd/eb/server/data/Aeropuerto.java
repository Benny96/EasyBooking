package es.deusto.ingenieria.sd.eb.server.data;

import java.io.Serializable;

public class Aeropuerto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Codigo;
	private String Nombre;
	
    public Aeropuerto(String codigo, String nombre) {
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
