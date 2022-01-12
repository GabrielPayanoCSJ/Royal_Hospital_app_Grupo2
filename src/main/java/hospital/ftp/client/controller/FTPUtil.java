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
 * @author Guillermo Gonz�lez de Miguel
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
	 * @author Guillermo Gonz�lez de Miguel
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
	 * @author Guillermo Gonz�lez de Miguel
	 */
	public static void downloadFile(FTPClient ftpClient, String localPath) {
		String localSavePath = chooseFile(new File(localPath).getName(), "SELECCIONE UNA UBICACI�N PARA GUARDARLO",
				"GUARDAR");
		if (localSavePath != "") {
			try {

				File downlFile = new File(localSavePath);
				System.out.println("DESCARGADO: " + downlFile.getAbsolutePath());

				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downlFile));
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
	 * @param ftpClient         of type FTPClient, used for the operations with the
	 *                          FTP server
	 * 
	 * @param remotePath        of type String, remote path selected for the upload
	 *                          of a file.
	 * 
	 * @param pathSelectedMinus type String , parent folder name for the file to be
	 *                          renamed
	 * 
	 * @author Guillermo Gonz�lez de Miguel
	 * 
	 *         Modify by Gabriel Payano
	 */
	public static void uploadFile(FTPClient ftpClient, String remotePath) {

		String fileSelected = chooseFile(null, "SELECCIONA UN FICHERO PARA SUBIR", "SUBIR");

		if (fileSelected != "") {
			File localFile = new File(fileSelected);
			String remoteFile = remotePath + "/" + localFile.getName();
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(localFile);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				// System.out.println("REMOTE FILE: " + remoteFile);
				// System.out.println("INPUT STREAM: " + inputStream.toString());
				// System.out.println("PATH SELECTED Minus : " + pathSelectedMinus );
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

	/* DELETES------------------ */

	// Main deleted method thats calls the delete method for each case.
	/**
	 * Main deleted method thats calls the delete method for each case.
	 * 
	 * @author Gabriel Payano
	 * @param ftpClient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * @param parentDir  Type string , name of the parent directory selected by the
	 *                   user
	 * @param currentDir Type string , contains the current directory name into the
	 *                   directory list of a folder(Directory)
	 */
	public static void manageDelete(FTPClient ftpClient, String parentDir, String currentDir) {

		switch (parentDir) {
		case "/medicos":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de medicos");
			break;
		case "/pacientes":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de pacientes");
			break;
		case "/doctors":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de medicos");
			break;
		case "/patients":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de pacientes");
			break;
		case "":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de ra�z");
			break;
		default:
			if (deleteFile(ftpClient, parentDir)) {
				System.out.println("-----------");
				System.out.println("Is a File");

			} else if (deleteEmptyFolder(ftpClient, parentDir)) {
				System.out.println("-----------");
				System.out.println("Is an empty directory");
			} else {
				System.out.println("-----------");
				System.out.println("Is a Directory with elements");
				deleteFileRecursive(ftpClient, parentDir, currentDir);

			}
			break;
		}

	}

	// Delete a file in the server by the remote path.
	/**
	 * Delete a file in the server by the remote path.
	 * 
	 * @author Gabriel Payano
	 * @param ftpclient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * @param remotePath Type String , path of the remote file in the server to
	 *                   delete
	 * @return type Boolean , indicates if the file is correctly deleted.
	 */
	public static boolean deleteFile(FTPClient ftpclient, String remotePath) {
		// System.out.println("REMOTE fILE: " + remotePath);
		// System.out.println("--------------------DELETE FILE----------");
		try {
			boolean deleted = ftpclient.deleteFile(remotePath);
			if (deleted) {
				System.out.println("The file was deleted successfully.");
				return true;
			} else {
				System.out.println("Could not delete the file.");
			}
		} catch (IOException ex) {
			System.out.println("Oh no, there was an error: " + ex.getMessage());
		}

		return false;
	}

	// Deletes the whole directory selected by the user , if is not empty it deletes
	// de content on it and then deletes that directory
	/**
	 * Deletes the whole directory selected by the user , if is not empty it deletes
	 * de content on it and then deletes that directory
	 * 
	 * @author Gabriel Payano
	 * @param ftpClient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * @param parentDir  Type string , name of the parent directory selected by the
	 *                   user
	 * @param currentDir Type string , contains the current directory name into the
	 *                   directory list of a folder(Directory)
	 */
	public static void deleteFileRecursive(FTPClient ftpClient, String parentDir, String currentDir) {

		// System.out.println("DELETE RECURSIVO----------------------------");

		// System.out.println("Current vale: " + currentDir);

		String dirToList = parentDir;
		if (!currentDir.equals("")) {
			dirToList += "/" + currentDir;
		}

		System.out.println("DIR TO LIST: " + dirToList);
		FTPFile[] subFiles = null;
		try {
			subFiles = ftpClient.listFiles(dirToList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();
				System.out.println("ACTUAL: " + currentFileName);
				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					// skip parent directory and the directory itself
					continue;
				}
				String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
				if (currentDir.equals("")) {
					filePath = parentDir + "/" + currentFileName;
				}

				if (aFile.isDirectory()) {
					// remove the sub directory
					deleteFileRecursive(ftpClient, dirToList, currentFileName);
				} else {
					// delete the file
					boolean deleted = false;
					try {
						deleted = ftpClient.deleteFile(filePath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (deleted) {
						System.out.println("DELETED the file: " + filePath);
					} else {
						System.out.println("CANNOT delete the file: " + filePath);
					}
				}
			}

			// finally, remove the directory itself
			boolean removed = false;
			try {
				removed = ftpClient.removeDirectory(dirToList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (removed) {
				System.out.println("REMOVED the directory: " + dirToList);
			} else {
				System.out.println("CANNOT remove the directory: " + dirToList);
			}
		}

		else {
			deleteEmptyFolder(ftpClient, dirToList);
		}

		// System.out.println("---------------------------------------------DELETE
		// RECURSIVO");
	}

//	Deletes an empty directory on the server by the path given.
	/**
	 * Deletes an empty directory on the server by the path given.
	 * 
	 * @author Gabriel Payano.
	 * @param ftpclient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * @param remotePath Type String , path of the remote file in the server to
	 *                   delete
	 * @return Type boolean , indicates if the directory is deleted properly
	 */
	public static boolean deleteEmptyFolder(FTPClient ftpclient, String remotePath) {
		// System.out.println("-----------INTO DELETE EMPTY FOLDER--------");
		// System.out.println("Cadena a borrar: " + remotePath);
		try {
			boolean deleted = ftpclient.removeDirectory(remotePath);
			if (deleted) {
				System.out.println("The directory was removed successfully.");
				return true;
			} else {
				System.out.println("Could not delete the directory, it may not be empty");
			}
		} catch (IOException ex) {
			System.out.println("Oh no, there was an error: " + ex.getMessage());
		}

		return false;
	}

	/*---------------------DELETES*/

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
	 * @author Guillermo Gonz�lez de Miguel
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
					Tool.showGUIinfo("Directorio creado con �xito.", "INFORMACI�N" + " - " + "CREACI�N EXITOSA");
				else
					Tool.showGUIerror("Fallo al crear el directorio.", "ERROR" + " - " + "CREACI�N FALLIDA");

			} catch (IOException e) {
				Tool.showGUIerror("Ha fallado la creaci�n del directorio." + "\nSistema dice: " + e.getMessage(),
						"ERROR - CREACI�N DIRECTORIO");
			}
		} else {
			Tool.showGUIinfo("Has cancelado la creaci�n de un nuevo directorio.", "CREAR DIRECTORIO");
		}
	}

	/**
	 * Method for renaming files or directories on the remote FTP server.
	 * 
	 * @param ftpClient         of type FTPClient, used for the operations with the
	 *                          FTP server
	 * 
	 * @param pathSelected      of type String, path of the directory or file to be
	 *                          renamed
	 * 
	 * @param pathSelectedMinus type String , parent folder name for the file to be
	 *                          renamed
	 * 
	 * @author Guillermo Gonz�lez de Miguel
	 * 
	 *         Modify by Gabriel Payano
	 */
	public static void renameFile(FTPClient ftpClient, String pathSelected, String pathSelectedMinus) {
		/*
		System.out.println("---------------IN RENAME------------------");
		System.out.println("PATH SELECTED: " + pathSelected);
		System.out.println("PATH SELECTED Minus : " + pathSelectedMinus);
		System.out.println("ÚLTIMA PALABRA: " + pathSelectedMinus);
*/
		switch (pathSelected) {
		case " ":
			JOptionPane.showMessageDialog(null, "No se puede renombrar la carpeta de Raíz");
			break;
		case "/medicos":
			JOptionPane.showMessageDialog(null, "No se puede renombrar la carpeta de medicos");
			break;
		case "/pacientes":
			JOptionPane.showMessageDialog(null, "No se puede renombrar la carpeta de pacientes");
			break;
		case "/doctors":
			JOptionPane.showMessageDialog(null, "No se puede renombrar la carpeta de medicos");
			break;
		case "/patients":
			JOptionPane.showMessageDialog(null, "No se puede renombrar la carpeta de pacientes");
			break;
		default:
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

				// System.out.println("EL GET NAME TIENE: " + renameFile.getName());

				boolean success = ftpClient.rename(pathSelected, pathSelectedMinus
						+ Tool.inputGUIpane("Introduzca el nuevo nombre", title, renameFile.getName()).toString());
				if (success) {
					System.out.println("THE HAS CHANGE TO THE NEW ONE");
				}
			} catch (IOException e) {
				Tool.showGUIerror(
						"Ha habido un fallo al realizar la operacion renombrar.\n" + "Sistema dice: " + e.getMessage(),
						"ERROR AL RENOMBRAR");
			}

			// System.out.println("---------------OUT OF RENAME------------------");
			break;
		}

		
	}

	public static void showResponse(FTPClient ftpClient) {
		System.out.println(ftpClient.getReplyStrings().length);
		System.out.println(ftpClient.getReplyString());
//		System.out.println(ftpClient.getr);
		System.out.println(ftpClient.getReplyCode());
	}
}
