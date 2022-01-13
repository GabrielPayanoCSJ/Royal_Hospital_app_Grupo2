package hospital.mail.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import hospital.mail.client.controller.Pipe_MailUpdater;
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
	 * @param txtTitle    of type {@link String}, the view's title.
	 * @param txtBtnWrite of type {@link String}, the text of the write button.
	 * @param txtBtnRead  of type {@link String}, the text of the read button.
	 * @param txtBtnExit  of type {@link String}, the text of the exit button
	 * @param txtInbox    of type {@link String}, the text of the head of inbox.
	 * @param txtTotal    of type {@link String}, the text of the total of mails in
	 *                    inbox.
	 * @param txtUnseen   of type {@link String}, the text of the total of mails
	 *                    unseen in inbox.
	 */
	public JF_MailClient(String txtTitle, String txtBtnWrite, String txtBtnRead, String txtBtnExit, String txtInbox,
			String txtTotal, String txtUnseen) {
		// create the panels
		this.sidePanel = new Pa_Side(txtBtnWrite, txtBtnRead, txtBtnExit);
		this.inboxPanel = new Pa_Inbox(txtInbox);
		this.counterPanel = new Pa_Counter(txtTotal, txtUnseen);
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		appendObjectsPanel();
		add(this.panel);
		defaultOperations(txtTitle);
		
	}

	/**
	 * Method that append to panel ({@link JPanel}) the rest of panels, sidePanel,
	 * inboxPanel and counterPanel (all {@link JPanel}).
	 */
	private void appendObjectsPanel() {
		this.panel.add(this.sidePanel, BorderLayout.EAST);
		this.panel.add(this.inboxPanel, BorderLayout.CENTER);
		this.panel.add(this.counterPanel, BorderLayout.SOUTH);
	}

	/**
	 * Method that set properties of the view.
	 * 
	 * @param txtTitle type of {@link String}, the text of title.
	 */
	private void defaultOperations(String txtTitle) {
		this.setTitle(txtTitle);
	//
		this.setSize(new Dimension(900, 400));
		this.setResizable(false);
	}

	/**
	 * Getter for the side's panel.
	 * 
	 * @return the sidePanel of type {@link JPanel}.
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

}
