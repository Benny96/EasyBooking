package es.deusto.ingenieria.sd.eb.server.remote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import es.deusto.ingenieria.sd.eb.server.data.Usuario;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioAssembler;
import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.eb.server.db.DBManager;
import es.deusto.ingenieria.sd.eb.server.gateway.IGatewayAuth;

public class UsuarioAdmin extends UnicastRemoteObject implements IUsuarioAdmin
{
	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios = new TreeMap<String, Usuario>();
	private IGatewayAuth gatewayGoogle;
	
	//private Map<String, UsuarioDTO> usuarios = new TreeMap<String, UsuarioDTO>();

	public UsuarioAdmin(IGatewayAuth gservice) throws RemoteException {
		super();
		gatewayGoogle = gservice;
	}
	public synchronized void generarNuevoUsuario (String email) throws RemoteException {
		//TODO: Hacer llamada a Gateway Facebook o Google.
		try 
		{
			if (gatewayGoogle.darAltaUsuario(email)==0)
			{
				System.out.println("Este usuario existe en Google+. Se procederá a crear el usuario con el correo "+ email);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Este usuario no existe en Google+. - El usuario no se creará");
			throw new RemoteException();
		}
		//TODO: Escribir análogo para Facebook.
		/*if (gatewayGoogle.darAltaUsuario(email)==0)
		{
			System.out.println("Este usuario existe en Google+. Se procederá a crear el usuario con el correo "+ email);
		}*/
		if (!(usuarios.containsKey(email)))
		{
			System.out.println("* Creando un nuevo usuario: " + email);
			Usuario user = new Usuario(email);
			usuarios.put(email, user);
			DBManager.getInstance().guardarUsuario(user);
		}
		else
		{
			System.out.println("The user has not been created");
			throw new RemoteException();
		}
	}

	public synchronized void eliminarUsuario(String email) {
		usuarios.remove(email);
		System.out.println("* Removing users: " + email);
	}

	public synchronized List<UsuarioDTO> getUsuariosDTO() {
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		UsuarioAssembler assembler = new UsuarioAssembler();
		usuarios = assembler.assemble(getUsuarios());
		return usuarios;
	}
	
	public synchronized List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		//TODO: Hacer llamada a Gateway Facebook o Google.
		System.out.println("* Retrieving Usuarios ...");
		for(Entry<String, Usuario> entry : this.usuarios.entrySet()) {
			  usuarios.add(entry.getValue());
			}
		return usuarios;
	}
}
