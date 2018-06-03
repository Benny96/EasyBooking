package es.deusto.ingenieria.sd.eb.client.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.airmi.server.remote.IRMIAirline;
import es.deusto.ingenieria.sd.eb.server.remote.IReservaAdmin;
import es.deusto.ingenieria.sd.eb.server.remote.IUsuarioAdmin;

public class RMIServiceLocator{
	// The Cache
	private IReservaAdmin reservaService;
	private IUsuarioAdmin usuarioService;
	private IRMIAirline airlineService;

    public RMIServiceLocator() {}

    public void setService(String[] args) {
    	
    	// Add your code to get the TARGET reference HERE
      	try 
      	{
      		reservaService = (IReservaAdmin) Naming.lookup("//" + args[0] + ":" + args[1] + "/" + args[2]);
    	} catch (MalformedURLException | RemoteException | NotBoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    	}	
      	try 
      	{
      		usuarioService = (IUsuarioAdmin) Naming.lookup("//" + args[0] + ":" + args[1] + "/" + args[3]);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	try 
      	{
      		airlineService = (IRMIAirline) Naming.lookup("//" + args[0] + ":" + args[1] + "/" + args[4]);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

    public IReservaAdmin getReservaService() {
    	return reservaService;
    }
    
    public IUsuarioAdmin getUsuarioService() {
    	return usuarioService;
    }
    public IRMIAirline getRMIAirlineService() {
    	return airlineService;
    }
}