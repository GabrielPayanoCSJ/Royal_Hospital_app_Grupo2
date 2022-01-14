package hospital.mail.client.controller;

import javax.mail.Message;
import javax.swing.JFrame;

import hospital.mail.server.controller.Utils_Methods;

/**
 * 
 * @author Gabriel Payano
 * @date 12/01/2022
 * @version 1.2
 *
 */
public class Pipe_MailUpdater {

	private MailClientController mailClient;
	private boolean continueListing = true;

	/**
	 * Constructor of the class
	 * 
	 * @param mailClient type MailClientController , object of the class
	 *                   MailClientController to access to the mailClient property
	 */
	public Pipe_MailUpdater(MailClientController mailClient) {
		this.mailClient = mailClient;
	}

	/**
	 * 
	 * @return type boolean , returns the value of the continueList variable
	 */
	public boolean isContinueListing() {
		return continueListing;
	}

	/**
	 * 
	 * @param continueListing type boolean , receive the continueList variable new
	 *                        value.
	 */
	public void setContinueListing(boolean continueListing) {
		this.continueListing = continueListing;
	}

	/**
	 * Refresh the email list on the email client
	 */
	public void updateEmailList() {
		try {
			// System.out.println("------------ACTUALIZO--------");
			Utils_Methods.openFolder("INBOX");
			Message msgs[] = Utils_Methods.getFolder().getMessages();
			mailClient.getClientView().getInboxPanel().getEmailList().clear();

			for (int i = msgs.length - 1; i >= 0; i--) {
				mailClient.getClientView().getInboxPanel().appendNewEmail(msgs[i].getMessageNumber(), msgs[i].getFrom(),
						msgs[i].getSubject(), msgs[i].getSentDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop the updaterMailList thread by changing the value of the continueListing variable.
	 */
	public void stopThread() {
		// System.out.println("ME PARO ");
		continueListing = false;
		// return JFrame.DISPOSE_ON_CLOSE; // = 2
	}

}
