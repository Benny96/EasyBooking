package es.deusto.ingenieria.sd.eb.server.data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Persona {
	
	@PrimaryKey
	private String Pasaporte;
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	@Join
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	public Persona(String pasaporte, String nombre, String apellido1, String apellido2)
	{
		Pasaporte=pasaporte;
		Nombre=nombre;
		Apellido1=apellido1;
		Apellido2=apellido2;
	}
	
	public String getPasaporte() {
		return Pasaporte;
	}
	public void setPasaporte(String pasaporte) {
		Pasaporte = pasaporte;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido1() {
		return Apellido1;
	}
	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}
	public String getApellido2() {
		return Apellido2;
	}
	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
}
