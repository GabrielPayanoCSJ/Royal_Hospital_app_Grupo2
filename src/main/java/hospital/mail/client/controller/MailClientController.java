package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailClient;

public class MailClientController {
	private JF_MailClient view;

	public MailClientController() {
		view = new JF_MailClient("title", "Write", "Read", "Go to FTP", "Exit", "Inbox", "Total", "Unseen");
		addEvents();

		view.setVisible(true);
	}

	private void addEvents() {
		for (int i = 0; i < view.getSidePanel().getButtons().size(); i++) {
			view.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(view));
		}
	}

}
