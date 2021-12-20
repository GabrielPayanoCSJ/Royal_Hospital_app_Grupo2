package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hospital.mail.client.view.JF_MailClient;

public class Ev_MailClient implements ActionListener {

	private JF_MailClient principal;

	public Ev_MailClient(JF_MailClient principal) {
		this.principal = principal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(principal.getSidePanel().getButtons().get(0))) {
			WriteMailController writeC = new WriteMailController();
		} else if (e.getSource().equals(principal.getSidePanel().getButtons().get(1))) {
			ReadMailController read = new ReadMailController();
		} else if (e.getSource().equals(principal.getSidePanel().getButtons().get(2))) {
			// change to ftp
		} else if (e.getSource().equals(principal.getSidePanel().getButtons().get(3))) {
			principal.dispose();
		}
	}

}
