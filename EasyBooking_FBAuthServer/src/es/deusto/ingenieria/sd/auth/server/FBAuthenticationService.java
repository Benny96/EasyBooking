package es.deusto.ingenieria.sd.auth.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class FBAuthenticationService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private static String DELIMITER = "#";
	
	public FBAuthenticationService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# FBAuthenticationService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();			
			System.out.println("   - FBAuthenticationService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.translate(data);
			this.out.writeUTF(data);					
			System.out.println("   - FBAuthenticationService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FBAuthenticationService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FBAuthenticationService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FBAuthenticationService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String translate(String msg) {
		String translation = null;
		
		if (msg != null && !msg.trim().isEmpty()) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(msg, DELIMITER);
		
				Language langFrom = Language.valueOf(tokenizer.nextToken());
				Language langTo = Language.valueOf(tokenizer.nextToken());
				String text = tokenizer.nextToken();
				
				if (langFrom != null && langTo != null && text != null && !text.trim().isEmpty()) {				
					Translate.setClientId("SSDD_Translator");
					Translate.setClientSecret("Software_Design_2015-16");			   
					translation = Translate.execute(text, langFrom, langTo);
				}
			} catch (Exception e) {
				System.err.println("   # FBAuthenticationService - Translation API error:" + e.getMessage());					
			
				translation = null;
			}
		}
		
		return (translation == null) ? "ERR" : "OK" + DELIMITER + translation;
	}
}