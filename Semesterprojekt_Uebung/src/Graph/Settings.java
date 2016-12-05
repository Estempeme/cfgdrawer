package Graph;

public class Settings {

	private static String type; // outputfile typ, only png works

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Settings.type = type;
	}

	public static String getPath() {
		return path;
	}

	private static String path; // working directory

	public static void setPath(String path) {
		Settings.path = path;
	}

	private static final String jan = "/home/jan/Dropbox/SemesterprojectBugMining/workspace/cfgdrawer/Semesterprojekt_Uebung/graph.pnggraph."
			+ type;

	private static final String mihaly = "C:/Users/Misi HP/Documents/Iskola/Humboldt/programok/cfgdrawer/Semesterprojekt_Uebung/graph."
			+ type;

	/*
	 * main for testing, set your path, then a graph is drawn
	 */
	public static void main(String[] args) {

		setPath(jan);
		// setPath(mihaly);

		setType("png");// only type that works until now

		Graph g = new Graph();

		g.addEdge(5, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(1, 2);
		g.addEdge(3, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 4);
		/*
		 * for (int i = 0; i < 1000; i++) { //Extrem case. g.addEdge(i, (i+1));
		 * }
		 */
		/*
		 * int n = 50; for (int i = 0; i < n; i++) { int a = (int)
		 * (Math.random()*n); int b = (int) (Math.random()*n); g.addEdge(a, b);
		 * }
		 */
		/*
		 * for (int i = 0; i < 10; i++) { for (int j = i; j < 10; j++) {
		 * g.addEdge(i, j); } }
		 */
		g.createPicture();
	}

}
