package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.ftp.client.controller.ClientFTP;
import hospital.ftp.model.Group;
import hospital.ftp.model.User;
import hospital.ftp.server.controller.ViewController;
import hospital.languages.Language;
import hospital.mail.client.controller.LoginControllerMail;
import hospital.tools.database.DB;
import main.view.Jf_Main;

public class EV_Principal implements ActionListener {
	private Jf_Main mainView;
	private DB db;
	private User userdb;
	private Group groupdb;

	public EV_Principal(Jf_Main mainView, DB db, User userdb, Group groupdb) {
		this.mainView = mainView;
		this.db = db;
		this.userdb = userdb;
		this.groupdb = groupdb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// comboBox
		if (e.getSource() == this.mainView.getPaMain().getJcLanguages()) {
			Language.selectLanguage(this.mainView.getPaMain().getJcLanguages().getSelectedIndex());
			System.out.println(this.mainView.getPaMain().getJcLanguages().getSelectedIndex());
		}

		// buttons
		if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(0))) {
//			new FTPServer(this.db, this.userdb, this.groupdb);
			new ViewController(this.db, this.userdb, this.groupdb);
		} else if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(1))) {
			new ClientFTP(this.db, this.userdb, this.groupdb);
		} else if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(2))) {
			new LoginControllerMail();
		}
	}
}
