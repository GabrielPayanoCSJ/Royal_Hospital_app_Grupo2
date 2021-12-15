/**
 * 
 */
package hospital.ftp.client.view.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;


/**
 * @author 
 *
 */
public class Pa_Login extends JPanel {

	private JLabel lbl_user;
	private JLabel lbl_passwd;
	
	private JTextField tfield_user;
	private JTextField tfiled_passwd;
	
	private JButton btn_connect;
	
	/**
	 * 
	 */
	public Pa_Login() {
		this.setName("panel_login");
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lbl_user = new JLabel("User:");
		lbl_user.setBorder(new EmptyBorder(5, 5, 5, 5));
		lbl_user.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_passwd = new JLabel("Password:");
		lbl_passwd.setBorder(new EmptyBorder(5, 5, 0, 5));
		lbl_passwd.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_passwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tfield_user = new JTextField();
		tfield_user.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_user.setLabelFor(tfield_user);
		tfiled_passwd = new JTextField();
		lbl_passwd.setLabelFor(tfiled_passwd);
		this.setLayout(new GridLayout(1, 2, 0, 0));

		this.add(lbl_user);
		this.add(tfield_user);
		this.add(lbl_passwd);
		this.add(tfiled_passwd);
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
	 * @return the tfiled_passwd
	 */
	public JTextField getTfiled_passwd() {
		return tfiled_passwd;
	}

	/**
	 * @return the btn_connect
	 */
	public JButton getBtn_connect() {
		return btn_connect;
	}
}
