package hospital.mail.client.view.panels.Client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Counters' panel with a tags ({@link ArrayList} of type {@link JLabel}) that
 * contains a label with text to show the total of mails [0], a label with the
 * text as a number to show total [1], a label with text to show the total of
 * mails that are unseen [2] and a label with the text as a number to show total
 * of unseen mails [3].
 * 
 * @author Jorge Fernández Ruiz
 * @date 15/12/21
 * @version 1.0
 * 
 */
public class Pa_Counter extends JPanel {
	private ArrayList<JLabel> tags;
	private JLabel numTotal;
	private JLabel numUnseen;

	/**
	 * Constructor.
	 * 
	 * @param txtLabelTotal  of type {@link String}, the text of total.
	 * @param txtLabelUnseen of type {@link String}, the text of total unseen.
	 */
	public Pa_Counter(String txtLabelTotal, String txtLabelUnseen) {
		tags = new ArrayList<>();

		this.setLayout(new FlowLayout());

		fillTags(txtLabelTotal, txtLabelUnseen);

		appendObjects();
	}

	/**
	 * Method that append objects to the panel (this class).
	 */
	private void appendObjects() {
		for (JLabel tag : tags) {
			this.add(tag);
			this.add(Box.createRigidArea(new Dimension(15, 15)));
		}

	}

	/**
	 * Method to fill the tags ({@link ArrayList} of type {@link JLabel}) creating
	 * the labels.
	 * 
	 * @param txtLabelTotal  of type {@link String}, the text of total.
	 * @param txtLabelUnseen of type {@link String}, the text of total unseen.
	 */
	private void fillTags(String txtLabelTotal, String txtLabelUnseen) {
		tags.add(new JLabel(txtLabelTotal + ":"));
		tags.add(numTotal = new JLabel("0"));
		tags.add(new JLabel(txtLabelUnseen + ":"));
		tags.add(numUnseen = new JLabel("0"));
	}

	/**
	 * Getter for the label that is used as a counter of total mails.
	 * 
	 * @return of type {@link JLabel}.
	 */
	public JLabel getNumTotal() {
		return numTotal;
	}

	/**
	 * Getter for the label that is used as a counter of total unseen mails.
	 * 
	 * @return of type {@link JLabel}.
	 */
	public JLabel getNumUnseen() {
		return numUnseen;
	}

}
