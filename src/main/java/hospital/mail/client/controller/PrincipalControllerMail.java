package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailLogIn;

public class PrincipalControllerMail {

	private JF_MailLogIn login;

	public PrincipalControllerMail() {
		this.login = new JF_MailLogIn("Log-In Mail", "Acceder", "Volver", "Body", "Mail: ", "Contraseña: ");
		this.login.setVisible(true);
		
		for (int i = 0; i < login.getButtonsLogin().size(); i++) {
			login.getButtonsLogin().get(i).addActionListener(new Ev_MainController(login));
		}
	}

//	public void LoginListener(JButton boton) {
//
//	}
	public static void main(String[] args) {
		new PrincipalControllerMail();
	}
}
