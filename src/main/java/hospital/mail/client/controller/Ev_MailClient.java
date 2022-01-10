package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.mail.Message;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.client.view.panels.Client.Pa_Inbox;
import hospital.mail.server.controller.Utils_Methods;

/**
 * Events class of mail client. fill the list of mails that has received and
 * each button call other controller.
 * 
 * @author Jorge Fernández Ruiz.
 * @date 21/12/2021
 * @version 1.0
 */
public class Ev_MailClient implements ActionListener, MouseListener {
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

			for (int i = msgs.length - 1; i >= 0; i--) {
				clientView.getInboxPanel().appendNewEmail(msgs[i].getFrom(), msgs[i].getSubject(),
						msgs[i].getSentDate());

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

	// Buttons event
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(clientView.getSidePanel().getButtons().get(0))) {
			new WriteMailController();
			System.out.println("Write is open");
		} else if (e.getSource().equals(clientView.getSidePanel().getButtons().get(1))) {
			new ReadMailController(clientView.getInboxPanel(), aux);
			System.out.println("Read is open");
			
		} else if (e.getSource().equals(clientView.getSidePanel().getButtons().get(2))) {
			clientView.dispose();
		}
	}

	// Mouse events
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
			new ReadMailController(clientView.getInboxPanel());
			System.out.println("Read is open");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Not implemented

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not implemented

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not implemented

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not implemented

	}

}
