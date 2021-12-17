/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.JF_MailLogIn;

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
		this.principal = new JF_MailClient("Cliente Correo", "Redactar", "Leer", "FTP", "Salir", "Inbox",
				"Contador mensajes", "No leídos");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < principal.getSidePanel().getButtons().size(); i++) {
				principal.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(principal));
			}
}

}
