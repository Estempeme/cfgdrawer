/**
 * 
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author jan
 *
 */
public class Startpanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Startpanel() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmBeedtruiea = new JMenuItem("Beedtruiea");
		mnNewMenu.add(mntmBeedtruiea);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JList<?> list = new JList();
		getContentPane().add(list);
		
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(comboBox, popupMenu);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame Startpanel = new Startpanel();
		
		Startpanel.setVisible(true);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
