// the edges are handed over in a java.util.concurrent.BlockingQueue<Edge>
// someone creates a queue and gives it to the graph builder and executor
// in their constructors. When the executor observes that the program
// terminates, a special invalid edge with isFinished() returning true is
// returned.

class Edge {

	// the method this edge is in
	String method;

	// the last line we executed in this method
	int lineFrom;

	// the line we reached from the last line
	int lineTo;

	Edge(String method, int lineFrom, int lineTo){
		this.method = method;
		this.lineFrom = lineFrom;
		this.lineTo = lineTo;
	}

	boolean isFinished() {
		return method.equals("");
	}

	boolean isFirstLine() {
		return lineFrom == -1;
	}
}
