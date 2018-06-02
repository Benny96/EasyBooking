package es.deusto.ingenieria.sd.pay.server;

import java.io.IOException;
import java.net.ServerSocket;

public class TarjetaServer {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: TarjetaServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - TarjetaServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new TarjetaService(tcpServerSocket.accept());
				System.out.println(" - TarjetaServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# TarjetaServer: IO error:" + e.getMessage());
		}
	}
}