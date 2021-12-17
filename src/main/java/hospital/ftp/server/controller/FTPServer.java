/**
 * 
 */
package hospital.ftp.server.controller;

import java.util.ArrayList;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
/**
 * @author prodi
 *
 */
public class FTPServer {
	private FtpServer ftpserver;
	private final int PORT = 6000;
	private String username = "";
	private String password = "";
	private String root = "";
	private FtpServerFactory serverFactory;
	private ListenerFactory listenerFactory;
	private BaseUser user;
	
	/**
	 * 
	 */
	public FTPServer() {
		this.serverFactory = new FtpServerFactory();
		this.listenerFactory = new ListenerFactory();
		this.listenerFactory.setPort(PORT);
		
		serverFactory.addListener("default", listenerFactory.createListener());
		this.user = new BaseUser();
		this.user.setName("guillermo");
		this.user.setPassword("123456");
		this.user.setHomeDirectory("D:\\Users\\prodi\\Desktop");
		ArrayList<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new WritePermission());
		user.setAuthorities(authorities);
		
		try {
			serverFactory.getUserManager().save(user);
			
			this.user = new BaseUser();
			this.user.setName("paco");
			this.user.setPassword("123");
			this.user.setHomeDirectory("D:\\Users\\prodi");
			ArrayList<Authority> authorities2 = new ArrayList<Authority>();
			authorities2.add(new WritePermission());
			user.setAuthorities(authorities2);
			serverFactory.getUserManager().save(user);

			
			
			
			FtpServer server = serverFactory.createServer();
			server.start();
		} catch (FtpException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FTPServer();
	}

}
