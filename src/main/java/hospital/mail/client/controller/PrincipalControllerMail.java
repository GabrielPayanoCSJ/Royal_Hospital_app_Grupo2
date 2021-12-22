package hospital.mail.client.controller;

import javax.swing.JButton;
import hospital.mail.client.view.JF_MailLogIn;

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
