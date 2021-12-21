package hospital.mail.server.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3SSLStore;

/**
 * View for write a mail. Has a panel for the description, for the body and for
 * the buttons (all JPanel). Has a BorderLayout.
 * 
 * @author Javier Gómez Villasclaras
 * @date 17/12/2021
 * @version 1.0
 */
public class Utils_Methods {

	/**
	 * Constructor. Empty constructor in case we need to instantiate the class
	 */
	Utils_Methods() {

	}

	/**
	 * Variables
	 * 
	 */
	private static Session session;
	private static POP3SSLStore store;
	private static String username = "testhospitalroyale1";
	private static String password = "estoesuntest";
	private static POP3Folder folder;
	public static String numberOfFiles = null;
	public static int toCheck = 0;
	public static Writer output = null;
	static URLName url;
	public static String receiving_attachments = "C:\\Users\\Profesor\\Documents\\Correos";

	/**
	 * Email Send Method.
	 * 
	 * @param origen  Where the messages comes from.
	 * @param destino To whom you want to send it to.
	 * @param host    Host of mail.
	 * @param mensaje Bulk ot the text message.
	 * @param tema    Subject of the mail.
	 * @author Javier Gómez Villasclaras
	 */
	public static void enviaremail(String origen, String destino, String host, String mensaje, String tema) {

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try {

			// Mime es un tipo de formato de email, lo podemos cambiar
			MimeMessage message = new MimeMessage(session);

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
			message.setSubject(tema);
			message.setText(mensaje);

			Transport.send(message);
			System.out.println("Mensaje enviado");

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private static String USER_NAME = "testhospitalroyale1"; // GMail username
	 * (la parte antes de "@gmail.com") private static String PASSWORD =
	 * "estoesuntest"; // GMail pass private static String RECIPIENT =
	 * "testhospitalroyale1@gmail.com";
	 * 
	 * public static void main(String[] args) { String from = USER_NAME; String pass
	 * = PASSWORD; String[] to = { RECIPIENT }; // list of recipient email addresses
	 * String subject = "Ejemplo de envio de email"; String body =
	 * "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet massa sapien. "
	 * +
	 * "Etiam fringilla eros sed purus tempor, at molestie turpis blandit. Vestibulum id justo odio. "
	 * +
	 * "In hac habitasse platea dictumst. Nunc libero lectus, facilisis et sem quis, ornare gravida nulla."
	 * +
	 * " Integer condimentum nulla nec odio posuere tincidunt. Proin congue consequat ex ut elementum. "
	 * ;
	 * 
	 * sendFromGMail(from, pass, to, subject, body);
	 * System.out.println("Correo Enviado"); }
	 */

	/**
	 * Email Send Method especific to G-MAIL.
	 * 
	 * @param origen  Where the messages comes from.
	 * @param destino To whom you want to send it to.
	 * @param host    Host of mail.
	 * @param mensaje Bulk ot the text message.
	 * @param tema    Subject of the mail.
	 * @author Javier Gómez Villasclaras
	 */
	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props = propertiessmtp(host, from, pass);

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// Recorremos el array de direcciones
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	/**
	 * Connection method for POP3.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static void connect() throws Exception {
		Properties pop3Props = new Properties();
		pop3Props = propertiesPOP3();
		url = new URLName("pop3", "pop.gmail.com", 995, "", username, password);
		session = Session.getInstance(pop3Props, null);
		store = new POP3SSLStore(session, url);
		store.connect();
	}

	/**
	 * Connection method for POP3.
	 * 
	 * This method will save the emails for later display of such.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static void openFolder(String folderName) throws Exception {
		folder = (POP3Folder) store.getFolder(folderName);
		System.out.println((new StringBuilder("For test----")).append(folder.getParent().getFullName()).toString());
		if (folder == null)
			throw new Exception("Invalid folder");
		try {
			folder.open(2);
			System.out.println((new StringBuilder("Nombre Carpeta----")).append(folder.getFullName()).toString());
		} catch (Exception ex) {
			System.out.println((new StringBuilder("Excepción al abrir carpeta..")).append(ex).toString());
		}
	}

	public void closeFolder() throws Exception {
		folder.close(false);
	}

	public int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	public int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	public void disconnect() throws Exception {
		store.close();
	}

	/**
	 * Connection method for POP3.
	 * 
	 * This method will save the emails for later display of such.
	 * 
	 * @param message We will utilize this to read the text of the message
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	private static String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			String result = "";
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			int count = mimeMultipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + "\n" + bodyPart.getContent();
					break; // Sin break vuelve a aparecer el texto
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + "\n" + Jsoup.parse(html).text();
				}
			}
			return result;
		}
		return "";
	}

	/**
	 * Main method to present the text in the view
	 * 
	 * It makes an array of messages then it makes a "for" loop to present them.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static void printAllMessages() throws Exception {
		Message msgs[] = folder.getMessages();

		FetchProfile fp = new FetchProfile();
		folder.fetch(msgs, fp);
		for (int i = 0; i < msgs.length; i++) {
			Message message = msgs[i];
			dumpEnvelope(msgs[i]);
			System.out.println("==============================");
			System.out.println("Email #" + (i + 1));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + getTextFromMessage(message));
		}
	}

	/**
	 * Method to save file.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static int saveFile(File saveFile, Part part) throws Exception {

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveFile));

		byte[] buff = new byte[2048];
		InputStream is = part.getInputStream();
		int ret = 0, count = 0;
		while ((ret = is.read(buff)) > 0) {
			bos.write(buff, 0, ret);
			count += ret;
		}
		bos.close();
		is.close();
		return count;
	}

	/**
	 * Method to translate the message from multimedia type to plain text
	 * 
	 * @param m The message to translate.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	private static void dumpEnvelope(Message m) throws Exception {
		String body = "";
		String path = "";

		Object content = m.getContent();
		if (content instanceof String) {
			body = (String) content;
		} else if (content instanceof Multipart) {
			Multipart mp = (Multipart) content;
			for (int j = 0; j < mp.getCount(); j++) {
				Part part = mp.getBodyPart(j);
				String disposition = part.getDisposition();
				// System.out.println("test disposition---->>"+disposition);
				if (disposition == null) {
					// Check if plain
					MimeBodyPart mbp = (MimeBodyPart) part;
					if (mbp.isMimeType("text/plain")) {
						body += mbp.getContent().toString();
					} else if (mbp.isMimeType("TEXT/HTML")) {
						body += mbp.getContent().toString();
					} else {
						// desconocido
					}
				} else if ((disposition != null)
						&& (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE)
								|| disposition.equals("ATTACHMENT") || disposition.equals("INLINE"))) {

					MimeBodyPart mbp = (MimeBodyPart) part;
					if (mbp.isMimeType("text/plain")) {
						body += (String) mbp.getContent();
					} else if (mbp.isMimeType("TEXT/HTML")) {
						body += mbp.getContent().toString();
					} else {
						File savedir = new File(receiving_attachments);
						savedir.mkdirs();
						File savefile = new File(savedir + "\\" + part.getFileName());
						path = savefile.getAbsolutePath();

					}
				}
			}
		}
	}

	/**
	 * Authentification method for our mail login.
	 * 
	 * We will use gmail services to verify the user in te login.
	 * 
	 * @param user User we want to use.
	 * @param pass Password of the user.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static boolean userauth(String user, String pass) throws MessagingException {
		boolean exists = false;
		Properties props = System.getProperties();
		props = propertiesauth(user, pass);
		String host = "smtp.gmail.com";
		Session session = Session.getDefaultInstance(props);
		Transport transport2 = session.getTransport("smtp");
		System.out.println(user + " " + pass);

		try {
			transport2.connect(host, user, pass);
			exists = true;
			transport2.close();
		} catch (AuthenticationFailedException e) {
			System.out.println("Comprueba el usuario y la contraseña");
			transport2.close();
		} catch (MessagingException e) {
			transport2.close();
		}
		return exists;
	}

	/**
	 * Contained method of the properties of the authentification
	 * 
	 * @param user User we want to use.
	 * @param pass Password of the user.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static Properties propertiesauth(String user, String pass) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		return props;
	}

	/**
	 * Contained method of the properties of smtp
	 * 
	 * @param user User we want to use.
	 * @param pass Password of the user.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static Properties propertiessmtp(String host, String from, String pass) {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		return props;
	}

	/**
	 * Contained method of the properties of POP3
	 * 
	 * @param user User we want to use.
	 * @param pass Password of the user.
	 * 
	 * @author Javier Gómez Villasclaras
	 */
	public static Properties propertiesPOP3() {
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties pop3Props = new Properties();
		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
		pop3Props.setProperty("mail.pop3.port", "995");
		pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

		return pop3Props;
	}

}