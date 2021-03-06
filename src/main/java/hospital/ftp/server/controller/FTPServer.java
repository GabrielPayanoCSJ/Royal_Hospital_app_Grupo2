/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
import hospital.ftp.server.view.serverView;
import hospital.tools.Tool;
import hospital.tools.database.DB;

/**
 * Clase que contiene m?todos encargado de la iniciaci?n del servidor FTP
 * 
 * @author Guillermo Gonz?lez de Miguel
 * @version 1.0
 * @dateCreated 17/12/2021
 */
public class FTPServer {
	private static final String HOST = "192.168.1.98";
	private static final int PORT = 6000;
	private FtpServerFactory serverFactory;
	private ListenerFactory listenerFactory;
	private FtpServer server;
	private DB db;
	private User userdb;
	private Group groupdb;

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private static final int PORT_SERVERSOCKET = 7000;
	private Thread thread;
	private serverView serverView;

	/**
	 * @param serverView
	 * 
	 */
	public FTPServer(DB db, User userdb, Group groupdb, serverView serverView) {
		this.db = db;
		this.userdb = userdb;
		this.groupdb = groupdb;
		this.serverView = serverView;

		this.serverFactory = new FtpServerFactory();
		this.listenerFactory = new ListenerFactory();
		this.listenerFactory.setServerAddress(HOST);
		this.listenerFactory.setPort(PORT);
		this.serverFactory.addListener("default", listenerFactory.createListener());

//		String rootDir = "";
//		JFileChooser f = new JFileChooser();
//		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE EST?N LOS FICHEROS");
//		int returnVal = f.showDialog(f, "Seleccionar");
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			File file = f.getSelectedFile();
//			rootDir = file.getAbsolutePath();
////			System.out.println(rootDir);
//		}
//
//		if (rootDir.equals("")) {
//			Tool.showGUIinfo("Debe seleccionar un directorio ra?z", "Informaci?n");
//		} else {
//			if (generateUserFTP(rootDir)) {
//				startFTPSever();
//				startThread();
//			} else {
//				Tool.showGUIinfo("No existe ning?n usuario en la base de datos.", "INFORMACI?N");
//			}
//		}
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

	/**
	 * @throws IOException
	 * 
	 */
	public void startFTPSever() {
		try {

			String rootDir = "";
			JFileChooser f = new JFileChooser();
			f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE EST?N LOS FICHEROS");
			int returnVal = f.showDialog(f, "Seleccionar");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = f.getSelectedFile();
				rootDir = file.getAbsolutePath();
//				System.out.println(rootDir);
			}

			if (rootDir.equals("")) {
				Tool.showGUIinfo("Debe seleccionar un directorio ra?z", "Informaci?n");
			} else {
				if (generateUserFTP(rootDir)) {
					// startFTPSever();

					this.server = this.serverFactory.createServer();
					// this.server.resume();

					this.server.start();
					this.serverView.getpButtons().getButtons().get(0).setEnabled(true);
					this.serverView.getpButtons().getButtons().get(1).setEnabled(false);

					startThread();
				} else {
					Tool.showGUIinfo("No existe ning?n usuario en la base de datos.", "INFORMACI?N");
				}
			}

		} catch (FtpException e1) {
		} catch (Exception e2) {

			if (e2.getCause().getClass().getSimpleName().equals("IOException")) {
				String addr = this.listenerFactory.getServerAddress();
				if (this.listenerFactory.getServerAddress().equals("localhost"))
					addr += "/127.0.0.1";

				Tool.showGUIerror(
						"No es posible iniciar el servidor FTP, " + addr + ":" + this.listenerFactory.getPort()
								+ " est? en uso.\n\nCompruebe que no disponga de otro servidor activado.",
						"ERROR SERVIDOR FTP NO PUEDE INICIAR");
			}

		}
	}

	private void startThread() {

		ServerFTPPipeline pipeline = new ServerFTPPipeline();

		try {
			this.serverSocket = new ServerSocket(PORT_SERVERSOCKET); // el puerto ya est? siendo usado por el socket del
																		// mail (FTP)
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		AcceptThread acceptThread = new AcceptThread(clientSocket, serverSocket, db, thread, pipeline, userdb);
		acceptThread.t.start();
	}

	private void startFTPServer() {
		if (!this.server.isStopped()) {
			try {
				this.server.start();
			} catch (FtpException e) {
				Tool.showGUIerror("El servidor ya est? iniciado.", "ERROR SERVIDOR FTP YA ESTA INICIADO");
			}
		}
	}

	public void stopFTPServer() {
		if (!this.server.isStopped()) {
			this.server.stop();
			System.out.println("Server closed");
		}
	}
}
