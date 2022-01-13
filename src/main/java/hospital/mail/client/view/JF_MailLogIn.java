package hospital.mail.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import hospital.mail.client.view.panels.LoginMail.Pa_Login;

/**
 * Email login's view, has a buttons (ArrayList of type JButton), a panes
 * (ArrayList of type JTextPane), a contentPane (JPanel), txtFmail (JTextField)
 * and passPassWord (JPassWordField).
 * 
 * @author Javier Gómez
 * @date 14/12/2021
 * @editor Jorge Fernández Ruiz
 * @editDate 16/12/2021
 * 
 * @NewReDesign Guillermo González de Miguel
 * @dateNewReDesign 12/01/2022
 * 
 * @version 1.2
 */
public class JF_MailLogIn extends JFrame {
	/**
	 * 
	 */
	private Pa_Login paLogin;
	
	/**
	 * 
	 */
	private int posX = 0;
	
	/**
	 * 
	 */
	private int posY = 0;

	/**
	 * Constructor of view.
	 * 
	 * @param txtTitleFrame of type String, title's text of the view.
	 * @param txtLblHeader  of type String, head text of body of the mail.
	 * @param txtLblMail    of type String, the text of mail.
	 * @param txtLblPass    of type String, the text of password.
	 * @param txtBtnLogin   of type String, button text of login.
	 * @param txtBtnCancel  of type String, button text of cancel.
	 */
	public JF_MailLogIn(String txtTitleFrame, String txtLblHeader, String txtLblMail, String txtLblPass,
			String txtBtnLogin, String txtBtnCancel) {

		super(txtTitleFrame); // JFRAME TITLE

		defaultOperations(); // FRAME CONFIGURATION

		// ADD LOGIN PANEL TO FRAME
		this.paLogin = new Pa_Login(txtLblHeader, txtLblMail, txtLblPass, txtBtnLogin, txtBtnCancel);
		this.add(paLogin, BorderLayout.CENTER);
		moveJFrame();
	}

	/**
	 * Method that set properties of the view.
	 * 
	 * @param txtTitle type of String, the text of title.
	 */
	private void defaultOperations() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(600, 360));
		this.setLocation(500, 500);
//		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout(0, 0));
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(000, 000, 000, 120)));
	}


	/**
	 * 
	 */
	private void moveJFrame() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
			}
		});
	}

	/**
	 * @return the paLogin
	 */
	public Pa_Login getPaLogin() {
		return paLogin;
	}
}