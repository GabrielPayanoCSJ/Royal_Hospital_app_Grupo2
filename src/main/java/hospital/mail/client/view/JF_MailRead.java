package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.mail.client.view.panels.ReadMail.Pa_Body;
import hospital.mail.client.view.panels.ReadMail.Pa_Description;

/**
 * View for read a mail. Has a panel for the description, for the body and for
 * the exit button (all {@link JPanel}).
 * 
 * @author Jorge Fernández Ruiz
 * @date 15/12/2021
 * @version 1.0
 */
public class JF_MailRead extends JFrame {
	private JPanel panel;
	private Pa_Description descPanel;
	private Pa_Body bodyPanel;

	/**
	 * Constructor.
	 * 
	 * @param txtTitle           of type {@link String}, the view's title.
	 * @param txtDescriptionHead of type {@link String}, the text of the
	 *                           description's head.
	 * @param txtSender          of type {@link String}, the text of sender.
	 * @param txtSubject         of type {@link String}, the text of subject.
	 * @param txtBodyHead        of type {@link String}, the text of the
	 *                           description's head.
	 */
	public JF_MailRead(String txtTitle, String txtDescriptionHead, String txtSender, String txtSubject,
			String txtBodyHead) {
		this.panel = new JPanel();
		this.descPanel = new Pa_Description(txtDescriptionHead, txtSender, txtSubject);
		this.bodyPanel = new Pa_Body(txtBodyHead);
		this.panel.setLayout(new BorderLayout());
		appendPanel();
		this.add(this.panel);
		defaultOperations(txtTitle);
	}

	/**
	 * Method that set properties of the view.
	 */
	private void defaultOperations(String txtTitle) {
		this.setTitle(txtTitle);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setResizable(true);
	}

	/**
	 * Method that append to panel ({@link JPanel}) the descPanel ({@link JPanel})
	 * and the bodyPanel ({@link JPanel}).
	 */
	private void appendPanel() {
		this.panel.add(this.descPanel, BorderLayout.NORTH);
		this.panel.add(this.bodyPanel, BorderLayout.CENTER);
	}

	/**
	 * Getter of description's panel.
	 * 
	 * @return the descPanel of type {@link JPanel}.
	 */
	public Pa_Description getDescPanel() {
		return descPanel;
	}

	/**
	 * Getter of body's panel.
	 * 
	 * @return the bodyPanel of type {@link JPanel}.
	 */
	public Pa_Body getBodyPanel() {
		return bodyPanel;
	}
}
