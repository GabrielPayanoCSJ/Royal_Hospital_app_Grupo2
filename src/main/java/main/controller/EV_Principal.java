package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.ftp.client.controller.ClientFTP;
import hospital.ftp.server.controller.FTPServer;
import hospital.languages.Language;
import hospital.mail.client.controller.LoginControllerMail;
import main.view.Jf_Main;

public class EV_Principal implements ActionListener {
//	private JF_Principal view;
	private Jf_Main mainView;

//	public EV_Principal(JF_Principal view) {
//		this.view = view;
//	}

	public EV_Principal(Jf_Main mainView) {
		this.mainView = mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		// comboBox
//		if (e.getSource() == view.getJcLanguages()) {
//			Language.selectLanguage(view.getJcLanguages().getSelectedIndex());
//		}
//		
//		// buttons
//		if (view.getButtons().get(0) == e.getSource()) {
//			new FTPServer();
//		} else if (view.getButtons().get(1) == e.getSource()) {
//			new ClientFTP();
//		} else if (view.getButtons().get(2) == e.getSource()) {
//			new LoginControllerMail();
//		}

		// comboBox
		if (e.getSource() == this.mainView.getPaMain().getJcLanguages()) {
			Language.selectLanguage(this.mainView.getPaMain().getJcLanguages().getSelectedIndex());
		}

		// buttons
		if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(0))) {
			new FTPServer();
		} else if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(1))) {
			new ClientFTP();
		} else if (e.getSource().equals(this.mainView.getPaMain().getButtons().get(2))) {
			new LoginControllerMail();
		}

	}

}
