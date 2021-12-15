package hospital.mail.server.controller;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Aux_Methods {
	
	Aux_Methods() {

	}
	
	public void enviaremail(String origen, String destino,String host,String mensaje) {
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		
		Session session = Session.getDefaultInstance(properties);
		
		try {
		
		//Mime es un tipo de formato de email, lo podemos cambiar 
		MimeMessage message = new MimeMessage(session);
		
		
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
