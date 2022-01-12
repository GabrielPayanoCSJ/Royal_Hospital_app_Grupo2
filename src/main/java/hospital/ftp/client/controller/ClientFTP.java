// PACKAGE
package hospital.ftp.client.controller;

import java.io.IOException;
import java.net.Socket;

// IMPORTS
import org.apache.commons.net.ftp.FTPClient;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.ftp.model.Group;
import hospital.ftp.model.Log;
import hospital.ftp.model.User;
import hospital.languages.Language;
import hospital.tools.database.DB;

/**
 * @author Guillermo Gonz�lez de Miguel
 * @version 1.0
 * @dateCreated 18/12/2021
 */
public class ClientFTP {

	private JF_FTPClient jfClient;
	private FTPClient ftpCliente;
	private DB db;
	private User user;
	private Group group;
	private Log log;
	private Socket socket = null;

	/**
	 * 
	 */
	public ClientFTP() {
		this.db = new DB();
		this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
		this.user = new User(db);
		this.group = new Group(db);
		this.log = new Log(db);
		this.ftpCliente = new FTPClient();
		this.jfClient = new JF_FTPClient();
		this.jfClient.setVisible(true);
		
		try {
			this.socket = new Socket("localhost", 6000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.jfClient.getPanel_login().getButtons().size(); i++) {
			this.jfClient.getPanel_login().getButtons().get(i).addActionListener(
					new Ev_FTPConnect(this.ftpCliente, this.jfClient, this.user, this.group, this.log));
		}
		
		System.out.println("Justo antes de a�adir los eventos");
		for (int i = 0; i < this.jfClient.getPanel_button().getButtons().size(); i++) {
			this.jfClient.getPanel_button().getButtons().get(i).addActionListener(
					new Ev_FTPButtons(this.ftpCliente, this.jfClient, this.user, this.group, this.log, this.socket));
		}
	}

}
