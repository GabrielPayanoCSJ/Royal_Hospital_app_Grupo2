/**
 * 
 */
package hospital.ftp.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

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
	private String URLnodeFormated;

	/**
	 * 
	 */
	public JF_FTPClient() {
		super(Language.getFtpClient_txts(7));
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel btnContainer = new JPanel();
		this.setSize(new Dimension(900, 800));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.panel_login = new Pa_Login();
		this.panel_directory = new Pa_Directory();
		this.panel_log = new Pa_Log();
		this.panel_button = new Pa_Buttons(Language.getFtpClient_txts(0), Language.getFtpClient_txts(1),
				Language.getFtpClient_txts(11), Language.getFtpClient_txts(2), Language.getFtpClient_txts(3));

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

	// ---------------------------------------------

	// METHODS TO MANAGE JTREE
	/**
	 * METHODS TO MANAGE JTREE
	 * 
	 * @author Gabriel Payano
	 * @param url an String in URL format by the string provide.
	 * @return String Type , returns the url formated (/root/directory/file.txt).
	 */
	public static String generateURL(String url) {
		// Modify the string to be like an URL
		url = url.replace("[", "");
		url = url.replace("\\", "Hospital");
		url = url.replace(",", "/");
		url = url.replace(" ", "");
		url = url.replace("]", "");
		url = url.substring(1);
		url = url.trim();
		//url += "/";
		return url;
	}

	// DELETES THE SELECTED NODE IN THE JTHREE
	/**
	 * DELETES THE SELECTED NODE IN THE JTHREE
	 * 
	 * @author Gabriel Payano Type void deletes the selected node in the JTree
	 */
	public void deleteNode() {
		System.out.println("Delete node");
		panel_directory.getTree().removeSelectionPath(panel_directory.getTree().getSelectionPath());
		panel_directory.getTree().updateUI();
	}

	// METHOD TO CREATE A NEW NODE (FOLDER) INTO THE NODE TREE
	/**
	 * METHOD TO CREATE A NEW NODE (FOLDER) INTO THE NODE TREE
	 * 
	 * @author Gabriel Payano
	 * @param folderName a String type that have the name of the new node to create
	 *                   in the parent node selected by the user
	 */
	public void createNodeDirectory(String folderName) {
		TreePath path = this.panel_directory.getTree().getSelectionPath();
		if (path == null) {
			JOptionPane.showMessageDialog(null, "No Folder selected");
		} else {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(folderName);
			selectedNode.add(newNode);
			this.panel_directory.getTree().updateUI();
		}

	}

	// METHOD TO ASK FOR THE NEW NODE NAME TO UPDATE INTO THE JTREE
	/**
	 * METHOD TO ASK FOR THE NEW NODE NAME TO UPDATE INTO THE JTREE
	 * 
	 * @author Gabriel Payano
	 */
	public void askForNewFolderName() {
		String newFileName = JOptionPane.showInputDialog("Write the new folder name:");
		JOptionPane.showMessageDialog(null, "Folder name:  " + newFileName);
		createNodeDirectory(newFileName);
	}

	// GENERATES A JTREE TO DISPLAY IN THE PANEL DIRECTORY BASED ON A FILE.
	/**
	 * GENERATES A JTREE TO DISPLAY IN THE PANEL DIRECTORY BASED ON A FILE.
	 * 
	 * @author Gabriel Payano.
	 * @param directory Type File , contains the pointer to de main directory to
	 *                  list in the JTree.
	 * @param level     Type int , contains the depth level of the directory
	 *                  listing.
	 * @return JTree Type , returns an JTree based in the directory to list.
	 */
	public JTree generateTreeByFile(File directory, int level) {
		// System.out.println(directorio.getName());
		int actualLevel = level;
		File f = directory;
		String[] archivos = f.list();
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(directory.getName().toString());

		for (int i = 0; i < archivos.length; i++) {
			File f2 = new File(f, archivos[i]);
			// Formateo de nivel -Introducción de espacios-
			for (int espacios = 0; espacios < actualLevel; espacios++) {
				System.out.print("  A:");

			}
			System.out.println(archivos[i]);
			parentNode.add(new DefaultMutableTreeNode(archivos[i]));
			if (f2.isDirectory()) {
				System.out.println("fOILDER");
				generateTreeByFile(f2, actualLevel + 1);
			} else {
			}
		}

		return new JTree(parentNode);

	}

	/**
	 * DISPLAY THE CONTENT INSIDE A DIRECTORY INTO THE JTREE
	 * 
	 * @author Gabriel Payano.
	 * @param dir   File Type contains the root directory to display.
	 * @param root2 DefaultMutableTreeNode Type represent root directory in the
	 *              tree.
	 * @throws InterruptedException Throws an exception in case the method get
	 *                              interrupted.
	 */

	// DISPLAY THE CONTENT INSIDE A DIRECTORY INTO THE JTREE
	public void displayDirectoryContents(File dir, DefaultMutableTreeNode root2) throws InterruptedException {

		DefaultMutableTreeNode newdir = new DefaultMutableTreeNode();

		// creates array of file type for all the files found
		File[] files = dir.listFiles();

		for (File file : files) {
			if (file == null) {
				System.out.println("NUll directory found ");
				continue;
			}
			if (file.isDirectory()) {
				// file is a directory that is a folder has been dound

				if (file.listFiles() == null) {
					// skips null files
					continue;
				}

				// gets the current model of the jtree
				DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();

				// gets the root
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

				// generates a node newdir using filename
				newdir = new DefaultMutableTreeNode(file.getName());

				// adds a node to the root of the jtree
				root2.add(newdir);

				// refresh the model to show the changes
				model.reload();

				// recursively calls the function again to explore the contents
				// folder
				displayDirectoryContents(file, newdir);
			} else {
				// Else part File is not a directory

				// gets the current model of the tree
				DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();

				// selected node is the position where the new node will be
				// inserted
				DefaultMutableTreeNode selectednode = root2;

				DefaultMutableTreeNode newfile = new DefaultMutableTreeNode(file.getName());

				// inserts a node newfile under selected node which is the root
				model.insertNodeInto(newfile, selectednode, selectednode.getChildCount());

				// refresh the model to show the changes
				model.reload();

			}

		}
	}

	// GENERATE THE FILE TO DISPLAY INTRO THE JTREE BASED IN THE URL PASED
	/**
	 * GENERATE THE FILE TO DISPLAY INTRO THE JTREE BASED IN THE URL PASED
	 * 
	 * @author Gabriel Payano.
	 * @param url String Type , contains the url to generate a file pointing to it
	 *            in order to list that directory.
	 * @throws InterruptedException throws an InterruptedException.
	 */
	public void scanner(String url) throws InterruptedException {
		// creates a file with the location filename
		String location = url;
		File currentDir = new File(location);
		// result is the variable name for jtree
		DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();
		// gets the root of the current model used only once at the starting
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		// function caled
		// displayDirectoryContents(currentDir, root);
	}

	/*----------------------FTPCLIENT------------------------------------*/

	// DISPLAY THE CONTENT INSIDE A DIRECTORY INTO THE JTREE
	/**
	 * 
	 * DISPLAY THE CONTENT INSIDE A DIRECTORY INTO THE JTREE
	 * 
	 * @author Gabriel Payano.
	 * @param ftpClient  type FTPClient , references the class FTPClient.
	 * @param parentDir  String Type , contains the parent directory to list.
	 * @param currentDir String Type , contains the current directory that we are
	 *                   listing.
	 * @param level      int Type , contains the depth level of the directory.
	 * @param root2      DefaultMutableTreeNode Type , contains the node matching
	 *                   the depht level into the directory.
	 * @throws IOException throws an IOException.
	 */
	public void listDirectory(FTPClient ftpClient, String parentDir, String currentDir, int level,
			DefaultMutableTreeNode root2) throws IOException {

		// ADDING LISTENER
		panel_directory.getTree().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				URLnodeFormated = generateURL(panel_directory.getTree().getSelectionPath().toString()); // GETTING THEACTUAL NODE SELECTED
				System.out.println("URL: " + URLnodeFormated);
			}
		});

		DefaultMutableTreeNode newdir = new DefaultMutableTreeNode();

		String dirToList = parentDir;
		if (!currentDir.equals("")) {
			dirToList += "/" + currentDir;
		}
		FTPFile[] subFiles = ftpClient.listFiles(dirToList);

		if (subFiles != null && subFiles.length > 0) {

			for (FTPFile aFile : subFiles) {

				String currentFileName = aFile.getName();

				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					// skip parent directory and directory itself
					continue;
				}

				for (int i = 0; i < level; i++) {
					System.out.print("\t");
				}

				if (aFile.isDirectory()) {
					System.out.println("[" + currentFileName + "]");

					// gets the current model of the jtree
					DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();

					// gets the root
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

					// generates a node newdir using filename
					newdir = new DefaultMutableTreeNode(aFile.getName());

					// adds a node to the root of the jtree
					root2.add(newdir);

					// refresh the model to show the changes
					model.reload();

					// recursively calls the function again to explore the contents
					// folder
					listDirectory(ftpClient, dirToList, currentFileName, level + 1, newdir);
				} else {

					// Else part File is not a directory

					// gets the current model of the tree
					DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();

					// selected node is the position where the new node will be
					// inserted
					DefaultMutableTreeNode selectednode = root2;

					DefaultMutableTreeNode newfile = new DefaultMutableTreeNode(aFile.getName());

					// inserts a node newfile under selected node which is the root
					model.insertNodeInto(newfile, selectednode, selectednode.getChildCount());

					// refresh the model to show the changes
					model.reload();
					System.out.println(currentFileName);
				}
			}
		}
	}

	// GENERATE THE FILE TO DISPLAY INTRO THE JTREE BASED IN THe ftpclient
	/**
	 * GENERATE THE FILE TO DISPLAY INTRO THE JTREE BASED IN THe ftpclient
	 * 
	 * @author Gabriel Payano.
	 * @param ftpClient type FTPClient , references the class FTPClient.
	 * @throws InterruptedException throws an InterruptedException.
	 */
	public void scannerFTP(FTPClient ftpClient) {

		String dirToList = "";
		String user = panel_login.getTfield_user().getText().toString();

		// result is the variable name for jtree
		DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();
		// gets the root of the current model used only once at the starting
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		try {
			listDirectory(ftpClient, dirToList, dirToList, 0, root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DELETES THE SELECTED NODES FROM THE TREE
	/**
	 * DELETES THE SELECTED NODES FROM THE TREE
	 * @author Gabriel Payano
	 */
	public void deleteNodeFromTree() {

		DefaultTreeModel model = (DefaultTreeModel) panel_directory.getTree().getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		TreePath[] paths = panel_directory.getTree().getSelectionPaths();
		if (paths != null) {
			for (TreePath path : paths) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				if (node.getParent() != null) {
					model.removeNodeFromParent(node);
				}
			}
		}
		model.reload(root);
	}

	// SIMULATES THE UPDATE ON THE DIRECTORY BY REPLACING THE ACTUAL TREE NODE WITH
	// A NEW ONE
	// AND THEN LISTING ALL THE DIRECTORY AGAIN IN THAT NEW ROOT.
	/**
	 *  SIMULATES THE UPDATE ON THE DIRECTORY BY REPLACING THE ACTUAL TREE NODE WITH A NEW ONE AND THEN LISTING ALL THE DIRECTORY AGAIN IN THAT NEW ROOT.
	 * @author Gabriel Payano.
	 * @param ftpClient
	 */
	public void updateTree(FTPClient ftpClient) {
		// Create a new model and a new root node to empty the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("/");
		DefaultTreeModel model = new DefaultTreeModel(root);

		// Set a new Model to empty the JTree.
		panel_directory.getTree().setModel(model);

		// Reload the JTree to adapt it to the new Model
		model.reload(root);
		panel_directory.getTree().updateUI();

		// Load all directories again to get the new folders.
		scannerFTP(ftpClient);
	}
}
