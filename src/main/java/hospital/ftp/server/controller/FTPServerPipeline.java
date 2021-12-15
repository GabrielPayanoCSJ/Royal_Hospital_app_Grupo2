/**
 * 
 */
package hospital.ftp.server.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @author prodi
 *
 */
public class FTPServerPipeline {

	/**
	 * 
	 */
	public FTPServerPipeline() {
	}

	//Comprobar si es correcto de esta manera
	public void UploadFile(FTPClient cliente,String origen,String destino) {
		/*try {
			InputStream fis = new FileInputStream(origen);
			cliente.storeFile(destino, fis);
			fis.close();
		} catch (Exception e) {
			System.out.println("Excepción en el upload file");
		}*/
	}
	
	public void DownloadFile() {
		
	}
	
}
