package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.view.JF_Principal;

public class EV_Principal implements ActionListener {
	private JF_Principal view;

	public EV_Principal(JF_Principal view) {
	this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (view.getBtnCorreo()==e.getSource()) {
			//llamar a new controlador del correo
		}else if(view.getBtnFTP()==e.getSource()) {
			//llamar al controlador FTP
		}
		
	}

}
