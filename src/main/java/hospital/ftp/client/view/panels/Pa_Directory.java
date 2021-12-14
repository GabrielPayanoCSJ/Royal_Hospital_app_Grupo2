/**
 * 
 */
package hospital.ftp.client.view.panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

/**
 * @author prodi
 *
 */
public class Pa_Directory extends JPanel {

	/**
	 * 
	 */
	public Pa_Directory() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		add(tree, BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}

}
