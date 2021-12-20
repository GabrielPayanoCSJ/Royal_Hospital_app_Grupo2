package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailWrite;

public class WriteMailController {
	private JF_MailWrite write;

	public WriteMailController() {
		write = new JF_MailWrite("Title", "Description", "Body", "Issue", "Message", "Sender", "Addressee", "Cancel",
				"Send");
		AddEvents();
		
		write.setVisible(true);
	}

	private void AddEvents() {
		for (int i = 0; i < write.getButtons().getButtons().size(); i++) {
			write.getButtons().getButtons().get(i).addActionListener(new Ev_MailWrite(write));
		}
	}

}
