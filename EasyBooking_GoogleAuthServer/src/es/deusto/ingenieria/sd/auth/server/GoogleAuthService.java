package es.deusto.ingenieria.sd.auth.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class GoogleAuthService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private ArrayList<String> lista;
	
	private static String DELIMITER = "#";
	
	public GoogleAuthService(Socket socket) {
		lista.add("IMANOL");
		lista.add("ANNE");
		lista.add("GARI");
		lista.add("BEÑAT");
		System.out.println("LLego1");
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# GoogleAuthenticationService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			int resultado;
			System.out.println("LLego1");
			System.out.println("   - GoogleAuthenticationService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			resultado= this.verificar(data);
			this.out.writeInt(resultado);					
			System.out.println("   - GoogleAuthenticationService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # GoogleAuthenticationService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # GoogleAuthenticationService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # GoogleAuthenticationService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public int verificar(String msg) {
		int existe=0;
		System.out.println("LLego2");
		for(String aux : lista)
		{
			if(aux.compareTo(msg)==0)
				existe=0;
		}
		return existe;
	}
}