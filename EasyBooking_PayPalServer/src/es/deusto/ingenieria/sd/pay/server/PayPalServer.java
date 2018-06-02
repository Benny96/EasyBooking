package es.deusto.ingenieria.sd.pay.server;

import java.io.IOException;
import java.net.ServerSocket;

public class PayPalServer {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: PayPalServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - PayPalServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new TarjetaService(tcpServerSocket.accept());
				System.out.println(" - PayPalServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# PayPalServer: IO error:" + e.getMessage());
		}
	}
}