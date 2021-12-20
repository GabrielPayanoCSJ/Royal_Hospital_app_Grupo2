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
public class Lang_esES {

	/**
	 * 
	 */
	private ArrayList<ArrayList<String>> esES;

	/**
	 * 
	 */
	public Lang_esES() {
		this.esES = new ArrayList<ArrayList<String>>(Arrays.asList( // LIST SPANISH TEXTS
				loadErrorTxts(), // 0
				loadDatabaseTxts(), // 1
				loadFTPClientTxts(), // 2
				loadFTPServerTxts(), // 3
				loadMailClientTxts(), // 4
				loadMailServerTxts(), // 5
				loadLanguageTxts() // 6
		));
	}

	private ArrayList<String> loadLanguageTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST LANGUAGES TEXTS
				"ESPAÑA", // 0
				"INGLÉS" // 1
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
		return new ArrayList<String>(Arrays.asList( // LIST FTP CLIENT TEXTS
				"CREAR DIRECTORIO", // 0
				"ELIMINAR", // 1
				"SUBIR", // 2
				"DESCARGAR", // 3
				"SALIR", // 4
				"LOG CLIENTE", // 5
				"DIRECTORIO REMOTO", // 6
				"FTP CLIENTE - GRUPO 2", // 7
				"CONECTAR", // 8 
				"USUARIO: ", // 9
				"CONTRASEÑA: ", // 10
				"RENOMBRAR", // 11
				"DESCONECTAR" // 12
		));
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
				"Clase no encontrada", // 0
				"", "", "", "", "", "", "", "", "", "", ""));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadDatabaseTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST DATABASE TEXTS
				"Driver MySQL no encontrado.", // 0
				"Ha fallado la conexión a la base de datos. \n\n+ Sistema dice: ", // 1
				"Ha fallado la consulta parametrizada. \n\n+ Sistema dice:", // 2
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
	 * @return the esES
	 */
	public ArrayList<ArrayList<String>> getEsES() {
		return esES;
	}
}
