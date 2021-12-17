/**
 * 
 */

// PACKAGE
package hospital.languages;

// IMPORTS
import java.util.*;
import hospital.tools.Tool;

/**
 * 
 * @author Guillermo González
 * @version 1.0
 */
public class Language {

	/**
	 * 
	 */
	private static Lang_esES lang_es;

	/**
	 * 
	 */
	private static Lang_enUK lang_uk;

	// OTHER TEXTS

	/**
	 * 
	 */
	private static ArrayList<String> error_txts;

	/**
	 * 
	 */
	private static ArrayList<String> db_txts;

	// FTP SERVICE TEXTS

	/**
	 * 
	 */
	private static ArrayList<String> ftpClient_txts;

	/**
	 * 
	 */
	private static ArrayList<String> ftpServer_txts;

	// MAIL SERVICE TEXTS

	/**
	 * 
	 */
	private static ArrayList<String> mailClient_txts;

	/**
	 * 
	 */
	private static ArrayList<String> mailServer_txts;

	
	private static ArrayList<String> language_txts;
	
	/**
	 * 
	 * @param langOption
	 */
	public static void selectLanguage(int langOption) {
		
		/**
		 * 
		 */
		lang_uk = new Lang_enUK(); // DEFAULT LENGUAGE - ENGLISH TEXTS
		
		lang_es = new Lang_esES(); // SPANISH TEXTS

		/**
		 * 
		 */
		ArrayList<ArrayList<String>> langTexts = null;
		boolean isExist = true;
		

		switch (langOption) {
		case 0:
			langTexts = lang_uk.getEnUK();
			break;
		case 1:
			langTexts = lang_es.getEsES();
			break;
		default:
			Tool.showConsoleMessage("Option[ " + langOption + " ] doesn't exist.", true);
			langTexts = lang_uk.getEnUK();
			isExist = false;
			break;
		}

		loadTexts(langTexts);
	}

	/**
	 * 
	 * @param langTexts
	 */
	private static void loadTexts(ArrayList<ArrayList<String>> langTexts) {

		for (int option = 0; option < langTexts.size(); option++) {
			switch (option) {
			case 0: // ERROR TEXTS
				error_txts = langTexts.get(option);
				break;
			case 1: // DATABASE TEXTS
				db_txts = langTexts.get(option);
				break;
			case 2: // FTP CLIENT TEXTS
				ftpClient_txts = langTexts.get(option);
				break;
			case 3: // FTP SERVER TEXTS
				ftpServer_txts = langTexts.get(option);
				break;
			case 4: // MAIL CLIENT TEXTS
				mailClient_txts = langTexts.get(option);
				break;
			case 5: // MAIL SERVER TEXTS
				mailServer_txts = langTexts.get(option);
				break;
			case 6: // MAIL SERVER TEXTS
				language_txts = langTexts.get(option);
				break;
			default:
				Tool.showConsoleMessage("Option [ " + option + " ] doesn't exist.", true);
				break;
			}
		}
	}

	/**
	 * @return 
	 */
	public String getLanguage_txts(int index) {
		return language_txts.get(index);
	}

	/**
	 * @return
	 */
	public static String getError_txts(int index) {
		return error_txts.get(index);
	}

	/**
	 * @return
	 */
	public static String getDb_txts(int index) {
		return db_txts.get(index);
	}

	/**
	 * @return
	 */
	public static String getFtpClient_txts(int index) {
		return ftpClient_txts.get(index);
	}

	/**
	 * @return
	 */
	public static String getFtpServer_txts(int index) {
		return ftpServer_txts.get(index);
	}

	/**
	 * @return
	 */
	public static String getMailClient_txts(int index) {
		return mailClient_txts.get(index);
	}

	/**
	 * @return the mailServer_txts
	 */
	public static String getMailServer_txts(int index) {
		return mailServer_txts.get(index);
	}

}
