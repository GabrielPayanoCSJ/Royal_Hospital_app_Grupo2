package hospital.mail.client.view.panels.WriteMail;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Description's panel for write emails. Has a tags ({@link ArrayList} of type
 * {@link JLabel}) and a txFDesc ({@link ArrayList} of type {@link JTextField}).
 * Has a horizontal {@link BoxLayout}.
 * 
 * @author Jorge Fernández Ruiz
 * @date 16/12/2021
 * @version 1.0
 */
public class Pa_DescWriteMail extends JPanel {
	private ArrayList<JLabel> tags;
	private ArrayList<JTextField> txFDesc;

	/**
	 * Constructor.
	 * 
	 * @param txtDescHead  of type {@link String}, the text of description's head.
	 * @param txtSender    of type {@link String}, the text of sender.
	 * @param txtAddressee of type {@link String}, the text of addressee.
	 */
	public Pa_DescWriteMail(String txtDescHead, String txtSender, String txtAddressee) {
		// create the arrays
		tags = new ArrayList<>();
		txFDesc = new ArrayList<>();

		fillTags(txtSender, txtAddressee);
		fillDesc();

		panelProperties(txtDescHead);
		appendObjects();
	}

	/**
	 * Method that append to the panel (this class) all objects, in order label and
	 * its {@link JTextField}.
	 */
	private void appendObjects() {
		for (int i = 0; i < tags.size(); i++) {
			this.add(tags.get(i));
			this.add(txFDesc.get(i));
		}
	}

	/**
	 * Method that set some properties to the panel (this class).
	 * 
	 * @param txtDescHead of type {@link String}, the text for the description's
	 *                    head.
	 */
	private void panelProperties(String txtDescHead) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(
				new CompoundBorder(new TitledBorder(new EtchedBorder(), txtDescHead), new EmptyBorder(5, 5, 5, 5)));
	}

	/**
	 * Method that fill txFDesc ({@link ArrayList} of type {@link JTextFlied})
	 * creating a {@link JTextField} disabled, for the sender, and other new, for
	 * the addressee.
	 */
	private void fillDesc() {
		JTextField txFsender = new JTextField();
		txFsender.setEnabled(false);

		txFDesc.add(txFsender);
		txFDesc.add(new JTextField());
	}

	/**
	 * Method that fill tags ({@link ArrayList} of type {@link JLabel}) creating a
	 * label for the text of sender and other for the addressee.
	 * 
	 * @param txtSender    of type {@link String}, the text for the sender.
	 * @param txtAddressee of type {@link String}, the text for the addressee.
	 */
	private void fillTags(String txtSender, String txtAddressee) {
		tags.add(new JLabel("  " + txtSender + ":  "));
		tags.add(new JLabel("  " + txtAddressee + ":  "));
	}

	/**
	 * Getter for the txFDesc ({@link ArrayList} of type {@link JTextField}). [0] ->
	 * sender [1] -> addressee
	 * 
	 * @return the txFDesc of type {@link JTextField}.
	 */
	public ArrayList<JTextField> getTxFDesc() {
		return txFDesc;
	}

}
