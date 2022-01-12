package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.mail.Message;
import javax.mail.MessagingException;

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
public class Ev_MailClient implements ActionListener, MouseListener {
	private String mail;
	private JF_MailClient clientView;

	/**
	 * Constructor.
	 * 
	 * @param clientView of type {@link JF_MailClient}.
	 */
	public Ev_MailClient(JF_MailClient clientView, String mail) {
		this.clientView = clientView;
		this.mail = mail;
	}

	// Buttons event
	@Override
	public void actionPerformed(ActionEvent ev) {

		if (ev.getSource().equals(this.clientView.getSidePanel().getButtons().get(0))) {
			new WriteMailController(mail);
//			System.out.println("Write is open");
		} else if (ev.getSource().equals(this.clientView.getSidePanel().getButtons().get(1))) {
			new ReadMailController(getMessage(this.clientView.getInboxPanel().getEmails().getSelectedIndex()));
//				System.out.println("Read is open");
		} else if (ev.getSource().equals(this.clientView.getSidePanel().getButtons().get(2))) {
			this.clientView.dispose();
		}
	}

	private Message getMessage(int selectedIndex) {

		try {
			int msgsLength = Utils_Methods.getFolder().getMessages().length - 1;

			int index = 0;
			if (selectedIndex != 0) {
				index = msgsLength - selectedIndex;
			} else {
				index = msgsLength;
			}

			System.out.println(index);
			return Utils_Methods.getFolder().getMessages()[index];
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return null;
	}

	// Mouse events
	@Override
	public void mouseClicked(MouseEvent event) {

		if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
			new ReadMailController(getMessage(this.clientView.getInboxPanel().getEmails().getSelectedIndex()));
//			System.out.println("Read is open");
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
