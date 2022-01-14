package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.mail.client.view.JF_MailLogIn;
import hospital.tools.Tool;

/**
 * @author Javier Gï¿½mez
 * @edited Jorge Fernï¿½ndez Ruiz
 */
public class Ev_MainController implements ActionListener {

	private JF_MailLogIn loginView;

	public Ev_MainController(JF_MailLogIn loginView) {
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.loginView.getPaLogin().getButtons().get(0))) {

			if (this.loginView.getPaLogin().getTxtFmail().getText() != ""
					&& Tool.getPass(this.loginView.getPaLogin().getPassPassword().getPassword()) != null) {

				if (Tool.checkEmailFormat(this.loginView.getPaLogin().getTxtFmail().getText())) {
					new MailClientController(this.loginView);
					this.loginView.dispose();
				} else {
					Tool.showGUIerror("No es un correo válido.", "ERROR MAIL");
				}

			} else {
				Tool.showGUIerror("El campo email o contraseña está vacío.", "ERROR CORREO");
			}

		} else {
			this.loginView.dispose();
		}
	}

}
