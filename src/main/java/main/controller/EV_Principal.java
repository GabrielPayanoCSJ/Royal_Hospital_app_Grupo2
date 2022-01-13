package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.ftp.client.controller.ClientFTP;
import hospital.ftp.server.controller.FTPServer;
import hospital.languages.Language;
import hospital.mail.client.controller.LoginControllerMail;
import hospital.mail.client.controller.Pipe_MailUpdater;
import main.view.JF_Principal;

public class EV_Principal implements ActionListener {
	private JF_Principal view;

	public EV_Principal(JF_Principal view ) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// comboBox
		if (e.getSource() == view.getJcLanguages()) {
			Language.selectLanguage(view.getJcLanguages().getSelectedIndex());
		}
		
		// buttons
		if (view.getButtons().get(0) == e.getSource()) {
			new FTPServer();
		} else if (view.getButtons().get(1) == e.getSource()) {
			new ClientFTP();
		} else if (view.getButtons().get(2) == e.getSource()) {
			new LoginControllerMail();
		}
		
	}

}
