package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Javier G�mez
 * @edited Jorge Fern�ndez Ruiz
 */
public class Ev_MainController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new MailClientController(0);
	}

}
