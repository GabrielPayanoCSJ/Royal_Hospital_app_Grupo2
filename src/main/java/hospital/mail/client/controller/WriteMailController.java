package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailWrite;
import hospital.mail.server.controller.Utils_Methods;

public class WriteMailController {
	private JF_MailWrite write;


	public WriteMailController() {
		write = new JF_MailWrite("test", "test", "test", "test", "test", "test", "test", "test", "test");
		write.getButtons().getButtons().get(1).addActionListener(new Ev_WriteMail(write));
		write.setVisible(true);
		
	}
	
	

}
