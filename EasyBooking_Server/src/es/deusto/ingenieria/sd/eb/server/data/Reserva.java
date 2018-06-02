package es.deusto.ingenieria.sd.eb.server.data;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reserva {
	
	@PrimaryKey
	private int CodigoReserva;
	private String CodigoVuelo;
	private Date Fecha;
	private String email;
	
	@Join
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	
	public Reserva(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<Persona> personas) {
		setEmail(email);
		CodigoReserva = codigoReserva;
		CodigoVuelo = codigoVuelo;
		Fecha = fecha;
		this.personas = personas;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
}
