package main.controller;

import hospital.languages.Language;
import main.view.JF_Principal;
import main.view.Jf_Main;

public class Principalcontroller {
	private JF_Principal mainWindow;
	private Jf_Main mainView;
	private final int DEFAULT_LANGUAGE= 0;

	public Principalcontroller() {
		Language.selectLanguage(DEFAULT_LANGUAGE);
//		mainWindow = new JF_Principal();
		this.mainView = new Jf_Main("VENTANA PRINCIPAL", "EJECUTADOR PRINCIPAL", "Seleccione un idioma", "SERVIDOR FTP",
				"CLIENTE FTP", "CLIENTE CORREO");
		addEvents();
//		mainWindow.setVisible(true);
		this.mainView.setVisible(true);
	}

	private void addEvents() {
//		for (JButton button : mainWindow.getButtons()) {
//			button.addActionListener(new EV_Principal(mainWindow));
//		}

		for (int i = 0; i < this.mainView.getPaMain().getButtons().size(); i++) {
			this.mainView.getPaMain().getButtons().get(i).addActionListener(new EV_Principal(this.mainView));
		}
		
//		mainWindow.getJcLanguages().addActionListener(new EV_Principal(mainWindow));
		this.mainView.getPaMain().getJcLanguages().addActionListener(new EV_Principal(this.mainView));
	}
}
