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
		// TODO Auto-generated method stub

	}

}
