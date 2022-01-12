// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;

import org.apache.commons.net.ftp.FTPClient;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.ftp.model.Group;
import hospital.ftp.model.Log;
import hospital.ftp.model.User;
import hospital.languages.Language;
import hospital.tools.Tool;

/**
 * 
 * @author Guillermo Gonzï¿½lez de Miguel
 * @version 1.0
 * @dateCreated 18/12/2021
 */
public class Ev_FTPButtons implements ActionListener {

	private FTPClient ftpClient;
	private JF_FTPClient jfClient;
	private User user;
	private Group group;
	private Log log;
	private Socket socket = null;
	private DataOutputStream dos = null;

	/**
	 * 
	 * @param ftpClient
	 * @param jfClient
	 * @param user
	 * @param group
	 * @param log
	 * @param socket
	 */
	public Ev_FTPButtons(FTPClient ftpClient, JF_FTPClient jfClient, User user, Group group, Log log, Socket socket) {
		this.ftpClient = ftpClient;
		this.jfClient = jfClient;
		this.user = user;
		this.group = group;
		this.log = log;
		this.socket = socket;

		try {
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		switch (Integer.parseInt(button.getName())) {
		case 0:
			// CREATE DIRECTORY
			System.out.println(Language.getFtpClient_txts(0));
			System.out.println("funciona el crear");
			if (FTPUtil.createDirectory(ftpClient, jfClient.generateURL(getPathTree()))) {
				System.out.println("entra al if");
				jfClient.updateTree(ftpClient); // Update the node after Change.
				buildLog("CRT");
			}
			break;
			
		case 1:
			// DELETE FILES AND DIRECTORIES
			System.out.println(Language.getFtpClient_txts(1));

			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 2:
			// RENAME FILE OR DIRECTORY
			System.out.println(Language.getFtpClient_txts(11));
			FTPUtil.renameFile(ftpClient, jfClient.generateURL(getPathTree()), getPathTreeMinus());

			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 3:
			// UPLOAD FILE
			System.out.println(Language.getFtpClient_txts(2));
			FTPUtil.uploadFile(ftpClient, jfClient.generateURL(getPathTree()));

			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 4:
			// DOWNLOAD FILE
			System.out.println(Language.getFtpClient_txts(3));
			FTPUtil.downloadFile(ftpClient, jfClient.generateURL(getPathTree()));

			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		default:
			Tool.showConsoleError("This option doesn't exist.");
			break;
		}
	}

	/**
	 * 
	 * @param operation
	 * @author Jorge Fernández Ruiz
	 */
	private void buildLog(String operation) {
		try {
			String desc;
			switch (operation) {
			// created
			case "CRT":
				desc = "File '" + FTPUtil.getNameNewDir() + "' was created in: " + FTPUtil.getUrlCreated();
				String log = operation + " ¬ " + desc;
				dos.writeUTF(log);
				writeClientLog(log);
				break;

			// deleted
			case "DLT":
				desc = "File '" + getterDelNombreBorrado;
				break;

			// renamed
			case "RNM":
				break;

			// downloaded
			case "DWL":
				break;

			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeClientLog(String newLog) {
		String oldLog = jfClient.getPanel_log().getTxA_Log().getText();
		jfClient.getPanel_log().getTxA_Log().setText(oldLog + "\n " + newLog);
	}

	private String getPathTree() {
		return jfClient.getPanel_directory().getTree().getSelectionPath().toString();
	}

	// RETURNS THE URL FORMATED WITHOUT THE LAST FOLDER.
	/**
	 * @author Gabriel Payano
	 * @return String Type , return the URL Formated to without the last
	 *         file/folder.
	 */
	private String getPathTreeMinus() {

		String path = jfClient.getPanel_directory().getTree().getSelectionPath().toString();
		String url = "";
		path = path.replace("[", "");
		path = path.replace("]", "");
		path = path.replace(",", "/");
		path = path.replace(" ", "");

		String[] parts = path.split("/");
		System.out.println("Selected PAth es: " + path);
		System.out.println("LA ÚLTIMA PALABRA ES: " + parts[parts.length - 1]);

		// parts[parts.length-1] -> Last word of the Selected Path in the tree node
		url = path.replace(parts[parts.length - 1], "");// Deleting the last word
		url = url.substring(1);
		return url;
	}

}
