/**
 * 
 */

// PACKAGE
package hospital.languages;

// IMPORTS
import java.util.*;

/**
 * @author Guillermo González
 * @version 1.0
 */
public class Lang_enUK {

	/**
	 * 
	 */
	private ArrayList<ArrayList<String>> enUK;

	/**
	 * 
	 */
	public Lang_enUK() {
		this.enUK = new ArrayList<ArrayList<String>>(Arrays.asList( // LIST ENGLISH BRITISH TEXTS
				loadErrorTxts(), // 0
				loadDatabaseTxts(), // 1
				loadFTPClientTxts(), // 2
				loadFTPServerTxts(), // 3
				loadMailClientTxts(), // 4
				loadMailServerTxts(), // 5
				loadLanguageTxts(), // 6
				loadMainTxts(), // 7
				loadMailLoginTxts(), // 8
				loadReadMailTxts(), // 9
				loadWriteMailTxts() // 10
		));
	}

	private ArrayList<String> loadLanguageTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST LANGUAGES TEXTS
				"SPANISH", // 0
				"ENGLISH" // 1
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMailClientTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST MAIL CLIENT TEXTS
				"MAIL CLIENT", // 0
				"INBOX", // 1
				"WRITE", // 2
				"READ", // 3
				"EXIT", // 4
				"TOTAL: ", // 5
				"NEWS: " // 6
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadReadMailTxts() { // NOT IN ENGLISH
		return new ArrayList<String>(Arrays.asList( // LIST READ MAIL TEXTS
				"READ MAIL", // 0
				"DESCRIPTION", // 1
				"FROM: ", // 2
				"SUBJECT: ", // 3
				"BODY" // 4
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadWriteMailTxts() { // NOT IN ENGLISH
		return new ArrayList<String>(Arrays.asList( // LIST MAIL CLIENT TEXTS
				"REDACTION", // 0
				"FROM: ", // 1
				"TO: ", // 2
				"DESCRIPTION", // 3
				"SUBJECT", // 4
				"MESSAGE", // 5
				"SEND" // 6
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMailLoginTxts() { // NOT IN ENGLISH
		return new ArrayList<String>(Arrays.asList( // LIST MAIL LOGIN TEXTS
				"ACCESS WINDOW", // 0
				"MAIL ACCESS", // 1
				"EMAIL", // 2
				"PASSWORD", // 3
				"LOG-IN", // 4
				"CANCEL" // 5
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMainTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST MAIN TEXTS
				"MAIN WINDOW", // 0
				"PRINCIPAL EXECUTER", // 1
				"CHOOSE A LANGUAGE", // 2
				"FTP SERVER", // 3
				"FTP CLIENT", // 4
				"MAIL CLIENT" // 5
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMailServerTxts() {
		return new ArrayList<String>(Arrays.asList(""));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadFTPClientTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST FTP CLIENT TEXTS
				"CREATE DIRECTORY", // 0
				"DELETE", // 1
				"UPLOAD", // 2
				"DOWNLOAD", // 3
				"EXIT", // 4
				"CLIENT LOG", // 5
				"REMOTE DIRECTORY", // 6
				"FTP CLIENT - GROUP 2", // 7
				"CONNECT", // 8
				"USER: ", // 9
				"PASSWORD: ", // 10
				"RENAME", // 11
				"DISCONNECT", // 12
				"CREATE DIRECTORY", // 13
				"Write the name of the new directory:", // 14
				"REMANE FILE", // 15
				"Write the new name", // 16
				"Select a file to upload", // 17
				"Select a path to save" // 18

		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadFTPServerTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST FTP SERVER TEXTS
				"SERVER WINDOW", // 0
				"STOP", // 1
				"START" // 2
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadErrorTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST ERROR TEXTS & TITLES
				"CLASS NOT FOUND", // 0
				"INFO", // 1 (Title)
				"SELECT A ROOT DIRECTORY", // 2 (text of 1)
				"LOGIN FIELDS EMPTY", // 3 (title)
				"USER EMPTY", // 4 (text of 3)
				"ERROR - FTP SERVER NOT FOUND", // 5 (title)
				"NOT POSSIBLE TO GET A CONNECTION WITH THE FTP SERVER", // 6 (text of 5)
				"ERROR TO RENAME", // 7 (title)
				"THERE WAS AN ERROR IN THE RENAME PROCESS. \n SISTEM SAYS: ", // 8 (text of 7)
				"AUTHENTICATION ERROR", // 9
				"USER OR PASSWORD NOT VALID" // 10
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadDatabaseTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST DATABASE TEXTS
				"MySQL driver not found", // 0
				"Database connection failed. \n\n+ System says:", // 1
				"Parameterised query failed. \n\n+ System says:", // 2
				"", // 3
				"", // 4
				"", // 5
				"", // 6
				"", // 7
				"", // 8
				"", // 9
				"" // 10
		));
	}

	/**
	 * @return the enUK
	 */
	public ArrayList<ArrayList<String>> getEnUK() {
		return enUK;
	}

}
