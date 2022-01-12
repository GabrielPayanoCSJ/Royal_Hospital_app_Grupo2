/**
 * 
 */

package hospital.mail.client.view.panels.LoginMail;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Guillermo González de Miguel
 *
 */
public class Pa_Login extends JPanel {

	/**
	 * 
	 */
	private ArrayList<JPanel> panels;
	
	/**
	 * 
	 */
	private ArrayList<JButton> buttons;
	
	/**
	 * 
	 */
	private ArrayList<JLabel> labels;
	
	/**
	 * 
	 */
	private JTextField txtFmail;
	
	/**
	 * 
	 */
	private JPasswordField passPassword;

	/**
	 * 
	 * @param txtLblHeader
	 * @param txtLblMail
	 * @param txtLblPass
	 * @param txtBtnLogin
	 * @param txtBtnCancel
	 */
	public Pa_Login(String txtLblHeader, String txtLblMail, String txtLblPass, String txtBtnLogin,
			String txtBtnCancel) {
		this.setLayout(new BorderLayout());
		generateJLabel(new ArrayList<String>(Arrays.asList(txtLblHeader, txtLblMail, txtLblPass)));
		generateJButton(new ArrayList<String>(Arrays.asList(txtBtnLogin, txtBtnCancel)));
		generateJPanel(6);
		confJPanel();
	}

	/**
	 * 
	 */
	private void confJPanel() {

		// GRID LAYOUT 3 - BUTTONS
		GridLayout gridlayout = new GridLayout(1, 2);
		gridlayout.setHgap(25); // MARGIN BUTTON

		// GRID LAYOUT 3 - BUTTONS
		this.panels.get(5).setLayout(gridlayout);
		this.panels.get(5).add(this.buttons.get(0));
		this.panels.get(5).add(this.buttons.get(1));
		this.panels.get(5).setPreferredSize(new Dimension(100, 80));
		this.panels.get(5).setBorder(new EmptyBorder(20, 0, 0, 0));

		// GRID LAYOUT 2 - PASSWORD
		this.passPassword = new JPasswordField();
		this.panels.get(4).setLayout(new GridLayout(2, 1));
		this.panels.get(4).add(this.labels.get(2));
		this.panels.get(4).add(this.passPassword);
		this.panels.get(4).setPreferredSize(new Dimension(400, 100));

		// GRID LAYOUT 1 - EMAIL
		this.txtFmail = new JTextField();
		this.panels.get(3).setLayout(new GridLayout(2, 1));
		this.panels.get(3).setPreferredSize(new Dimension(400, 100));
		this.panels.get(3).add(this.labels.get(1));
		this.panels.get(3).add(this.txtFmail);

		// ADD GRID PANELS TO BOX PANEL
		this.panels.get(2).setLayout(new BoxLayout(this.panels.get(2), BoxLayout.Y_AXIS));
		this.panels.get(2).add(this.panels.get(3)); // ADD GRID LAYOUT 1
		this.panels.get(2).add(this.panels.get(4)); // ADD GRID LAYOUT 2
		this.panels.get(2).add(this.panels.get(5)); // ADD GRID LAYOUT 3

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
	 * @return the buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	/**
	 * @return the txtFmail
	 */
	public JTextField getTxtFmail() {
		return txtFmail;
	}

	/**
	 * @return the passPassword
	 */
	public JPasswordField getPassPassword() {
		return passPassword;
	}
	
}
