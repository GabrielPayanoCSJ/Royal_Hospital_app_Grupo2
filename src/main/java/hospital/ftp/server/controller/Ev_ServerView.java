package hospital.ftp.server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.ftp.server.view.serverView;
import hospital.tools.database.DB;

public class Ev_ServerView implements ActionListener {
	private serverView serverView;
	private DB db;
	private User userDb;
	private Group groupDb;
	private FTPServer ftpServer;

	public Ev_ServerView(serverView serverView, DB db, User userDb, Group groupDb, FTPServer ftpServer) {
		this.serverView = serverView;
		this.db = db;
		this.userDb = userDb;
		this.groupDb = groupDb;
		this.ftpServer = ftpServer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == serverView.getpButtons().getButtons().get(0)) { // finish
			System.out.println("termina");
			ftpServer.stopFTPServer();
			serverView.getpButtons().getButtons().get(0).setEnabled(false);
			serverView.getpButtons().getButtons().get(1).setEnabled(true);
			

		} else if (e.getSource() == serverView.getpButtons().getButtons().get(1)) { // start
			System.out.println("empieza");
			ftpServer.startFTPSever();
			try {
			} catch (Exception e2) {
				System.out.println("Cancelado");
			}
		}

	}

}
