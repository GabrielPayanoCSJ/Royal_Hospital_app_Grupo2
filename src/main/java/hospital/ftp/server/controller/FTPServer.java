/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystem;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.tools.Tool;
import hospital.tools.database.DB;

/**
 * Clase que contiene métodos encargado de la iniciación del servidor FTP
 * 
 * @author Guillermo González de Miguel
 * @version 1.0
 * @dateCreated 17/12/2021
 */
public class FTPServer {
	private static final String HOST = "localhost";
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
		Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
////		log.
//		log.trace("DEBUG");
//		log.info("Hello world");
//		log.debug("Hello world 2");
//		log.warn("MENSAJE DE FALLO");
		this.db = new DB();
		this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
		this.serverFactory = new FtpServerFactory();
		this.listenerFactory = new ListenerFactory();
		this.listenerFactory.setServerAddress(HOST);
		this.listenerFactory.setPort(PORT);
		this.serverFactory.addListener("default", listenerFactory.createListener());

//		System.out.println(this.listenerFactory.getPort());
//		System.out.println(this.listenerFactory.getServerAddress());
//		System.out.println(this.listenerFactory.getIdleTimeout());

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

		if (generateUserFTP(rootDir))
				startFTPSever();
		else
			Tool.showGUIinfo("No existe ningún usuario en la base de datos.", "INFORMACIÓN");
	}

	private void enableLog4j() {
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

//	private void createFTPserver() {
//		this.server = this.serverFactory.createServer();
//	}

	/**
	 * @throws IOException
	 * 
	 */
	private void startFTPSever() {
		try {
			this.server = this.serverFactory.createServer();
//			this.server.resume();

			this.server.start();

		} catch (FtpException e1) {
		} catch (Exception e2) {

			if (e2.getCause().getClass().getSimpleName().equals("IOException")) {
				String addr = this.listenerFactory.getServerAddress();
				if (this.listenerFactory.getServerAddress().equals("localhost"))
					addr += "/127.0.0.1";
				
				Tool.showGUIerror("No es posible iniciar el servidor FTP, " + addr + ":" + this.listenerFactory.getPort()
				+ " está en uso.\n\nCompruebe que no disponga de otro servidor activado.", "ERROR SERVIDOR FTP NO PUEDE INICIAR");
			}

		}
	}

	private void stopFTPServer() {
		if (!this.server.isStopped()) {
			this.server.stop();
		}
	}

	private void suspendFTPServer() {
		if (!this.server.isSuspended()) {
			this.server.suspend();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FTPServer();
	}

}
