package hospital.mail.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import hospital.mail.client.view.panels.Client.Pa_Inbox;

/**
 * Email login's view, has a buttons (ArrayList of type {@link JButton}), a
 * panes (ArrayList of type {@link JTextPane}), a contentPane ({@link JPanel}),
 * txtFmail ({@link JTextField}) and passPassWord ({@link JPassWordField}).
 * 
 * @author Javier Gómez
 * @date 14/12/2021
 * @editor Jorge Fernández Ruiz
 * @editDate 16/12/2021
 * 
 * @editor Guillermo González de Miguel
 * @editDate 11/01/2022
 * 
 * @version 1.2
 */
public class JF_MailLogIn extends JFrame {
	private ArrayList<JButton> buttons;
	private ArrayList<JLabel> labels;
	private JPanel contentPane;
	private JTextField txtFmail;
	private JPasswordField passPassword;

	/**
	 * Constructor of view.
	 * 
	 * @param txtTitle        of type {@link String}, title's text of the view.
	 * @param txtBtnLogin     of type {@link String}, button text of login.
	 * @param txtBtnReturn    of type {@link String}, button text of return.
	 * @param txtMailBodyHead of type {@link String}, head text of body of the mail.
	 * @param txtMail         of type {@link String}, the text of mail.
	 * @param txtPassword     of type {@link String}, the text of password.
	 */
	public JF_MailLogIn(String txtTitle, String txtBtnLogin, String txtBtnReturn, String txtMailBodyHead,
			String txtMail, String txtPassword) {

		super(txtTitle);
		// this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// create objects
		txtFmail = new JTextField();
		contentPane = new JPanel();
		passPassword = new JPasswordField();
		buttons = new ArrayList<>();
		labels = new ArrayList<>();

		
		JPanel panel2 = new JPanel();
		JLabel title = new JLabel("ACCESO CORREO");
		title.setFont(new Font("Sans-serif", Font.BOLD, 54));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.add(title);
		setSize(new Dimension(400,400));
		setLayout(new BorderLayout(0, 0));


//		fillButtons(txtBtnLogin, txtBtnReturn);
//		fillPanes();
//		setObjectsProperties(txtMailBodyHead, txtMail, txtPassword);
//		appendPaneObjects();
		add(panel2, BorderLayout.NORTH);
//		this.setContentPane(contentPane);
//		setUndecorated(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}

	/**
	 * Method that append to contentPane ({@link JPanel}) all objects.
	 */
	private void appendPaneObjects() {
		
		contentPane.setLayout(new GridLayout(3,2));
		
		contentPane.add(labels.get(0));
		contentPane.add(txtFmail);
		
		contentPane.add(labels.get(1));
		contentPane.add(passPassword);

		contentPane.add(buttons.get(1));
		contentPane.add(buttons.get(0));
	}

	/**
	 * Method that set the properties of the objects.
	 * 
	 * @param txtMailBodyHead type of {@link String}, the text of body's head.
	 * @param txtMail         type of {@link String}, the text of mail.
	 * @param txtPassword     type of {@link String}, the text of password.
	 */
	private void setObjectsProperties(String txtMailBodyHead, String txtMail, String txtPassword) {
		// JPanel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);

		// JTextField
		txtFmail.setToolTipText(txtMailBodyHead);

		// JLabels (ArrayList)
		labels.get(0).setText(txtMail);
		labels.get(1).setText(txtPassword);
	}

	/**
	 * Method that fill labels ({@link ArrayList} of type {@link JLabel}) creating
	 * news.
	 */
	private void fillPanes() {
		labels.add(new JLabel()); // mail's label
		labels.add(new JLabel()); // password's label
	}

	/**
	 * Method that fill buttons ({@link ArrayList} of type {@link JButton}) creating
	 * news.
	 * 
	 * @param txtBtnLogin  type of {@link String}, the text of login button.
	 * @param txtBtnReturn type of {@link String}, the text of return button.
	 */
	private void fillButtons(String txtBtnLogin, String txtBtnReturn) {
		buttons.add(new JButton(txtBtnLogin));
		buttons.add(new JButton(txtBtnReturn));
	}

	/**
	 * Getter of buttons ({@link ArrayList} of type {@link JButton}). [0] ->
	 * btnLogin || [1] -> btnReturn
	 * 
	 * @return the buttons of type {@link JButton}.
	 */
	public ArrayList<JButton> getButtonsLogin() {
		return buttons;
	}

	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public JTextField getTxtFmail() {
		return txtFmail;
	}

	public JPasswordField getPassPassword() {
		return passPassword;
	}

}
