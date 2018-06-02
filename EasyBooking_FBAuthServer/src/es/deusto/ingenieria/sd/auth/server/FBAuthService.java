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

public class FBAuthService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private ArrayList<String> lista = new ArrayList <String>();
	
	public FBAuthService(Socket socket) {
		lista.add("IMANOL");
		lista.add("ANNE");
		lista.add("BEÑAT");
		lista.add("PEPITO");
		lista.add("JON");
		System.out.println("LLego1");
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# FBAuthService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("LLego1");
			System.out.println("   - FBAuthService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data= Integer.toString(this.verificar(data));
			this.out.writeUTF(data);					
			System.out.println("   - FBAuthService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FBAuthService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FBAuthService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FBAuthService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public int verificar(String msg) {
		int existe=-1;
		System.out.println("LLego2");
		for(String aux : lista)
		{
			if(aux.compareTo(msg)==0)
				existe=0;
		}
		return existe;
	}
}