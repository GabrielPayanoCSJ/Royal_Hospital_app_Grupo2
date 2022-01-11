package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailRead;
import hospital.mail.client.view.panels.Client.Pa_Inbox;
import hospital.mail.server.controller.Utils_Methods;

public class ReadMailController {
	private Pa_Inbox pa_Inbox;
	private Utils_Methods aux;
	
	public ReadMailController(Pa_Inbox pa_Inbox, Utils_Methods aux) {
		this.pa_Inbox = pa_Inbox;
		this.aux = aux;
		
		
		
		
		new JF_MailRead("Lectura correo", "CABECERA", "Desde:", "Hacia:", "CUERPO").setVisible(true);
		
	}
}
