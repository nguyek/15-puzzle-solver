/**
 * Main Driver class for FifteenProblem solver
 * 
 * 
 * @author Kevin Nguyen
 *
 */
public class FifteenProblemMain {
	public static void main(String[] args) {
		
		if (args.length < 2 || args.length > 3) {
			throw new IllegalArgumentException("Wrong number of inputs");
		}
		String state = args[0]; // Initial State
		String type = args[1]; // Search Type
		String option = null;
		int numArgs = args.length;
		SearchType st = null;
		if (numArgs == 3) {
			option = args[2]; // Optional
		} 
		
		solve(type, state, option, st);	
	}
	
	// Solves the given state with the search type. 
	private static void solve(String type, String state, String option, SearchType st) {
		if (type.equals("BFS")) {
			st = new BreadthFirst(state);
		} else if (type.equals("DFS")) {
			st = new DepthFirst(state);
		} else if (type.equals("GBFS")) {
			if (option.equals("h1")) {
				st = new GreedyBestFit(state, true);
			} else {
				st = new GreedyBestFit(state, false);
			}
		} else if (type.equals("Astar")) {
			if (option.equals("h1")) {
				st = new Astar(state, true);
			} else {
				st = new Astar(state, false);
			}
		} else if (type.equals("DLS")) {
			int limit = Integer.parseInt(option);
			st = new DepthLimited(state, limit);
		}
		st.solve();
	}
	
	

}
