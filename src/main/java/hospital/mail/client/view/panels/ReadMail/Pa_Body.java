package hospital.mail.client.view.panels.ReadMail;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
 * @version 1.0
 */
public class Pa_Body extends JPanel {
	private final int HEIGTH;
	private final int WIDTH;
	private JTextArea txA_body;
	private JScrollPane scroll;

	/**
	 * Constructor
	 * 
	 * @param txtHead of type String, the text of panel's head.
	 */
	public Pa_Body(String txtHead) {
		HEIGTH = 30;
		WIDTH = 100;

		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), txtHead), new EmptyBorder(5, 5, 5, 5)));

		txA_body = new JTextArea(HEIGTH, WIDTH);
		txA_body.setEditable(false);

		// this serves if it was written more tan the columns number, the last word will
		// be write on the next line.
		txA_body.setLineWrap(true);
		txA_body.setWrapStyleWord(true);

		scroll = new JScrollPane(txA_body);

		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		this.add(scroll);
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
