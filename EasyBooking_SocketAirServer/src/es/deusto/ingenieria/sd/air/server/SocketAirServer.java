package es.deusto.ingenieria.sd.air.server;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketAirServer {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: AirlineServer [PORT]");
			System.exit(1);
		}
		
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - AirlineServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new SocketAirService(tcpServerSocket.accept());
				System.out.println(" - AirlineServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# AirlineServer: IO error:" + e.getMessage());
		}
	}
}