package hospital.mail.client.controller;

import hospital.languages.Language;
import hospital.mail.client.view.JF_MailClient;

/**
 * Mail client controller, add the events to all buttons through Ev_MailClient
 * (listener class).
 * 
 * @author Jorge Fernández Ruiz
 * @date 20/12/2021
 * @version 1.0
 */
public class MailClientController {
	private JF_MailClient view;

	/**
	 * Constructor.
	 * 
	 * @param language of type {@link Integer}, the language number.
	 */
	public MailClientController(int language) {
		Language.selectLanguage(language);
		// change to language array
		view = new JF_MailClient("test", "test", "test", "test", "test", "test", "test", "test");
		view.getInboxPanel().getEmailList().clear();
		
		addEvents();

		view.setVisible(true);
	}

	/**
	 * Add the event listener for each button that are in Ev_MailClient (listener
	 * class).
	 */
	private void addEvents() {
		for (int i = 0; i < view.getSidePanel().getButtons().size(); i++) {
			view.getSidePanel().getButtons().get(i).addActionListener(new Ev_MailClient(view));
		}
		view.getInboxPanel().getEmails().addMouseListener(new Ev_MailClient(view));
	}

}
