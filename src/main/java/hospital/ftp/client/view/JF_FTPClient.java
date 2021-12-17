/**
 * 
 */
package hospital.ftp.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import hospital.ftp.client.view.panels.Pa_Buttons;
import hospital.ftp.client.view.panels.Pa_Directory;
import hospital.ftp.client.view.panels.Pa_Log;
import hospital.ftp.client.view.panels.Pa_Login;
import hospital.languages.Language;

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
		super(Language.getFtpClient_txts(7));
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JPanel btnContainer = new JPanel();
		this.setSize(new Dimension(800, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.panel_login = new Pa_Login();
		this.panel_directory = new Pa_Directory();
		this.panel_log = new Pa_Log();
		this.panel_button = new Pa_Buttons(Language.getFtpClient_txts(0), Language.getFtpClient_txts(1), Language.getFtpClient_txts(11),
				Language.getFtpClient_txts(2), Language.getFtpClient_txts(3), Language.getFtpClient_txts(4));
		
		this.getContentPane().add(panel_login, BorderLayout.NORTH);
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
		Language.selectLanguage(2);
		JF_FTPClient jframe = new JF_FTPClient();
		jframe.setVisible(true);
	}
	
	//METHODS TO MANAGE JTREE
	/**
	 * 
	 * @param url an String in URL format by the string provide
	 * @return
	 */
	public static String generateURL(String url) {
		// Modify the string to be like an URL
		url = url.replace("\\", "Hospital");
		url = url.replace("|", "\\");
		url = url.replace(" ", "");
		return url;
	}
	
	
	//METHOD TO CREATE A NEW NODE (FOLDER) INTO THE NODE TREE
	/**
	 * 
	 * @param folderName a String type that have the name of the new node to create in the parent node selected by the user
	 */
	public void createNodeDirectory(String folderName) {
		TreePath path = this.panel_directory.getTree().getSelectionPath();
		if(path == null) {
			 JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna Carpeta");
		}else {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(folderName);
			selectedNode.add(newNode);
			this.panel_directory.getTree().updateUI();
		}
			
	}
	
	//METHOD TO ASK FOR THE NEW NODE NAME TO UPDATE INTO THE JTREE
	/**
	 * 
	 */
	public void askForNewFolderName() {
		String newFileName = JOptionPane.showInputDialog("Introduce el Nombre de la carpeta:");
		JOptionPane.showMessageDialog(null, "Nombre de la carpeta:  " + newFileName);
		createNodeDirectory(newFileName);
	}


}
