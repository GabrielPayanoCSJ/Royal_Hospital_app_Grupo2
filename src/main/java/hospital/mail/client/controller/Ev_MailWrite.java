/**
 * 
 */
package hospital.mail.client.controller;

import hospital.mail.client.view.JF_MailWrite;
import hospital.mail.server.controller.Utils_Methods;

/**
 * @author prodi
 *
 */
public class Ev_MailWrite {

	/**
	 * 
	 */
	public Ev_MailWrite( JF_MailWrite escribir) {
		Utils_Methods.enviaremail(escribir.getDesc().getTxFDesc().get(0).toString(), escribir.getDesc().getTxFDesc().get(1).toString(), "smtp.gmail.com", escribir.getBody().getTxA_body().toString(), escribir.getBody().getTxtIssue().toString());
	}

}
