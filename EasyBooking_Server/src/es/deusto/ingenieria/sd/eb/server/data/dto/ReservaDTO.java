package es.deusto.ingenieria.sd.eb.server.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.eb.server.data.Persona;

public class ReservaDTO implements Serializable {
	
private static final long serialVersionUID = 1L;

	private int CodigoReserva;
	private String CodigoVuelo;
	private Date Fecha;
	private String email_;
	
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	public ReservaDTO(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<Persona> personas) {
		super();
		setEmail_(email);
		CodigoReserva = codigoReserva;
		CodigoVuelo = codigoVuelo;
		Fecha = fecha;
		this.setPersonas(personas);
	}
	
	public int getCodigoReserva() {
		return CodigoReserva;
	}
	public void setCodigoReserva(int codigoReserva) {
		CodigoReserva = codigoReserva;
	}
	public String getCodigoVuelo() {
		return CodigoVuelo;
	}
	public void setCodigoVuelo(String codigoVuelo) {
		CodigoVuelo = codigoVuelo;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getEmail_() {
		return email_;
	}
	public void setEmail_(String email_) {
		this.email_ = email_;
}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
		

}
