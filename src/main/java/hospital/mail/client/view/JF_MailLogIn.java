package hospital.mail.client.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class JF_MailLogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFcorreo;
	private JPasswordField passPassword;
	private JButton btnVolver;
	private static JButton btnLogin = new JButton("Log-In");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		final JF_MailLogIn frame = new JF_MailLogIn();
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JF_MailLogIn() {
		setResizable(false);
		setTitle("Log Correo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnLogin.setBounds(61, 159, 106, 23);
		contentPane.add(btnLogin);
		
		txtFcorreo = new JTextField();
		txtFcorreo.setToolTipText("Escriba aqui su correo");
		txtFcorreo.setBounds(10, 31, 207, 20);
		contentPane.add(txtFcorreo);
		txtFcorreo.setColumns(10);
		
		JTextPane txtpnCorreo = new JTextPane();
		txtpnCorreo.setText("Correo:");
		txtpnCorreo.setBounds(84, 11, 48, 20);
		contentPane.add(txtpnCorreo);
		
		passPassword = new JPasswordField();
		passPassword.setBounds(10, 90, 207, 20);
		contentPane.add(passPassword);
		
		JTextPane txtpnContrasea = new JTextPane();
		txtpnContrasea.setText("Contrase\u00F1a:");
		txtpnContrasea.setBounds(74, 62, 69, 20);
		contentPane.add(txtpnContrasea);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(61, 193, 106, 23);
		contentPane.add(btnVolver);
	}
}
