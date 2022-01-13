package main.controller;

import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.languages.Language;
import hospital.tools.database.DB;
import main.view.Jf_Main;

public class Principalcontroller {
	private Jf_Main mainView;
	private final int DEFAULT_LANGUAGE= 0;
	private DB db;
	private User userdb;
	private Group groupdb;
	
	public Principalcontroller() {
		this.db = new DB();
		this.db.ConnectMySQL(true, "jdbc:mysql://localhost:3306", "grupo2_hospitaldb", "root", "");
		this.userdb = new User(db);
		this.groupdb = new Group(db);
		
		Language.selectLanguage(DEFAULT_LANGUAGE);
		
		this.mainView = new Jf_Main("VENTANA PRINCIPAL", "EJECUTADOR PRINCIPAL", "Seleccione un idioma", "SERVIDOR FTP",
				"CLIENTE FTP", "CLIENTE CORREO");
		
		addEvents();
		
		this.mainView.setVisible(true);
	}

	private void addEvents() {

		for (int i = 0; i < this.mainView.getPaMain().getButtons().size(); i++) {
			this.mainView.getPaMain().getButtons().get(i).addActionListener(new EV_Principal(this.mainView, this.db, this.userdb, this.groupdb));
		}
		
		this.mainView.getPaMain().getJcLanguages().addActionListener(new EV_Principal(this.mainView, this.db, this.userdb, this.groupdb));
	}
}
