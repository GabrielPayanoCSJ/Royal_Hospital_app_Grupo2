package hospital.ftp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import hospital.tools.database.DB;

/**
 * 
 * @author prodi
 *
 */
public class Group {

	private int id;
	private String groupname;
	private String homeDir;
	private boolean write_priv;
	private boolean read_priv;
	private boolean execute_priv;
	private boolean enable;
	private DB db;
	private ArrayList<Group> groups;

	public Group(DB db) {
		this.db = db;
		this.groups = getGroupsBD();
	}

	public Group(int id, String groupname, String homeDir, boolean write_priv, boolean read_priv, boolean execute_priv,
			boolean enable) {
		this.id = id;
		this.groupname = groupname;
		this.homeDir = homeDir;
		this.write_priv = write_priv;
		this.read_priv = read_priv;
		this.execute_priv = execute_priv;
		this.enable = enable;
	}


	private ArrayList<Group> getGroupsBD() {
		this.groups = new ArrayList<Group>();
		this.db.getStatement("SELECT * FROM GR2_GROUP");
		ResultSet r = this.db.getResSQL();

		try {
			while (r.next()) {
				this.groups.add(new Group(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getBoolean(5),
						r.getBoolean(6), r.getBoolean(7)));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}

		return this.groups;
	}

	public int searchGroupID(String groupname) {
		this.db.getPreStatement("SELECT id FROM GR2_GROUP WHERE groupname = ?",
				new ArrayList<Object>(Arrays.asList(groupname)));

		try {
			if (this.db.getResSQL().next()) {
				return this.db.getResSQL().getInt(1);
			}
		} catch (SQLException e) {
		}

		return -1;
	}

	public boolean checkWritePriv(int groupID) {
		this.db.getPreStatement("SELECT write_priv FROM GR2_GROUP WHERE id = ?",
				new ArrayList<Object>(Arrays.asList(groupID)));
			try {
				if (this.db.getResSQL().next()) {
					return this.db.getResSQL().getBoolean(1);
				}
			} catch (SQLException e) {
			}
		
		return false;
	}

	public boolean checkReadPriv(int groupID) {
		this.db.getPreStatement("SELECT read_priv FROM GR2_GROUP WHERE id = ?",
				new ArrayList<Object>(Arrays.asList(groupID)));
		try {
			if (this.db.getResSQL().next()) {
				return this.db.getResSQL().getBoolean(1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		return false;
	}

	public boolean checkExecutePriv(int groupID) {
		this.db.getPreStatement("SELECT execute_priv FROM GR2_GROUP WHERE id = ?",
				new ArrayList<Object>(Arrays.asList(groupID)));
			
		try {
			if (this.db.getResSQL().next()) {
					return this.db.getResSQL().getBoolean(1);
			}
		} catch (SQLException e) {
		}
		
		return false;
	}

	public String searchGroupname(int id) {
		this.db.getPreStatement("SELECT groupname FROM GR2_GROUP WHERE id = ?",
				new ArrayList<Object>(Arrays.asList(id)));

		try {
			if (this.db.getResSQL().next()) {
				return this.db.getResSQL().getString(1);
			}
		} catch (SQLException e) {
		}

		return null;
	}

	public boolean isEnable(int id) {
		this.db.getPreStatement("SELECT enable FROM GR2_GROUP WHERE id = ?", new ArrayList<Object>(Arrays.asList(id)));

		try {
			if (this.db.getResSQL().next()) {
				return this.db.getResSQL().getBoolean(1);
			}
		} catch (SQLException e) {
		}

		return false;
	}

	/**
	 * @return the groups
	 */
	public ArrayList<Group> getGroups() {
		return groups;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @return the homeDir
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * @return the write_priv
	 */
	public boolean isWrite_priv() {
		return write_priv;
	}

	/**
	 * @return the read_priv
	 */
	public boolean isRead_priv() {
		return read_priv;
	}

	/**
	 * @return the execute_priv
	 */
	public boolean isExecute_priv() {
		return execute_priv;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

}
