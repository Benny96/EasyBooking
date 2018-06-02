package es.deusto.ingenieria.sd.auth.server;

import java.io.IOException;
import java.net.ServerSocket;

public class FBAuthenticationServer {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: TranslationServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - FBAuthenticationServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new FBAuthenticationService(tcpServerSocket.accept());
				System.out.println(" - FBAuthenticationServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# FBAuthenticationServer: IO error:" + e.getMessage());
		}
	}
}