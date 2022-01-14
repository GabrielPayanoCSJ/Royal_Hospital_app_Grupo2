package hospital.mail.client.controller;

import hospital.languages.Language;
import hospital.mail.client.view.JF_MailLogIn;

/**
 * Login mail controller, add the events to all buttons through
 * Ev_MainController (listener class).
 * 
 * @author Javier G�mez
 * @date 20/12/2021
 * @editor Jorge Fern�ndez Ruiz
 * @editDate 20/12/2021
 * @version 1.1
 */
public class LoginControllerMail {

	private JF_MailLogIn login;

	public LoginControllerMail() {
		this.login = new JF_MailLogIn(Language.getMailLogin_txts().get(0), Language.getMailLogin_txts().get(1), Language.getMailLogin_txts().get(2), Language.getMailLogin_txts().get(3), Language.getMailLogin_txts().get(4), Language.getMailLogin_txts().get(5));
		loginListener();
		this.login.setVisible(true);
	}

	/**
	 * 
	 */
	private void loginListener() {
		for (int i = 0; i < login.getPaLogin().getButtons().size(); i++) {
			login.getPaLogin().getButtons().get(i).addActionListener(new Ev_MainController(login));
		}
	}

}
