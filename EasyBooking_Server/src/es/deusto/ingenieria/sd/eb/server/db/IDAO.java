package es.deusto.ingenieria.sd.eb.server.db;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.eb.server.data.Persona;
import es.deusto.ingenieria.sd.eb.server.data.Reserva;
import es.deusto.ingenieria.sd.eb.server.data.Usuario;

public interface IDAO 
{
	public ArrayList<Persona> getPersonas();
	public ArrayList<Usuario> getUsuarios();
	public boolean guardarUsuario(Usuario usuario);
	public boolean guardarPersona(Persona persona);
	public boolean guardarReserva(Reserva reserva);
	public int getNumReservas();
}
