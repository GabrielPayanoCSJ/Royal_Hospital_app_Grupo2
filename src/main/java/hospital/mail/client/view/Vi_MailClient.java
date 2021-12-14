package hospital.mail.client.view;

import java.util.*;
import javax.swing.*;

public class Vi_MailClient extends JFrame {
	// create Model
	private ArrayList<JTextField> texts;
	private ArrayList<JButton> buttons;
	private JPanel generalPanel;
	private JPanel emailPanel;
	private JPanel sidePanel;
	private JTable inbox;

	public Vi_MailClient(String title) {
		super(title);

		generalPanel = new JPanel();
		emailPanel = new JPanel();
		sidePanel = new JPanel();

		texts = new ArrayList<>();
		buttons = new ArrayList<>();

		fillButtons();

		createInboxTable();
		
		//createPanelLayout();
		
		defaultOperations();

	}

	private void defaultOperations() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void createInboxTable() {
		// change for model's strings
		Object[] columnName = { "Sender", "Subject" };
		Object[][] rows = { { "", ""} }; 
		
		
	}

	private void fillButtons() {
		// change for model's strings
		buttons.add(new JButton("Write"));
		buttons.add(new JButton("Read"));
		buttons.add(new JButton("Go to FTP"));
		buttons.add(new JButton("Exit"));

	}

}
