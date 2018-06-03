package es.deusto.ingenieria.sd.eb.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class PagoTarjeta implements IGatewayPago {

	private String host;
	private int port;
	
	public PagoTarjeta(String arg1, int arg2)
	{
		host = arg1;
		port = arg2;
	}
	@Override
	public int efectuarPago(String correo, double cantidad) 
	{
		String data = null;
		Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int resultado=0;
		try {
			socket = new Socket(host, port);
			data = correo.toUpperCase();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(data);
			out.writeDouble(cantidad);
			System.out.println("   - PagoTarjeta - Sent data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data.toUpperCase() + "'");
			
			//Read request from the client
			data = in.readUTF();
			resultado = Integer.parseInt(data);	
			System.out.println("   - PagoTarjeta - Received data from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + data + "'");		
			socket.close();
		}catch (EOFException e) {
			System.err.println("   # ReservaService - PagoTarjeta - TCPConnection EOF error" + e.getMessage());
		}	
		catch (IOException e1) {
			System.err.println("   # ReservaService - PagoTarjeta - TCPConnection IO error:" + e1.getMessage());
		}
		return resultado;
	}
	
}
