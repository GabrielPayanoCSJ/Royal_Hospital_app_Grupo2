package main.controller;

import java.sql.SQLException;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.languages.Language;
import hospital.tools.Tool;
import hospital.tools.database.DB;
import main.view.Jf_Main;

public class Principalcontroller {
	private Jf_Main mainView;
	private final int DEFAULT_LANGUAGE = 0;
	private DB db;
	private User userdb;
	private Group groupdb;

	public Principalcontroller() {
		Language.selectLanguage(DEFAULT_LANGUAGE);
		this.db = new DB();
		this.mainView = new Jf_Main(Language.getMain_txts().get(0), Language.getMain_txts().get(1), Language.getMain_txts().get(2), Language.getMain_txts().get(3),
				Language.getMain_txts().get(4), Language.getMain_txts().get(5));

		try {

			this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
			this.userdb = new User(db);
			this.groupdb = new Group(db);

		} catch (ClassNotFoundException e) {
			Tool.showConsoleError(Language.getDb_txts(0));
			this.mainView.getPaMain().getButtons().get(0).setEnabled(false);
		} catch (SQLException e) {
			this.mainView.getPaMain().getButtons().get(0).setEnabled(false);
			Tool.showGUIerror(Language.getDb_txts(1) + "\n" + e.getMessage(), this.getClass().getSimpleName());
		}

		addEvents();

		this.mainView.setVisible(true);
	}

	private void addEvents() {

		for (int i = 0; i < this.mainView.getPaMain().getButtons().size(); i++) {
			this.mainView.getPaMain().getButtons().get(i)
					.addActionListener(new EV_Principal(this.mainView, this.db, this.userdb, this.groupdb));
		}

		this.mainView.getPaMain().getJcLanguages()
				.addActionListener(new EV_Principal(this.mainView, this.db, this.userdb, this.groupdb));
	}
}
