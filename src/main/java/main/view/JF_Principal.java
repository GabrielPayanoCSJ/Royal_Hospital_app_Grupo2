package main.view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View of the sections select.
 * 
 * @author Jorge Fernández Ruiz
 *
 */
public class JF_Principal extends JFrame {
	private ArrayList<JButton> buttons;
	private JComboBox<String> jcLanguages;
	private JPanel contentPane;

	public JF_Principal() {
		buttons = new ArrayList<>();
		jcLanguages = new JComboBox<>();
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		fillButtons();
		buttonsProperties();
		appendObjects();
		
		fillLanguages();
		
		this.add(contentPane);

		properties();
	}

	private void fillLanguages() {
		jcLanguages.addItem("English");
		jcLanguages.addItem("Spanish");
		
	}

	private void buttonsProperties() {
		buttons.get(1).setEnabled(false);
		
		for (JButton button : buttons) {
			button.setSize(new Dimension(3000, button.getHeight()));
		}
	}

	private void appendObjects() {
		for (JButton button : buttons) {
			contentPane.add(button);
		}
		contentPane.add(jcLanguages);

	}

	private void fillButtons() {
		buttons.add(new JButton("FTP Server"));
		buttons.add(new JButton("FTP client"));
		buttons.add(new JButton("Mail"));

	}

	private void properties() {
		this.setTitle("Title");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 200));
		this.setResizable(false);

	}

	/**
	 * Getter of buttons [0] -> FTP Server || [1] -> FTP Client || [2] -> Mail
	 *
	 * @return the buttons, of type {@link ArrayList} of type {@link JButton}
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/**
	 * Getter of comboBox
	 *
	 * @return the jcLanguages
	 */
	public JComboBox<String> getJcLanguages() {
		return jcLanguages;
	}

	
}
