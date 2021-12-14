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
				loadMailServerTxts() // 5
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMailClientTxts() {
		return new ArrayList<String>(Arrays.asList(""));
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
		return new ArrayList<String>(Arrays.asList(""));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadFTPServerTxts() {
		return new ArrayList<String>(Arrays.asList(""));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadErrorTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST ERROR TEXTS
				"Test error message" // 0
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
