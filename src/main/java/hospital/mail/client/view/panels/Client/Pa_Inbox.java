package hospital.mail.client.view.panels.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Inbox's panel with a emails (JList), where will write the inbox (date -
 * sender - issue), a scroll (JScrollPane) in emails, and emailList
 * (DefaultListModel) that is the model for the emails.
 * 
 * @author Jorge Fernández Ruiz
 * @date 15/12/21
 * @version 1.0
 *
 */

public class Pa_Inbox extends JPanel {
	// Objects
	private JList<String> emails;
	private JScrollPane scroll;
	private DefaultListModel emailList;

	// JList's measures
	private final int HEIGHT;
	private final int WIDTH;

	/**
	 * Constructor.
	 * 
	 * @param txtHead of type String, the text of panel's head.
	 */
	public Pa_Inbox(String txtHead) {
		// set the layout for this panel
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHead), new EmptyBorder(5, 5, 5, 5)));

		// Values of TextArea's measures
		HEIGHT = 400;
		WIDTH = 600;

		// creation of the list for the emails' list
		emailList = new DefaultListModel();

		// Creation of objects
		emails = new JList<>();
		emails.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// creation of scroll bar
		scroll = new JScrollPane(emails);

		// vertical scroll always visible and horizontal not
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll, BorderLayout.CENTER); // add JList to panel (this class)
	}

	/**
	 * Method to add a new element into the email list and update the list (JList).
	 * 
	 * @param sender of type String, the person that sent the mail.
	 * @param issue  of type String, the issue of the mail.
	 * @param date   of type String, the date when was written the mail.
	 */
	public void appendNewEmail(String sender, String issue, String date) {
		String text = date + " - " + sender + " - " + issue;
		emailList.addElement(text);
		emails.setModel(emailList);
	}

	/**
	 * Getter of emails (JList).
	 * 
	 * @return of type JList, returns the emails' list.
	 */
	public JList<String> getEmails() {
		return emails;
	}

}
