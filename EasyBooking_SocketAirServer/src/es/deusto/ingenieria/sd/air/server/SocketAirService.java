package es.deusto.ingenieria.sd.air.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketAirService extends Thread {
	
	//private ObjectOutputStream out;
	//private ObjectInputStream in;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private ArrayList<AeropuertoSocketDTO> lista = new ArrayList <AeropuertoSocketDTO>();
	
	public SocketAirService(Socket socket) {
		lista.add(new AeropuertoSocketDTO("AERO4", "dddddd"));
		lista.add(new AeropuertoSocketDTO("AERO5", "eeeeee"));
		System.out.println("LLego1");
		try {
			this.tcpSocket = socket;
			//this.out = new ObjectOutputStream(socket.getOutputStream());
		    //this.in = new ObjectInputStream(socket.getInputStream());
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# SocketAirService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			System.out.println("LLego1");
			System.out.println("   - SocketAirService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort());	
			out.writeInt(lista.size());
			for (int i = 0; i < lista.size(); i++)
			{
				out.writeUTF(lista.get(i).getCodigo());
				out.writeUTF(lista.get(i).getNombre());
			}
			//this.out.writeObject(recopilar());					
			System.out.println("   - SocketAirService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort());
		} catch (EOFException e) {
			System.err.println("   # SocketAirService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # SocketAirService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # SocketAirService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	/*public ArrayList <AeropuertoSocketDTO> recopilar() {
		return lista;
	}*/
}