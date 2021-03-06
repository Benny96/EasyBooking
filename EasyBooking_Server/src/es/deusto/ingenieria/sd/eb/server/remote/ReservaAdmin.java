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
import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.eb.server.data.Persona;
import es.deusto.ingenieria.sd.eb.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.eb.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.PersonaDTO;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaAssembler;
import es.deusto.ingenieria.sd.eb.server.data.dto.ReservaDTO;
import es.deusto.ingenieria.sd.eb.server.db.DBManager;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayAir;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayPago;

public class ReservaAdmin extends UnicastRemoteObject implements IReservaAdmin{
	
	private static final long serialVersionUID = 1L;
	private IGatewayPago resTarjetaService;
	private IGatewayPago resPayPalService;
	private IGatewayAir resAirSocketService;
	private Map<Integer, Reserva> reservas = new TreeMap<Integer, Reserva>();
	
	public ReservaAdmin(IGatewayPago resTService, IGatewayPago resPPService, IGatewayAir resSAService) throws RemoteException {
		super();
		this.resTarjetaService=resTService;
		this.resPayPalService=resPPService;
		this.resAirSocketService=resSAService;
	}

	@Override
	public void nuevaReserva(int codigoReserva, String email, String codigoVuelo, Date fecha, ArrayList<PersonaDTO> personas, int pago) throws RemoteException 
	{
		if (pago == 0)
		{
			System.out.println("Comprobando saldo de Tarjeta del email "+ email);
			if(resTarjetaService.efectuarPago(email, personas.size()*50)==0)
			{
				System.out.println("Hay saldo suficiente para operar en la Tarjeta.");
				System.out.println("* Creando una nueva reserva: " + codigoReserva);
				Reserva reserva = new Reserva (codigoReserva, email, codigoVuelo, fecha, getPersonas(personas));
				
				//TODO: Se ha intentado hacer una actualización de los Usuarios pero no se ha conseguido. Se deja como mejora futura.
				/*List<Usuario> usuarios = new ArrayList<Usuario>();
				usuarios = DBManager.getInstance().getUsuarios();
				for (Usuario aux: usuarios)
				{
					if (aux.getEmail().compareTo(email)==0)
					{
						Usuario usu = new Usuario(aux.getEmail());*/
						/*for (int i = 0; i<aux.getReservas().size(); i++)
						{
							usu.setReserva(aux.getReservas().get(i));
						}*/
				/*		usu.setReserva(reserva);
						DBManager.getInstance().guardarUsuario(usu); //No hace una update de la tabla.
						break;
					}
				}*/
				reservas.put(codigoReserva, reserva);
				DBManager.getInstance().guardarReserva(reserva);
			}
			else
			{
				System.out.println("No hay saldo suficiente en la Tarjeta. No se puede hacer esta reserva.");
				throw new RemoteException();
			}
		}
		else
		{
			System.out.println("Comprobando saldo de PayPal del email "+ email);
			if(resPayPalService.efectuarPago(email, personas.size()*50)==0)
			{
				System.out.println("Hay saldo suficiente para pagar con PayPal.");
				System.out.println("* Creando una nueva reserva: " + codigoReserva);
				Reserva reserva = new Reserva (codigoReserva, email, codigoVuelo, fecha, getPersonas(personas));
				
				//TODO: Se ha intentado hacer una actualización de los Usuarios pero no se ha conseguido. Se deja como mejora futura.
				/*List<Usuario> usuarios = new ArrayList<Usuario>();
				usuarios = DBManager.getInstance().getUsuarios();
				for (Usuario aux: usuarios)
				{
					if (aux.getEmail().compareTo(email)==0)
					{
						Usuario usu = new Usuario(aux.getEmail());*/
						/*for (int i = 0; i<aux.getReservas().size(); i++)
						{
							usu.setReserva(aux.getReservas().get(i));
						}*/
				/*		usu.setReserva(reserva);
						DBManager.getInstance().guardarUsuario(usu); //No hace una update de la tabla.
						break;
					}
				}*/
				reservas.put(codigoReserva, reserva);
				DBManager.getInstance().guardarReserva(reserva);
			}
			else
			{
				System.out.println("No hay saldo suficiente en PayPal. No se puede hacer esta reserva.");
				throw new RemoteException();
			}
		}
	}
	
	@Override
	public int numeroReservas() throws RemoteException {
		return DBManager.getInstance().getNumReservas();
	}
	
	public synchronized ArrayList<Persona> getPersonas(ArrayList<PersonaDTO> people) {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		for(PersonaDTO aux : people) {
			personas.add(new Persona(aux.getPasaporte(), aux.getNombre(), aux.getApellido1(), aux.getApellido2()));
			}
		return personas;
	}
	
	@Override
	public List<AeropuertoDTO> getAeropuertosSocketDTO() throws RemoteException {
		return AeropuertoAssembler.getInstance().assemble(getAeropuertosSocket());
	}
	
	public synchronized List<Aeropuerto> getAeropuertosSocket() {
		return resAirSocketService.buscarVuelos();
	}

	@Override
	public List<ReservaDTO> getReservasDTO() throws RemoteException {
		return ReservaAssembler.getInstance().assemble(getReservas());
	}
	
	public synchronized List<Reserva> getReservas() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		System.out.println("* Recuperando reservas...");
		for(Entry<Integer, Reserva> entry : this.reservas.entrySet()) {
			reservas.add(entry.getValue());
			}
		return reservas;
	}
	
	//TODO: Finalmente no se ha utilizado en esta implementación. Se podría tratar de asignar esta función en un futuro.
	@Override
	public void cancelarReserva(int codigoReserva) throws RemoteException {
		reservas.remove(codigoReserva);
		System.out.println("* Cancelando reserva: " + codigoReserva);
	}
}
