/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

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
	private ServerFTPPipeline pipeline;
	private Thread thread;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	private DB db;
	private String prevLog = "";

	/**
	 * @param pipeline
	 * @param clientSocket
	 * @param db
	 * 
	 */
	public ServerFTPThread(Socket clientSocket, ServerFTPPipeline pipeline, DB db) {
		this.clientSocket = clientSocket;
		this.pipeline = pipeline;
		this.db = db;
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
		try {
			// llamada al controlador de la ventana del servidor

			// si se mete una vez va a imprimir infinitamente ese log hasta que se inserte
			// otro
			while (pipeline.isLife()) {
				String log = dataInput.readUTF().toString();
				if (log.equals(prevLog)) {
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println("Server thread wait");
						e.printStackTrace();
					}
				} else {
					if (log.split("¬")[0].equals("CRT")) {
						insertDBLog(log.split("¬")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split("¬")[0].equals("DLT")) {
						insertDBLog(log.split("¬")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split("¬")[0].equals("RNM")) {
						insertDBLog(log.split("¬")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split("¬")[0].equals("DWL")) {
						insertDBLog(log.split("¬")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split("¬")[0].equals("UPL")) {
						insertDBLog(log.split("¬")[0], log.split("¬")[1], log.split("¬")[2]);
					}
					prevLog = log;
					notifyAll();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void insertDBLog(String operation, String description, String date) {
		User user = new User(db);
		ArrayList<Object> fields = new ArrayList<>(
				Arrays.asList(user.getId(), user.getGroupID(), operation, description, date));
		db.setPreStatement("INSERT INTO gr2_info_log VALUES (NULL, ?, ?, ?, ?, ?);", fields);

	}

}
