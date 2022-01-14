package hospital.ftp.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import hospital.ftp.model.User;
import hospital.tools.database.DB;

public class AcceptThread implements Runnable {
	public Thread t;
	private boolean isLife = true;
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private DB db;
	private Thread thread;
	private ServerFTPPipeline pipeLine;
	private User userDb;

	public AcceptThread(Socket clientSocket, ServerSocket serverSocket, DB db, Thread thread, ServerFTPPipeline pipeline, User userdb) {
		this.clientSocket = clientSocket;
		this.serverSocket = serverSocket;
		this.db = db;
		this.thread = thread;
		this.pipeLine = pipeline;
		this.userDb = userdb;
		
		t = new Thread(this);
		t.setName("accept thread");
	}

	@Override
	public void run() {
		try {
			while (isLife) {
				this.clientSocket = new Socket();
				this.clientSocket = this.serverSocket.accept();
				System.out.println("cliente aceptado");
				this.thread = new Thread(new ServerFTPThread(this.clientSocket, this.pipeLine, this.db, this.userDb));
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}

	}

	/**
	 * @return the isLife
	 */
	public boolean isLife() {
		return isLife;
	}

	/**
	 * @param isLife the isLife to set
	 */
	public void setLife(boolean isLife) {
		this.isLife = isLife;
	}

}
