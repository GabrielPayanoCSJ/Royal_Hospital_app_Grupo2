/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.JF_MailLogIn;
import hospital.mail.server.controller.Utils_Methods;
import hospital.tools.Tool;

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
	public void actionPerformed(ActionEvent ev) {
		// JButton buttonLogin = (JButton) ev.getSource();

		try {
			String passwdLogin = "";
			for (int i = 0; i < this.login.getPassPassword().getPassword().length; i++) {
				passwdLogin += this.login.getPassPassword().getPassword()[i];
			}
			System.out.println(passwdLogin);
			if (Utils_Methods.userauth(login.getTxtFmail().getText(), passwdLogin)) {
				//new MailClientControler();
				Tool.showConsoleMessage("Se ha conectado", true);
				login.setVisible(false);
			} else {
				Tool.showGUIerror("Usuario o contraseña incorrectos", "Error de Login");
			}
		} catch (NoSuchProviderException e) {
			Tool.showGUIerror("Usuario o contraseña incorrectos", "Error de Login");
		} catch (MessagingException e) {
			System.out.println("Wow");
			e.printStackTrace();
		}
	}

}
