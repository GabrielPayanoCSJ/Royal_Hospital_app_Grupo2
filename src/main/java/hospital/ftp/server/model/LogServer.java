/**
 * 
 */
package hospital.ftp.server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import hospital.tools.database.DB;

/**
 * @author Guillermo González de Miguel
 * @version 1.0
 */
public class LogServer {
	private int id;
	private LocalDate event_date;
	private String type_ope;
	private String desc_ope;
	private DB db;
	private ArrayList<LogServer> logs;

	/**
	 * 
	 */
	public LogServer(DB db) {
		this.db = db;
		this.logs = getLogServDB();
	}

	/**
	 * 
	 */
	public LogServer(int id, LocalDate event_date, String type_ope, String desc_ope) {
		this.id = id;
		this.event_date = event_date;
		this.type_ope = type_ope;
		this.desc_ope = desc_ope;
	}

	private ArrayList<LogServer> getLogServDB() {
		this.db.getStatement("SELECT * FROM GR2_INFO_LOG_SERVER");

		ResultSet r = this.db.getResSQL();

		try {
			while (r.next()) {

				logs.add(new LogServer(r.getInt(1), dateFormatter(r.getDate(2).toString()), r.getString(3),
						r.getString(4)));
				System.out.println(dateFormatter(r.getDate(2).toString()).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private LocalDate dateFormatter(String datetime) {
		return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}
