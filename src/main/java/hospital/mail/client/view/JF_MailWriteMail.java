package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.mail.client.view.panels.WriteMail.Pa_DescWriteMail;
import hospital.mail.client.view.panels.WriteMail.Pa_WriteBody;
import hospital.mail.client.view.panels.WriteMail.Pa_WriteButtons;

/**
 * View for write a mail. Has a panel for the description, for the body and for
 * the buttons (all JPanel). Has a BorderLayout.
 * 
 * @author Jorge Fern�ndez Ruiz
 * @date 17/12/2021
 * @version 1.0
 */
public class JF_MailWriteMail extends JFrame {
	private Pa_DescWriteMail desc;
	private Pa_WriteBody body;
	private Pa_WriteButtons buttons;
	private JPanel panel;

	/**
	 * Constructor.
	 * 
	 * @param txtTitle       of type String, the text of title.
	 * @param txtHeadDesc    of type String, the text of description's head.
	 * @param txtHeadBody    of type String, the text of body's head.
	 * @param txtHeadIssue   of type String, the text of issue's head.
	 * @param txtHeadMessage of type String, the text of message's head.
	 * @param txtSender      of type String, the text of sender.
	 * @param txtAddressee   of type String, the text of addressee.
	 * @param txtBtnCancel   of type String, the text of cancel button.
	 * @param txtBtnSend     of type String, the text of send button.
	 */
	public JF_MailWriteMail(String txtTitle, String txtHeadDesc, String txtHeadBody, String txtHeadIssue,
			String txtHeadMessage, String txtSender, String txtAddressee, String txtBtnCancel, String txtBtnSend) {
		// create panels
		panel = new JPanel();
		desc = new Pa_DescWriteMail(txtHeadDesc, txtSender, txtAddressee);
		body = new Pa_WriteBody(txtHeadBody, txtHeadIssue, txtHeadMessage);
		buttons = new Pa_WriteButtons(txtBtnCancel, txtBtnSend);

		panel.setLayout(new BorderLayout());

		appendPanel();

		this.add(panel);

		defaultOperations(txtTitle);
	}

	/**
	 * Method that append the panels to the panel (JPanel)
	 */
	private void appendPanel() {
		panel.add(desc, BorderLayout.NORTH);
		panel.add(body, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);

	}

	/**
	 * Method that set properties of the view.
	 */
	private void defaultOperations(String txtTitle) {
		this.setTitle(txtTitle);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Getter for description panel.
	 * 
	 * @return the desc of type Pa_DescWriteMail.
	 */
	public Pa_DescWriteMail getDesc() {
		return desc;
	}

	/**
	 * Getter for body panel.
	 * 
	 * @return the body of type Pa_WriteBody.
	 */
	public Pa_WriteBody getBody() {
		return body;
	}

	/**
	 * Getter for buttons panel.
	 * 
	 * @return the buttons of type Pa_WriteButtons.
	 */
	public Pa_WriteButtons getButtons() {
		return buttons;
	}

}