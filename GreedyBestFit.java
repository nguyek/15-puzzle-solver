import java.util.PriorityQueue;

/**
 * Uses Greedy Best Fit to find a solution
 * 
 * GBFS heuristic function considers only h = f = Manhattan heuristic
 * or Straight line heuristic
 * @author Kevin Nguyen
 *
 */
public class GreedyBestFit extends SearchType {
	public boolean h1;

	public GreedyBestFit(String state, boolean useH1) {
		super(state);
		h1 = useH1;
		fringe = new PriorityQueue<Node>();
	}
	
	/**
	 * Heuristic 1 is the number of misplaced tiles
	 * @param state : the current game state
	 * @return		: the heuristic, as an int.
	 */
	protected static int calcHeuristic1(String state) {
		int numMisplaced2 = 0;
		int numMisplaced1 = 0;
		for (int i = 0; i < state.length(); i++) {
			char c = state.charAt(i);
			if (c != goalState.charAt(i)) {
				numMisplaced1++;
			}
			if (c != goalState2.charAt(i)) {
				numMisplaced2++;
			}
		}
		return Math.min(numMisplaced1, numMisplaced2);
		
	}
	
	/**
	 * Heuristic 2 is the sum of Manhattan distances for 
	 * each tile.
	 * @param state : the current game state
	 * @return 		: the heuristic as a sum
	 */
	protected static int calcHeuristic2(String state) {
		return Math.min(calcHeuristicHelper(state, goalState), calcHeuristicHelper(state, goalState2));
	}
	
	/**
	 * 
	 * @param currentState
	 * @param gState
	 * @return
	 */
	protected static int calcHeuristicHelper(String currentState, String gState) {
		int heuristic = 0;
		for (int i = 0; i < currentState.length(); i++) {
			char c = gState.charAt(i);
			int currIndex = currentState.indexOf(c);
			// i = 0 so '1'
			int goalRow1 = i / 4;
			int currRow1 = currIndex / 4;
			
			int diffInRow = Math.abs(goalRow1 - currRow1);
			int column = Math.abs(currIndex - (4 * diffInRow));
			heuristic += Math.abs(column - i) + diffInRow;
		}
		return heuristic;
	}
	
	/**
	 * Adds the node n to the fringe
	 */
	protected void addToFringe(Node n) {
		fringe.add(n);
	}
	
	/**
	 * Removes and returns a node from the fringe
	 */
	protected Node removeFromFringe() {
		return fringe.remove();
		
	}
	
	@Override
	/**
	 * Makes a move, adding to the fringe based on
	 * the heuristic functions defined above
	 * @param moves : the possible moves
	 * @param node  : the node we are making moves from
	 */
	protected void performMove(String moves, Node node) {	
		Node n;
		if (moves.contains("R")) {
			n = makeSwap('R', node);		
			applyHeuristic(n);
			addToFringe(n);
		}
		if (moves.contains("D")) {
			n = makeSwap('D', node);		
			applyHeuristic(n);
			addToFringe(n);
		}
		if (moves.contains("L")) {
			n = makeSwap('L', node);		
			applyHeuristic(n);
			addToFringe(n);
		}
		if (moves.contains("U")) {
			n = makeSwap('U', node);		
			applyHeuristic(n);
			addToFringe(n);
		}
	}
	
	/**
	 * Adds the heuristic to the node based on 
	 * the node's game state
	 * @param node : the node we are assigning a
	 * 				 heuristic to.
	 */
	protected void applyHeuristic(Node node) {
		if (h1)
			node.setHeuristic(calcHeuristic1(node.getConfig()));
		else
			node.setHeuristic(calcHeuristic2(node.getConfig()));
			
	}
//	node.heuristic = calcHeuristic1(node.getConfig());
//	node.heuristic = calcHeuristic2(node.getConfig());
	
}
