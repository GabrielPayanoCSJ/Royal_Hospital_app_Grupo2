package hospital.mail.server.controller;

public class aaa {

	public static void main(String[] args) {
		
	        try
	        {
	            Aux_Methods gmail = new Aux_Methods();
	            gmail.connect();
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
