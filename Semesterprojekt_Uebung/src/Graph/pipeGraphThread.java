package Graph;

import java.io.IOException;
import java.io.PipedReader;

/*
 * This class reads info from the pipe and creates the graph.
 */
public class pipeGraphThread extends Thread {
	
	private Graph g;
	private PipedReader pr;
	
	public pipeGraphThread(PipedReader pr) {
		this.pr = pr;
	}
	
	public void run() {
		g = new Graph();
		int input;
		try {
			while ((input = pr.read()) != -1){
				int from = input;
				System.out.print(Integer.toString(input)+" ");
				if ((input = pr.read()) != -1) return;
				int to = input;
				//System.out.println(Integer.toString(input));
				g.addEdge(from, to);
				//System.out.print((char) input);
			}
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPipedReader(PipedReader pr) {
		this.pr = pr;
	}
	
	public Graph getGraph() {
		return g;
	}
	
	public void resetGraph() {
		g = new Graph();
	}
}
