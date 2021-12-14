/**
 * 
 */

// PACKAGE
package hospital.ftp.client.view.panels;

// IMPORTS
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 
 *
 */
public class Pa_Directory extends JPanel {
	
	private JTree tree;
	private JScrollPane scroll;
	
	/**
	 * 
	 */
	public Pa_Directory() {
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), "REMOTO"), new EmptyBorder(5,5,5,5)));
		this.tree = new JTree();
		this.scroll = new JScrollPane(tree);
		this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
	}

	/**
	 * @return the tree
	 */
	public JTree getTree() {
		return tree;
	}

}
