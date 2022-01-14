// PACKAGE
package hospital.tools.database;

// IMPORTS
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import hospital.languages.Language;
import hospital.tools.Tool;

/**
 * 
 * @author Guillermo González
 * @version 1.0
 */
public class DB {

	private Connection conSQL;
	private Statement staSQL;
	private PreparedStatement preStaSQL;
	private ResultSet resSQL;
	private final String CLASSNAME = this.getClass().getSimpleName();

	/**
	 * 
	 */
	public DB() {
		this.conSQL = null;
		this.staSQL = null;
		this.preStaSQL = null;
		this.resSQL = null;
	}

	/**
	 * 
	 * @param driverMySQL
	 * @param urlDB
	 * @param nameDB
	 * @param userDB
	 * @param passwdDB
	 * @throws ClassNotFoundException
	 */
	public void ConnectMySQL(boolean driverMySQL, String urlDB, String nameDB, String userDB, String passwdDB) {

		try {
			Class.forName(LoadDriverMySQL(driverMySQL));
			this.conSQL = DriverManager.getConnection(urlDB + "/" + nameDB, userDB, passwdDB);
//			Tool.showGUIinfo("Conexión a la base de datos realizada con exito", "MYSQL CONNECT");
		} catch (ClassNotFoundException e) {
			Tool.showConsoleError(Language.getDb_txts(0));
		} catch (SQLException e) {
			Tool.showGUIerror(Language.getDb_txts(1) + "\n" + e.getMessage(), CLASSNAME);
		}
	}

	/**
	 * 
	 * @param choose
	 * @return
	 */
	public String LoadDriverMySQL(boolean choose) {
		return (choose) ? "com.mysql.cj.jdbc.Driver" : "com.mysql.jdbc.Driver";
	}

//	public static void main(String[] args) {
//		Language.selectLanguage(0);
//		new DB().ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "rodot", "");
//	}

	/**
	 * 
	 * @param sql
	 * @param values
	 */
	public void getPreStatement(String sql, ArrayList<Object> values) {

		try {
			this.preStaSQL = this.conSQL.prepareStatement(sql);
			selectDataType(values);
			this.resSQL = this.preStaSQL.executeQuery();
		} catch (SQLException e) {
//			Tool.showGUIerror(Language.getDb_txts(2) + "\n" + e.getMessage(), CLASSNAME);
			Tool.showConsoleError(Language.getDb_txts(2) + "\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public int setPreStatement(String sql, ArrayList<Object> values) {

		int rows = 0;
		
		try {
			this.preStaSQL = this.conSQL.prepareStatement(sql);
			selectDataType(values);
			rows = this.preStaSQL.executeUpdate();
		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
		
		return rows;
	}
	
	/**
	 * 
	 * @param values
	 */
	private void selectDataType(ArrayList<Object> values) {

		try {
			/**
			 * 
			 */
			int pointer = 1;

			for (int i = 0; i < values.size(); i++) {
				switch (values.get(i).getClass().getSimpleName()) {
				case "String":
					preStaSQL.setString(pointer, (String) values.get(i));
					break;
				case "Integer":
					preStaSQL.setInt(pointer, (Integer) values.get(i));
					break;
				case "Boolean":
					preStaSQL.setBoolean(pointer, (Boolean) values.get(i));
					break;
				case "Float":
					preStaSQL.setFloat(pointer, (Float) values.get(i));
					break;
				case "Double":
					preStaSQL.setDouble(pointer, (Double) values.get(i));
					break;
				case "Long":
					preStaSQL.setLong(pointer, (Long) values.get(i));
					break;
				case "LocalDate":
					preStaSQL.setDate(pointer, Date.valueOf((LocalDate) values.get(i)));
				default:
					Tool.showConsoleError(CLASSNAME + " - " + "Switch: Este tipo de dato [ "
							+ values.get(i).getClass().getSimpleName() + " ] no se encuentra.");
					break;
				}
			}

		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}

	}

	/**
	 * 
	 * @param sql
	 */
	public void getStatement(String sql) {
		try {
			this.staSQL = this.conSQL.createStatement();
			this.resSQL = this.staSQL.executeQuery(sql);
		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param sql
	 */
	public int setStatement(String sql) {
		int valuesRows = 0;
		try {
			this.staSQL = this.conSQL.createStatement();
			valuesRows = this.staSQL.executeUpdate(sql);
		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
		
		return valuesRows;
	}
	
	
	/**
	 * 
	 */
	public void closeResultSet() {
		try {

			if (!this.resSQL.isClosed())
				this.resSQL.close();

		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	public void closePreStatement() {
		try {
			
			if (!this.preStaSQL.isClosed())
				this.preStaSQL.close();
		
		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	public void closeStatement() {
		try {
			
			if (!this.staSQL.isClosed())
				this.staSQL.close();
			
		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	public void closeConnection() {
		try {

			if (!this.conSQL.isClosed())
				this.conSQL.close();

		} catch (SQLException e) {
			Tool.showConsoleError(CLASSNAME + " - " + e.getMessage());
		}
	}

	/**
	 * @return the conSQL
	 */
	public Connection getConSQL() {
		return conSQL;
	}

	/**
	 * @return the staSQL
	 */
	public Statement getStaSQL() {
		return staSQL;
	}

	/**
	 * @return the preStaSQL
	 */
	public PreparedStatement getPreStaSQL() {
		return preStaSQL;
	}

	/**
	 * @return the resSQL
	 */
	public ResultSet getResSQL() {
		return resSQL;
	}

	/**
	 * @return the cLASSNAME
	 */
	public String getCLASSNAME() {
		return CLASSNAME;
	}
}
