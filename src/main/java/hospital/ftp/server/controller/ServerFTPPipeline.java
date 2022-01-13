/**
 * 
 */
package hospital.ftp.server.controller;

/**
 * Pipe class.
 * 
 * @author prodi
 *
 */
public class ServerFTPPipeline {
	private boolean life = true;
	
	/**
	 * Constructor.
	 */
	public ServerFTPPipeline() {
//		new ServerController(this);
	}
	
	/**
	 * Stop the ServerFTPThread by changing the value of the life variable.
	 */
	public void stopThread() {
		// System.out.println("ME PARO ");
		life = false;
	}

	/**
	 * @return the life
	 */
	public boolean isLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(boolean life) {
		this.life = life;
	}

}
