package hospital.ftp.server.controller;

import javax.swing.JButton;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.ftp.server.view.serverView;
import hospital.tools.database.DB;

public class ViewController {
	private serverView serverView;
	private DB db;
	private User userDb;
	private Group groupDb;
	private FTPServer ftpServer;

	public ViewController(DB db, User userdb, Group groupdb) {
		this.db = db;
		this.userDb = userdb;
		this.groupDb = groupdb;
		
		
		serverView = new serverView();
		 ftpServer = new FTPServer(db, userdb, groupdb, serverView);
		serverView.setVisible(true);

		addEvents();
	}

	private void addEvents() {
		for (JButton button : serverView.getpButtons().getButtons()) {
			button.addActionListener(new Ev_ServerView(serverView, db, userDb, groupDb, ftpServer));
		}

	}

}
