package es.deusto.ingenieria.sd.eb.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import es.deusto.ingenieria.sd.eb.server.data.Reserva;
import es.deusto.ingenieria.sd.eb.server.data.Persona;
import es.deusto.ingenieria.sd.eb.server.data.dto.PersonaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaAssembler;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioAssembler;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.eb.server.db.DBManager;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayPago;

public class ReservaAdmin extends UnicastRemoteObject implements IReservaAdmin{
	
	private static final long serialVersionUID = 1L;
	private IGatewayPago resService;
	private Map<Integer, Reserva> reservas = new TreeMap<Integer, Reserva>();
	
	public ReservaAdmin(IGatewayPago resService) throws RemoteException {
		super();
		this.resService=resService;
	}

	@Override
	public List<ReservaDTO> getReservasDTO() throws RemoteException {
		List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
		ReservaAssembler assembler = new ReservaAssembler();
		reservas = assembler.assemble(getReservas());
		return reservas;
	}
	
	public synchronized List<Reserva> getReservas() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		System.out.println("* Recuperando reservas...");
		for(Entry<Integer, Reserva> entry : this.reservas.entrySet()) {
			reservas.add(entry.getValue());
			}
		return reservas;
	}

	@Override
	public boolean nuevaReserva(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<PersonaDTO> personas) throws RemoteException 
	{
		//TODO: Test a cambiar.
		//if(resService.efectuarPago(email, 50.0)==1) 
		//{
			System.out.println("* Creando una nueva reserva: " + codigoReserva);
			Reserva reserva = new Reserva (codigoReserva, email, codigoVuelo, fecha, getPersonas(personas));
			//TODO: PENSAR SI SOBRA EL MAPA.
			reservas.put(codigoReserva, reserva);
			DBManager.getInstance().guardarReserva(reserva);
			return true;
		//}
		//return false;
	}
	
	public synchronized ArrayList<Persona> getPersonas(ArrayList<PersonaDTO> people) {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		for(PersonaDTO aux : people) {
			personas.add(new Persona(aux.getPasaporte(), aux.getNombre(), aux.getApellido1(), aux.getApellido2()));
			}
		return personas;
	}

	@Override
	public void cancelarReserva(int codigoReserva) throws RemoteException {
		reservas.remove(codigoReserva);
		System.out.println("* Cancelando reserva: " + codigoReserva);
	}
	@Override
	public int numeroReservas() throws RemoteException {
		return DBManager.getInstance().getNumReservas();
	}
}
