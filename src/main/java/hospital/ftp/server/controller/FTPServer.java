/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.tools.Tool;
import hospital.tools.database.DB;


/**
 * @author prodi
 *
 */
public class FTPServer {
	private static final int PORT = 6000;
	private FtpServerFactory serverFactory;
	private ListenerFactory listenerFactory;
	private FtpServer server;
	private DB db;
	private User userdb;
	private Group groupdb;

	/**
	 * 
	 */
	public FTPServer() {
		this.db = new DB();
		this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
		this.serverFactory = new FtpServerFactory();
		this.listenerFactory = new ListenerFactory();
		this.listenerFactory.setPort(PORT);
		this.serverFactory.addListener("default", listenerFactory.createListener());

		String rootDir = "";
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE ESTÁN LOS FICHEROS");
		int returnVal = f.showDialog(f, "Seleccionar");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			rootDir = file.getAbsolutePath();
			System.out.println(rootDir);
		}

		if (rootDir.equals("")) {
			Tool.showGUIinfo("Debe seleccionar un directorio raíz", "Información");
			System.exit(1);
		}
//		System.out.println();

		if(generateUserFTP(rootDir))
			startFTPSever();
		else
			Tool.showGUIinfo("No existe ningún usuario en la base de datos.", "INFORMACIÓN");
	}
	

	/**
	 * 
	 * @param rootDir
	 */
	public boolean generateUserFTP(String rootDir) {
		this.userdb = new User(db);
		this.groupdb = new Group(db);
		boolean existUser = false;
		ArrayList<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new WritePermission());

		BaseUser user;
		for (int i = 0; i < this.userdb.getUsers().size(); i++) {
			int userGroupID = userdb.getUsers().get(i).getGroupID();
			if (this.userdb.getUsers().get(i).isEnable() && groupdb.isEnable(userGroupID)) {
				user = new BaseUser();
				user.setName(userdb.getUsers().get(i).getUsername());
				user.setPassword(userdb.getUsers().get(i).getPassword());

				String homeDir = createUserDirectory(rootDir, i, userGroupID);

				user.setHomeDirectory(homeDir);
				user.setEnabled(userdb.getUsers().get(i).isEnable());
				user.setAuthorities(authorities);
				try {
					this.serverFactory.getUserManager().save(user);
				} catch (FtpException e) {
				}
				existUser = true;
			}

		}

		return existUser;
	}

	/**
	 * 
	 * 
	 * @param rootDir
	 * @param i
	 * @return
	 */
	private String createUserDirectory(String rootDir, int i, int userGroupID) {
		String homeDir = rootDir;
		File fi = null;
		

		if (userGroupID == groupdb.searchGroupID("pacientes")) {
			homeDir = rootDir + File.separator + groupdb.searchGroupname(userdb.getUsers().get(i).getGroupID())
					+ File.separator + this.userdb.getUsers().get(i).getUsername();
			fi = new File(homeDir);
		} else if (userGroupID == groupdb.searchGroupID("medicos")) {
			fi = new File(rootDir + File.separator + groupdb.searchGroupname(userdb.getUsers().get(i).getGroupID())
					+ File.separator + userdb.getUsers().get(i).getUsername());
		}

		if (!fi.exists())
			fi.mkdirs();
		return homeDir;
	}

	/**
	 * 
	 */
	private void startFTPSever() {
		try {
			this.server = this.serverFactory.createServer();
			this.server.start();
		} catch (FtpException e) {
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FTPServer();
	}

}
