package hospital.mail.client.view;

import java.util.*;
import javax.swing.*;

public class Vi_MailClient extends JFrame {
	
	private ArrayList<JTextField> jtexts;
	private ArrayList<JButton> jbuttons;
	
	
	
	public Vi_MailClient() {
		super("Ventana");
		this.setSize(800,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new Vi_MailClient().setVisible(true);
	
	}

}


