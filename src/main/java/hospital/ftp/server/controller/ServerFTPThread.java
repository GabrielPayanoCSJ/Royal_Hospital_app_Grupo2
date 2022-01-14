/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import hospital.ftp.model.User;
import hospital.tools.database.DB;

/**
 * Thread class.
 * 
 * @author prodi
 *
 */
public class ServerFTPThread implements Runnable {

	private Socket clientSocket;
	private static ServerFTPPipeline pipeline;
	private Thread thread;
	private static DataInputStream dataInput;
	private DataOutputStream dataOutput;
	private static DB db;
	private static User userDB;

	/**
	 * @param pipeline
	 * @param clientSocket
	 * @param db
	 * @param userDb
	 * 
	 */
	public ServerFTPThread(Socket clientSocket, ServerFTPPipeline pipeline, DB db, User userDb) {
		this.clientSocket = clientSocket;
		this.pipeline = pipeline;
		this.db = db;
		this.userDB = userDb;
		this.thread = new Thread(this);
		this.thread.setName("Server thread");

		try {
			this.dataInput = new DataInputStream(clientSocket.getInputStream());
			this.dataOutput = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// llamada al controlador de la ventana del servidor

		checkClose(true);
	}

	public static void checkClose(boolean exit) {
		if (exit) {
			pipeline.writeLogDB(dataInput, db, userDB, exit);
		}
	}
}
