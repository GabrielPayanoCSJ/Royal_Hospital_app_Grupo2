package hospital.mail.client.view.panels.ReadMail;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Description panel, with a panel to the text of sender ({@link JLabel}, in
 * tags[0]) and the name of sender ({@link JLabel}, in tags[1]), and a panel to
 * the text of issue ({@link JLabel}, in tags[2]) and the message of issue
 * ({@link JLabel}, in tags[3]). Has a {@link BorderLayout}.
 * 
 * @author Jorge Fernández Ruiz
 * @date 15/12/2021
 * @version 1.0
 */
public class Pa_Description extends JPanel {
	private ArrayList<JLabel> tags;
	private ArrayList<JPanel> descPanels;

	/**
	 * Constructor
	 * 
	 * @param txtHead   of type {@link String}, the text of the panel's head.
	 * @param txtSender of type {@link String}, the text of sender.
	 * @param txtIssue  of type {@link String}, the text of issue.
	 */
	public Pa_Description(String txtHead, String txtSender, String txtIssue) {
		this.setLayout(new BorderLayout(0, 0));
		// change LOG for model
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHead), new EmptyBorder(5, 5, 5, 5)));

		// create the ArrayLists
		tags = new ArrayList<>();
		descPanels = new ArrayList<>();

		fillTags(txtSender, txtIssue);
		fillDescPanel();

		// append tags to descPanels
		appendTagsPanel();

		appendPanels();
	}

	/**
	 * Method that append to the panel (this class) the other panels (in descPanels,
	 * {@link ArrayList} of type {@link JPanel}).
	 */
	private void appendPanels() {
		for (JPanel panel : descPanels) {
			this.add(panel);
		}

	}

	/**
	 * Method that append the tags ({@link JLabel}) in their panels
	 * ({@link JPanel}).
	 */
	private void appendTagsPanel() {
		for (int i = 0; i < descPanels.size(); i++) {
			for (int j = 0; j < tags.size(); j++) {
				descPanels.get(i).add(tags.get(j));
			}
		}
	}

	/**
	 * Method that append news panel to descPanels ({@link ArrayList} of type
	 * {@link JPanel}).
	 */
	private void fillDescPanel() {
		descPanels.add(new JPanel());
		descPanels.add(new JPanel());
	}

	/**
	 * Method that fill tags ({@link ArrayList} of type {@link JLabel}), in [0] the
	 * text of sender, [1] the sender, [2] the text of issue, [3] the issue.
	 * 
	 * @param txtSender of type {@link String}, the text of sender.
	 * @param txtIssue  of type {@link String}, the text of issue.
	 */
	private void fillTags(String txtSender, String txtIssue) {
		tags.add(new JLabel(txtSender + ":"));
		tags.add(new JLabel(""));
		tags.add(new JLabel(txtIssue + ":"));
		tags.add(new JLabel(""));
	}
}
