/**
 * 
 */
package hospital.ftp.server.controller;

import java.net.Socket;

import org.apache.commons.net.SocketClient;

/**
 * @author Guillermo González de Miguel
 * @version 1.0
 */
public class FTPServerThread implements Runnable {

	private Socket socketClient;
	private Thread thread;
	private FTPServerPipeline pipe;
	
	/**
	 * 
	 */
	public FTPServerThread(FTPServerPipeline pipe, FTPServerThread thread) {
		this.pipe = pipe;
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
