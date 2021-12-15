package hospital.mail.client.view;

import java.awt.BorderLayout;

import javax.swing.*;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.languages.Language;
import hospital.mail.client.view.panels.Pa_Counter;
import hospital.mail.client.view.panels.Pa_Inbox;
import hospital.mail.client.view.panels.Pa_Side;

/**
 * Mails' view, it has panel for the inbox, for the side panel and for the
 * counters tags.
 * 
 * @author Jorge Fernández Ruiz
 *
 */
public class Vi_MailClient extends JFrame {
	private Pa_Side sidePanel;
	private Pa_Inbox inboxPanel;
	private Pa_Counter counterPanel;
	private JPanel panel;

	/**
	 * Constructor
	 */
	public Vi_MailClient() {
		// change for lang
		// create the panels
		sidePanel = new Pa_Side("WRITE", "READ", "GO TO FTP", "EXIT");
		inboxPanel = new Pa_Inbox();
		counterPanel = new Pa_Counter("TOTAL", "UNSEEN");
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		appendObjectsPanel();

		add(panel);

		defaultOperations();
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
	 */
	private void defaultOperations() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	// only fot test
	public static void main(String[] args) {
		Language.selectLanguage(1);
		Vi_MailClient jframe = new Vi_MailClient();
		jframe.setVisible(true);
	}

}
