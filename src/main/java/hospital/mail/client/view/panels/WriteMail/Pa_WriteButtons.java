package hospital.mail.client.view.panels.WriteMail;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Buttons' panel of write email part. Has a buttons ({@link ArrayList} of type
 * {@link JButton}) and has a horizontal {@link BoxLayout}.
 * 
 * @author Jorge Ferández Ruiz
 * @date 16/12/2021
 * @version 1.0
 */
public class Pa_WriteButtons extends JPanel {
	private ArrayList<JButton> buttons;

	/**
	 * Constructor.
	 * 
	 * @param txtBtnCancel of type {@link String}, the text of cancel button.
	 * @param txtBtnSend   of type {@link String}, the text of send button.
	 */
	public Pa_WriteButtons(String txtBtnCancel, String txtBtnSend) {
		buttons = new ArrayList<>();

		fillButtons(txtBtnCancel, txtBtnSend);

		this.setLayout(new FlowLayout());

		appendObjects();

	}

	/**
	 * Method that append the buttons to the panel (this class).
	 */
	private void appendObjects() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setName(i + "");
			this.add(buttons.get(i));
		}

	}

	/**
	 * Method that fill buttons ({@link ArrayList} of type {@link JButton}) creating
	 * a {@link JButton} for cancel and other for send.
	 * 
	 * @param txtBtnCancel of type {@link String}, text of cancel button.
	 * @param txtBtnSend   of type {@link String}, text of send button.
	 */
	private void fillButtons(String txtBtnCancel, String txtBtnSend) {
		buttons.add(new JButton(txtBtnCancel));
		buttons.add(new JButton(txtBtnSend));
	}

	/**
	 * Getter for buttons ({@link ArrayList} of type {@link JButton}). [0] -> cancel
	 * || [1] -> send
	 * 
	 * @return the buttons ({@link ArrayList} of type {@link JButton}).
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}
