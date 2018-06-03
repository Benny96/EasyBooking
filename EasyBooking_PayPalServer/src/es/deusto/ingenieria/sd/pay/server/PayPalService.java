package es.deusto.ingenieria.sd.pay.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class PayPalService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	
	private ArrayList<CuentaPayPal> lista = new ArrayList <CuentaPayPal>();
	
	public PayPalService(Socket socket) {
		lista.add(new CuentaPayPal("IMANOL", 100.0));
		lista.add(new CuentaPayPal("GARI", 150.0));
		lista.add(new CuentaPayPal("ANNE", 250.0));
		lista.add(new CuentaPayPal("BEÑAT", 200.0));
		lista.add(new CuentaPayPal("PEPITO", 125.0));
		lista.add(new CuentaPayPal("JON", 175.0));
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# PayPalService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			double cantidad = this.in.readDouble();
			System.out.println("LLego1");
			System.out.println("   - PayPalService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data= Integer.toString(verificar(data, cantidad));
			this.out.writeUTF(data);					
			System.out.println("   - PayPalService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # PayPalService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # PayPalService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # PayPalService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public int verificar(String msg, double cantidad) {
		int existe=-1;
		System.out.println("LLego2");
		for(int i = 0; i < lista.size(); i++)
		{
			if(lista.get(i).getEmail().compareTo(msg)==0 && lista.get(i).getCant()>=cantidad)
			{
				lista.get(i).setCant(lista.get(i).getCant()-cantidad);
				existe=0;
			}			
		}
		return existe;
	}
}