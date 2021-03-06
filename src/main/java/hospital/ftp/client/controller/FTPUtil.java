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
 * @author Guillermo Gonzï¿½lez de Miguel
 * @version 1.0
 * @dateCreated 19/12/2021
 */
public class FTPUtil {
	private static String nameNewDir;
	private static String urlCreated;
	private static String delectedURL;
	private static String renamedURL;
	private static String oldName;
	private static String newName;
	private static String uploadedName;
	private static String uploadedServerURL;
	private static String downloadedName;
	private static String downloadedURL;

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
	 * @author Guillermo Gonzï¿½lez de Miguel
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
	 * @author Guillermo Gonzï¿½lez de Miguel
	 */
	public static void downloadFile(FTPClient ftpClient, String localPath) {
		downloadedURL = localPath;
		downloadedName = getLastWordURL(localPath);
		
		String localSavePath = chooseFile(new File(localPath).getName(), "SELECCIONE UNA UBICACIï¿½N PARA GUARDARLO",
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
	 * @param ftpClient         of type FTPClient, used for the operations with the
	 *                          FTP server
	 * 
	 * @param remotePath        of type String, remote path selected for the upload
	 *                          of a file.
	 * 
	 * @param pathSelectedMinus type String , parent folder name for the file to be
	 *                          renamed
	 * 
	 * @author Guillermo González de Miguel
	 * 
	 *         Modify by Gabriel Payano
	 */
	public static void uploadFile(FTPClient ftpClient, String remotePath) {
		uploadedServerURL = remotePath;

		String fileSelected = chooseFile(null, "SELECCIONA UN FICHERO PARA SUBIR", "SUBIR");

		if (fileSelected != "") {
			File localFile = new File(fileSelected);
			uploadedName = localFile.getName().trim().replace(" ", "_");
			String remoteFile = remotePath + "/" + uploadedName;
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
	 * @return
	 */
	public static int manageDelete(FTPClient ftpClient, String parentDir, String currentDir) {
		// -1 = no files deleted || 0 = file deleted || 1 = empty folder deleted || 2 =
		// filled folder deleted
		int deletedFiles = -1;
		delectedURL = parentDir;

		switch (parentDir) {
		case "/medicos":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de medicos");
			break;
		case "/pacientes":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de pacientes");
			break;
		case "":
			JOptionPane.showMessageDialog(null, "No se puede eliminar la carpeta de raï¿½z");
			break;
		default:
			if (deleteFile(ftpClient, parentDir)) {
				deletedFiles = 0;
				System.out.println("-----------");
				System.out.println("Is a File");
			} else if (deleteEmptyFolder(ftpClient, parentDir)) {
				deletedFiles = 1;
				System.out.println("-----------");
				System.out.println("Is an empty directory");
			} else {
				System.out.println("-----------");
				System.out.println("Is a Directory with elements");
				deleteFileRecursive(ftpClient, parentDir, currentDir);
				deletedFiles = 2;
			}
			break;
		}

		return deletedFiles;

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
	 * @param ftpclient  of type FTPClient, used for the operations with the FTP
	 *                   server
	 * @param remotePath Type String , path of the remote file in the server to
	 *                   delete
	 * @return Type boolean , indicates if the directory is deleted properly
	 * 
	 * @author Gabriel Payano.
	 * @editor Jorge Fernández Ruiz
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
	 * @author Guillermo Gonzï¿½lez de Miguel
	 * @editor Jorge Fernández Ruiz
	 */
	public static boolean createDirectory(FTPClient ftpClient, String selectedDir) {
		urlCreated = selectedDir;
		boolean created = false;
		File file = new File(selectedDir);
		String pathToCreate = file.getPath() + File.separator;
		FTPFile[] files = null;
		nameNewDir = Tool.inputGUIpane("Indique el nombre del nuevo directorio:", "CREAR DIRECTORIO");

		if (nameNewDir != null) {		
			nameNewDir = nameNewDir.trim().replace(" ", "_");

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

				if (success) {
					Tool.showGUIinfo("Directorio creado con ï¿½xito.", "INFORMACIï¿½N" + " - " + "CREACIï¿½N EXITOSA");
					created = true;
				} else {
					Tool.showGUIerror("Fallo al crear el directorio.", "ERROR" + " - " + "CREACIï¿½N FALLIDA");
				}
			} catch (IOException e) {
				Tool.showGUIerror("Ha fallado la creaciï¿½n del directorio." + "\nSistema dice: " + e.getMessage(),
						"ERROR - CREACIï¿½N DIRECTORIO");
			}
		} else {
			Tool.showGUIinfo("Has cancelado la creaciï¿½n de un nuevo directorio.", "CREAR DIRECTORIO");
		}
		return created;
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
	 * @author Guillermo Gonzï¿½lez de Miguel
	 * 
	 *         Modify by Gabriel Payano
	 */
	public static void renameFile(FTPClient ftpClient, String pathSelected, String pathSelectedMinus) {
		renamedURL = pathSelected;
		System.out.println("---------------IN RENAME------------------");
		System.out.println("PATH SELECTED: " + pathSelected);
		System.out.println("PATH SELECTED Minus : " + pathSelectedMinus);
		File renameFile = new File(pathSelected);

		try {
			String title = "RENOMBRAR ";
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

			oldName = renameFile.getName().toString().trim().replace(" ", "_");
//			System.out.println(oldName);
			newName = Tool.inputGUIpane("Introduzca el nuevo nombre", title, oldName).toString().trim().replace(" ", "_");

			boolean success = ftpClient.rename(pathSelected, pathSelectedMinus + newName);
			if (success) {
				System.out.println("THE HAS CHANGE TO THE NEW ONE");
			}
		} catch (IOException e) {
			Tool.showGUIerror(
					"Ha habido un fallo al realizar la operacion renombrar.\n" + "Sistema dice: " + e.getMessage(),
					"ERROR AL RENOMBRAR");
		}

		// System.out.println("---------------OUT OF RENAME------------------");
	}

	public static void showResponse(FTPClient ftpClient) {
		System.out.println(ftpClient.getReplyStrings().length);
		System.out.println(ftpClient.getReplyString());
//		System.out.println(ftpClient.getr);
		System.out.println(ftpClient.getReplyCode());
	}

	/**
	 * @author Jorge Fernández Ruiz
	 * @param URL
	 * @return
	 */
	public static String getLastWordURL(String URL) {
		String[] words = URL.split("/");
		return words[words.length - 1];
	}

	/**
	 * Getter the name of a created new file
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return nameNewDir, of type {@link String}
	 */
	public static String getNameNewDir() {
		return nameNewDir;
	}

	/**
	 * Getter the url where was created the new file.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the urlCreated
	 */
	public static String getUrlCreated() {
		return urlCreated;
	}

	/**
	 * Getter the url where was deleted the file.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the delectedURL
	 */
	public static String getDelectedURL() {
		return delectedURL;
	}

	/**
	 * Getter the url where was rename the file.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the renamedURL
	 */
	public static String getRenamedURL() {
		return renamedURL;
	}

	/**
	 * Getter the file's old name.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the oldName
	 */
	public static String getOldName() {
		return oldName;
	}

	/**
	 * Getter the file's new name.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the newName
	 */
	public static String getNewName() {
		return newName;
	}

	/**
	 * Getter the uploaded file's name.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the uploadedName
	 */
	public static String getUploadedName() {
		return uploadedName;
	}

	/**
	 * Getter the uploaded file's URL.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the uploadedURL
	 */
	public static String getUploadedServerURL() {
		return uploadedServerURL;
	}

	/**
	 * Getter the downloaded file's name.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the downloadedName
	 */
	public static String getDownloadedName() {
		return downloadedName;
	}

	/**
	 * Getter the downloaded file's URL.
	 * 
	 * @author Jorge Fernández Ruiz
	 * @return the downloadedURL
	 */
	public static String getDownloadedURL() {
		return downloadedURL;
	}
	
	

}
