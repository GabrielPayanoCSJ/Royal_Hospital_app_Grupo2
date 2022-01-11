/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import hospital.mail.client.view.JF_MailLogIn;
import hospital.mail.client.view.JF_MailWrite;
import hospital.mail.server.controller.Utils_Methods;
import hospital.tools.Tool;

/**
 * @author Javier G�mez
 *@version 1.0
 */
public class Ev_WriteMail implements ActionListener {

	JF_MailWrite write;
	JF_MailLogIn log;

	JTextField subject;
	String from;
	JTextField to;
	JTextArea body;
	String host = "smtp.gmail.com";

	
	/**
	 * @param write
	 * 
	 */
	public Ev_WriteMail(JF_MailWrite write) {
		this.write = write;
		subject = write.getBody().getTxtIssue();
		from = log.getTxtFmail().getText();
		to = write.getDesc().getTxFDesc().get(1);
		body = write.getBody().getTxA_body();
	}

	/**Event handler for the button send E-mail
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Tool.checkEmail(to.getText())) {
			Utils_Methods.enviaremail(from, to.getText(), host, body.getText(), subject.getText());
			write.dispose();
		}else {
			Tool.showGUIerror("Error destinatario", "Fallo en el formato del email destinatario(Asegurate que es formato *@*.*) "); 
		}

	}

}