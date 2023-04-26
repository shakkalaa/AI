package edu.uno.ai.planning.ex;

import edu.uno.ai.SearchBudget;
import edu.uno.ai.planning.ss.StateSpacePlanner;
import edu.uno.ai.planning.ss.StateSpaceProblem;
import edu.uno.ai.planning.ss.StateSpaceSearch;

/**
 * A planner that uses simple breadth first search through the space of states.
 * 
 * @author Your Name
 */
public class ExamplePlanner extends StateSpacePlanner {

	/**
	 * Constructs a new breadth first search planner. You should change the
	 * string below from "Example" to your ID. You should also change the name
	 * of this class. In Eclipse, you can do that easily by right-clicking on
	 * this file (ExamplePlanner.java) in the Package Explorer and choosing
	 * Refactor > Rename.
	 */
	public ExamplePlanner() {
		super("Example");
	}

	@Override
	protected StateSpaceSearch makeStateSpaceSearch(StateSpaceProblem problem, SearchBudget budget) {
		return new BreadthFirstSearch(problem, budget);
	}
	
	//Extend the plan graph until all goals are non-mutex.
		//Let G be the current goals, initially the problem’s goals.
		//Let n be the current level, initially the highest level.
		//To satisfy the goals in G at level n:
		    //If n = 0, return the plan as a solution.
		    //Choose a set of steps S which achieve all the goals in G.
		    //(Every pair of steps in S must be non-mutex).
		    //Add all steps in S to the plan.
		    //Let G = all the preconditions of the steps in S.
		    //Recursively satisfy all the goals in G at level n – 1.
		//If satisfying G fails, add a level to the graph and try again.
}
