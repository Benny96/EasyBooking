package es.deusto.ingenieria.sd.eb.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.eb.server.data.dto.UsuarioDTO;

public interface IUsuarioAdmin extends Remote {
	public List<UsuarioDTO> getUsuariosDTO() throws RemoteException;
	public void generarNuevoUsuario (String email) throws RemoteException;
	public void eliminarUsuario (String email) throws RemoteException;
}