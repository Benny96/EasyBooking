package es.deusto.ingenieria.sd.eb.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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
	public ArrayList <Aeropuerto> buscarVuelos() 
	{
		ArrayList<Aeropuerto> data = new ArrayList<Aeropuerto>();
		Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		Aeropuerto aux = null;
		int numdatos = -1;
		try {
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("   - AirToScreen - Sent request to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
			
			numdatos = in.readInt();
			for (int i = 0; i < numdatos; i++)
			{
				aux = new Aeropuerto(in.readUTF(),in.readUTF());
				data.add(aux);
			}
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
