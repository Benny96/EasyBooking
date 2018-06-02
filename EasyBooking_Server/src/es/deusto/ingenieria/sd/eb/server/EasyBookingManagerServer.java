package es.deusto.ingenieria.sd.eb.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayPago;
import es.deusto.ingenieria.sd.eb.server.gateway.PagoPayPal;
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
			
			//IGatewayPago resService = new PagoPayPal(args[6], Integer.parseInt(args[7]));
			//IGatewayAir airService = new AirToScreen(args[8], Integer.parseInt(args[9]));
			
			IGatewayAuth googleService = new GoogleAuth(args[10], Integer.parseInt(args[11]));
			IGatewayAuth facebookService = new FacebookAuth(args[12], Integer.parseInt(args[13]));
			
			IReservaAdmin reservaAdminService = new ReservaAdmin();			
			Naming.rebind(nameReserva, reservaAdminService);
			System.out.println("* Reserva Admin Service '" + nameReserva + "' active and waiting...");
			
			IUsuarioAdmin usuarioAdminService = new UsuarioAdmin(googleService, facebookService);
			Naming.rebind(nameUsuario, usuarioAdminService);
			System.out.println("* Usuario Admin Service '" + nameUsuario + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ UsuarioManager exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		// Load Persistence Manager Factory - referencing the Persistence Unit defined in persistence.xml
		//PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
				// Persistence Manager
		//PersistenceManager pm = null;
				//Transaction to group DB operations
		//Transaction tx = null;		
				/**
				try {
					System.out.println("- Store objects in the DB");			
					//Get the Persistence Manager, using the Persistence Manager Factory.
					//JDO es una interfaz estándar de Java. Está pensado para que pueda haber cualquier gestor por debajo.
					//El PM es de DataNucleus; el de Hibernate sería otro.
					pm = pmf.getPersistenceManager();
					//Obtain the current transaction
					tx = pm.currentTransaction();		
					//Start the transaction
					tx.begin();
					
					pm.makePersistent(user1);
					pm.makePersistent(user2);			
					
					//End the transaction
					tx.commit();			
				} catch (Exception ex) {
					System.err.println(" $ Error storing objects in the DB: " + ex.getMessage());
					ex.printStackTrace();
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					
					if (pm != null && !pm.isClosed()) {
						pm.close();
						// ATTENTION -  Datanucleus detects that the objects in memory were changed and they are flushed to DB
					}
				}
				
				try {
					System.out.println("- Retrieving all the accounts using an 'Extent'...");			
					//Get the Persistence Manager
					pm = pmf.getPersistenceManager();
					//Obtain the current transaction
					tx = pm.currentTransaction();		
					//Start the transaction
					tx.begin();
				
					Extent<Account> extent = pm.getExtent(Account.class, true);
					
					for (Account account : extent) {
						System.out.println("  -> " + account);
					}
					//Notice the change in the accounts' balances
					//End the transaction
					tx.commit();
				} catch (Exception ex) {
					System.err.println(" $ Error retrieving accounts using an 'Extent': " + ex.getMessage());
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					
					if (pm != null && !pm.isClosed()) {
						pm.close();
					}
				}

				try {
					System.out.println("- Retrieving accounts with balace > 200.0 using a 'Query'...");			
					//Get the Persistence Manager
					pm = pmf.getPersistenceManager();
					//Obtain the current transaction
					tx = pm.currentTransaction();		
					//Start the transaction
					tx.begin();

					Query<Account> query = pm.newQuery(Account.class);
					query.setFilter("balance > 200.0");
					
					@SuppressWarnings("unchecked")
					List<Account> accounts = (List<Account>) query.execute();

					//End the transaction
					tx.commit();
					
					for (Account account : accounts) {
						System.out.println("  -> " + account.getUser().getFullName() + " - " + account.getBankName());
					}
				} catch (Exception ex) {
					System.err.println(" $ Error retrieving accounts using a 'Query': " + ex.getMessage());
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					
					if (pm != null && !pm.isClosed()) {
						pm.close();
					}
				}

				try {
					System.out.println("- Deleting 'User->Address' relation...");			
					//Get the Persistence Manager
					pm = pmf.getPersistenceManager();
					//Obtain the current transaction
					tx = pm.currentTransaction();		
					//Start the transaction
					tx.begin();

					Query<User> query = pm.newQuery(User.class);
					@SuppressWarnings("unchecked")
					List<User> users = (List<User>) query.execute();
					
					for (User user : users) {
						System.out.println("  -> Retrieved user: " + user.getFullName());
						System.out.println("     + Removing user from the addresses ... ");
						user.removeUserFromAddresses();
					}
					
					//End the transaction
					tx.commit();
				} catch (Exception ex) {
					System.err.println(" $ Error deleting 'User->Address' relation: " + ex.getMessage());
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					
					if (pm != null && !pm.isClosed()) {
						pm.close();
					}
				}

				try {
					System.out.println("- Cleaning the DB...");			
					//Get the Persistence Manager
					pm = pmf.getPersistenceManager();
					//Obtain the current transaction
					tx = pm.currentTransaction();
					//Start the transaction
					tx.begin();
					
					//Delete users from DB
					// As we are considering accounts as dependents on user - CASCADING BEHAVIOUR - ACCOUNTS DELETED
					Query<User> query1 = pm.newQuery(User.class);
					System.out.println(" * '" + query1.deletePersistentAll() + "' users and their accounts deleted from the DB.");
					//Delete addresses from DB
					Query<Address> query2 = pm.newQuery(Address.class);
					System.out.println(" * '" + query2.deletePersistentAll() + "' addresses deleted from the DB.");
					
					//End the transaction
					tx.commit();
				} catch (Exception ex) {
					System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
					ex.printStackTrace();
				} finally {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
					
					if (pm != null && !pm.isClosed()) {
						pm.close();
					}
					**/
	}

}
