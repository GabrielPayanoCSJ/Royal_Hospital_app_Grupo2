package main.controller;

import main.view.JF_Principal;

public class Principalcontroller {

	public Principalcontroller() {
		JF_Principal mainWindow = new JF_Principal();
		mainWindow.getBtnCorreo().addActionListener(new EV_Principal(mainWindow));
		mainWindow.getBtnFTP().addActionListener(new EV_Principal(mainWindow));
	}
}
