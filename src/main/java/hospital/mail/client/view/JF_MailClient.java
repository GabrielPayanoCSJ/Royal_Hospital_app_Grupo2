package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.*;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.languages.Language;
import hospital.mail.client.view.panels.Client.Pa_Counter;
import hospital.mail.client.view.panels.Client.Pa_Inbox;
import hospital.mail.client.view.panels.Client.Pa_Side;

/**
 * Mails' view, it has panel for the inbox, for the side panel and for the
 * counters tags.
 * 
 * @author Jorge Fernández Ruiz
 *
 */
public class JF_MailClient extends JFrame {
	private Pa_Side sidePanel;
	private Pa_Inbox inboxPanel;
	private Pa_Counter counterPanel;
	private JPanel panel;

	/**
	 * Constructor
	 * 
	 * @param txtTitle    of type String, the view's title.
	 * @param txtBtnWrite of type String, the text of the write button.
	 * @param txtBtnRead  of type String, the text of the read button.
	 * @param txtBtnFtp   of type String, the text of the go to ftp button.
	 * @param txtBtnExit  of type String, the text of the exit button
	 * @param txtInbox    of type String, the text of the head of inbox.
	 * @param txtTotal    of type String, the text of the total of mails in inbox.
	 * @param txtUnseen   of type String, the text of the total of mails unseen in
	 *                    inbox.
	 */
	public JF_MailClient(String txtTitle, String txtBtnWrite, String txtBtnRead, String txtBtnFtp, String txtBtnExit,
			String txtInbox, String txtTotal, String txtUnseen) {
		// create the panels
		sidePanel = new Pa_Side(txtBtnWrite, txtBtnRead, txtBtnFtp, txtBtnExit);
		inboxPanel = new Pa_Inbox(txtInbox);
		counterPanel = new Pa_Counter(txtTotal, txtUnseen);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		appendObjectsPanel();

		add(panel);

		defaultOperations(txtTitle);
	}

	/**
	 * Method that append to panel (JPanel) the rest of panels, sidePanel,
	 * inboxPanel and counterPanel (all JPanel).
	 */
	private void appendObjectsPanel() {
		panel.add(sidePanel, BorderLayout.EAST);
		panel.add(inboxPanel, BorderLayout.WEST);
		panel.add(counterPanel, BorderLayout.SOUTH);

	}

	/**
	 * Method that set properties of the view.
	 * 
	 * @param txtTitle type of String, the text of title.
	 */
	private void defaultOperations(String txtTitle) {
		this.setTitle(txtTitle);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Getter for the side's panel.
	 * 
	 * @return the sidePanel of type JPanel.
	 */
	public Pa_Side getSidePanel() {
		return sidePanel;
	}

	/**
	 * Getter for the side's panel.
	 * 
	 * @return the inboxPanel of type JPanel.
	 */
	public Pa_Inbox getInboxPanel() {
		return inboxPanel;
	}

	/**
	 * Getter for the side's panel.
	 * 
	 * @return the counterPanel of type JPanel.
	 */
	public Pa_Counter getCounterPanel() {
		return counterPanel;
	}

	// only fot test
//	public static void main(String[] args) {
//		Language.selectLanguage(1);
//		JF_MailClient jframe = new JF_MailClient();
//		jframe.setVisible(true);
//	}

}
