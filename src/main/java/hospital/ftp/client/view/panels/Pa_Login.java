/**
 * 
 */
package hospital.ftp.client.view.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import hospital.languages.Language;

/**
 * @author 
 *
 */
public class Pa_Login extends JPanel {

	private JLabel lbl_user;
	private JLabel lbl_passwd;
	private JTextField tfield_user;
	private JPasswordField pfield_passwd;
	private ArrayList<JButton> buttons;
	
	/**
	 * 
	 */
	public Pa_Login() {
		this.buttons = new ArrayList<JButton>();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.buttons.add(new JButton(Language.getFtpClient_txts(8)));
		this.buttons.add(new JButton(Language.getFtpClient_txts(12)));
		
		lbl_user = new JLabel(Language.getFtpClient_txts(9));
		lbl_user.setBorder(new EmptyBorder(5, 5, 5, 5));
		lbl_user.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_passwd = new JLabel(Language.getFtpClient_txts(10));
		lbl_passwd.setBorder(new EmptyBorder(5, 5, 0, 5));
		lbl_passwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_passwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tfield_user = new JTextField();
		tfield_user.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_user.setLabelFor(tfield_user);
		pfield_passwd = new JPasswordField();
		lbl_passwd.setLabelFor(pfield_passwd);
		this.setLayout(new GridLayout(1, 7, 0, 0));
		this.add(lbl_user);
		this.add(tfield_user);
		this.add(lbl_passwd);
		this.add(pfield_passwd);
		this.add(new JLabel());
		
		for (int i = 0; i < buttons.size(); i++) {
			
			if(i == 1)
				this.buttons.get(i).setVisible(false);
			
			this.buttons.get(i).setName(String.valueOf(i));
			this.add(buttons.get(i));
		}
	}

	/**
	 * @return the lbl_user
	 */
	public JLabel getLbl_user() {
		return lbl_user;
	}

	/**
	 * @return the lbl_passwd
	 */
	public JLabel getLbl_passwd() {
		return lbl_passwd;
	}

	/**
	 * @return the tfield_user
	 */
	public JTextField getTfield_user() {
		return tfield_user;
	}

	/**
	 * @return the pfield_passwd
	 */
	public JPasswordField getPfield_passwd() {
		return pfield_passwd;
	}

	/**
	 * @return the buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
}
