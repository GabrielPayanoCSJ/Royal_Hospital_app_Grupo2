package main.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pa_Main extends JPanel {
	private ArrayList<JLabel> labels;
	private ArrayList<JPanel> panels;
	private ArrayList<JButton> buttons;
	private JComboBox<String> jcLanguages;

	public Pa_Main(String txtHeader, String txtLblLang, String txtBtnServerFTP, String txtBtnClientFTP,
			String txtBtnMailClient) {
		this.setLayout(new BorderLayout());
		generateJLabel(new ArrayList<String>(Arrays.asList(txtHeader, txtLblLang)));
		generateJButton(new ArrayList<String>(Arrays.asList(txtBtnServerFTP, txtBtnClientFTP, txtBtnMailClient)));
		loadItemComboBox(new ArrayList<String>(Arrays.asList("English", "Spanish")));
		generateJPanel(6);
		confJPanel();
	}

	/**
	 * 
	 */
	private void confJPanel() {

		// GRID LAYOUT 2 - Buttons
		this.panels.get(4).setLayout(new GridLayout(0, 1, 0, 20));
		this.panels.get(4).add(this.buttons.get(0));
		this.panels.get(4).add(this.buttons.get(1));
		this.panels.get(4).add(this.buttons.get(2));
		this.panels.get(4).setPreferredSize(new Dimension(400, 200));
//		this.panels.get(4).setBorder(new EmptyBorder(20, 0, 0, 0));

		// GRID LAYOUT 1 - JCombo
		this.jcLanguages.setFont(new Font("Sans-serif", Font.BOLD, 24));
		this.panels.get(3).setLayout(new GridLayout(2, 1, 0, 0));
//		this.panels.get(3).setPreferredSize(new Dimension(100, 50));
		this.panels.get(3).setBorder(new EmptyBorder(0, 0, 20, 0));
		this.panels.get(3).add(this.labels.get(1));
		this.labels.get(1).setFont(new Font("Sans-serif", Font.BOLD, 16));
		this.panels.get(3).add(this.jcLanguages);

		// ADD GRID PANELS TO BOX PANEL
		this.panels.get(2).setLayout(new GridLayout(3, 1, 0, 0));
		this.panels.get(2).add(this.panels.get(3)); // ADD GRID LAYOUT 1
		this.panels.get(2).add(this.panels.get(4)); // ADD GRID LAYOUT 2

		// ADD BOX PANEL TO FLOW PANEL
		this.panels.get(1).setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panels.get(1).add(this.panels.get(2));

		// ADD HEADER LABEL TO FLOW PANEL
		this.labels.get(0).setFont(new Font("Sans-serif", Font.BOLD, 36));
		this.panels.get(0).setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panels.get(0).add(this.labels.get(0)); // TEXT HEADER LABEL

		// ADD PANELS TO PRINCIPAL PANEL
		this.add(this.panels.get(0), BorderLayout.NORTH);
		this.add(this.panels.get(1), BorderLayout.CENTER);
	}

	/**
	 * 
	 * @param amount
	 */
	private void generateJPanel(int amount) {
		this.panels = new ArrayList<JPanel>();

		for (int i = 0; i < amount; i++) {
			this.panels.add(new JPanel());
		}
	}

	/**
	 * 
	 * @param txtlabels
	 */
	private void generateJLabel(ArrayList<String> txtlabels) {
		this.labels = new ArrayList<JLabel>();

		for (int i = 0; i < txtlabels.size(); i++) {
			this.labels.add(new JLabel(txtlabels.get(i)));
		}
	}

	/**
	 * 
	 * @param txtbtns
	 */
	private void generateJButton(ArrayList<String> txtbtns) {
		this.buttons = new ArrayList<JButton>();

		for (int i = 0; i < txtbtns.size(); i++) {
			this.buttons.add(new JButton(txtbtns.get(i)));
		}
	}

	/**
	 * 
	 * @param languages
	 */
	private void loadItemComboBox(ArrayList<String> languages) {
		this.jcLanguages = new JComboBox<String>();

		for (int i = 0; i < languages.size(); i++) {
			this.jcLanguages.addItem(languages.get(i));
		}
	}
	
	/**
	 * @return the buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/**
	 * @return the jcLanguages
	 */
	public JComboBox<String> getJcLanguages() {
		return jcLanguages;
	}
}
