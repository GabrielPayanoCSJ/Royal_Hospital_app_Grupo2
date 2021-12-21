/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.JF_MailLogIn;

/**
 * @author Javier Gómez
 * @edited Jorge Fernández Ruiz
 */
public class Ev_MainController implements ActionListener {

	private JF_MailClient mailClientView;
	private JF_MailLogIn login;

	/**
	 * @param login
	 * 
	 */
	public Ev_MainController(JF_MailLogIn login) {
		// TODO Auto-generated constructor stub
		this.login = login;
		this.mailClientView = new JF_MailClient("Cliente Correo", "Redactar", "Leer", "FTP", "Salir", "Inbox",
				"Contador mensajes", "No leídos");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < mailClientView.getSidePanel().getButtons().size(); i++) {
			mailClientView.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(mailClientView));
		}
	}

}
