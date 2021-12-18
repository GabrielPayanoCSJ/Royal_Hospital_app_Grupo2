/**
 * 
 */
package hospital.ftp.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
			 JOptionPane.showMessageDialog(null,"No Folder selected");
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
		String newFileName = JOptionPane.showInputDialog("Write the new folder name:");
		JOptionPane.showMessageDialog(null, "Folder name:  " + newFileName);
		createNodeDirectory(newFileName);
	}
	
	public JTree generateTreeByFile(File directorio, int nivel) {
		//System.out.println(directorio.getName());
        int nivelActual = nivel;
        File f = directorio;
        String[] archivos = f.list();
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(directorio.getName().toString());

        for (int i = 0; i < archivos.length; i++) {
            File f2 = new File(f, archivos[i]);
            // Formateo de nivel -Introducción de espacios-
            for (int espacios = 0; espacios < nivelActual; espacios++) {
                System.out.print("  A:");
               
            }
            System.out.println(archivos[i]);
            parentNode.add(new DefaultMutableTreeNode(archivos[i]));
            if (f2.isDirectory()) {
            	System.out.println("fOILDER");
            	generateTreeByFile(f2, nivelActual + 1);
            } else {
                // Si el fichero que encuentra en la carpeta es "Companies.txt" entonces
                // procedemos a realizar las copias
               /* if (archivos[i].equals("Companies.txt")) {
                    System.out.println("      -----||Companies.txt encontrado||----");
                    File rutaCompanies = new File(f, archivos[i]);// Creamos un objeto de tipo File con la nueva ruta
                                                                    // que nos lleva a Companies
                    // System.out.println(" Ruta a companies: " + rutaCompanies.getAbsolutePath());
                    crearCopiaBinaria(f, rutaCompanies);// Creamos la copia en binario Owners.dat

                }*/
            }
        }
        
        return new JTree(parentNode); 
        

	}
	/**
	 * 
	 * @param dir File Type contains the root directory to display 
	 * @param root2 DefaultMutableTreeNode Type represent root directory in the tree
	 * @throws InterruptedException Throws an exception in case the method get interrupted
	 */
	
	//DISPLAY THE CONTENT INSIDE A DIRECTORY INTO THE JTREE
	public void displayDirectoryContents(File dir, DefaultMutableTreeNode root2)
		       throws InterruptedException {

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
	
	//GENERATE THE FILE TO DISPLAY INTRO THE JTREE BASED IN THE URL PASED
	public void scanner(String url) throws InterruptedException {
	    // creates a file with the location filename
	    String location = url;
	    File currentDir = new File(location);
	    // result is the variable name for jtree
	    DefaultTreeModel model = (DefaultTreeModel) this.panel_directory.getTree().getModel();
	    // gets the root of the current model used only once at the starting
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
	    // function caled
	    displayDirectoryContents(currentDir, root);
	}
	
}
