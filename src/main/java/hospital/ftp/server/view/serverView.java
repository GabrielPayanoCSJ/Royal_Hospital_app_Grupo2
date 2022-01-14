package hospital.ftp.server.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.ftp.server.view.ServerPanels.Pa_Buttons;
import hospital.ftp.server.view.ServerPanels.Pa_Log;

public class serverView extends JFrame {
	private Pa_Buttons pButtons;
	private Pa_Log pLog;
	private JPanel mainPanel;

	public serverView() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout());
		
		
	}

}
