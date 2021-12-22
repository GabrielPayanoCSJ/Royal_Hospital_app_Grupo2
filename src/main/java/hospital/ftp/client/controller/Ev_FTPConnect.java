// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.ftp.model.Group;
import hospital.ftp.model.Log;
import hospital.ftp.model.User;
import hospital.tools.Tool;

/**
 * 
 * @author Guillermo González de Miguel
 * @version 1.0
 * @dateCreated 18/12/2021
 */
public class Ev_FTPConnect implements ActionListener {
	private Logger logClient;
	private final String HOST = "localhost";
	private final int PORT = 6000;
	private FTPClient ftpClient;
	private JF_FTPClient jfClient;
	private User user;
	private Group group;
	private Log log;
	private JTextField tfielduser;
	private JPasswordField pfieldpass;

	/**
	 * 
	 * @param ftpClient
	 * @param jfClient
	 * @param user
	 * @param group
	 * @param log
	 */
	public Ev_FTPConnect(FTPClient ftpClient, JF_FTPClient jfClient, User user, Group group, Log log) {
//		this.logClient = LoggerFactory.getLogger(this.getClass().getSimpleName());
		this.ftpClient = ftpClient;
		this.jfClient = jfClient;
		this.user = user;
		this.group = group;
		this.log = log;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.tfielduser = this.jfClient.getPanel_login().getTfield_user();
		this.pfieldpass = this.jfClient.getPanel_login().getPfield_passwd();
		JButton button = (JButton) e.getSource();

		switch (button.getName()) {
		case "0":
			connectClientFTP(button);
			break;
		case "1":
			disconnectClientFTP();
			break;
		default:
			Tool.showConsoleError("This option don't exist");
			break;
		}
	}

	/**
	 * 
	 * @param button
	 */
	private void connectClientFTP(JButton button) {
		if (!this.tfielduser.getText().isEmpty()) {

			try {
				System.out.println("Nos conectamos a: " + HOST);
				this.ftpClient.connect(HOST, PORT);
				FTPUtil.showResponse(ftpClient);
				String userLogin = this.tfielduser.getText().toLowerCase();
				String passwdLogin = "";
				for (int i = 0; i < this.pfieldpass.getPassword().length; i++) {
					passwdLogin += this.pfieldpass.getPassword()[i];
				}

				if (ftpClient.login(userLogin, passwdLogin)) {
					this.tfielduser.setEditable(false);
					this.pfieldpass.setEditable(false);
					button.setVisible(false);
					this.jfClient.getPanel_login().getButtons().get(1).setVisible(true);

					checkPrivilegesFTP(userLogin);

				} else {
					Tool.showGUIerror("El usuario o contraseña son incorrecto.", "ACCESO INCORRECTO");
					ftpClient.disconnect();
				}

			} catch (SocketException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			Tool.showGUIerror("No se ha introducido ningún usuario", "CAMPOS DE LOGIN VACÍOS");
		}

			jfClient.scannerFTP(ftpClient);
	}

	/**
	 * @param userLogin
	 */
	private void checkPrivilegesFTP(String userLogin) {
		if (!group.checkWritePriv(user.searchUserGroup(userLogin))) {
			this.jfClient.getPanel_button().getButtons().get(3).setVisible(false);
		} else {
			this.jfClient.getPanel_button().getButtons().get(3).setEnabled(true);
		}

		if (!group.checkReadPriv(user.searchUserGroup(userLogin))) {
			this.jfClient.getPanel_button().getButtons().get(4).setVisible(false);
		} else {
			this.jfClient.getPanel_button().getButtons().get(4).setEnabled(true);
		}

		if (!group.checkExecutePriv(user.searchUserGroup(userLogin))) {
			this.jfClient.getPanel_button().getButtons().get(0).setVisible(false);
			this.jfClient.getPanel_button().getButtons().get(1).setVisible(false);
			this.jfClient.getPanel_button().getButtons().get(2).setVisible(false);
		} else {
			this.jfClient.getPanel_button().getButtons().get(0).setEnabled(true);
			this.jfClient.getPanel_button().getButtons().get(1).setEnabled(true);
			this.jfClient.getPanel_button().getButtons().get(2).setEnabled(true);
		}
	}

	/**
	 * 
	 */
	private void disconnectClientFTP() {
		if (this.ftpClient.isConnected()) {
			try {
				this.ftpClient.logout();
				this.ftpClient.disconnect();
				System.exit(1);
			} catch (IOException e1) {
				Tool.showConsoleError(e1.getMessage());
			}
		}
	}
}
