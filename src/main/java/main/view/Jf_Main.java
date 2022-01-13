package main.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import main.view.panels.Pa_Main;

public class Jf_Main extends JFrame {
	private Pa_Main paMain;

	public Jf_Main(String txtTitleFrame, String txtHeader, String txtLblLang, String txtBtnServerFTP, String txtBtnClientFTP,
			String txtBtnMailClient) {
		super(txtTitleFrame);
		this.paMain = new Pa_Main(txtHeader, txtLblLang, txtBtnServerFTP, txtBtnClientFTP, txtBtnMailClient);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(600, 550));
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.add(this.paMain);
	}

	public static void main(String[] args) {
		new Jf_Main("VENTANA PRINCIPAL", "EJECUTADOR PRINCIPAL", "Seleccione un idioma", "SERVIDOR FTP", "CLIENTE FTP", "CLIENTE CORREO")
				.setVisible(true);
	}

	/**
	 * @return the paMain
	 */
	public Pa_Main getPaMain() {
		return paMain;
	}

}
