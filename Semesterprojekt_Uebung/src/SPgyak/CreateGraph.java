/**
 * 
 * @author Mihaly Ijjas
 *
 */

package SPgyak;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

/*
 import org.eclipse.ui.plugin.AbstractUIPlugin;
 import org.osgi.framework.BundleContext;
 */

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;




/*// these are only for the plug-in 
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
*/
import graphviz.GraphViz;

public class CreateGraph {

	/*
	 * Questions: - Should the plug-in close the opened window(s) after opening
	 * a new one? (Currently it does not.) - Make a menu for different
	 * functions, like "draw graph", "save graph" ect...? - Save an absolute
	 * path of a selected dictionary for a faster access of input files?
	 */

	/*
	 * - First: "build": make the code nice.
	 * - Separate nodes and edges come and save to a database. 
	 * - Make a dot file from database after execution is finished. 
	 * - Draw graph.
	 */

	// For testing, until we don't have a given (relative?) path
	// to the input-file, we have a variable for it.
	private static String eigenerPfad = "/Users/Misi HP/Documents/Iskola/Humboldt/programok";

	/*
	 * These two are for determinating the global output types. globalType can
	 * be changed freely (see function start()) If globalType is set to pdf, the
	 * image-reading function does not work. With png it's fine.
	 */
	private static String globalType = "png";
	private static String globalRepesentationType = "dot";

	// This is the "main" function, it describes what will happen
	// when the plug-in's button is pressed.
	public static void main(String[] args) {
		
		
		pictureToScreen(graphToImageAndSave(incomingGraph(8, 3)));
		
		//readAndPutWithJFrame(0);
		// start();
		// start2();
		// ciklikus();
		
		
		
		
	}
	
	
	
	/*This function creates a graph directly from the incoming information.
	* Let's see how effective is it...
	* The parameters are the number of the nodes (vertices) in the graph 
	*	and the average number of edges per node.
	* Note: vertices without nodes aren't shown and are not part of the graph.
	*/
	public static GraphViz incomingGraph(int num_nodes, int avg_edges) {
		
		GraphViz gv = new GraphViz();
		
		//Create graph
		gv.addln(gv.start_graph());
		for (int i = 0; i < num_nodes*avg_edges;i++) {
			int node1 = (int)(Math.random()*num_nodes);
			int node2 = (int)(Math.random()*num_nodes);
			gv.addln(node1+" -> "+node2+";");
		}
		gv.addln(gv.end_graph());
		return gv;
	}
	

