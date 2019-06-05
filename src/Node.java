/**
 * Node class represents a node
 * @author Kevin Nguyen
 *
 */
public class Node implements Comparable<Node>{
	private String config;
	private int depth;
	private Node parent;
	private int heuristic;
	
	/**
	 * Creates the node class
	 * @param theConfiguration : the state of the puzzle
	 * @param theDepth		   : the depth this node lives at
	 * @param theParent		   : the parent of this node
	 */
	public Node(String theConfiguration, int theDepth, Node theParent) {
		config = theConfiguration;
		depth = theDepth;
		parent = theParent;
	}
	
	/** 
	 * Returns the depth this node lives at in the search space.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Returns the parent node
	 * @return
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Returns the configuration
	 * @return
	 */
	public int getHeuristic() {
		return heuristic;
	}
	
	/**
	 * Returns the configuration
	 * @return
	 */
	public String getConfig() {
		return config;
	}
	
	/**
	 * Sets the heuristic value
	 * @param n : the heuristic
	 */
	public void setHeuristic(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Heuristics cannot be negative!");
		}
		this.heuristic = n;
	}

	/**
	 * A node that has a higher heuristic is considered "closer" to the solution.
	 */
	@Override
	public int compareTo(Node o2) {
		if (this.heuristic < o2.heuristic) {
			return -1;
		} else if (this.heuristic > o2.heuristic) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Used to debug
	 */
	public String toString() {
		return heuristic + "";
	}
	
	/**
	 * Used to implement compareTo
	 */
	public int hashCode() {
		return config.hashCode();
	}
	
	/**
	 * Two Nodes are equal if their configurations are equal,
	 * meaning they are in the same state.
	 */
	public boolean equals(Object o) {
		Node x = (Node) o;
		return (this.config.equals(x.config));
		
	}
	
	
}
