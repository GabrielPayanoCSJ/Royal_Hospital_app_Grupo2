/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Thread class.
 * 
 * @author prodi
 *
 */
public class ServerFTPThread implements Runnable {
	
	private Socket clientSocket;
	private ServerFTPPipeline pipeline;
	private Thread thread;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	
	/**
	 * @param pipeline 
	 * @param clientSocket 
	 * 
	 */
	public ServerFTPThread(Socket clientSocket, ServerFTPPipeline pipeline) {
		this.clientSocket = clientSocket;
		this.pipeline = pipeline;
		this.thread = new Thread(this);
		
		try {
			this.dataInput = new DataInputStream(clientSocket.getInputStream());
			this.dataOutput = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		
	}

}
