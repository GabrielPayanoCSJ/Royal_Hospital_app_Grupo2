/**
 * 
 */
package hospital.mail.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import hospital.mail.client.view.JF_MailWrite;
import hospital.mail.server.controller.Utils_Methods;
import hospital.tools.Tool;

/**
 * @author Javier Gómez
 *@version 1.0
 */
public class Ev_WriteMail implements ActionListener {

	JF_MailWrite write;

	JTextField subject;
	JTextField from;
	JTextField to;
	JTextArea body;
	String host = "smtp.gmail.com";
	 String regexPattern ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \r\n" +
	"+ \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
	
	/**
	 * @param write
	 * 
	 */
	public Ev_WriteMail(JF_MailWrite write) {
		this.write = write;
		subject = write.getBody().getTxtIssue();
		from = write.getDesc().getTxFDesc().get(0);
		to = write.getDesc().getTxFDesc().get(1);
		body = write.getBody().getTxA_body();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Pattern.matches(regexPattern,(CharSequence) to)) {
			Utils_Methods.enviaremail(from.getText(), to.getText(), host, body.getText(), subject.getText());
		}else {
			Tool.showGUIerror("Error destinatario", "Fallo en el formato del email destinatario(Asegurate que es formato *@*.*) "); 
		}

	}

}
