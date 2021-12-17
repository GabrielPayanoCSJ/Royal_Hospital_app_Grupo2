package hospital.mail.client.controller;

import java.awt.event.WindowAdapter;

import javax.swing.JButton;

import hospital.mail.client.view.JF_MailLogIn;
import main.controller.EV_Principal;

public class PrincipalControllerMail {

	private JF_MailLogIn login;

	public PrincipalControllerMail() {
		this.login = new JF_MailLogIn("Log-In Mail", "Acceder", "Volver", "Body", "Mail: ", "Contraseña: ");
		this.login.setVisible(true);
	}

	public void LoginListener(JButton boton) {
		for (int i = 0; i < login.getButtonsLogin().size(); i++) {
			login.getButtonsLogin().get(i).addActionListener(new Ev_MainController(login));
		}
	}
}
