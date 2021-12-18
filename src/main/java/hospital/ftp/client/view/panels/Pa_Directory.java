/**
 * 
 */

// PACKAGE
package hospital.ftp.client.view.panels;

// IMPORTS
import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;

import hospital.languages.Language;

/**
 * @author Guillermo Gonz�lez de Miguel
 * @version 1.0
 *
 */
public class Pa_Directory extends JPanel {
	
	/**
	 * 
	 */
	private JTree tree;

	/**
	 * 
	 */
	private JScrollPane scroll;
	
	/**
	 * 
	 */
	public Pa_Directory() {
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(), Language.getFtpClient_txts(6)), new EmptyBorder(5,5,5,5)));
		this.tree = new JTree(new DefaultMutableTreeNode("\\"));
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
	/**
	 * 
	 * @param tree the actual tree
	 */
	public void setTree(JTree tree) {
		this.tree = tree;
		this.tree.updateUI();
	}

}
