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
import hospital.tools.Tool;
import hospital.tools.database.DB;

/**
 * @author Guillermo González de Miguel
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
	public ClientFTP(DB db, User userdb, Group groupdb) {
		this.db = db;
		this.user = userdb;
		this.group = groupdb;
		this.ftpCliente = new FTPClient();
		this.jfClient = new JF_FTPClient();

		try {
			this.socket = new Socket("localhost", 7000); // CONNECTION WITH SOCKET CLIENT LOG

			this.jfClient.setVisible(true);
			
			for (int i = 0; i < this.jfClient.getPanel_login().getButtons().size(); i++) {
				this.jfClient.getPanel_login().getButtons().get(i).addActionListener(
						new Ev_FTPConnect(this.ftpCliente, this.jfClient, this.user, this.group));
			}
			
			for (int i = 0; i < this.jfClient.getPanel_button().getButtons().size(); i++) {
				this.jfClient.getPanel_button().getButtons().get(i).addActionListener(
						new Ev_FTPButtons(this.ftpCliente, this.jfClient, this.user, this.group, this.log, this.socket));
			}
		} catch (IOException e) {
			Tool.showGUIerror("Problema con el socket del LOG en la clase FTPClient", "ERROR - Socket Log");
		}
	
	}

}
