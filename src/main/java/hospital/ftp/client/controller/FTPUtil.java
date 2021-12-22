// PACKAGE
package hospital.ftp.client.controller;

// IMPORTS
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import hospital.tools.Tool;

/**
 * Class containing the methods to perform the upload, download, rename, delete
 * and directory creation operations on the remote FTP server.
 * 
 * @author Guillermo González de Miguel
 * @version 1.0
 * @dateCreated 19/12/2021
 */
public class FTPUtil {

	/**
	 * Method for displaying a directory or file selection window. This window
	 * returns the path that is selected.
	 * 
	 * @param name    of type String, name of the file or directory.
	 * 
	 * @param title   of type String, title of the JFileChooser window
	 * 
	 * @param btnText of type String, JFileChooser window button text
	 * 
	 * @return String value, returns the selected path.
	 * 
	 * @author Guillermo González de Miguel
	 */
	private static String chooseFile(String name, String title, String btnText) {
		JFileChooser f = new JFileChooser();

		String fileSelected = "";
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		f.setDialogTitle(title);
		if (name != null) {
			f.setSelectedFile(new File(name));
		}

		int returnVal = f.showDialog(f, btnText);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			fileSelected = file.getAbsolutePath();
//			System.out.println(fileSelected);
		}

		return fileSelected;
	}

	/**
	 * Method of downloading files or directories from the remote FTP server to a
	 * specified location on the local computer.
	 * 
	 * @param ftpClient of type FTPClient, used for the operations with the FTP
	 *                  server
	 * 
	 * @param localPath of type String, local path selected to save the file
	 *                  downloaded from the FTP server
	 * 
	 * @author Guillermo González de Miguel
	 */
	public static void downloadFile(FTPClient ftpClient, String localPath) {
		String localSavePath = chooseFile(new File(localPath).getName(), "SELECCIONE UNA UBICACIÓN PARA GUARDARLO",
				"GUARDAR");

		if (localSavePath != "") {

			try {

				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(localSavePath)));
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				boolean success = ftpClient.retrieveFile(localPath, outputStream);

				outputStream.close();

				if (success) {
					System.out.println("File #1 has been downloaded successfully.");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Tool.showGUIinfo("Has cancelado la descarga del fichero u directorio", "BAJADA CANCELADA");
		}
	}

	/**
	 * 
	 * @param ftpClient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * 
	 * @param remotePath of type String, remote path selected for the upload of a
	 *                   file.
	 * 
	 * @author Guillermo González de Miguel
	 */
	public static void uploadFile(FTPClient ftpClient, String remotePath) {

		String fileSelected = chooseFile(null, "SELECCIONA UN FICHERO PARA SUBIR", "SUBIR");

		if (fileSelected != "") {
			File localFile = new File(fileSelected);
			String remoteFile = remotePath + localFile.getName();
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(localFile);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				boolean success = ftpClient.storeFile(remoteFile, inputStream);
				inputStream.close();

				if (success) {
					Tool.showGUIinfo("La subida ha sido exitosa.", "SUBIDA DE ARCHIVOS");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Tool.showGUIinfo("Has cancelado la subida al servidor FTP", "SUBIDA CANCELADA");
		}
	}

	/**
	 * Method for creating a new directory in a specified path on the remote FTP
	 * server.
	 * 
	 * @param ftpClient   of type FTPClient, used for the operations with the FTP
	 *                    server
	 * 
	 * @param selectedDir of type String, path selected for the creation of the new
	 *                    directory
	 * 
	 * @author Guillermo González de Miguel
	 */
	public static void createDirectory(FTPClient ftpClient, String selectedDir) {
		File file = new File(selectedDir);
		String pathToCreate = file.getPath() + File.separator;
		FTPFile[] files = null;
		String nameNewDir = Tool.inputGUIpane("Indique el nombre del nuevo directorio:", "CREAR DIRECTORIO");

		if (nameNewDir != null) {
			try {
				files = ftpClient.listFiles(selectedDir);

				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().contains(file.getName())) {
						if (files[i].isFile()) {
//							System.out.println(file.getPath());
							pathToCreate = file.getParent() + File.separator;
						}
						i = files.length;
					}
				}

				boolean success = ftpClient.makeDirectory(pathToCreate + nameNewDir); // CREATE A NEW DIRECTORY

				if (success)
					Tool.showGUIinfo("Directorio creado con éxito.", "INFORMACIÓN" + " - " + "CREACIÓN EXITOSA");
				else
					Tool.showGUIerror("Fallo al crear el directorio.", "ERROR" + " - " + "CREACIÓN FALLIDA");

			} catch (IOException e) {
				Tool.showGUIerror("Ha fallado la creación del directorio." + "\nSistema dice: " + e.getMessage(),
						"ERROR - CREACIÓN DIRECTORIO");
			}
		} else {
			Tool.showGUIinfo("Has cancelado la creación de un nuevo directorio.", "CREAR DIRECTORIO");
		}
	}

	/**
	 * Method for renaming files or directories on the remote FTP server.
	 * 
	 * @param ftpClient    of type FTPClient, used for the operations with the FTP
	 *                     server
	 * 
	 * @param pathSelected of type String, path of the directory or file to be
	 *                     renamed
	 * 
	 * @author Guillermo González de Miguel
	 * 
	 * Modify by Gabriel Payano
	 */
	public static void renameFile(FTPClient ftpClient, String pathSelected , String pathSelectedMinus) {
		System.out.println("---------------IN RENAME------------------");

		System.out.println("PATH SELECTED: " + pathSelected);
		System.out.println("PATH SELECTED Minus : " + pathSelectedMinus );
		File renameFile = new File(pathSelected);

		try {
			String title = "RENOMBRAR ";
			String aux;
			if (renameFile.isDirectory())
				title += "DIRECTORIO";
			else
				title += "FICHERO";

			/*
			 * Original Part by: Guillermo boolean success =
			 * ftpClient.rename(renameFile.getName(),
			 * Tool.inputGUIpane("Introduzca el nuevo nombre", title,
			 * renameFile.getName()).toString());
			 */

			/**
			 * @author Gabriel Payano Modify by: Gabriel Payano
			 */
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			System.out.println("EL GET NAME TIENE: " + renameFile.getName());

			boolean success = ftpClient.rename(pathSelected, pathSelectedMinus + Tool.inputGUIpane("Introduzca el nuevo nombre", title,
					  renameFile.getName()).toString());
			if (success) {
				System.out.println("THE HAS CHANGE TO THE NEW ONE");
			}
		} catch (IOException e) {
			Tool.showGUIerror(
					"Ha habido un fallo al realizar la operacion renombrar.\n" + "Sistema dice: " + e.getMessage(),
					"ERROR AL RENOMBRAR");
		}

		System.out.println("---------------OUT OF RENAME------------------");
	}

	public static void showResponse(FTPClient ftpClient) {
		System.out.println(ftpClient.getReplyStrings().length);
		System.out.println(ftpClient.getReplyString());
//		System.out.println(ftpClient.getr);
		System.out.println(ftpClient.getReplyCode());
	}
}
