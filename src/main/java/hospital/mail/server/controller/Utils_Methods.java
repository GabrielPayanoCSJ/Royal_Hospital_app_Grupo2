package hospital.mail.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3SSLStore;

public class Utils_Methods {

	private static POP3SSLStore store;
	private static POP3Folder folder;
//	private static String numberOfFiles = null;
//	private static int toCheck = 0;
//	private static Writer output = null;
	private static URLName url;
//	private static String receiving_attachments = "C:\\Users\\Profesor\\Documents\\Correos";

	private static Session session;
	private static Properties properties = System.getProperties();
	private static Transport transport;
	private static MimeMessage message;

	private static final String SMTP = "smtp.gmail.com";
	private static final String POP3 = "pop.gmail.com";

	private static final int PORT_SMTP = 25;
	private static final int PORT_POP3 = 995;

	// private static String username =
	// "jorgefernandezruiz.sanjose@alumnado.fundacionloyola.net";
	// private static String password = "50008606";
	// private static String username = "testhospitalroyale1@gmail.com";
	// private static String password = "estoesuntest";

	private static String username = "";
	private static String password = "";

	/**
	 * 
	 * @param user
	 * @param pass
	 * @return
	 * @throws MessagingException
	 */
	public static boolean userauth(String user, String pass) throws MessagingException {
		boolean exists = false;
		createSession(true);
		transport = session.getTransport("smtp");
		try {
			transport.connect(SMTP, PORT_SMTP, user, pass);
			exists = true;
			transport.close();
		} catch (AuthenticationFailedException e) {
			transport.close();
		} catch (MessagingException e) {
			transport.close();
		}

		return exists;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public static void connect(String user, String pass) {
		try {
			createSession(false); // SESSION POP3
			url = new URLName("pop3", POP3, PORT_POP3, "", user, pass);
			store = new POP3SSLStore(session, url);
			store.connect();
			username = user;
			password = pass;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param to
	 * @param host
	 * @param menssage
	 * @param subject
	 */
//	public static void sendEmail(String to, String host, String menssage, String subject) {
//		properties.setProperty("mail.smtp.host", host);
//		session = Session.getDefaultInstance(properties);
//
//		try {
//			// Mime es un tipo de formato de email, lo podemos cambiar
//			message = new MimeMessage(session);
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setSubject(subject);
//			message.setText(menssage);
//			Transport.send(message);
////			System.out.println("Mensaje enviado");
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * @param type
	 */
	private static void createSession(boolean type) {

		if (type) { // SMTP SESSION

			properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", SMTP);
			properties.put("mail.smtp.user", username);
			properties.put("mail.smtp.password", password);
			properties.put("mail.smtp.port", String.valueOf(PORT_SMTP));
			properties.put("mail.smtp.auth", "true");

			session = Session.getDefaultInstance(properties);

		} else { // POP3 SESSION

			String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			properties = new Properties();
			properties.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
			properties.setProperty("mail.pop3.socketFactory.fallback", "false");
			properties.setProperty("mail.pop3.port", String.valueOf(PORT_POP3));
			properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(PORT_POP3));

			session = Session.getInstance(properties, null);
		}
	}

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public static void sendFromGMail(String[] to, String subject, String body) {
		createSession(true);
		message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(username));
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

			transport = session.getTransport("smtp");
			transport.connect(SMTP, PORT_SMTP, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	/**
	 * 
	 * @param folderName
	 * @throws Exception
	 */
	public static void openFolder(String folderName) throws Exception {
		folder = (POP3Folder) store.getFolder(folderName);
//		System.out.println((new StringBuilder("For test----")).append(folder.getParent().getFullName()).toString());
		if (folder == null)
			throw new Exception("Invalid folder");
		try {
			folder.open(2);
//			System.out.println((new StringBuilder("Nombre Carpeta----")).append(folder.getFullName()).toString());
		} catch (Exception ex) {
			System.out.println((new StringBuilder("Excepción al abrir carpeta..")).append(ex).toString());
		}
	}

	/**
	 * Método encargado de transformar el cuerpo de un mensaje a texto plano.
	 * 
	 * @param message de tipo String, Cuerpo de
	 * @return
	 */
	public static String getTextFromMessage(Message message) {
		String result = "";
		try {
			if (message.isMimeType("text/plain")) {
				result = message.getContent().toString();
			} else if (message.isMimeType("multipart/*")) {
				MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
				result = getTextFromMimeMultipart(mimeMultipart);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	public static String[] stringtoArray(String cadena) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		String[] correoscorrectos;
		ArrayList<String> lista = new ArrayList<String>();
		cadena = cadena.replaceAll("\\s+", "");
		Collections.addAll(lista, cadena.split(";"));
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		for (int i = 0; i < lista.size(); i++) {
			matcher = pattern.matcher(lista.get(i));
			if (!matcher.matches()) {
				lista.remove(i);
			}
		}
		correoscorrectos = new String[lista.size()];
		for (int i = 0; i < correoscorrectos.length; i++) {
			correoscorrectos[i] = lista.get(i);
		}
		return correoscorrectos;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void closeFolder() throws Exception {
		folder.close(false);
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	/**
	 * 
	 * @return
	 */
	public static POP3Folder getFolder() {
		return folder;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception {
		store.close();
	}

	// public static String getTextFromMessage(Message message) throws Exception {
	// if (message.isMimeType("text/plain")) {
	// return message.getContent().toString();
	// } else if (message.isMimeType("multipart/*")) {
	// String result = "";
	// MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	// int count = mimeMultipart.getCount();
	// for (int i = 0; i < count; i++) {
	// BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	// if (bodyPart.isMimeType("text/plain")) {
	// result = result + "\n" + bodyPart.getContent();
	// break; // Sin break vuelve a aparecer el texto
	// } else if (bodyPart.isMimeType("text/html")) {
	// String html = (String) bodyPart.getContent();
	// result = result + "\n" + Jsoup.parse(html).text();
	//
	// }
	// }
	// return result;
	// }
	// return "";
	// }

	// public static void printAllMessages() throws Exception {
	// Message msgs[] = folder.getMessages();
	//
	// FetchProfile fp = new FetchProfile();
	// folder.fetch(msgs, fp);
	// for (int i = 0; i < msgs.length; i++) {
	// Message message = msgs[i];
	// dumpEnvelope(msgs[i]);
	// System.out.println("==============================");
	// System.out.println("Email #" + (i + 1));
	// System.out.println("Subject: " + message.getSubject());
	// System.out.println("From: " + message.getFrom()[0]);
	// System.out.println("Text: " + getTextFromMessage(message));
	// }
	// }

	// public static int saveFile(File saveFile, Part part) throws Exception {
	//
	// BufferedOutputStream bos = new BufferedOutputStream(new
	// FileOutputStream(saveFile));
	//
	// byte[] buff = new byte[2048];
	// InputStream is = part.getInputStream();
	// int ret = 0, count = 0;
	// while ((ret = is.read(buff)) > 0) {
	// bos.write(buff, 0, ret);
	// count += ret;
	// }
	// bos.close();
	// is.close();
	// return count;
	// }

	// public static void dumpEnvelope(Message m) throws Exception {
	// String body = "";
	// String path = "";
	// int size = 0;
	//
	// Object content = m.getContent();
	// if (content instanceof String) {
	// body = (String) content;
	// } else if (content instanceof Multipart) {
	// Multipart mp = (Multipart) content;
	// m.setContent(body, "text/html;charset=utf-8");
	// for (int j = 0; j < mp.getCount(); j++) {
	// Part part = mp.getBodyPart(j);
	// String disposition = part.getDisposition();
	// // System.out.println("test disposition---->>"+disposition);
	// if (disposition == null) {
	// // Check if plain
	// MimeBodyPart mbp = (MimeBodyPart) part;
	// if (mbp.isMimeType("text/plain")) {
	// body += mbp.getContent().toString();
	// } else if (mbp.isMimeType("TEXT/HTML")) {
	// body += mbp.getContent().toString();
	// } else {
	// // desconocido
	// }
	// } else if ((disposition != null)
	// && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE)
	// || disposition.equals("ATTACHMENT") || disposition.equals("INLINE"))) {
	//
	// MimeBodyPart mbp = (MimeBodyPart) part;
	// if (mbp.isMimeType("text/plain")) {
	// body += (String) mbp.getContent();
	// } else if (mbp.isMimeType("TEXT/HTML")) {
	// body += mbp.getContent().toString();
	// } else {
	// File savedir = new File(receiving_attachments);
	// savedir.mkdirs();
	// File savefile = new File(savedir + "\\" + part.getFileName());
	// path = savefile.getAbsolutePath();
	// size = saveFile(savefile, part);
	//
	// }
	// }
	// }
	// }
	// }

	// public static void main(String[] args) {
	// String from = USER_NAME;
	// String pass = PASSWORD;
	// String[] to = { RECIPIENT }; // list of recipient email addresses
	// String subject = "Ejemplo de envio de email";
	// String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.
	// Curabitur sit amet massa sapien. "
	// + "Etiam fringilla eros sed purus tempor, at molestie turpis blandit.
	// Vestibulum id justo odio. "
	// + "In hac habitasse platea dictumst. Nunc libero lectus, facilisis et sem
	// quis, ornare gravida nulla."
	// + " Integer condimentum nulla nec odio posuere tincidunt. Proin congue
	// consequat ex ut elementum. ";
	//
	// sendFromGMail(from, pass, to, subject, body);
	// System.out.println("Correo Enviado");
	// }
}
