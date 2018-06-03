package es.deusto.ingenieria.sd.eb.server.db;

import java.util.ArrayList;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.eb.server.data.Persona;
import es.deusto.ingenieria.sd.eb.server.data.Reserva;
import es.deusto.ingenieria.sd.eb.server.data.Usuario;

public class DBManager {
	
	private static DBManager instance = new DBManager();
	
	private ArrayList<Usuario> usuariosCache;
	private ArrayList<Persona> personasCache;
	private ArrayList<Reserva> reservasCache;
	
	PersistenceManagerFactory pmf;
	// Persistence Manager
	PersistenceManager pm;
	//Transaction to group DB operations
	Transaction tx;	
	
	private DBManager() {
		this.usuariosCache = new ArrayList<Usuario>();
		this.personasCache = new ArrayList<Persona>();
		this.reservasCache = new ArrayList<Reserva>();
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		pm = null;
		tx = null;
	}

	public static DBManager getInstance() {
		return instance;
	}
	
	public ArrayList<Persona> getPersonas() 
	{
		try 
		{
		//Get the Persistence Manager
		pm = pmf.getPersistenceManager();
		//Obtain the current transaction
		tx = pm.currentTransaction();		
		//Start the transaction
		tx.begin();
	
		Extent<Persona> extent = pm.getExtent(Persona.class, true);
		
		for (Persona persona : extent) 
		{
			this.personasCache.add(persona);
			//System.out.println("  -> " + account);
		}
		//Notice the change in the accounts' balances
		//End the transaction
		//tx.commit();
		} 
		catch (Exception ex) 
		{
			System.err.println(" $ Error retrieving people using an 'Extent': " + ex.getMessage());
		}
		finally 
		{
			/*if (tx != null && tx.isActive()) {
				tx.rollback();
			}*/
		
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		return this.personasCache;
	}
	public ArrayList<Usuario> getUsuarios() 
	{
		try 
		{
		//Get the Persistence Manager
		pm = pmf.getPersistenceManager();
		//Obtain the current transaction
		tx = pm.currentTransaction();		
		//Start the transaction
		tx.begin();
	
		Extent<Usuario> extent = pm.getExtent(Usuario.class, true);
		this.usuariosCache.clear();
		for (Usuario usuario : extent) 
		{
			this.usuariosCache.add(usuario);
			//System.out.println("  -> " + account);
		}
		//Notice the change in the accounts' balances
		//End the transaction
		//tx.commit();
		} 
		catch (Exception ex) 
		{
			System.err.println(" $ Error retrieving people using an 'Extent': " + ex.getMessage());
		}
		finally 
		{
			/*if (tx != null && tx.isActive()) {
				tx.rollback();
			}*/
		
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		return this.usuariosCache;
	}
	public boolean guardarUsuario(Usuario usuario) {
		boolean retorno = false;
		//Usuario aux;
		//Object id;
			try
			{	
				getUsuarios();
				pm = pmf.getPersistenceManager();
				tx = pm.currentTransaction();		
				//Start the transaction
				tx.begin();
				
				//TODO: Update. Fracaso estrepitoso.
				/*if (usuario.getReservas().size()>0)
				{
					for (int i = 0; i < usuariosCache.size(); i++)
					{
						if (usuariosCache.get(i).getEmail().compareTo(usuario.getEmail())==0)
						{
							id = pm.getObjectById(Usuario.class, usuariosCache.get(i).getEmail());
							aux = (Usuario) pm.getObjectById(id);
							usuariosCache.set(i, usuario);
							aux.setReserva(usuario.getReservas().get(0)); //Solo deberia guardar 1 reserva asi.
							break;
						}
					}
				}
				else 
				{*/
					this.usuariosCache.add(usuario);
					pm.makePersistent(usuario);	
				//}
						
				
				//End the transaction
				tx.commit();
				retorno = true;
			}
			catch (Exception ex) 
			{
				System.err.println(" $ Error saving usuarios: " + ex.getMessage());
			}
			finally 
			{
				/*if (tx != null && tx.isActive()) {
					tx.rollback();
				}*/
			
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}	
			return retorno;
	}
	public boolean guardarPersona(Persona persona) {
		boolean retorno = false;
			try
			{
				this.personasCache.add(persona);
				
				pm = pmf.getPersistenceManager();
				tx = pm.currentTransaction();		
				//Start the transaction
				tx.begin();
				
				pm.makePersistent(persona);			
				
				//End the transaction
				tx.commit();
				retorno = true;
			}
			catch (Exception ex) 
			{
				System.err.println(" $ Error saving reservas: " + ex.getMessage());
			}
			finally 
			{
				/*if (tx != null && tx.isActive()) {
					tx.rollback();
				}*/
			
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}	
			return retorno;
	}
	public boolean guardarReserva(Reserva reserva) {
		boolean retorno = false;
		
		try
		{
			this.reservasCache.add(reserva);
			
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();
			
			pm.makePersistent(reserva);			
			
			//End the transaction
			tx.commit();
			retorno = true;
		}
		catch (Exception ex) 
		{
			System.err.println(" $ Error saving reservas: " + ex.getMessage());
		}
		finally 
		{
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}		
		return retorno;
	}

	public int getNumReservas() 
	{
		int numreservas = 0;
		try 
		{
		//Get the Persistence Manager
		System.out.println("3333333333333333333333333");
		pm = pmf.getPersistenceManager();
		System.out.println("4444444444444444444444444");
		//Obtain the current transaction
		tx = pm.currentTransaction();
		System.out.println("5555555555555555555555555");
		//Start the transaction
		tx.begin();
	
		Extent<Reserva> extent = pm.getExtent(Reserva.class, true);
		
		for (@SuppressWarnings("unused") Reserva reserva : extent) 
		{
			numreservas++;
		}
		} 
		catch (Exception ex) 
		{
			System.err.println(" $ Error retrieving reservas using an 'Extent': " + ex.getMessage());
		}
		finally 
		{
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		return numreservas;
	}
}