// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author Guillermo González de Miguel
 * @version 1.0
 * @dateCreated 18/12/2021
 */
public class Ev_FTPButtons implements ActionListener {

	private FTPClient ftpClient;
	private JF_FTPClient jfClient;
	private User user;
	private Group group;
	private Log log;

	/**
	 * 
	 * @param ftpClient
	 * @param jfClient
	 * @param user
	 * @param group
	 * @param log
	 */
	public Ev_FTPButtons(FTPClient ftpClient, JF_FTPClient jfClient, User user, Group group, Log log) {
		this.ftpClient = ftpClient;
		this.jfClient = jfClient;
		this.user = user;
		this.group = group;
		this.log = log;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		switch (Integer.parseInt(button.getName())) {
		case 0:
			// CREATE DIRECTORY
			System.out.println(Language.getFtpClient_txts(0));
			FTPUtil.createDirectory(ftpClient, "");
			break;
		case 1:
			// DELETE FILES AND DIRECTORIES
			System.out.println(Language.getFtpClient_txts(1));

			break;
		case 2:
			// RENAME FILE OR DIRECTORY
			System.out.println(Language.getFtpClient_txts(11));
			FTPUtil.renameFile(ftpClient, "");
			break;
		case 3:
			// UPLOAD FILE
			System.out.println(Language.getFtpClient_txts(2));
			FTPUtil.uploadFile(ftpClient, null);
			break;
		case 4:
			// DOWNLOAD FILE
			System.out.println(Language.getFtpClient_txts(3));
			FTPUtil.downloadFile(ftpClient, null);
			break;
		default:
			Tool.showConsoleError("This option doesn't exist.");
			break;
		}
	}

}
