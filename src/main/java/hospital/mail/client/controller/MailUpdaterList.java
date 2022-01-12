package hospital.mail.client.controller;

import javax.mail.Message;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.server.controller.Utils_Methods;

/**
 * 
 * @author Gabriel Payano
 * @version 1.0
 * Date of creation: 11/01/2022
 */

public class MailUpdaterList implements Runnable {
	
	private Thread thread;
	private MailClientController mailClient;
	
	public MailUpdaterList(MailClientController mailClient ) {
		 this.thread = new Thread(this);
		 this.mailClient = mailClient;
	}

	@Override
	public void run() {
		/*
			while(true) {
				
				try {
					Thread.sleep(60000);
					System.out.println("------------ACTUALIZO--------");
					Utils_Methods.openFolder("INBOX");
					Message msgs[] = Utils_Methods.getFolder().getMessages();
//					System.out.println(msgs.length);
					mailClient.getClientView().getInboxPanel().getEmailList().clear();

					for (int i = msgs.length - 1; i >= 0; i--) {
						mailClient.getClientView().getInboxPanel().appendNewEmail(msgs[i].getMessageNumber(), msgs[i].getFrom(),
								msgs[i].getSubject(), msgs[i].getSentDate());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			*/
	}

}
