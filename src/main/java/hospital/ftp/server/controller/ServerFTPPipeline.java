/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.SQLException;

import hospital.ftp.model.User;
import hospital.tools.database.DB;

/**
 * Pipe class.
 * 
 * @author prodi
 *
 */
public class ServerFTPPipeline {
	private static boolean life = true;
	private String nomUser = "";
	private String prevLog = "";

	/**
	 * Stop the ServerFTPThread by changing the value of the life variable.
	 * @param exit 
	 */
	public synchronized void stopThread(boolean exit) {
		// System.out.println("ME PARO ");
		life = exit;
	}
	
	public synchronized void writeLogDB(DataInputStream dataInput, DB db, User userDB, boolean exit) {
		while (exit) {
			try {
				nomUser = dataInput.readUTF();
				String log = dataInput.readUTF();
				if (log.equals(prevLog)) {
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println("Server thread wait");
//						e.printStackTrace();
					}
				} else {
					System.out.println("------------- Entra en el else ----------------");

					System.out.println("------------" + log.split(" ¬ ")[0] + "----------------");
					if (log.split(" ¬ ")[0].equals("CRT")) {
						insertDBLog(db, userDB, log.split(" ¬ ")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split(" ¬ ")[0].equals("DLT")) {
						insertDBLog(db, userDB, log.split(" ¬ ")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split(" ¬ ")[0].equals("RNM")) {
						insertDBLog(db, userDB, log.split(" ¬ ")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split(" ¬ ")[0].equals("DWL")) {
						insertDBLog(db, userDB, log.split(" ¬ ")[0], log.split("¬")[1], log.split("¬")[2]);
					} else if (log.split(" ¬ ")[0].equals("UPL")) {
						insertDBLog(db, userDB, log.split(" ¬ ")[0], log.split("¬")[1], log.split("¬")[2]);
					}

					prevLog = log;
					this.notifyAll();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void insertDBLog(DB db, User userDB, String operation, String description, String date) {
		System.out.println("Entra al metodo ---------------------");
		System.out.println(nomUser + "////////////");
		if (userDB.searchUser(nomUser)) {
			try {
				db.setStatement(
						"INSERT INTO gr2_info_log (user_id, group_id, type_ope, description_ope) VALUES ('"
								+ db.getResSQL().getInt(1) + "', '" + db.getResSQL().getInt(5) + "', '" + operation
								+ "', '" + description + "');");
//				System.out.println(operation + " - " + description + " - " + Date.);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the life
	 */
	public boolean isLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(boolean life) {
		this.life = life;
	}

}
