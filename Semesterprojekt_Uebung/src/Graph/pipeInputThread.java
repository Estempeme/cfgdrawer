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
			pw.write("t1-21;2-21;");
			pw.write("2-  2345;");
			pw.write("2  -2345;");
			// pw.write ("2-3");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
