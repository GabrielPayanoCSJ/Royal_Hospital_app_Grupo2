package hospital.mail.client.controller;

import javax.mail.Message;

import hospital.mail.client.view.JF_MailClient;
import hospital.mail.server.controller.Utils_Methods;

/**
 * 
 * @author Gabriel Payano
 * @version 1.0 Date of creation: 11/01/2022
 */

public class MailUpdaterList implements Runnable {

	private Thread thread;
	private MailClientController mailClient;
	private Pipe_MailUpdater pipe;
 
	/**
	 * 
	 * @param mailClient Type MailClientController , mailClientController object to access his properties
	 * @param pipe Type Pipe_MailUpdater , Objetc of a pipe class that defines the methods for this thread.
	 */
	public MailUpdaterList(MailClientController mailClient, Pipe_MailUpdater pipe) {
		this.pipe = pipe;
		this.thread = new Thread(this);
		this.mailClient = mailClient;

	}

	/**
	 * Main running method of the thread.
	 * Function: call the updateMail method in the pipe to refresh the email list every minute.
	 *
	 */
	@Override
	public void run() {
		 while (pipe.isContinueListing()) {	 
		try {
		        Thread.sleep(60000);
		        pipe.updateEmailList();		       
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
	}
}
