/**
 * 
 */
package hospital.ftp.client.view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import hospital.ftp.client.view.panels.Pa_Buttons;
import hospital.ftp.client.view.panels.Pa_Directory;
import hospital.ftp.client.view.panels.Pa_Log;
import hospital.ftp.client.view.panels.Pa_Login;
import hospital.languages.Lang_enUK;
import hospital.languages.Language;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.GridLayout;

/**
 * @author
 */
public class JF_FTPClient extends JFrame {

	private Pa_Login panel_login;
	private Pa_Directory panel_directory;
	private Pa_Log panel_log;
	private Pa_Buttons panel_button;

	/**
	 * 
	 */
	public JF_FTPClient() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JPanel btnContainer = new JPanel();
		this.setSize(new Dimension(800, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.panel_login = new Pa_Login();
		this.panel_directory = new Pa_Directory();
		this.panel_log = new Pa_Log();
		this.panel_button = new Pa_Buttons(Language.getFtpClient_txts(0), Language.getFtpClient_txts(1),
				Language.getFtpClient_txts(2), Language.getFtpClient_txts(3), Language.getFtpClient_txts(4));
		centerPanel.setLayout(new GridLayout(2, 1, 0, 0));
		centerPanel.add(panel_log, BorderLayout.NORTH);
		centerPanel.add(panel_directory, BorderLayout.SOUTH);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		btnContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnContainer.add(panel_button);
		this.getContentPane().add(btnContainer, BorderLayout.SOUTH);
	}

	/**
	 * @return the panel_login
	 */
	public Pa_Login getPanel_login() {
		return panel_login;
	}

	/**
	 * @return the panel_directory
	 */
	public Pa_Directory getPanel_directory() {
		return panel_directory;
	}

	/**
	 * @return the panel_log
	 */
	public Pa_Log getPanel_log() {
		return panel_log;
	}

	/**
	 * @return the panel_button
	 */
	public Pa_Buttons getPanel_button() {
		return panel_button;
	}
	
	public static void main(String[] args) {
//		Language.selectLanguage(1);
		JF_FTPClient jframe = new JF_FTPClient();
		jframe.setVisible(true);
	}

}
