package hospital.mail.client.controller;

import java.util.Iterator;

import javax.mail.Message;

import hospital.languages.Language;
import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.JF_MailLogIn;
import hospital.mail.server.controller.Utils_Methods;

/**
 * Mail client controller, add the events to all buttons through Ev_MailClient
 * (listener class).
 * 
 * @author Jorge Fernández Ruiz
 * @date 20/12/2021
 * @version 1.0
 */
public class MailClientController {
	private JF_MailClient clientView;
	private JF_MailLogIn loginView;

	/**
	 * Constructor.
	 * 
	 * @param language of type {@link Integer}, the language number.
	 */
	public MailClientController(JF_MailLogIn loginView) {
		this.loginView = loginView;

		String pass = "";
		String user = this.loginView.getPaLogin().getTxtFmail().getText();
		for (int i = 0; i < this.loginView.getPaLogin().getPassPassword().getPassword().length; i++) {
			pass += this.loginView.getPaLogin().getPassPassword().getPassword()[i];
		}

		try {
//			if (Utils_Methods.userauth(user, pass)) {
			Utils_Methods.connect(user, pass);

			// change to language array
			this.clientView = new JF_MailClient("title", "write", "read", "exit", "test", "test", "test");
			addEvents();
			fillMails();
			changeCounts();
			this.clientView.setVisible(true);
//			} else {
//				Tool.showGUIerror("Contraseña o usuario no válidos", "Fallo de autentificación");
//				loginView.getTxtFmail().setText("");
//				loginView.getPassPassword().setText("");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add the event listener for each button that are in {@link Ev_MailClient}
	 * (listener class) and a mouse listener to email list for read them (.
	 */
	private void addEvents() {

		for (int i = 0; i < this.clientView.getSidePanel().getButtons().size(); i++) {
			this.clientView.getSidePanel().getButtons().get(i).addActionListener(
					new Ev_MailClient(this.clientView, this.loginView.getPaLogin().getTxtFmail().getText()));
		}

		this.clientView.getInboxPanel().getEmails().addMouseListener(
				new Ev_MailClient(this.clientView, this.loginView.getPaLogin().getTxtFmail().getText()));
	}

	/**
	 * Method that fill the mail's list looking for in a folder URL.
	 */
	private void fillMails() {
		try {
			Utils_Methods.openFolder("INBOX");
			Message msgs[] = Utils_Methods.getFolder().getMessages();
//			System.out.println(msgs.length);
			this.clientView.getInboxPanel().getEmailList().clear();

			for (int i = msgs.length - 1; i >= 0; i--) {
				this.clientView.getInboxPanel().appendNewEmail(msgs[i].getMessageNumber(), msgs[i].getFrom(),
						msgs[i].getSubject(), msgs[i].getSentDate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that write the values of totals count.
	 */
	private void changeCounts() {
		try {
			this.clientView.getCounterPanel().getNumTotal().setText(Utils_Methods.getMessageCount() + "");
			this.clientView.getCounterPanel().getNumUnseen().setText(Utils_Methods.getNewMessageCount() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
