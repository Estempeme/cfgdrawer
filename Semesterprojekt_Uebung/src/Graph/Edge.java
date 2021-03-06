package Graph;

/*
 * An Edge object contains a number, which identificates the node (vertex) 
 * 	to which the edge leads, 
 * 	a number of how many times this edge [...],
 * 	and a pointer of the next Edge object.
 */
public class Edge {

	private int id;
	private int num;
	private Edge next;

	public Edge(int n) {
		this.id = n;
		this.num = 1; // at first we ran this edge only once
		this.next = null;
	}

	public void incNum() {
		num++;
	}

	public int getID() {
		return id;
	}

	public Edge getNext() {
		return next;
	}

	public int getNum() {
		return this.num;
	}

	// Insert a new Edge after this Edge
	public void setNext(Edge newEdge) {
		Edge temp = this.next;
		this.next = newEdge;
		newEdge.next = temp;
	}

}
