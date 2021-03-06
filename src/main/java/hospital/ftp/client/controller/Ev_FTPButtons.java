// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

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
 * @author Guillermo Gonz�lez de Miguel
 * @editor Jorge Fern?ndez Ruiz
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
	private LocalDateTime localDate;
	private String date = "";
	private String nomUser;

	/**
	 * 
	 * @param ftpClient
	 * @param jfClient
	 * @param user
	 * @param group
	 * @param log
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
				jfClient.updateTree(ftpClient); // Update the node after Change.
				buildLog("CRT");
			}
			break;
		case 1:
			// DELETE FILES AND DIRECTORIES
			System.out.println(Language.getFtpClient_txts(1));
			buildLog("DLT", FTPUtil.manageDelete(ftpClient, jfClient.generateURL(getPathTree()), ""));
			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 2:
			// RENAME FILE OR DIRECTORY
			System.out.println(Language.getFtpClient_txts(11));
			FTPUtil.renameFile(ftpClient, jfClient.generateURL(getPathTree()), getPathTreeMinus());
			buildLog("RNM");
			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 3:
			// UPLOAD FILE
			System.out.println(Language.getFtpClient_txts(2));
			FTPUtil.uploadFile(ftpClient, jfClient.generateURL(getPathTree()));
			buildLog("UPL");
			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		case 4:
			// DOWNLOAD FILE
			System.out.println(Language.getFtpClient_txts(3));
			FTPUtil.downloadFile(ftpClient, jfClient.generateURL(getPathTree()));
			buildLog("DWL");
			jfClient.updateTree(ftpClient); // Update the node after Change.
			break;
		default:
			Tool.showConsoleError("This option doesn't exist.");
			break;
		}
	}

	/**
	 * 
	 * @author Jorge Fern?ndez Ruiz
	 * @param operation
	 */
	private void buildLog(String operation) {
		this.nomUser = jfClient.getPanel_login().getTfield_user().getText();
		System.out.println("EVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV " + nomUser);
		try {
			String desc = "";
			String log = "";
			switch (operation) {
			// created
			case "CRT":
				desc = "File [" + FTPUtil.getNameNewDir() + "] was create in: " + FTPUtil.getUrlCreated();
				getDateNow();
				break;

			// renamed
			case "RNM":
				desc = "File [" + FTPUtil.getOldName() + "] was rename for [" + FTPUtil.getNewName() + "] in: "
						+ FTPUtil.getRenamedURL();
				getDateNow();
				break;

			// uploaded
			case "UPL":
				desc = "File [" + FTPUtil.getUploadedName() + "] was upload in: " + FTPUtil.getUploadedServerURL();
				getDateNow();
				break;

			// downloaded
			case "DWL":
				desc = "File [" + FTPUtil.getDownloadedName() + "] was download in: " + FTPUtil.getDownloadedURL();
				getDateNow();
				break;
			}

			log = operation + " ? " + desc + " ? " + date;
			writeClientLog(log);
			dos.writeUTF(nomUser);
			dos.writeUTF(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Jorge Fern?ndez Ruiz
	 * @param operation
	 * @param deletedIndex
	 */
	private void buildLog(String operation, int deletedIndex) {
		this.nomUser = jfClient.getPanel_login().getTfield_user().getText();
		System.out.println("EVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV " + nomUser);

		try {
			String desc = "";
			String log = "";
			if (operation.equals("DLT")) {
				switch (deletedIndex) {
				case -1:
					desc = "Nothing was delete";
					break;
				case 0:
					desc = "File [" + FTPUtil.getLastWordURL(FTPUtil.getDelectedURL()) + "] was delete in: "
							+ FTPUtil.getDelectedURL();
					getDateNow();
					break;
				case 1:
					desc = "Folder [" + FTPUtil.getLastWordURL(FTPUtil.getDelectedURL()) + "] was delete in: "
							+ FTPUtil.getDelectedURL();
					getDateNow();
					break;
				case 2:
					desc = "Folder [" + FTPUtil.getLastWordURL(FTPUtil.getDelectedURL()) + "] was delete in: "
							+ FTPUtil.getDelectedURL();
					getDateNow();
					break;
				}

				log = operation + " ? " + desc + " ? " + date;
				writeClientLog(log);
				dos.writeUTF(nomUser);
				dos.writeUTF(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDateNow() {
		localDate = LocalDateTime.now();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		int hour = localDate.getHour();
		int minutes = localDate.getMinute();
		int seconds = localDate.getSecond();

		String Str_month;
		String Str_day;
		String Str_hour;
		String Str_minutes;
		String Str_seconds;

		if (month < 10) {
			Str_month = "0" + month;
		} else {
			Str_month = month + "";
		}

		if (day < 10) {
			Str_day = "0" + day;
		} else {
			Str_day = day + "";
		}

		if (hour < 10) {
			Str_hour = "0" + hour;
		} else {
			Str_hour = hour + "";
		}

		if (minutes < 10) {
			Str_minutes = "0" + minutes;
		} else {
			Str_minutes = minutes + "";
		}

		if (seconds < 10) {
			Str_seconds = "0" + seconds;
		} else {
			Str_seconds = seconds + "";
		}

		date = localDate.getYear() + "/" + Str_month + "/" + Str_day + " - " + Str_hour + ":" + Str_minutes + ":"
				+ Str_seconds;
	}

	/**
	 * @author Jorge Fern?ndez Ruiz
	 * @param newLog
	 */
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
		System.out.println("LA ?LTIMA PALABRA ES: " + parts[parts.length - 1]);

		// parts[parts.length-1] -> Last word of the Selected Path in the tree node
		url = path.replace(parts[parts.length - 1], "");// Deleting the last word
		url = url.substring(1);
		return url;
	}

}
