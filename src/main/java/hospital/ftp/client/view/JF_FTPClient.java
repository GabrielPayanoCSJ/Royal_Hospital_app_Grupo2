/**
 * 
 */
package hospital.ftp.client.view;

import javax.swing.JFrame;

import hospital.ftp.client.view.panels.Pa_Directory;
import hospital.ftp.client.view.panels.Pa_Login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * @author 
 */
public class JF_FTPClient extends JFrame {

	private Pa_Login panel_login;
	private Pa_Directory panel_directory;
	
	/**
	 * 
	 */
	public JF_FTPClient() {
		setSize(new Dimension(800, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel_login = new Pa_Login();
		panel_directory = new Pa_Directory();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel_login, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel.add(panel_directory, BorderLayout.NORTH);
//		getContentPane().add(panel_directory, BorderLayout.CENTER);
	}

	
	public static void main(String[] args) {		
		new JF_FTPClient().setVisible(true);
	}


}
