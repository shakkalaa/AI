package edu.uno.ai.planning.ex;

import edu.uno.ai.SearchBudget;
import edu.uno.ai.planning.ss.StateSpacePlanner;
import edu.uno.ai.planning.ss.StateSpaceProblem;
import edu.uno.ai.planning.ss.StateSpaceSearch;

/**
 * A planner that uses simple breadth first search through the space of states.
 * 
 * @author Shakayla Mosely
 */
public class Smosely extends StateSpacePlanner {

	/**
	 * Constructs a new breadth first search planner. You should change the
	 * string below from "Example" to your ID. You should also change the name
	 * of this class. In Eclipse, you can do that easily by right-clicking on
	 * this file (ExamplePlanner.java) in the Package Explorer and choosing
	 * Refactor > Rename.
	*/
	public Smosely() {
		super("smosely");
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
	
	
	/* You see the weird “Atom” fields because Proposition itself is an abstract class that
	several other classes implement. That said, in all of the domains and problems for this
	project, you will only encounter two kinds of propositions: a literal by itself or a
	conjunction of literals. You can use Java’s instanceof keyword to tell the difference
	between the two.Here’s a helpful method to iterate through all the literals in a proposition:*/
	
	private static List<Literal> getLiterals(Proposition proposition) {
		ArrayList<Literal> list = new ArrayList<>();
		if(proposition instanceof Literal){
			list.add((Literal) proposition);
		}
		else{
			for(Proposition conjunct : ((Conjunction) proposition).arguments){
				list.add((Literal) conjunct);
			}
		}
		return list;
	}
	
	//Alternatively, if you want to avoid allocating lists on the heap every time, you can use the Consumer interface to invoke a function on each literal:
	private static void forEachLiteral(Proposition proposition, Consumer<? super Literal> consumer) {
		if(proposition instanceof Literal){
			consumer.accept((Literal) proposition);
		}
		else{
			for(Proposition conjunct : ((Conjunction) proposition).arguments){
				consumer.accept((Literal) conjunct);
			}
		}
	}
}
