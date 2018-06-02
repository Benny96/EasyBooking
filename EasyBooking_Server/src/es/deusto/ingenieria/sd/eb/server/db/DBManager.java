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
	
	/*private List<Category> categoriesCache;
	private List<Article> articlesCache;
	private List<User> usersCache;
	private List<Bid> bidsCache;*/
	
	private DBManager() {
		this.usuariosCache = new ArrayList<Usuario>();
		this.personasCache = new ArrayList<Persona>();
		this.reservasCache = new ArrayList<Reserva>();
		System.out.println("RTOIPMHRPTOMYPORMYOPRTYKROPKYRPOTYRTOPYKROPYJKROPTYKROPTYKROPTKYORPTY");
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		System.out.println("ASASDASDASDASDASDASDASDASDASDASDASDASDASDASDADASDASD");
		pm = null;
		tx = null;
		/*this.categoriesCache = new ArrayList<>();
		this.articlesCache = new ArrayList<>();
		this.usersCache = new ArrayList<>();
		this.bidsCache =  new ArrayList<>();

		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");
		user1.setPassword("12345");		
		
		User user2 = new User();
		user2.setEmail("troyaikman08@hotmail.com");
		user2.setNickname("troyaikman08");
		user2.setPassword("12345");
		
		this.usersCache.add(user1);
		this.usersCache.add(user2);
		
		Category iPadCat = new Category();
		iPadCat.setName("iPad");
		
		this.categoriesCache.add(iPadCat);
		
		Article iPadCover = new Article();
		iPadCover.setActive(true);
		iPadCover.setCategory(iPadCat);
		iPadCover.setInitialPrice(10.0f);
		iPadCover.setNumber(1);
		iPadCover.setOwner(user2);
		iPadCover.setTitle("iPad 2 Cover");		
		
		iPadCat.addArticle(iPadCover);
		user2.addArticle(iPadCover);
		
		Article iPadStylus = new Article();
		iPadStylus.setActive(true);
		iPadStylus.setCategory(iPadCat);
		iPadStylus.setInitialPrice(15.50f);
		iPadStylus.setNumber(2);
		iPadCover.setOwner(user2);
		iPadStylus.setTitle("iPad Stylus");
		
		iPadCat.addArticle(iPadStylus);
		user2.addArticle(iPadStylus);
		
		this.articlesCache.add(iPadCover);
		this.articlesCache.add(iPadStylus);*/
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
			try
			{
				this.usuariosCache.add(usuario);
				
				pm = pmf.getPersistenceManager();
				tx = pm.currentTransaction();		
				//Start the transaction
				tx.begin();
				
				pm.makePersistent(usuario);			
				
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
			/*if (tx != null && tx.isActive()) {
				tx.rollback();
			}*/
		
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
		
		for (Reserva reserva : extent) 
		{
			numreservas++;
			//System.out.println("  -> " + account);
		}
		//Notice the change in the accounts' balances
		//End the transaction
		//tx.commit();
		} 
		catch (Exception ex) 
		{
			System.err.println(" $ Error retrieving reservas using an 'Extent': " + ex.getMessage());
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
		return numreservas;
	}
	/**
	public boolean store(Category category) {
		this.categoriesCache.add(category);
		
		return true;
	}

	public boolean store(Article article) {
		this.articlesCache.add(article);
		
		return true;
	}

	public boolean store(User user) {
		this.usersCache.add(user);
		
		return true;
	}

	public boolean store(Bid bid) {
		this.bidsCache.add(bid);
		
		return true;
	}

	public Category getCategory(String categoryName) {		
		for (Category category : this.categoriesCache) {
			if (category.getName().equalsIgnoreCase(categoryName)) {
				return category;
			}
		}
		
		return null;
	}

	public List<Category> getCategories() {
		return this.categoriesCache;
	}

	public Article getArticle(long number) {
		
		for (Article article : this.articlesCache) {
			if (article.getNumber() == number) {
				return article;
			}
		}		
		
		return null;
	}

	public List<Article> getArticles(String category) {
		List<Article> articles = new ArrayList<>();
		
		for (Article article : this.articlesCache) {
			if (article.getCategory().getName().equalsIgnoreCase(category)) {
				articles.add(article);
			}			
		}
		
		return articles;
	}

	public User getUser(String email) {
		
		for (User user : this.usersCache) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}
		
		return null;
		*/
}