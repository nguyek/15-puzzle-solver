/**
 * Astar search Algorithm
 * 
 * Astar's heuristic function, h, considers the cost to get to this state.
 * h = f + g where f = h1 or h2 and g = depth to this node.
 * @author Kevin Nguyen
 *
 */
public class Astar extends GreedyBestFit {
	public Astar(String state, boolean useH1) {
		super(state, useH1);
	}
	
	protected void applyHeuristic(Node node) {
		super.applyHeuristic(node);
		node.setHeuristic(node.getHeuristic()+node.getDepth());
	}
}
