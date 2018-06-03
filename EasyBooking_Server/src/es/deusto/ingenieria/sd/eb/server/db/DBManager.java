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

public class DBManager implements IDAO {
	
	private static DBManager instance = new DBManager();
	
	private ArrayList<Usuario> usuariosCache;
	private ArrayList<Persona> personasCache;
	private ArrayList<Reserva> reservasCache;
	
	PersistenceManagerFactory pmf;
	PersistenceManager pm;
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
	@Override
	public ArrayList<Persona> getPersonas() 
	{
		try 
		{
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			Extent<Persona> extent = pm.getExtent(Persona.class, true);
			for (Persona persona : extent) 
			{
				this.personasCache.add(persona);
			}
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
	@Override
	public ArrayList<Usuario> getUsuarios() 
	{
		try 
		{
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();		
			tx.begin();
			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);
			this.usuariosCache.clear();
			for (Usuario usuario : extent) 
			{
				this.usuariosCache.add(usuario);
			}
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
	@Override
	public boolean guardarUsuario(Usuario usuario) 
	{
		boolean retorno = false;
		//Usuario aux;
		//Object id;
			try
			{	
				getUsuarios();
				pm = pmf.getPersistenceManager();
				tx = pm.currentTransaction();		
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
	@Override
	public boolean guardarPersona(Persona persona) 
	{
		boolean retorno = false;
			try
			{
				this.personasCache.add(persona);
				
				pm = pmf.getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				pm.makePersistent(persona);
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
	@Override
	public boolean guardarReserva(Reserva reserva) 
	{
		boolean retorno = false;
		try
		{
			this.reservasCache.add(reserva);
			
			pm = pmf.getPersistenceManager();
			tx = pm.currentTransaction();		
			tx.begin();
			pm.makePersistent(reserva);			
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
	@Override
	public int getNumReservas() 
	{
		int numreservas = 0;
		try 
		{
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		tx.begin();
		Extent<Reserva> extent = pm.getExtent(Reserva.class, true);
		
			for (Reserva reserva : extent) 
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