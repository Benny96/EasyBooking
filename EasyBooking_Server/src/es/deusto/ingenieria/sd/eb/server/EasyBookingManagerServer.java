package es.deusto.ingenieria.sd.eb.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayPago;
import es.deusto.ingenieria.sd.eb.server.gateway.PagoPayPal;
import es.deusto.ingenieria.sd.eb.server.gateway.PagoTarjeta;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayAir;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayAuth;
import es.deusto.ingenieria.sd.eb.server.gateway.AirToScreen;
import es.deusto.ingenieria.sd.eb.server.gateway.FacebookAuth;
import es.deusto.ingenieria.sd.eb.server.gateway.GoogleAuth;
import es.deusto.ingenieria.sd.eb.server.remote.IReservaAdmin;
import es.deusto.ingenieria.sd.eb.server.remote.IUsuarioAdmin;
import es.deusto.ingenieria.sd.eb.server.remote.ReservaAdmin;
import es.deusto.ingenieria.sd.eb.server.remote.UsuarioAdmin;


public class EasyBookingManagerServer {
	
	public static void main(String[] args) {
		if (args.length != 14) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		String nameReserva = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String nameUsuario = "//" + args[0] + ":" + args[1] + "/" + args[3];
		
		try {
			
			IGatewayPago resTarjetaService = new PagoTarjeta(args[4], Integer.parseInt(args[5]));
			IGatewayPago resPayPalService = new PagoPayPal(args[6], Integer.parseInt(args[7]));
			
			IGatewayAir airSocketService = new AirToScreen(args[8], Integer.parseInt(args[9]));
			
			IGatewayAuth googleService = new GoogleAuth(args[10], Integer.parseInt(args[11]));
			IGatewayAuth facebookService = new FacebookAuth(args[12], Integer.parseInt(args[13]));
			
			IReservaAdmin reservaAdminService = new ReservaAdmin(resTarjetaService, resPayPalService, airSocketService);			
			Naming.rebind(nameReserva, reservaAdminService);
			System.out.println("* Reserva Admin Service '" + nameReserva + "' active and waiting...");
			
			IUsuarioAdmin usuarioAdminService = new UsuarioAdmin(googleService, facebookService);
			Naming.rebind(nameUsuario, usuarioAdminService);
			System.out.println("* Usuario Admin Service '" + nameUsuario + "' active and waiting...");
		} 
		catch (Exception e) 
		{
			System.err.println("$ UsuarioManager exception: " + e.getMessage());
		}
	}

}
