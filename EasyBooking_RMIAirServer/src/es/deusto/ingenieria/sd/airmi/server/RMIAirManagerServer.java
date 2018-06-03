package es.deusto.ingenieria.sd.airmi.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import es.deusto.ingenieria.sd.airmi.server.remote.IRMIAirline;
import es.deusto.ingenieria.sd.airmi.server.remote.RMIAirline;

public class RMIAirManagerServer {
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		String nameAirline = "//" + args[0] + ":" + args[1] + "/" + args[2];
		
		try 
		{
			IRMIAirline airlineService = new RMIAirline();
			Naming.rebind(nameAirline, airlineService);
			System.out.println("* Airline Admin Service '" + nameAirline + "' active and waiting...");
		} 
		catch (Exception e) 
		{
			System.err.println("$ AirlineManager exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
