package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailLogIn;

/**
 * Login mail controller, add the events to all buttons through
 * Ev_MainController (listener class).
 * 
 * @author Javier Gómez
 * @date 20/12/2021
 * @editor Jorge Fernández Ruiz
 * @editDate 20/12/2021
 * @version 1.1
 */
public class LoginControllerMail {

	private JF_MailLogIn login;

	public LoginControllerMail() {
		this.login = new JF_MailLogIn("Log-In Mail", "Acceder", "Salir", "Body", "Mail: ", "Contraseña: ");
		loginListener();

		this.login.setVisible(true);
	}

	/**
	 * 
	 */
	private void loginListener() {
		for (int i = 0; i < login.getButtonsLogin().size(); i++) {
			login.getButtonsLogin().get(i).addActionListener(new Ev_MainController(this.login.getTxtFmail().getText()));
		}
	}

	public static void main(String[] args) {
		new LoginControllerMail();
	}
}
