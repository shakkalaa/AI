package edu.uno.ai.planning.ex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import edu.uno.ai.SearchBudget;
import edu.uno.ai.logic.Literal;
import edu.uno.ai.logic.Proposition;
import edu.uno.ai.logic.State;
import edu.uno.ai.planning.Plan;
import edu.uno.ai.planning.Step;
import edu.uno.ai.planning.ss.StateSpaceNode;
import edu.uno.ai.planning.ss.StateSpaceProblem;
import edu.uno.ai.planning.ss.StateSpaceSearch;

/**
 * A planner that uses simple breadth first search through the space of states.
 * 
 * @author Stephen G. Ware
 */
public abstract class Smosely extends StateSpaceSearch {

	/** The queue which will hold the frontier (states not yet visited) */
	private final Queue<StateSpaceNode> queue = new LinkedList<>();
	private final HashMap<Literal, Double> cost = new HashMap<>();

	/**
	 * Constructs a new state space search object.
	 * 
	 * @param problem the problem to solve
	 * @param budget the search budget, which constrains how many states may be
	 * visited and how much time the search can take
	 */
	public Smosely(StateSpaceProblem problem, SearchBudget budget) {
		super(problem, budget);
	}

	@Override
	public Plan solve() {
		// Start with only the root node (initial state) in the queue.
		queue.add(root);
		// Search until the queue is empty (no more states to consider).
		while(!queue.isEmpty()) {
			// Pop a state off the frontier.
			
			
			//Input: The current state.
			StateSpaceNode current = queue.peek();
			State currentState = current.state;
			//Every literal has a cost, initially ∞.
			for(Literal literal : problem.literals) {
				cost.put(literal, Double.POSITIVE_INFINITY);
			}
			//Every literal that is true in the current state has a cost of 0.
			for(Literal literal : currentState) {
				if(literal.isTrue(currentState)) {
					cost.put(literal, 0.0);
				}
			}
			//Note: The cost of a conjunction is the sum of the costs of its conjuncts.
			
			
			//Do this until the costs of the literals stop changing:
			boolean change = true;
			while(change) {
			
				//For every step S:
				for(Step s : problem.steps) {
					//For every literal E in the effect of S:
					for(Literal e :problem.literals) {
						if(e == s.effect) {
					
							//Let the cost of E be the minimum of:
							//1. The current cost of E.
							//2. The cost of S’s precondition + 1.
							//Return the cost of the problem’s goal.*/
						}	
					}
			
				}
			}
		}
		
		//You must perform your search using the provided StateSpaceNode object and its expand method
		// If the queue is empty and we never found a solution, the problem
		// cannot be solved. Return null.
		return null;
	}
	
	/*@Override
	protected abstract StateSpaceSearch makeStateSpaceSearch​(StateSpaceProblem problem, SearchBudget budget) {
		
	}
	 You see the weird “Atom” fields because Proposition itself is an abstract class that
	several other classes implement. That said, in all of the domains and problems for this
	project, you will only encounter two kinds of propositions: a literal by itself or a
	conjunction of literals. You can use Java’s instanceof keyword to tell the difference
	between the two.Here’s a helpful method to iterate through all the literals in a proposition:
	
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
	}*/

	/*To verify if a plan is a solution to the problem, you can
	make use of a line like this, invoking the isSolution method of your problem
	object, and passing it in a node’s plan:
	problem.isSolution(current.plan) */
}

/*// Check if it is a solution.
if(problem.isSolution(current.plan))
	return current.plan;
// Consider every possible step...
for(Step step : problem.steps) {
	// If it's precondition is met in the current state...
	if(step.precondition.isTrue(current.state)) {
		// Add the state results from that step to the frontier.
		queue.offer(current.expand(step));
	}
}*/
