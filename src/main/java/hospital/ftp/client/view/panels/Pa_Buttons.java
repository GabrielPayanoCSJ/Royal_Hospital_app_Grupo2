package hospital.ftp.client.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Buttons' panel with a buttons (ArrayList of type JButton) that contains a
 * button for all actions (create, delete, upload, download and exit). The
 * buttons will be visible or not depending on the permissions.
 * 
 * @author Jorge Fernández Ruiz
 * @date 14/12/2021
 * @version 1.0
 */
public class Pa_Buttons extends JPanel {
	// ArrayList of buttons
	private ArrayList<JButton> buttons;
	
	/**
	 * Constructor
	 * 
	 * @param txtBtnCreate   of String type, for create a directory.
	 * @param txtBtnDelete   of String type, for delete a directory.
	 * @param txtBtnUpload   of String type, for upload a directory.
	 * @param txtBtnDownload of String type, for download a directory.
	 * @param txtBtnExit     of String type, for exit of program and close the
	 *                       connections.
	 */
	public Pa_Buttons(String txtBtnCreate, String txtBtnDelete, String txtBtnUpload, String txtBtnDownload,
			String txtBtnExit) {
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// create the ArrayList
		buttons = new ArrayList<>();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// creation and append the buttons to ArrayList of buttons
		buttons.add(new JButton(txtBtnCreate));
		buttons.add(new JButton(txtBtnDelete));
		buttons.add(new JButton(txtBtnUpload));
		buttons.add(new JButton(txtBtnDownload));
		buttons.add(new JButton(txtBtnExit));

		// append each button to the panel (this class)
		for (JButton button : buttons) {
			this.add(button);
			this.add(Box.createRigidArea(new Dimension(15,15)));
		}
	}

	/**
	 * Getter for the ArrayList of JButtons
	 * 
	 * @return buttons, the JButton's ArrayList.
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}
