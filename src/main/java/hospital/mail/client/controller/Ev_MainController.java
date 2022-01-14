package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.mail.client.view.JF_MailLogIn;

/**
 * @author Javier G�mez
 * @edited Jorge Fern�ndez Ruiz
 */
public class Ev_MainController implements ActionListener {

	private JF_MailLogIn login;
	
	public Ev_MainController(JF_MailLogIn login) {
		this.login = login;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new MailClientController(login);
	}

}
