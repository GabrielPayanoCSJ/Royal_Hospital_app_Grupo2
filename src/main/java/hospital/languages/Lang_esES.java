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
				loadLanguageTxts(), // 6
				loadMainTxts(), // 7
				loadMailLoginTxts(), // 8
				loadReadMailTxts(), // 9
				loadWriteMailTxts() // 10
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
		return new ArrayList<String>(Arrays.asList( // LIST MAIL CLIENT TEXTS
				"CLIENTE CORREO", // 0
				"BANDEJA DE ENTRADA", // 1
				"REDACTAR", // 2
				"LEER", // 3
				"SALIR", // 4
				"TOTAL: ", // 5
				"NUEVOS: " // 6
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadReadMailTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST READ MAIL TEXTS
				"LECTURA CORREO", // 0
				"DESCRIPCIÓN", // 1
				"DESDE: ", // 2
				"ASUNTO: ", // 3
				"CUERPO" // 4
		));
	}
	
	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadWriteMailTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST MAIL CLIENT TEXTS
				"REDACCIÓN", // 0
				"DESDE: ", // 1
				"PARA: ", // 2
				"DESCRIPCIÓN", // 3
				"ASUNTO", // 4
				"MENSAJE", // 5
				"ENVIAR" // 6
			));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMailLoginTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST MAIL LOGIN TEXTS
				"VENTANA DE ACCESO", // 0
				"ACCESO DE CORREO", // 1
				"CORREO ELECTRÓNICO", // 2
				"CONSTRASEÑA", // 3
				"ACCEDER", // 4
				"CANCELAR" // 5
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadMainTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST MAIN TEXTS
				"Ventana principal", // 0
				"EJECUTADOR PRINCIPAL", // 1
				"Seleccione un idioma", // 2
				"Servidor FTP", // 3
				"Cliente FTP", // 4
				"Cliente correo" // 5
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
				"CREAR DIRECTORIO", // 0
				"ELIMINAR", // 1
				"", // 2 (not used)
				"DESCARGAR", // 3
				"SALIR", // 4
				"LOG CLIENTE", // 5
				"DIRECTORIO REMOTO", // 6
				"FTP CLIENTE - GRUPO 2", // 7
				"CONECTAR", // 8
				"USUARIO: ", // 9
				"CONTRASEÑA: ", // 10
				"RENOMBRAR", // 11
				"DESCONECTAR", // 12
				"CREAR DIRECTORIO", // 13
				"Indique el nombre del nuevo directorio:", // 14
				"RENOMBRAR FICHERO", // 15
				"Introduzca el nuevo nombre", // 16
				"SELECCIONA UN FICHERO PARA SUBIR", // 17
				"SELECCIONE UNA UBICACIÓN PARA GUARDARLO", // 18
				""));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadFTPServerTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST FTP SERVER TEXTS
				"Ventana Servidor", // 0
				"Finalizar", // 1
				"Empezar" // 2
		));
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> loadErrorTxts() {
		return new ArrayList<String>(Arrays.asList( // LIST ERROR TEXTS & TITLES
				"Clase no encontrada", // 0
				"Información", // 1 (Title)
				"Debe seleccionar un directorio raiz", // 2 (text of 1)
				"Campos del login vacíos", // 3 (title)
				"No se ha introducido ningún usuario", // 4 (text of 3)
				"ERROR - Servidor FTP no encontrado", // 5 (title)
				"Ha sido imposible establecer la conexión con el servidor FTP", // 6 (text of 5)
				"ERROR AL RENOMBRAR", // 7 (title)
				"Ha habido un fallo al realizar la operacion renombrar. \n Sistema dice: ", // 8 (text of 7)
				"Fallo de autentificación", // 9
				"Usuario o contraseña no válidos" // 10
		));
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
