package es.deusto.ingenieria.sd.eb.server.data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (detachable="true")
public class Usuario //extends Persona
{
	@PrimaryKey
	private String email;
	private String aero;
	
	@Persistent(mappedBy="email", dependentElement="true")
	@Join
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	public Usuario (String email) 
	{
		this.email = email;
		aero = "PP";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAeropuerto() {
		return aero;
	}
	public void setAeropuerto(String aero) {
		this.aero = aero;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReserva(Reserva reserva) {
		reservas.add(reserva);
	}
}