	// Reads simple.dot and puts it out in a JFrame window
	// and saves as an image.
	// If filechooser=0 then use default input, otherways use the file chooser.
	private static void readAndPutWithJFrame(int filechooser) {

		if (filechooser == 0) {
			GraphViz gv = readFromFile();
			writeToFile(gv);
		} else {

			GraphViz gv = chooseInputFile();
			writeToFile(gv);
		}
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:" + eigenerPfad + "/ausgabe."
					+ globalType));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pictureToScreen(img);
	}

	// Opens a file chooser to choose an input graph file.
	public static GraphViz chooseInputFile() {
		GraphViz gv = new GraphViz();
		JFileChooser chooser = new JFileChooser();
		String inputPath = "";

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			inputPath = chooser.getSelectedFile().getAbsolutePath();
		} else {
			inputPath = "C:" + eigenerPfad + "/SPgyak/simple.dot";
		}

		gv.readSource(inputPath);
		System.out.println(gv.getDotSource());
		return gv;
	}
	
	
	//saves the graph as an image and reads it in as a BufferedImage
	private static BufferedImage graphToImageAndSave(GraphViz gv) {
		BufferedImage img = null;
		
		//It's the same as writeToFile(), but saves a separate file.
		File out = new File("C:" + eigenerPfad + "/temp." + globalType);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), globalType,
				globalRepesentationType), out);
		try {
			img = ImageIO.read(new File("C:" + eigenerPfad + "/temp."
					+ globalType));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return img;
	}
	
	
	// Draw a picture on the screen.
	private static void pictureToScreen(BufferedImage img) {
		JFrame frame = new JFrame("And there was the Graph...");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon(img);
		frame.setLayout(new FlowLayout());
		
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		
	    frame.setLayout(new BorderLayout());

	    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
	    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);
	    
	    //vbar.setUnitIncrement(100); // doesn't work right now
	    lbl.add(hbar, BorderLayout.SOUTH);
	    lbl.add(vbar, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(lbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setBounds(50, 30, 300, 50);
        
        frame.add(scrollPane);
        /*
        JPanel contentPane = new JPanel(null);
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        */
		// frame.setSize(400, 400);
		frame.pack(); // sizes the window to the size of the picture
		// window will appear in the middle of the screen
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

	}

	// reads the graph from the file simple.dot
	// plan: make an other function that reads from the file given as a
	// parameter (?)
	private static GraphViz readFromFile() {
		String input = "C:" + eigenerPfad + "/cfgdrawer/Semesterprojekt_Uebung/simple.dot";
		GraphViz gv = new GraphViz();
		gv.readSource(input);
		System.out.println(gv.getDotSource());
		return gv;
	}

	private static void writeToFile(GraphViz gv) {
		File out = new File("C:" + eigenerPfad + "/ausgabe." + globalType);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), globalType,
				globalRepesentationType), out);
	}

	// Erstellt und zeihnet den Graphen in einer Schleife n-mal erneut.
	private void ciklikus() {

		for (int i = 0; i < 10; i++) {
			GraphViz gv = new GraphViz();
			gv.addln(gv.start_graph());
			gv.addln("1 -> 2;");
			gv.addln("2 -> 3;");
			gv.addln("3 -> 3;");
			gv.addln("3 -> 2;");
			String von = Integer.toString(i + 1);
			String zu = Integer.toString(i + 2);
			gv.addln(von + " -> " + zu + ";");
			gv.addln(gv.end_graph());

			String type = "pdf";
			String repesentationType = "dot";
			System.out.println(gv.getDotSource());

			File out = new File("C:" + eigenerPfad + "/ausgabe." + type); // Windows
			gv.writeGraphToFile(
					gv.getGraph(gv.getDotSource(), type, repesentationType),
					out);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// ab hier kommen die Funktionen aus Probe.java, aus GraphViz

	/**
	 * Construct a DOT graph in memory, convert it to image and store the image
	 * in the file system.
	 */
	private void start() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("1 -> 2;");
		gv.addln("2 -> 3;");
		gv.addln("3 -> 1;");
		gv.addln("3 -> 2;");
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		gv.increaseDpi(); // 106 dpi

		// String type = "gif";
		// String type = "dot";
		// String type = "fig"; // open with xfig
		String type = "pdf";
		// String type = "ps";
		// String type = "svg"; // open with inkscape
		// String type = "png";
		// String type = "plain";

		String repesentationType = "dot";
		// String repesentationType= "neato";
		// String repesentationType= "fdp";
		// String repesentationType= "sfdp";
		// String repesentationType= "twopi";
		// String repesentationType= "circo";

		// File out = new File("/tmp/out"+gv.getImageDpi()+"."+ type); // Linux
		File out = new File("C:" + eigenerPfad + "/ausgabe." + type); // Windows
		gv.writeGraphToFile(
				gv.getGraph(gv.getDotSource(), type, repesentationType), out);
	}

	/**
	 * Read the DOT source from a file, convert to image and store the image in
	 * the file system.
	 */
	private void start2() {
		/*
		 * String dir =
		 * "/home/jabba/Dropbox/git.projects/laszlo.own/graphviz-java-api"; //
		 * Linux String input = dir + "/sample/simple.dot";
		 */

		String input = "C:" + eigenerPfad + "/SPgyak/simple.dot"; // Windows
		GraphViz gv = new GraphViz();
		gv.readSource(input);
		System.out.println(gv.getDotSource());

		// String type = "gif";
		// String type = "dot";
		// String type = "fig"; // open with xfig
		String type = "pdf";
		// String type = "ps";
		// String type = "svg"; // open with inkscape
		// String type = "png";
		// String type = "plain";

		String repesentationType = "dot";
		// String repesentationType= "neato";
		// String repesentationType= "fdp";
		// String repesentationType= "sfdp";
		// String repesentationType= "twopi";
		// String repesentationType= "circo";

		// File out = new File("/tmp/simple." + type); // Linux
		File out = new File("C:" + eigenerPfad + "/ausgabe." + type); // Windows
		gv.writeGraphToFile(
				gv.getGraph(gv.getDotSource(), type, repesentationType), out);
	}

}
