package hospital.mail.server.controller;

public class aaa {

	public static void main(String[] args) {
		
	        try
	        {
	            Utils_Methods gmail = new Utils_Methods();
//	            gmail.connect();
	            gmail.openFolder("INBOX");
	            gmail.printAllMessages();

	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            System.exit(-1);
	        }
	    }
	}
