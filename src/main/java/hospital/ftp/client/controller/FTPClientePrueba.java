package hospital.ftp.client.controller;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.*;


public class FTPClientePrueba {

	

	
	
	
	public static void main(String[] args) {
		
		FTPClient cliente = new FTPClient();
		FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_L8);
		
		cliente.configure(config);
		try {
			cliente.connect("localhost");
			cliente.enterRemotePassiveMode();
			cliente.login("medico3", "1234");

			System.out.println("Conexion correcta");
			File archivo = new File("C:\\Users\\Profesor\\Documents\\foldermedicos");
			System.out.println(archivo.exists());

			
			
			FTPFile[] files = cliente.listFiles();
			 
			// iterates sobre los archivos e inprime los detalles de cada uno
			DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			for (FTPFile file : files) {
			    String details = file.getName();
			    if (file.isDirectory()) {
			        details = "[" + details + "]";
			    }
			    details += "\t\t" + file.getSize();
			    details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
			    System.out.println(details);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
