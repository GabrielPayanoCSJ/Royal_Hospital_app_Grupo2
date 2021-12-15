/**
 * 
 */

// PACKAGE
package hospital.ftp.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import hospital.tools.Tool;

/**
 * @author Guilermo González de Miguel
 * @version 1.0
 */
public class FTPServer {
	
	private ServerSocket socketServer;
	private Socket socketClient;
	private final int PORT = 6000;
	private Thread hilo;
	private FTPServerPipeline pipe;

	/**
	 * 
	 */
	public FTPServer() {
		try {
			this.socketServer = new ServerSocket(PORT); // START SERVER SOCKET
			
			
			while(true) {
				this.socketClient = new Socket(); // CLIENT SOCKET
				this.socketClient = this.socketServer.accept(); // ACCEPT REQUEST CLIENT SOCKET.
				
//				this.hilo = new Thread(new FTPServer());
			}
			

			
		} catch (IOException e) {
		}
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FTPServer();
	}

}
