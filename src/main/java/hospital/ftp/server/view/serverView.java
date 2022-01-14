package hospital.ftp.server.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
		pButtons = new Pa_Buttons();
		pLog = new Pa_Log();
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.add(pButtons, BorderLayout.SOUTH);
		mainPanel.add(pLog, BorderLayout.NORTH);
		
		this.add(mainPanel);
		
		defaultProperties();
	}

	private void defaultProperties() {
			this.setTitle("Server");
			this.setSize(new Dimension(900, 400));
			this.setResizable(false);
			this.setLocation(900, 200);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	/**
	 * @return the mainPanel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @param mainPanel the mainPanel to set
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * @return the pButtons
	 */
	public Pa_Buttons getpButtons() {
		return pButtons;
	}

	/**
	 * @return the pLog
	 */
	public Pa_Log getpLog() {
		return pLog;
	}
	
	

}
