
import java.util.Date;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Class that all search functionality, and form are
 * derived from.
 * @author Kevin Nguyen
 *
 */
public abstract class SearchType {
	// The list of all possible moves the blank
	// space can make based on location of blank space
	// in grid.
	public static final String[] MOVES = {
			"RD", // 0
			"RDL",
			"RDL",
			"DL",
			"RDU",
			"RDLU",
			"RDLU",
			"DLU",
			"RDU", 
			"RDLU",
			"RDLU",
			"DLU",
			"RU",
			"RLU",
			"RLU",
			"LU"
	};
	
	protected Node currentState;
	protected static String goalState;
	protected static String goalState2;
	protected Set<Node> visited;
	protected Queue<Node> fringe;
	protected int maxFringeSize;
	protected int nodesCreated;
	protected int nodesExpanded;
	
	/**
	 * Initializes the game state
	 * @param state : the initial game state
	 * 
	 */
	SearchType(String state) {
		currentState = new Node(state, 0, null);
		goalState = "123456789ABCDEF ";
		goalState2 = "123456789ABCDFE ";
		maxFringeSize = 0;
		nodesExpanded = 0;
		nodesCreated = 1;
		visited = new HashSet<Node>();
	}
	
	/**
	 * @param node    : the node we are examining
	 * @param pattern : the pattern that this node has
	 * @return true if in ancestry, false otherwise
	 */
	protected boolean containedInAncestry(Node node, String pattern) {
		if (node == null) {
			return false;
		}
		if (node.getConfig().equals(pattern)) {
			return true;
		}
		return containedInAncestry(node.getParent(), pattern);
	}
	
	/**
	 * Checks if the given pattern has been examined
	 * @param pattern the current game state
	 * @return true if pattern has been examined, false otherwise.
	 */
	protected boolean containedInAncestry(String pattern) {
		for (Node n : visited) {
			if (n.getConfig().equals(pattern)) 
				return true;
		}
		return false;
	}
	
	/**
	 * Makes the swap on the game board, i.e., one iteration of
	 * the game.
	 * 
	 * @param move the move to make
	 * @param node the node we are making a move from
	 * @returns a new node that is a child of node
	 */
	protected Node makeSwap(char move, Node node) {
		String config = node.getConfig();
		int space = config.indexOf(" ");
		char[] brokenConfig = config.toCharArray();
		char c;
		if (move == 'R') {
			c = brokenConfig[space + 1];
			brokenConfig[space+1] = ' ';
		} else if (move == 'D') {
			c = brokenConfig[space + 4];
			brokenConfig[space+4] = ' ';
		} else if (move == 'L') {
			c = brokenConfig[space-1];
			brokenConfig[space-1] = ' ';
		} else {
			c = brokenConfig[space - 4];
			brokenConfig[space-4] = ' ';
		}
		brokenConfig[space] = c;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < brokenConfig.length; i++) {
			sb.append(brokenConfig[i]);
		}
		nodesCreated++;
		return new Node(sb.toString(), node.getDepth()+1, node);
	}
	
	/**
	 * Returns the move that is associated with this index
	 * @param index : the location of the white space
	 * @return a String that represents the possible move
	 */
	protected static String getValidMoves(int index) {
		return MOVES[index];
	}
	
	/**
	 * Solves the game puzzle
	 * @returns -1 if a solution is never found
	 */
	public void solve() {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		Node node = null;
		String config = null;
		addToFringe(currentState);
		while (fringe.size() > 0|| elapsedTime < 60*1000) {
			maxFringeSize = Math.max(maxFringeSize, fringe.size());
			node = removeFromFringe();
			nodesExpanded++;
			config = node.getConfig();
			
	
			if (config.equals(goalState) || config.equals(goalState2)) {
				visited.add(node);
				reportSolutions(node.getDepth(), nodesCreated, nodesExpanded, maxFringeSize);
				return;
				
			} else if (!containedInAncestry(node.getParent(), config)) {
				visited.add(node);
				int spaceIndex = config.indexOf(" ");
				
				String moves = getValidMoves(spaceIndex);
				performMove(moves, node);
			}
			elapsedTime = (new Date()).getTime() - startTime;
			
		}

		// No solution was found in 1 minute
		reportSolutions(-1, 0, 0, 0);
	}
	
	// Empty function to be filled in by its inheritors.
	protected abstract void addToFringe(Node n);
	
	// Empty function to be filled by its inheritors.
	protected abstract Node removeFromFringe();
	
	/**
	 * Makes the moves based on given moves
	 * @param moves : The possible moves made by the white space
	 * @param node  : the node we are currently examining right
	 * 				: now
	 */
	protected void performMove(String moves, Node node) {
		if (moves.contains("R")) {
			addToFringe(makeSwap('R', node));
		}
		if (moves.contains("D")) {
			addToFringe(makeSwap('D', node));
		}
		if (moves.contains("L")) {
			addToFringe(makeSwap('L', node));
		}
		if (moves.contains("U")) {
			addToFringe(makeSwap('U', node));
		}
	}
	
	/** 
	 * Prints out the solution to console.
	 * @param depth the depth solution was found at, -1 if not found
	 * @param numCreated the number of nodes created
	 * @param numExpanded the number of nodes expanded
	 * @param maxFringe the maximum number of elements in the fringe
	 */
	public static void reportSolutions(int depth, int numCreated, 
								int numExpanded, int maxFringe) {
		System.out.println("Depth: "+ depth + ", NodesCreated: " + numCreated + ", \nNodesExpanded: " + numExpanded + ", MaxFringeSize:" + maxFringe);
	}
	
}
