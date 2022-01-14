package hospital.ftp.server.view.ServerPanels;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Pa_Buttons extends JPanel {
	private ArrayList<JButton> buttons;

	public Pa_Buttons() {
		buttons = new ArrayList<>();
		
		fillButtons();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		appendButtons();
	}

	private void appendButtons() {
		for (JButton button : buttons) {
			this.add(button);
		}
		
	}

	private void fillButtons() {
		JButton finish = new JButton("Finish");
		finish.setEnabled(false);
		
		buttons.add(finish);
		buttons.add(new JButton("Start"));
		
	}

	/**
	 * @return the buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	
}
