package es.deusto.ingenieria.sd.eb.server.data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Usuario //extends Persona
{
	@PrimaryKey
	private String Email;
	private String Aeropuerto; //Tendrá que ser un String, no un Aeropuerto.
	//private String Contrasena;//Esto lo quitamos despues
	
	@Persistent(mappedBy="email", dependentElement="true")
	@Join
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	public Usuario (String email) {
		//super();
		Email = email;
		Aeropuerto = "PP";
		//Contrasena=contrasena;
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
	public void setString(String aeropuerto) {
		Aeropuerto = aeropuerto;
	}
	/*public String getContrasena() {
		return Contrasena;
	}
	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}*/
	
	
}
