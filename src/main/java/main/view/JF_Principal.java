package main.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class JF_Principal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCorreo;
	private JButton btnFTP;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JF_Principal frame = new JF_Principal();
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
	public JF_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombres = new JLabel("Grupo 2\r\n\t-Gabriel\r\n\t-Jorge\r\n\t-Guillermo\r\n\t-Javi");
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombres.setBounds(0, 0, 434, 103);
		contentPane.add(lblNombres);
		
		btnFTP = new JButton("FTP");
		btnFTP.setBounds(32, 192, 113, 49);
		contentPane.add(btnFTP);
		
		btnCorreo = new JButton("Correo");
		btnCorreo.setBounds(287, 192, 113, 49);
		contentPane.add(btnCorreo);
	}

	public JButton getBtnCorreo() {
		return btnCorreo;
	}

	public void setBtnCorreo(JButton btnCorreo) {
		this.btnCorreo = btnCorreo;
	}

	public JButton getBtnFTP() {
		return btnFTP;
	}

	public void setBtnFTP(JButton btnFTP) {
		this.btnFTP = btnFTP;
	}
	
}
