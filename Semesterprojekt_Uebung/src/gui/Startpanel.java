/**
 * 
 */
package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	JMenuBar menuBar;
	JMenu mnNewMenu;
	JMenuItem mntmBeedtruiea;
	JLabel lblNewLabel;
	JButton btnNewButton;
	JButton startButton;
	ActionListener l;
	ActionListener l2;
	JPanel panel1, panel2, panel3;
	
	public Startpanel() {
		
		
		panel1 = new JPanel(new GridBagLayout());
		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel();
		getContentPane().add(panel1, 0);
		//getContentPane().add(panel2, 1);
		//getContentPane().add(panel3, 1);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		mntmBeedtruiea = new JMenuItem("Beedtruiea");
		mnNewMenu.add(mntmBeedtruiea);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel);
		
		btnNewButton = new JButton("New button");
		Dimension m = new Dimension();
		m.setSize(75, 25);
		btnNewButton.setSize(m);
		panel1.add(btnNewButton);
		l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(chooseFile());
			}
		};
		btnNewButton.addActionListener(l);
		startButton = new JButton("START");
		Dimension m2 = new Dimension();
		m2.setSize(75, 25);
		startButton.setSize(m2);
		panel1.add(startButton);
		l2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Graph started.");
			}
		};
		startButton.addActionListener(l2);

		
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Startpanel startpanel = new Startpanel();
		startpanel.setSize(500, 300);
		startpanel.setLocationRelativeTo(null);
		startpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startpanel.setVisible(true);
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
	
	public String chooseFile() {
		JFileChooser chooser = new JFileChooser();
		getContentPane().add(chooser);
		String inputPath = "";

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			inputPath = chooser.getSelectedFile().getAbsolutePath();
		} else {
			// Exception
			inputPath = "";
		}
		return inputPath;
	}
}
