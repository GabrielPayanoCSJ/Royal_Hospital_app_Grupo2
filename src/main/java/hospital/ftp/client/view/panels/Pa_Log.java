package hospital.ftp.client.view.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Log's panel with a txA_Log (JTextArea), where will write all the information
 * of any operation and error, and a scroll (JScrollPane) in txA_Log.
 * 
 * @author Jorge Fernández Ruiz
 * @date 14/12/2021
 * @version 1.0
 *
 */

public class Pa_Log extends JPanel {
	// Objects
	private JTextArea txA_Log;
	private JScrollPane scroll;

	// TextArea's measures
	private final int HEIGHT;
	private final int WIDTH;

	/**
	 * Constructor
	 */
	public Pa_Log() {
		// Values of TextArea's measures
		HEIGHT = 16;
		WIDTH = 58;

		// Creation of objects
		txA_Log = new JTextArea(HEIGHT, WIDTH);
		scroll = new JScrollPane(txA_Log);

		txA_Log.setEditable(false);

		// vertical scroll always visible
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(txA_Log); // add textArea to panel (this class)
	}

	/**
	 * Getter for the TextArea.
	 * 
	 * @return txA_Log of TextArea type.
	 */
	public JTextArea getTxA_Log() {
		return txA_Log;
	}

}
