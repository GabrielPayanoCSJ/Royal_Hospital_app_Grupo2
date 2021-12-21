package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.languages.Language;
import hospital.mail.client.view.panels.ReadMail.Pa_Body;
import hospital.mail.client.view.panels.ReadMail.Pa_Description;

/**
 * View for read a mail. Has a panel for the description, for the body and for
 * the exit button (all JPanel).
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
	 * @param txtTitle           of type String, the view's title.
	 * @param txtDescriptionHead of type String, the text of the description's head.
	 * @param txtSender          of type String, the text of sender.
	 * @param txtIssue           of type String, the text of issue.
	 * @param txtBodyHead        of type String, the text of the description's head.
	 */
	public JF_MailRead(String txtTitle, String txtDescriptionHead, String txtSender, String txtIssue,
			String txtBodyHead) {
		panel = new JPanel();
		descPanel = new Pa_Description(txtDescriptionHead, txtSender, txtIssue);
		bodyPanel = new Pa_Body(txtBodyHead);

		panel.setLayout(new BorderLayout());

		appendPanel();

		this.add(panel);

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
	 * Method that append to panel (JPanel) the descPanel (JPanel) and the bodyPanel
	 * (JPanel).
	 */
	private void appendPanel() {
		panel.add(descPanel, BorderLayout.NORTH);
		panel.add(bodyPanel, BorderLayout.CENTER);
	}

	/**
	 * Getter of description's panel.
	 * 
	 * @return the descPanel of type JPanel.
	 */
	public Pa_Description getDescPanel() {
		return descPanel;
	}

	/**
	 * Getter of body's panel.
	 * 
	 * @return the bodyPanel of type JPanel.
	 */
	public Pa_Body getBodyPanel() {
		return bodyPanel;
	}
}
