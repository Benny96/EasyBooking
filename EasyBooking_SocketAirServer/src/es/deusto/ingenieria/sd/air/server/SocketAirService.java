package es.deusto.ingenieria.sd.air.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import es.deusto.ingenieria.sd.air.server.Aeropuerto;

public class SocketAirService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	private static String DELIMITER = "#";
	
	public SocketAirService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# AirlineService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			ArrayList <Aeropuerto> aeropuertos = new ArrayList <Aeropuerto>();
			Aeropuerto a_1= new Aeropuerto("aaaaaa", "PP");
			Aeropuerto a_2= new Aeropuerto("bbbbbb", "PPA");
			Aeropuerto a_3= new Aeropuerto("cccccc", "PP");
			aeropuertos.add(a_1);
			aeropuertos.add(a_2);
			aeropuertos.add(a_3);
			/** TOOD: ENVIAR ARRAYLIST DE AEROPUERTOS**/
			String data = this.in.readUTF();			
			System.out.println("   - AirlineService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.translate(data);
			this.out.writeUTF(data);					
			System.out.println("   - AirlineService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # AirlineService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # AirlineService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # AirlineService - TCPConnection IO error:" + e.getMessage());
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
				System.err.println("   # AirlineService - Translation API error:" + e.getMessage());					
			
				translation = null;
			}
		}
		
		return (translation == null) ? "ERR" : "OK" + DELIMITER + translation;
	}
}