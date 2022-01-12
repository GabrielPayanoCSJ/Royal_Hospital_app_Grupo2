package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailWrite;

public class WriteMailController {
	private JF_MailWrite write;

	public WriteMailController(String mail) {
		write = new JF_MailWrite("Send E-mail", "", "test", "Issue", "Message", "From", "To", "Cancel", "Send");
		 
		for (int i = 0; i < write.getButtons().getButtons().size(); i++) {
			write.getButtons().getButtons().get(i).addActionListener(new Ev_WriteMail(write,mail));
		}
		
		write.getDesc().getTxFDesc().get(0).setText(mail);
		write.setVisible(true);
	}

}
