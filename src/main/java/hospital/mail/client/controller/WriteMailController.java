package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailWrite;

/**
 * Write mail controller, add the events to all buttons through Ev_MailWrite
 * (listener class).
 * 
 * @author Jorge Fernández Ruiz
 * @date 20/12/2021
 * @version 1.0
 *
 */
public class WriteMailController {
	private JF_MailWrite write;

	/**
	 * Constructor.
	 * 
	 * 
	 */
	//@param language of type {@link Integer}, is the number of language.
	public WriteMailController() {
		// change to language array
		write = new JF_MailWrite("test", "test", "test", "test", "test", "test", "test", "test", "test");
		AddEvents();

		write.setVisible(true);
	}

	/**
	 * Add the event listener for each button that are in Ev_MailWrite (listener
	 * class).
	 */
	private void AddEvents() {
		for (int i = 0; i < write.getButtons().getButtons().size(); i++) {
			write.getButtons().getButtons().get(i).addActionListener(new Ev_MailWrite(write));
		}
	}

}
