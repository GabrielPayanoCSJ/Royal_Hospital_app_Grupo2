/**
 * 
 */

// PACKAGE
package hospital.ftp.client.view.panels;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import hospital.languages.Language;

/**
 * @author Guillermo González de Miguel
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
		this.tree = generateTree();
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
	 * @author Gabriel Payano
	 * @param tree the tree
	 */
	public void setTree(JTree tree) {
		this.tree = tree;
	}
	
	//GENERATES A JTREE TO DISPLAY IN THE DIRECTORY PANEL WITH ONLY ONE ELEMENT "ROOT" ("/")
	/**
	 * GENERATES A JTREE TO DISPLAY IN THE DIRECTORY PANEL WITH ONLY ONE ELEMENT "ROOT" ("/").
	 * @author Gabriel Payano.
	 * @return JTree Type , returns an JTRee to display in the display panel.
	 */
	private static JTree generateTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("/");
		final JTree myTree = new JTree(root);
		return myTree;
	}

}
