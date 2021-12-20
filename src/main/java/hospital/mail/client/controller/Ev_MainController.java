/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.NoSuchProviderException;
import javax.swing.JOptionPane;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.JF_MailLogIn;
import hospital.mail.server.controller.Utils_Methods;

/**
 * @author Javier Gómez
 *
 */
public class Ev_MainController implements ActionListener {

	private JF_MailClient principal;
	private JF_MailLogIn login;

	/**
	 * @param login
	 * 
	 */
	public Ev_MainController(JF_MailLogIn login) {
		// TODO Auto-generated constructor stub
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(login.getButtonsLogin().get(0))) {
			try {
				if (Utils_Methods.userauth(login.getTxtFmail().getText(), login.getPassPassword().getText())) {
					MailClientControler principalcontroler = new MailClientControler();
					login.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
			} catch (NoSuchProviderException e) {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			}

		}
	}

}
