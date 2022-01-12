package hospital.mail.client.view.panels.Client;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
 * Inbox's panel with a emails ({@link JList}), where will write the inbox (date
 * - sender - issue), a scroll ({@link JScrollPane}) in emails, and emailList
 * ({@link DefaultListModel}) that is the model for the emails.
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
	private DefaultListModel<String> emailList;

	// JList's measures
//	private final int HEIGHT;
//	private final int WIDTH;

	/**
	 * Constructor.
	 * 
	 * @param txtHead of type {@link String}, the text of panel's head.
	 */
	public Pa_Inbox(String txtHead) {
		// set the layout for this panel
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHead), new EmptyBorder(5, 5, 5, 5)));

		// Values of TextArea's measures
//		HEIGHT = 400;
//		WIDTH = 600;

		// creation of the list for the emails' list
		this.emailList = new DefaultListModel<String>();
		
		// Creation of objects
		this.emails = new JList<>();
//		emails.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// creation of scroll bar
		this.scroll = new JScrollPane(this.emails);

		// vertical scroll always visible and horizontal not
		this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll, BorderLayout.CENTER); // add JList to panel (this class)
	}

	/**
	 * Method to add a new element into the email list and update the list
	 * ({@link JList}).
	 * 
	 * @param addresses of type {@link String}, the person that sent the mail.
	 * @param issue     of type {@link String}, the issue of the mail.
	 * @param date      of type {@link String}, the date when was written the mail.
	 * @throws ParseException
	 * @throws AddressException
	 */
	public void appendNewEmail(int idList, Address[] addresses, String issue, Date date)
			throws ParseException, AddressException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String formatDate = format.format(date);

		if (addresses.length == 1) {
			InternetAddress addresse = new InternetAddress(addresses[0].toString());
			String text = String.valueOf(idList) + " - " + formatDate + " - " + addresse.toUnicodeString() + " - "
					+ issue;
			this.emailList.addElement(text);
			this.emails.setModel(emailList);
		} else if (addresses.length > 1) {
			String text = formatDate + " - ";

			String someAddresses = "";

			for (int i = 0; i < addresses.length; i++) {
				someAddresses += addresses[i];
				someAddresses += " | ";
			}

			text += someAddresses + " - " + issue;

			this.emailList.addElement(text);
			this.emails.setModel(this.emailList);
		}

	}

	/**
	 * Getter of emails ({@link JList}).
	 * 
	 * @return of type JList, returns the emails' list.
	 */
	public JList<String> getEmails() {
		return emails;
	}

	/**
	 * Getter of email list {@link DefaultListModel}.
	 * 
	 * @return the emailList of type DefaultListModel.
	 */
	public DefaultListModel<String> getEmailList() {
		return emailList;
	}

}
