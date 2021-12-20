package hospital.mail.client.view.panels.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Side panel with a lblIco (JLabel), that contains as image the icon for the
 * hospital, and an buttons (ArrayList of type JButton), that contains the
 * buttons of write, read, ftp and exit. This panel has a BorderLayout.
 * 
 * @author Jorge Fernández Ruiz
 * @date 14/12/2021
 * @version 1.0
 * 
 */
public class Pa_Side extends JPanel {
	// label for the hospital's icon
	private JLabel lblIco;

	private JPanel imgPanel;
	private JPanel emailButtonsPanel;
	private JPanel controlPanel;

	// ArrayList of all buttons
	private ArrayList<JButton> buttons;

	/**
	 * Constructor
	 * 
	 * @param txtBtnWrite of type String, the text of button's write.
	 * @param txtBtnRead  of type String, the text of button's read.
	 * @param txtBtnFtp   of type String, the text of button's ftp for change the
	 *                    view.
	 * @param txtBtnExit  of type String, the text of button's exit.
	 */
	public Pa_Side(String txtBtnWrite, String txtBtnRead, String txtBtnFtp, String txtBtnExit) {
		imgPanel = new JPanel();
		emailButtonsPanel = new JPanel();
		controlPanel = new JPanel();

		buttons = new ArrayList<>();

		lblIco = new JLabel();
		lblIco.setBounds(ALLBITS, ABORT, 100, 100);
		lblIco.setIcon(new ImageIcon(getClass().getResource("/images/crown_77906.png"))); // relative URL

		fillButtons(txtBtnWrite, txtBtnRead, txtBtnFtp, txtBtnExit);

		// set border and the layout
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		appendObjectsPanels();

		appendPanels();
	}

	/**
	 * Method that append the extra panels to this class (JPanel).
	 */
	private void appendPanels() {
		this.add(imgPanel);
		this.add(emailButtonsPanel);
		this.add(controlPanel);

	}

	/**
	 * Method that append all objects to the panel.
	 */
	private void appendObjectsPanels() {
		imgPanel.add(lblIco);

		for (int i = 0; i < 2; i++) {
			buttons.get(i).setName(i + "");
			emailButtonsPanel.add(buttons.get(i));
			emailButtonsPanel.add(Box.createRigidArea(new Dimension(15, 15))); // spaces between buttons
		}

		for (int i = 2; i < buttons.size(); i++) {
			buttons.get(i).setName(i + "");
			controlPanel.add(buttons.get(i));
			controlPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		}

	}

	/**
	 * Method that fill the buttons (ArrayList of type JButton).
	 * 
	 * @param txtBtnWrite of type String, the text of button's write.
	 * @param txtBtnRead  of type String, the text of button's read.
	 * @param txtBtnFtp   of type String, the text of button's ftp for change the
	 *                    view.
	 * @param txtBtnExit  of type String, the text of button's exit.
	 */
	private void fillButtons(String txtBtnWrite, String txtBtnRead, String txtBtnFtp, String txtBtnExit) {
		buttons.add(new JButton(txtBtnWrite));
		buttons.add(new JButton(txtBtnRead));
		buttons.add(new JButton(txtBtnFtp));
		buttons.add(new JButton(txtBtnExit));
	}

	/**
	 * Getter of buttons (ArrayList of type JButton).
	 * 
	 * [0] -> Write || [1] -> Read || [2] -> Change to Ftp || [3] -> Exit
	 * 
	 * @return buttons (ArrayList of type JButton).
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}
