package es.deusto.ingenieria.sd.eb.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class FacebookAuth implements IGatewayAuth {

	private String host;
	private int port;
	
	public FacebookAuth(String arg1, int arg2)
	{
		host = arg1;
		port = arg2;
	}
	@Override
	public int darAltaUsuario(String correo, String pass){
		// TODO Hacer Alta Usuario FB.
		String data = null;
		Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int resultado=0;
		try {
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(correo.toUpperCase());
			System.out.println("   - FacebookAuth - Sent data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data.toUpperCase() + "'");
			
			//Read request from the client
			resultado = in.readInt();	
			System.out.println("   - FacebookAuth - Received data from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data + "'");		
			socket.close();
		}catch (EOFException e) {
			System.err.println("   # UsuarioAdmin - FacebookAuth - TCPConnection EOF error" + e.getMessage());
		}	
		catch (IOException e1) {
			System.err.println("   # UsuarioAdmin - FacebookAuth - TCPConnection IO error:" + e1.getMessage());
		}
		return resultado;
	}
	public int logearUsuario(String correo, String pass){
		// TODO Hacer Login Usuario FB.
		String data = null;
		Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int resultado=0;
		try {
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(correo.toUpperCase());
			System.out.println("   - PagoPayPal - Sent data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data.toUpperCase() + "'");
			
			//Read request from the client
			resultado = in.readInt();	
			System.out.println("   - FacebookAuth - Received data from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data + "'");		
			socket.close();
		}catch (EOFException e) {
			System.err.println("   # UsuarioService - FacebookAuth - TCPConnection EOF error" + e.getMessage());
		}	
		catch (IOException e1) {
			System.err.println("   # UsuarioService - FacebookAuth - TCPConnection IO error:" + e1.getMessage());
		}
		return resultado;
	}
}
