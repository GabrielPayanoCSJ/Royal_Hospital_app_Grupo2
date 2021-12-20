// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import org.apache.commons.net.ftp.FTPClient;
import hospital.ftp.client.view.JF_FTPClient;
import hospital.ftp.model.Group;
import hospital.ftp.model.Log;
import hospital.ftp.model.User;
import hospital.languages.Language;
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

	/**
	 * 
	 */
	public ClientFTP(int language) {
		this.db = new DB();
		this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
		this.user = new User(db);
		this.group = new Group(db);
		this.log = new Log(db);
		Language.selectLanguage(language);

		this.ftpCliente = new FTPClient();
		this.jfClient = new JF_FTPClient();
		this.jfClient.setVisible(true);

		for (int i = 0; i < this.jfClient.getPanel_login().getButtons().size(); i++) {
			this.jfClient.getPanel_login().getButtons().get(i).addActionListener(
					new Ev_FTPConnect(this.ftpCliente, this.jfClient, this.user, this.group, this.log));
		}

		for (int i = 0; i < this.jfClient.getPanel_button().getButtons().size(); i++) {
			this.jfClient.getPanel_button().getButtons().get(i).addActionListener(
					new Ev_FTPButtons(this.ftpCliente, this.jfClient, this.user, this.group, this.log));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientFTP(1);
	}

}
