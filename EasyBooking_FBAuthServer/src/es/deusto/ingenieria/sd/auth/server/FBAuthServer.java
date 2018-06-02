package es.deusto.ingenieria.sd.auth.server;

import java.io.IOException;
import java.net.ServerSocket;

public class FBAuthServer {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: FBAuthServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - FBAuthServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new FBAuthService(tcpServerSocket.accept());
				System.out.println(" - FBAuthServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# FBAuthServer: IO error:" + e.getMessage());
		}
	}
}