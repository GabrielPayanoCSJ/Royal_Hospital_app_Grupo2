// PACKAGE
package hospital.ftp.model;

// IMPORTS
import java.sql.*;
import java.time.*;
import java.util.*;
import hospital.tools.database.DB;

/**
 * @author Guillermo González de Miguel
 * @version 1.0
 */
public class Log {
	private DB db;
	private ArrayList<Log> logs;
	private int id;
	private int userID;
	private int groupID;
	private String type_ope;
	private String description;
	private LocalDate event_date;

	/**
	 * 
	 * @param db
	 */
	public Log(DB db) {
		this.db = db;
		this.logs = getLogDB();
	}

	/**
	 * 
	 * @param id
	 * @param userID
	 * @param groupID
	 * @param type_ope
	 * @param description
	 * @param event_date
	 */
	public Log(int id, int userID, int groupID, String type_ope, String description, LocalDate event_date) {
		this.id = id;
		this.userID = userID;
		this.groupID = groupID;
		this.type_ope = type_ope;
		this.description = description;
		this.event_date = event_date;
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<Log> getLogDB() {
		this.db.getStatement("SELECT * FROM GR2_INFO_LOG");
		ResultSet r = this.db.getResSQL();
		try {
			while (r.next()) {
				this.logs.add(new Log(r.getInt(1), r.getInt(2), r.getInt(3), r.getString(4), r.getString(5),
						r.getDate(6).toLocalDate()));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}

		return this.logs;
	}

	/**
	 * @return the logs
	 */
	public ArrayList<Log> getLogs() {
		return logs;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the groupID
	 */
	public int getGroupID() {
		return groupID;
	}

	/**
	 * @return the type_ope
	 */
	public String getType_ope() {
		return type_ope;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the event_date
	 */
	public LocalDate getEvent_date() {
		return event_date;
	}

}
