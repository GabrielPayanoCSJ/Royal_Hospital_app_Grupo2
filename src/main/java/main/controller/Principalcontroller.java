package main.controller;

import javax.swing.JButton;

import main.view.JF_Principal;

public class Principalcontroller {
	private JF_Principal mainWindow;

	public Principalcontroller() {
		mainWindow = new JF_Principal();

		addEvents();

		mainWindow.setVisible(true);
	}

	private void addEvents() {
		for (JButton button : mainWindow.getButtons()) {
			button.addActionListener(new EV_Principal(mainWindow));
		}
		
		mainWindow.getJcLanguages().addActionListener(new EV_Principal(mainWindow));
	}
}
