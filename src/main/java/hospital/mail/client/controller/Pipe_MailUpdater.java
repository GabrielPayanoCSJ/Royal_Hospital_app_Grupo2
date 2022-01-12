package hospital.mail.client.controller;

import javax.mail.Message;

import hospital.mail.server.controller.Utils_Methods;

public class Pipe_MailUpdater {
	
	private MailClientController mailClient;
	private boolean continueListing = true;
	
	public Pipe_MailUpdater(MailClientController mailClient) {
		 this.mailClient = mailClient;
	}

	public boolean isContinueListing() {
		return continueListing;
	}

	public void setContinueListing(boolean continueListing) {
		this.continueListing = continueListing;
	}
	
	public void updateEmailList() {
		try {
			System.out.println("------------ACTUALIZO--------");
			Utils_Methods.openFolder("INBOX");
			Message msgs[] = Utils_Methods.getFolder().getMessages();
//			System.out.println(msgs.length);
			mailClient.getClientView().getInboxPanel().getEmailList().clear();

			for (int i = msgs.length - 1; i >= 0; i--) {
				mailClient.getClientView().getInboxPanel().appendNewEmail(msgs[i].getMessageNumber(), msgs[i].getFrom(),
						msgs[i].getSubject(), msgs[i].getSentDate());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
