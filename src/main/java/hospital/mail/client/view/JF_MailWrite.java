package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.mail.client.view.panels.WriteMail.Pa_DescWriteMail;
import hospital.mail.client.view.panels.WriteMail.Pa_WriteBody;
import hospital.mail.client.view.panels.WriteMail.Pa_WriteButtons;

/**
 * View for write a mail. Has a panel for the description, for the body and for
 * the buttons (all {@link JPanel}). Has a {@link BorderLayout}.
 * 
 * @author Jorge Fernández Ruiz
 * @date 17/12/2021
 * @version 1.0
 */
public class JF_MailWrite extends JFrame {
	private Pa_DescWriteMail desc;
	private Pa_WriteBody body;
	private Pa_WriteButtons buttons;
	private JPanel panel;

	/**
	 * Constructor.
	 * 
	 * @param txtTitle       of type {@link String}, the text of title.
	 * @param txtHeadDesc    of type {@link String}, the text of description's head.
	 * @param txtHeadBody    of type {@link String}, the text of body's head.
	 * @param txtHeadIssue   of type {@link String}, the text of issue's head.
	 * @param txtHeadMessage of type {@link String}, the text of message's head.
	 * @param txtSender      of type {@link String}, the text of sender.
	 * @param txtAddressee   of type {@link String}, the text of addressee.
	 * @param txtBtnCancel   of type {@link String}, the text of cancel button.
	 * @param txtBtnSend     of type {@link String}, the text of send button.
	 */
	public JF_MailWrite(String txtTitle, String txtHeadDesc, String txtHeadBody, String txtHeadIssue,
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
	 * Method that append the panels to the panel ({@link JPanel})
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Getter for description panel.
	 * 
	 * @return the desc of type {@link Pa_DescWriteMail}.
	 */
	public Pa_DescWriteMail getDesc() {
		return desc;
	}

	/**
	 * Getter for body panel.
	 * 
	 * @return the body of type {@link Pa_WriteBody}.
	 */
	public Pa_WriteBody getBody() {
		return body;
	}

	/**
	 * Getter for buttons panel.
	 * 
	 * @return the buttons of type {@link Pa_WriteButtons}.
	 */
	public Pa_WriteButtons getButtons() {
		return buttons;
	}

}
