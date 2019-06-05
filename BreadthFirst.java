import java.util.*;
public class BreadthFirst extends SearchType {
	
	public BreadthFirst(String state) {
		super(state);
		fringe = new LinkedList<Node>();		
	}
		
	protected void addToFringe(Node n) {
		fringe.add(n);
	}
	
	protected Node removeFromFringe() {
		return fringe.remove();
	}
}
