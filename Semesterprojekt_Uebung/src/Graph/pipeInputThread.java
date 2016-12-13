package Graph;

import java.io.IOException;
import java.io.PipedWriter;


/*
 * This is a class that generates information for a fake input graph.
 */
public class pipeInputThread extends Thread {
	private PipedWriter pw;
	
	public pipeInputThread(PipedWriter pw) {
		this.pw = pw;
	}
	
	
	public void run() {
		try {
			pw.write ("121314");
			pw.close ();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
