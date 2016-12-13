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
		System.out.println("Thread awaken.");
		try {
			//We must use a tick-tack measure to determine wether the information
			// just arrived is the incoming or outgoing end of the edge.
			boolean pair = false; 
			int from = 0, to = 0;
			while ((input = pr.read()) != -1){
				if (!pair) {
					from = input;
					//System.out.print(Integer.toString(input)+" ");
				} else {
					to = input;
					//System.out.println(Integer.toString(input));
				}
				/*if ((input = pr.read()) != -1) {
					System.out.println("Pipeline ended too early. :(");
					return;
				}*/
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
