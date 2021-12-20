/**
 * 
 */
package hospital.ftp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import hospital.tools.Tool;
import hospital.tools.database.DB;

/**
 * @author 
 */
public class User {
	private ArrayList<User> users;
	private DB db;
	private int id;
	private String username;
	private String password;
	private String homeDir;
	private int groupID;
	private boolean enable;

	/**
	 * 
	 */
	public User(DB db) {
		this.db = db;
		this.users = getUsersBD();
	}

	public User(int id, String username, String password, String homeDir, int groupID, boolean enable) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.homeDir = homeDir;
		this.groupID = groupID;
		this.enable = enable;
	}

	private ArrayList<User> getUsersBD() {
		this.users = new ArrayList<User>();
		this.db.getStatement("SELECT * FROM GR2_USER");
		ResultSet r = this.db.getResSQL();

		try {
			while (r.next()) {
				this.users.add(new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5), r.getBoolean(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return this.users;
	}

	public boolean searchUser(String username) {

		this.db.getPreStatement("SELECT * FROM GR2_USER WHERE username = ?",
				new ArrayList<Object>(Arrays.asList(username)));
		boolean exist = true;
		try {
			if (!this.db.getResSQL().next()) {
				exist = false;
			}
		} catch (SQLException e) {
			Tool.showGUIinfo("El usuario " + username + " no existe", "ACCESO NO PERMITIDO");
		}

		return exist;
	}

	public int searchUserGroup(String username) {
		
		this.db.getPreStatement("SELECT group_id FROM GR2_USER WHERE username = ?", new ArrayList<Object>(Arrays.asList(username)));
		
		int groupID = -1;
		try {
			if (this.db.getResSQL().next()) {
				groupID = this.db.getResSQL().getInt(1);
			}
		} catch (SQLException e) {
			Tool.showGUIinfo("El usuario " + username + " no tiene grupo", "");
		}
		
		return groupID;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the homeDir
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * @return the groupID
	 */
	public int getGroupID() {
		return groupID;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}
}
