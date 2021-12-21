package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Message;

import hospital.ftp.client.controller.ClientFTP;
import hospital.mail.client.view.JF_MailClient;
import hospital.mail.server.controller.Utils_Methods;

/**
 * Events class of mail client. fill the list of mails that has received and
 * each button call other controller.
 * 
 * @author Jorge Fernández Ruiz.
 * @date 21/12/2021
 * @version 1.0
 */
public class Ev_MailClient implements ActionListener {
	private JF_MailClient clientView;
	private Utils_Methods aux;

	/**
	 * Constructor.
	 * 
	 * @param clientView of type {@link JF_MailClient}.
	 */
	public Ev_MailClient(JF_MailClient clientView) {
		this.clientView = clientView;
		aux = new Utils_Methods();

		try {
			// connect for read the received mails
			aux.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		fillMails();
		changeCounts();
		
		clientView.setVisible(true);
	}

	/**
	 * Method that fill the mail's list looking for in a folder URL.
	 */
	private void fillMails() {
		try {
			aux.openFolder("INBOX");

			Message msgs[] = aux.getFolder().getMessages();
			clientView.getInboxPanel().getEmailList().clear();

			for (Message message : msgs) {
				clientView.getInboxPanel().appendNewEmail(message.getFrom(), message.getSubject(),
						message.getSentDate());
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
			clientView.getCounterPanel().getNumTotal().setText(aux.getMessageCount() + "");
			clientView.getCounterPanel().getNumUnseen().setText(aux.getNewMessageCount() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method event which calls its corresponding controller.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(clientView.getSidePanel().getButtons().get(0))) {
			new WriteMailController();
		} else if (e.getSource().equals(clientView.getSidePanel().getButtons().get(1))) {
			new ReadMailController(clientView.getInboxPanel());
		} else if (e.getSource().equals(clientView.getSidePanel().getButtons().get(2))) {
			clientView.dispose();
		}
	}

}
