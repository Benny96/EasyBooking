package es.deusto.ingenieria.sd.pay.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TarjetaService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	private static String DELIMITER = "#";
	
	private ArrayList<String> ejemplo;
	
	public TarjetaService(Socket socket) {
		ejemplo.add("IMANOL");
		ejemplo.add("GARI");
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# TarjetaService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();			
			System.out.println("   - TarjetaService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			int resultado = this.translate(data);
			this.out.writeInt(resultado);					
			System.out.println("   - TarjetaService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # TarjetaService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # TarjetaService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # TarjetaService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public int translate(String msg) {
		
		for(String aux: ejemplo)
		{
			if(aux.compareTo(msg)==0)
				return 1;
		}
		return 0;
	}
}