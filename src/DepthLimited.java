import java.util.Date;
import java.util.Stack;
/**
 * Depth Limited Search
 * 
 * Search Technique is limited by a specific depth, 
 * or also by 1 minute, since there is a possibility
 * the solution is not in the DFS search-space.
 * 
 * @author Kevin Nguyen
 *
 */
public class DepthLimited extends SearchType {
	protected Stack<Node> fringe;
	protected int limit;
	
	/**
	 * Constructor
	 * @param state   : initial game state
	 * @param theLimit: the limit of depth we are allowing
	 */
	public DepthLimited(String state, int theLimit) {
		super(state);	
		fringe = new Stack<Node>();
		limit = theLimit;
	}
	
	@Override
	/**
	 * Solves the current state to the goal state 
	 * in depth limited fashion.
	 */
	public void solve() {
		// Used to check if solution is computable in under 1 minute.
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		
		addToFringe(currentState);
		while (fringe.size() > 0 && elapsedTime < 60*1000) {
			maxFringeSize = Math.max(maxFringeSize, fringe.size());
			Node node = removeFromFringe();
			if (node == null) {
				reportSolutions(-1, 0, 0, 0);
				return;
			}
			
			// Check if okay to expand aka not exceeded depth
			if (node.getDepth() <= limit) {
				nodesExpanded++;
				String config = node.getConfig();
				
				// Expanding
				if (!visited.contains(node)) {
					visited.add(node);
					int spaceIndex = config.indexOf(" ");
					
					String moves = getValidMoves(spaceIndex);
					performMove(moves, node);
				}
				
				// Checking if equal to a goal state
				if (config.equals(goalState) || config.equals(goalState2)) {
					visited.add(node);
					reportSolutions(node.getDepth(), nodesCreated, nodesExpanded, maxFringeSize);
					return;
				}
			}
			elapsedTime = (new Date()).getTime() - startTime;

		}
		// No solution was found in 1 minute
		reportSolutions(-1, 0, 0, 0);
	}
	
	/** 
	 * Returns true if the solver has encountered this game state
	 * before, and false otherwise.
	 */
	protected boolean hasVisited(String pattern) {
		for (Node n : visited) {
			if (n.getConfig().equals(pattern)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Evaluates possible space swaps, and does it in
	 * stack required order.
	 */
	protected void performMove(String moves, Node node) {
		if (moves.contains("U")) {
			addToFringe(makeSwap('U', node));
		}
		if (moves.contains("L")) {
			addToFringe(makeSwap('L', node));
		}
		if (moves.contains("D")) {
			addToFringe(makeSwap('D', node));
		}
		if (moves.contains("R")) {
			addToFringe(makeSwap('R', node));
		}
	}
	
	/**
	 * Adds n to our fringe
	 */
	protected void addToFringe(Node n) {
		fringe.push(n);
	}
	
	/**
	 * Removes and returns from the front. 
	 */
	protected Node removeFromFringe() {
		if (!fringe.isEmpty())
			return fringe.pop();
		return null;
	}


	
	

}
