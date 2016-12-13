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
			System.out.println("Writing information.");
			/*pw.write ("121314");
			pw.write ("2535");*/
			/*pw.write("a");
			pw.write("2");
			pw.write("a");
			pw.write("3");
			pw.write("a");
			pw.write("4");*/
			pw.write("121314894123579513579651387632135864");
			pw.close ();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
