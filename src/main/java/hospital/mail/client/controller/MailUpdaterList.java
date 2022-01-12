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
	private Pipe_MailUpdater pipe;
	
	public MailUpdaterList(Pipe_MailUpdater pipe ) {
		 this.pipe = pipe;
		 this.thread = new Thread(this);
	}

	@Override
	public void run() {	
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeEEEEEEEEEEEE");
			while(pipe.isContinueListing()) {			
				try {
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					Thread.sleep(60000);
					this.pipe.updateEmailList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

}
