package es.deusto.ingenieria.sd.eb.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.eb.server.data.Aeropuerto;

public class AirToScreen implements IGatewayAir {

	private String host;
	private int port;
	
	public AirToScreen(String arg1, int arg2)
	{
		host = arg1;
		port = arg2;
	}
	@Override
	public ArrayList <Aeropuerto> buscarVuelos(String cod_aero_or, String cod_aero_dest, Date fecha) {
		// TODO Auto-generated method stub
		//Recoger String con formato previo a traducir
		//Devolver el String con el formato adecuado
		ArrayList<Aeropuerto> data = null;
		Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		try {
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(cod_aero_or.toUpperCase());
			System.out.println("   - AirToScreen - Sent data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + cod_aero_or.toUpperCase() + "'");
			
			//Read request from the client
			//TODO: Cambiar la lectura de aeropuertos.
			//data = in.readUTF();			
			System.out.println("   - AirToScreen - Received data from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data + "'");		
			socket.close();
		}catch (EOFException e) {
			System.err.println("   # ReservaAdmin - AirToScreen - TCPConnection EOF error" + e.getMessage());
		}	
		catch (IOException e1) {
			System.err.println("   # ReservaAdmin - AirToScreen - TCPConnection IO error:" + e1.getMessage());
		}
		return data;
	}
	
}
