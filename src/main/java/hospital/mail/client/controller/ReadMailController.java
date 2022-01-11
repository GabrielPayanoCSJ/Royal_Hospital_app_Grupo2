package hospital.mail.client.controller;

import javax.mail.Message;

import hospital.mail.client.view.JF_MailRead;
import hospital.mail.server.controller.Utils_Methods;

public class ReadMailController {
	private JF_MailRead viMailRead;
	
	public ReadMailController(Message msgs) {
		viMailRead = new JF_MailRead("Lectura correo", "CABECERA", "Desde", "Asunto", "CUERPO");
		
		try {
			viMailRead.getDescPanel().getTags().get(1).setText(msgs.getFrom()[0].toString());
			viMailRead.getDescPanel().getTags().get(3).setText(msgs.getSubject().toString());
			viMailRead.getBodyPanel().getTxA_body().setText(Utils_Methods.getTextFromMessage(msgs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		viMailRead.setVisible(true);
		
	}
}
