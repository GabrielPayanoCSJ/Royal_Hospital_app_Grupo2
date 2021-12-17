package hospital.mail.client.view.panels.WriteMail;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Body's panel with a txA_body (JTextArea), where will write the message's
 * body, and a scroll (JScrollPane), vertical only if is needed and horizontal
 * never.
 * 
 * @author Jorge Fernández Ruiz
 * @date 15/12/2021
 * @version 1.2
 */
public class Pa_WriteBody extends JPanel {
	private final int HEIGTH;
	private final int WIDTH;
	private JPanel panelTxtIssue;
	private JPanel panelTxtMessage;
	private JTextField txtIssue;
	private JTextArea txA_body;
	private JScrollPane scroll;

	/**
	 * Constructor
	 * 
	 * @param txtHeadBody of type String, the text of panel's head.
	 */
	public Pa_WriteBody(String txtHeadBody, String txtHeadIssue, String txtHeadMessage) {
		HEIGTH = 30;
		WIDTH = 100;

		// border of body
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(
				new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHeadBody), new EmptyBorder(5, 5, 5, 5)));

		// creation of extra panels
		panelTxtIssue = new JPanel();
		panelTxtMessage = new JPanel();

		// border and layout of issue
		panelTxtIssue.setLayout(new BorderLayout(0, 0));
		panelTxtIssue.setBorder(
				new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHeadIssue), new EmptyBorder(5, 5, 5, 5)));

		// border and layout of message
		panelTxtMessage.setLayout(new BorderLayout(0, 0));
		panelTxtMessage.setBorder(
				new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHeadMessage), new EmptyBorder(5, 5, 5, 5)));

		// creation of objects
		txtIssue = new JTextField();
		txA_body = new JTextArea(HEIGTH, WIDTH);

		// this serves if it was written more tan the columns number, the last word will
		// be write on the next line.
		txA_body.setLineWrap(true);
		txA_body.setWrapStyleWord(true);

		// append the objects into its panels
		panelTxtIssue.add(txtIssue); // control max characters
		panelTxtMessage.add(txA_body);

		// append to this class (extends JPanel) the extra panels
		this.add(panelTxtIssue, BorderLayout.NORTH);
		this.add(panelTxtMessage, BorderLayout.SOUTH);
	}

	/**
	 * Getter of txtIssue (JTextField).
	 * 
	 * @return the txtIssue
	 */
	public JTextField getTxtIssue() {
		return txtIssue;
	}

	/**
	 * Getter of txA_body (JTextArea).
	 * 
	 * @return of type JTextArea.
	 */
	public JTextArea getTxA_body() {
		return txA_body;
	}

}
