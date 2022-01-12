package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;

import hospital.ftp.server.controller.FTPServer;
import hospital.mail.client.controller.LoginControllerMail;
import main.view.JF_Principal;

public class EV_Principal implements ActionListener {
	private JF_Principal view;

	public EV_Principal(JF_Principal view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (view.getButtons().get(0) == e.getSource()) {
			// llamar al controlador FTP server
			new FTPServer(view);
			System.out.println("FTP servidor");
		} else if (view.getButtons().get(1) == e.getSource()) {
			// llamar al controlador FTP client
			new FTPClient();
			System.out.println("FTP cliente");
		} else if (view.getButtons().get(2) == e.getSource()) {
			// llamar a new controlador del correo
			new LoginControllerMail();
			System.out.println("Mail");
		}
		
		if (e.getSource() == view.getJcLanguages()) {
			if (view.getJcLanguages().getSelectedItem() == view.getJcLanguages().getItemAt(0)) {
				// idioma inglés
				System.out.println("English");
				
			} else if (view.getJcLanguages().getSelectedItem() == view.getJcLanguages().getItemAt(1)) {
				// idioma español
				System.out.println("Spanish");
			}
		}
	}

}
