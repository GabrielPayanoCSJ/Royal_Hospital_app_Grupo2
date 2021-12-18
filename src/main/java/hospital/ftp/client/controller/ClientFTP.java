/**
 * 
 */
package hospital.ftp.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import hospital.ftp.client.view.JF_FTPClient;
import hospital.ftp.client.view.panels.Pa_Buttons;
import hospital.ftp.client.view.panels.Pa_Directory;
import hospital.ftp.client.view.panels.Pa_Log;
import hospital.ftp.client.view.panels.Pa_Login;
import hospital.languages.Language;
import hospital.tools.Tool;

/**
 * @author Guillermo González de Miguel
 */
public class ClientFTP {
	private JF_FTPClient jfClient;
	private FTPClient cliente;

	private Pa_Login plogin;
	private Pa_Log plog;
	private Pa_Directory pdirectory;
	private Pa_Buttons pbutton;
	private final String LOCATION = "C:\\2ºDAM\\ACDA\\pruebas";

	/**
	 * 
	 */
	public ClientFTP(int language) {
		Language.selectLanguage(0);

		this.cliente = new FTPClient();
		this.jfClient = new JF_FTPClient();
		this.plogin = this.jfClient.getPanel_login();
		this.plog = this.jfClient.getPanel_log();
		this.pdirectory = this.jfClient.getPanel_directory();
		this.pbutton = this.jfClient.getPanel_button();
		this.jfClient.setVisible(true);

		this.plogin.getBtn_connect().addActionListener(new Ev_FTPConnect(this.cliente, jfClient));

		for (int i = 0; i < this.pbutton.getButtons().size(); i++) {
			this.pbutton.getButtons().get(i).addActionListener(new Ev_FTPButtons(jfClient));
		}

	}

	/**
	 * 
	 * @author 
	 *
	 */
	class Ev_FTPButtons implements ActionListener {

		private JF_FTPClient jfClient;
		public Ev_FTPButtons(JF_FTPClient jfClient) {
			this.jfClient = jfClient;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			switch (Integer.parseInt(button.getName())) {
			case 0:
				System.out.println(Language.getFtpClient_txts(0));
				jfClient.askForNewFolderName();
				break;
			case 1:
				System.out.println(Language.getFtpClient_txts(1));
				try {
					jfClient.scanner(LOCATION);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				System.out.println(Language.getFtpClient_txts(11));
				break;
			case 3:
				System.out.println(Language.getFtpClient_txts(2));
				break;
			case 4:
				System.out.println(Language.getFtpClient_txts(3));
				break;
			case 5:
				System.out.println(Language.getFtpClient_txts(4));
				break;
			default:
				break;
			}
		}
	}

	class Ev_FTPConnect implements ActionListener {
		private FTPClient cliente;
		private JF_FTPClient jfClient;

		public Ev_FTPConnect(FTPClient cliente, JF_FTPClient jfClient) {
			this.cliente = cliente;
			this.jfClient = jfClient;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField user = this.jfClient.getPanel_login().getTfield_user();
			JPasswordField pass = this.jfClient.getPanel_login().getPfield_passwd();

			if (!user.getText().isEmpty() && pass.getPassword().length > 0) {

				try {
//					String usuario = "guillermo";
//					String clave = "123456";
					String servFTP = "localhost";
					System.out.println("Nos conectamos a: " + servFTP);
					this.cliente.connect(servFTP, 6000);

					String passwd = "";
					for (int i = 0; i < pass.getPassword().length; i++) {
						passwd += pass.getPassword()[i];
					}
					System.out.println("Contraseña: " + passwd);
					boolean login = cliente.login(user.getText(), passwd);

					if (login) {
						jfClient.getPanel_login().setVisible(false);
						System.out.println("Login correcto...");

						try {

							System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

							FTPFile[] ficheros = cliente.listFiles();
							boolean exist = cliente.changeWorkingDirectory("/Practica6");
							FTPFile[] fi = cliente.listFiles();
							System.out.println(exist + "\n" + cliente.getReplyString());

							System.out.println("Ficheros en el directorio actual: " + ficheros.length);

							String tipos[] = { "Fichero", "Directorio", "Enlace simb." };

							for (int i = 0; i < ficheros.length; i++) {
								System.out
										.println("\t" + ficheros[i].getName() + " => " + tipos[ficheros[i].getType()]);
							}

							System.out.println("Ficheros en el directorio actual: " + fi.length);

//							String tipos[] = { "Fichero", "Directorio", "Enlace simb." };

							for (int i = 0; i < fi.length; i++) {
								System.out.println("\t" + fi[i].getName() + " => " + tipos[fi[i].getType()]);
							}

//							boolean logout = cliente.logout();
//
//							if (logout)
//								System.out.println("Logout del servidor FTP...");
//							else
//								System.out.println("Error al hacer logout...");

//							cliente.disconnect();
//							System.out.println("Desconectado...");
						} catch (SocketException e2) {
							e2.printStackTrace();
						} catch (IOException e3) {
							e3.printStackTrace();
						}
					} else {
						System.out.println("Login Incorrecto...");
						cliente.disconnect();
//						System.exit(1);
					}

				} catch (SocketException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				Tool.showConsoleError("CAMPOS VACIO");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientFTP(1);
	}

}
