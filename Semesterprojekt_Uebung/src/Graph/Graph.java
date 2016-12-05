package Graph;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import graphviz.GraphViz;

public class Graph {

	// A pointer to all of the nodes.
	public Instruction firstinst;
	private String type = Settings.getType();
	private String path = Settings.getPath();

	public Graph() {
		this.firstinst = null;
	}

	// Simply add a new edge to the graph.
	public void addEdge(int from, int to) {
		boolean debugprint = false;
		if (firstinst == null) { // There are no nodes yet.
			if (debugprint)
				System.out.println("Adding new edge " + to
						+ " to new graph's new instr " + from);
			firstinst = new Instruction(from, to);
		} else {
			Instruction instr = searchInstr(from);
			if (instr == null) { // This node doesn't yet exist.
				if (debugprint)
					System.out.println("Adding new edge " + to
							+ " to new instr " + from);
				/* instr = this. */addInstr(from, to);
				return;
			} else { // The node exists, add edge to it.
				if (debugprint)
					System.out.println("Adding new edge " + to + " to instr "
							+ from);
				instr.addEdge(to);
			}
		}
	}

	/*
	 * Search for the Instruction (node) with the ID id. Returns a pointer to
	 * it, if it exists. Otherways returns null.
	 */
	public Instruction searchInstr(int id) {
		// System.out.println("The ID you are looking for is: " + id);
		if (firstinst == null)
			return null;
		Instruction instr = firstinst;
		while (instr != null) {
			if (instr.getID() == id) {
				return instr;
			}
			instr = instr.getNextInst();
		}

		return instr;
	}

	/*
	 * Adds a new instruction (node) to the graph.
	 * 
	 * @param: id: the id of the instruction, to: the id of the instruction to
	 * which the edge leads
	 */
	public Instruction addInstr(int id, int to) {
		Instruction instr = new Instruction(id, to);
		if (this.firstinst == null) {
			firstinst = instr;
			return firstinst;
		}
		Instruction iterator = firstinst;

		while (iterator.getNextInst() != null) {
			if (iterator.getNextInst().getID() > id) {
				Instruction temp = iterator.getNextInst();
				iterator.setNextInst(instr);
				instr.setNextInst(temp);
				return instr;
			}
			iterator = iterator.getNextInst();
		}
		iterator.setNextInst(instr);
		return instr;
	}

	// Makes a picture of the graph.
	public void createPicture() {
		// System.out.println("Drawing Graph.");
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		Instruction inst = firstinst;

		// horizontal iteration through the different instructions
		while (inst != null) {
			Edge edge = inst.getEdge();
			int id = inst.getID();

			// on every instruction vertical iteration through all the edges
			// from that instr.
			while (edge != null) {
				int edgeTo = edge.getID(); // The instruction to which the edge
											// leads.
				// String error = inst.getError();
				int label = edge.getNum();
				String out = Integer.toString(id) + " -> " + edgeTo
						+ " [label=\"" + label + "\"];";
				// System.out.println(out);
				gv.addln(out);
				edge = edge.getNext();
			}
			inst = inst.getNextInst();
		}

		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());
		File out = new File(path);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, "dot"), out);
		// Put picture to screen.
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pictureToScreen(img);
	}

	// Draw a picture on the screen, with scrollbars.
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

		// vbar.setUnitIncrement(100); // doesn't work right now
		lbl.add(hbar, BorderLayout.SOUTH);
		lbl.add(vbar, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane(lbl);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.setBounds(50, 30, 300, 50);

		frame.add(scrollPane);
		frame.pack(); // sizes the window to the size of the picture
		// window will appear in the middle of the screen
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

}
