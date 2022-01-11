package hospital.mail.client.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Email login's view, has a buttons (ArrayList of type {@link JButton}), a
 * panes (ArrayList of type {@link JTextPane}), a contentPane ({@link JPanel}),
 * txtFmail ({@link JTextField}) and passPassWord ({@link JPassWordField}).
 * 
 * @author Javier G�mez
 * @date 14/12/2021
 * @editor Jorge Fern�ndez Ruiz
 * @editDate 16/12/2021
 * @version 1.1
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

		passPassword = new JPasswordField();
		buttons = new ArrayList<>();
		labels = new ArrayList<>();

		setLayout(new BorderLayout(0, 0));


//		fillButtons(txtBtnLogin, txtBtnReturn);
//		fillPanes();
//		setObjectsProperties(txtMailBodyHead, txtMail, txtPassword);
//		appendPaneObjects();

//		this.setContentPane(contentPane);
//		setUndecorated(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}

	/**
	 * Method that append to contentPane ({@link JPanel}) all objects.
	 */
	private void appendPaneObjects() {
		
		contentPane.add(buttons.get(0));
		contentPane.add(buttons.get(1));
		contentPane.add(txtFmail);
		contentPane.add(labels.get(0));
		contentPane.add(labels.get(1));
		contentPane.add(passPassword);
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
		contentPane.setLayout(null);

		// JButtons (ArrayList)
		buttons.get(0).setBounds(61, 159, 106, 23);
		buttons.get(1).setBounds(61, 193, 106, 23);

		// JTextField
		txtFmail.setToolTipText(txtMailBodyHead);
		txtFmail.setBounds(10, 31, 207, 20);
		txtFmail.setColumns(10);

		// JLabels (ArrayList)
		labels.get(0).setText(txtMail);
		labels.get(0).setBounds(84, 11, 48, 20);
		labels.get(1).setText(txtPassword);
		labels.get(1).setBounds(74, 62, 69, 20);

		// JPasswordField
		passPassword.setBounds(10, 90, 207, 20);
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
	 * Method that set properties of the view.
	 * 
	 * @param txtTitle type of {@link String}, the text of title.
	 */
	private void defaultOperations(String txtTitle) {

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