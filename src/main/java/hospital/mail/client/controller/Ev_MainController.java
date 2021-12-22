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
<<<<<<< HEAD
		// TODO Auto-generated constructor stub
		this.login = login;

=======
	// TODO Auto-generated constructor stub
//		this.login = login;
//		this.principal = new JF_MailClient("Cliente Correo", "Redactar", "Leer", "FTP", "Salir", "Inbox",
//				"Contador mensajes", "No leídos");
>>>>>>> b061fbeb3809ad660653c649203f7b04e37ed007
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
//		for (int i = 0; i < mailClientView.getSidePanel().getButtons().size(); i++) {
//			mailClientView.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(mailClientView));
//		}
//		mailClientView.getInboxPanel().getEmails().addMouseListener(new Ev_MailClient(mailClientView));
		new MailClientController(0);
=======
		for (int i = 0; i < principal.getSidePanel().getButtons().size(); i++) {
			principal.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(principal));
		}
>>>>>>> b061fbeb3809ad660653c649203f7b04e37ed007
	}

}
