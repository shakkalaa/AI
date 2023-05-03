Open sidebar
Random SAT Solver.


hidden surprises
package edu.uno.ai.sat.ex;

import java.util.ArrayList;
import java.util.Random;
//import java.util.Collection;
import java.util.Iterator;

import edu.uno.ai.sat.Assignment;
import edu.uno.ai.sat.Solver;
import edu.uno.ai.sat.Value;
import edu.uno.ai.sat.Variable;
import edu.uno.ai.sat.Clause;
//import edu.uno.ai.sat.Result;


/**
 * 
 * @author 
 */
public class Smosely extends Solver {

	private final Random random = new Random(0);
	//private int maxOperations;
    private long startTime;
	
	/**
	 * Constructs a new random SAT solver. You should change the string below
	 * from "random" to your ID. You should also change the name of this class.
	 * In Eclipse, you can do that easily by right-clicking on this file
	 * (RandomAgent.java) in the Package Explorer and choosing Refactor > Rename.
	 */
	public Smosely() {
		super("smosely1");
		//this.maxOperations = 1000000;
        this.startTime = System.currentTimeMillis();
	}


	@Override
    public boolean solve(Assignment assignment) {
		
		//int numOperations;
		
		if (System.currentTimeMillis() - startTime >= 5 * 60 * 1000) {
            return false; // exceeded time
        }
		
        // If the problem has no variables, it is trivially true or false.
        if(assignment.problem.variables.size() == 0)
            return assignment.getValue() == Value.TRUE;
        else {
            // Simplify the formula by unit propagation and pure literal elimination.
            assignment = simplifyFormula(assignment);
        
            // If the simplified formula is unsatisfiable, backtrack.
            if (assignment.getValue() == Value.FALSE) {
                return false;
            }
        
            /*// If the simplified formula is satisfiable, check if it is a complete assignment.
            if (assignment.problem.solve()) {
                return true;
            }*/
        
            // Choose a variable whose value will be set.
            Variable variable = chooseVariable(assignment);
        
            // Try setting the variable to TRUE.
            assignment.setValue(variable, Value.TRUE);
            if (solve(assignment)) {
                return true;
            }
        
            // If setting the variable to TRUE does not lead to a solution, try setting it to FALSE.
            assignment.setValue(variable, Value.FALSE);
            if (solve(assignment)) {
                return true;
            }
        
            // If neither setting the variable to TRUE nor FALSE leads to a solution, backtrack.
            assignment.setValue(variable, Value.UNKNOWN);
            return false; 
        }
    }

	
	/**
	 * Randomly choose a variable from the problem whose value will be set. If
	 * any variables have the value 'unknown,' choose one of those first;
	 * otherwise choose any variable.
	 * 
	 * @param assignment the assignment being worked on
	 * @return a variable, chosen randomly
	 */
	private final Variable chooseVariable(Assignment assignment) {
		// This list will hold all variables whose current value is 'unknown.'
		ArrayList<Variable> unknown = new ArrayList<>();
		// Loop through all the variables in the problem and find ones whose
		// current value is 'unknown.'
		for(Variable variable : assignment.problem.variables)
			if(assignment.getValue(variable) == Value.UNKNOWN)
				unknown.add(variable);
		// If any variables are 'unknown,' choose one of them randomly.
		if(unknown.size() > 0)
			return unknown.get(random.nextInt(unknown.size()));
		// Otherwise, choose any variable from the problem at random.
		else
			return assignment.problem.variables.get(random.nextInt(assignment.problem.variables.size()));
	}

    //@SuppressWarnings("unchecked")
	private Assignment simplifyFormula(Assignment assignment) {
        Assignment simplifiedAssignment = assignment.clone();
        
        // Perform unit propagation.
        boolean unitPropagated = true;
        while (unitPropagated) {
            unitPropagated = false;
            //int clauseSize = clause.length;
            
            for (Clause clause : simplifiedAssignment.problem.clauses) {
            	
                if (clause.literals.size() == 1) {
                	//Iterator<Variable> iterator = ((Iterable<Variable>) clause).iterator();
                    
                	Variable variable = clause.literals.get(0).variable;
              
                    Value value = simplifiedAssignment.getValue(variable) == Value.TRUE ? Value.FALSE : Value.TRUE;
                    
                    if (simplifiedAssignment.getValue(variable) == Value.UNKNOWN) {
                    	simplifiedAssignment.setValue(variable, value);
                    	unitPropagated = true;
                       
                    } else if (simplifiedAssignment.getValue(variable) != value) {
                    	//simplifiedAssignment.setValue(variable, Value.FALSE);
                    	return null;
                    			//simplifiedAssignment;
                    }
                    
                }
            }
        }
        
        // Perform pure literal elimination.
        for (Variable variable : simplifiedAssignment.problem.variables) {
            boolean isPureLiteral = true;
            for (Clause clause : simplifiedAssignment.problem.clauses) {
                if (containsVariable(clause, variable)) {
                    isPureLiteral = false;
                    break;
                }
            }
            if (isPureLiteral) {
                Value value = simplifiedAssignment.getValue(variable) == Value.UNKNOWN ? Value.TRUE : simplifiedAssignment.getValue(variable);
                simplifiedAssignment.setValue(variable, value);
            }
        }
        
        return simplifiedAssignment;
    }
    
    @SuppressWarnings("unlikely-arg-type")
	public boolean containsVariable(Clause clause, Variable variable) {
        
    	Iterator<Clause> iterator = clause.problem.clauses.iterator();
    	while (iterator.hasNext()) {
    		Clause var = iterator.next();
            if (var.equals(variable)) {
                return true;
            }
        }
        return false;
    } 

}
how do i get rid of the stack over flow error

Hello! How can I assist you today?




hidden surprises
what kind of sat solver is this?

This is a simple recursive backtracking SAT solver that uses unit propagation and pure literal elimination for simplifying the problem. The solver randomly selects an unassigned variable and tries to assign it a value. If assigning a value to a variable leads to a contradiction, it backtracks and tries another value for the same variable or another variable, until a solution is found or all assignments have been tried.

The solver is called "Smosely" and appears to be a custom implementation created by the user who wrote this code.




hidden surprises
whats a good dpll sat solver in java

There are several good DPLL SAT solvers available in Java. Here are a few options:

MiniSat-Java: This is a Java port of the popular MiniSat solver. It has a fast implementation and is widely used.

JSAT: This is a pure Java implementation of a SAT solver. It includes several different solvers, including a DPLL-based solver.

SAT4J: This is a Java library that provides a set of classes and methods for solving SAT problems. It includes several different solvers, including a DPLL-based solver.

Lingeling-Java: This is a Java port of the Lingeling solver, which is known for its high performance. It includes a DPLL-based solver.

All of these solvers have their strengths and weaknesses, so the best one for your use case will depend on your specific needs.




hidden surprises
package edu.uno.ai.sat.ex;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

import edu.uno.ai.sat.Assignment;
import edu.uno.ai.sat.Solver;
import edu.uno.ai.sat.Value;
import edu.uno.ai.sat.Variable;
import edu.uno.ai.sat.*;

public class Smosely extends Solver {

    private final Random random = new Random(0);

    public Smosely() {
        super("smosely1");
    }

    @Override
    public boolean solve(Assignment assignment) {
    	
        // If the problem has no variables, it is trivially true or false.
        if (assignment.problem.variables.size() == 0)
            return assignment.getValue() == Value.TRUE;
        
        else {
            // Keep trying until the assignment is satisfying.
            while (assignment.getValue() != Value.TRUE) {
                
            	// Perform unit propagation
                while (unitPropagation(assignment)) {

                	// Perform pure literal elimination
                	pureLiteralElimination(assignment);

                	// Choose a variable whose value will be set using a heuristic
                	Variable variable = chooseVariable(assignment);

                	// Choose 'true' or 'false' for the variable using a heuristic
                	Value value = chooseValue(variable, assignment);

                	// Assign the chosen value to the chosen variable.
                	assignment.setValue(variable, value);

                	// Check if the maximum number of operations or time limit has been reached
                	if (getOperationCount() >= 100000 || getTimeElapsed() >= 300000) {
                		return false;
                	}
                }	
            }
            // Return success. (Note, if the problem cannot be solved, this
            // solver will run until it reaches the operations or time limit.)
            return true;
        }
    }

    /**
     * Perform unit propagation by assigning the value of any variable that
     * appears in a clause with all other variables assigned except for one.
     * 
     * @param assignment the assignment being worked on
     * @return true if any assignments were made, false otherwise
     */
    private final boolean unitPropagation(Assignment assignment) {
        boolean assignmentMade = false;
        for (Variable variable : assignment.problem.variables) {
            if (assignment.getValue(variable) == Value.UNKNOWN) {
                ArrayList<Variable> unassignedVariables = new ArrayList<>();
                Value unassignedValue = Value.UNKNOWN;
                for (int i = 0; i < variable.clauses.size(); i++) {
                    int unknownCount = 0;
                    Variable unknownVariable = null;
                    for (Variable v : variable.clauses.get(i).variables) {
                        Value value = assignment.getValue(v);
                        if (value == Value.UNKNOWN) {
                            unknownCount++;
                            unknownVariable = v;
                        } else if (value == Value.TRUE) {
                            unknownCount = -1;
                            break;
                        }
                    }
                    if (unknownCount == 1) {
                        unassignedVariables.add(unknownVariable);
                        unassignedValue = variable.clauses.get(i).getValue(unknownVariable);
                    } else if (unknownCount == 0) {
                        if (variable.clauses.get(i).getValue(variable) == Value.TRUE) {
                            assignment.setValue(variable, Value.TRUE);
                            assignmentMade = true;
                            break;
                        }
                    }
                }
                if (unassignedVariables.size() == 1) {
                    assignment.setValue(unassignedVariables.get(0), unassignedValue);
                    assignmentMade = true;
                    break;
                }
            }
        }
        return assignmentMade;
    }

    /**
     * Perform pure literal elimination by assigning the value of any variable
     * that appears only with one polarity in the entire formula.
     * 
     * @param assignment theassignment being worked on
*/
private final void pureLiteralElimination(Assignment assignment) {
	for (Variable variable : assignment.problem.variables) {
		if (assignment.getValue(variable) == Value.UNKNOWN) {
			boolean hasPositive = false;
			boolean hasNegative = false;
			
			for (int i = 0; i < variable.clauses.size(); i++) {
				if (variable.clauses.get(i).getValue(variable) == Value.TRUE) {
					hasPositive = true;
				} else {
					hasNegative = true;
				}
			}
			if (hasPositive && !hasNegative) {
				assignment.setValue(variable, Value.TRUE);
			} else if (hasNegative && !hasPositive) {
				assignment.setValue(variable, Value.FALSE);
			}
		}
		}
	}
	/**
* Choose a variable to be assigned next, using the Jeroslow-Wang heuristic.
*
* @param assignment the assignment being worked on
* @return the chosen variable
*/
private final Variable chooseVariable(Assignment assignment) {
	
	// Compute the Jeroslow-Wang score for each variable.
	double[] scores = new double[assignment.problem.variables.size()];
	for (Variable variable : assignment.problem.variables) {
		if (assignment.getValue(variable) == Value.UNKNOWN) {
			double score = 0;
			for (int i = 0; i < variable.clauses.size(); i++) {
					score += Math.pow(2, -variable.clauses.get(i).countUnknown());
			}
			scores[variable.index] = score;
		}
	}
	    // Choose the variable with the highest score.
	double maxScore = Double.NEGATIVE_INFINITY;
	Variable chosenVariable = null;
	for (Variable variable : assignment.problem.variables) {
		if (assignment.getValue(variable) == Value.UNKNOWN && scores[variable.index] > maxScore) {
			maxScore = scores[variable.index];
			chosenVariable = variable;
		}
	}
	
	return chosenVariable;
	
}	
	
	//Set a variable to a value, and if it doesn’t work, undo it.
    private boolean tryValue(Assignment a, Variable var, Value val){
    //Back up the variable’s current value.
        Value backup = a.getValue(var);
        //Set the variable to the given value.
        a.setValue(var, val);
        //Try to solve the problem with this new information.
        if(solve(a)){
            return true;
    }
    //If we failed to solve the problem, return the variable to its previous value.
    else{
        a.setValue(var, backup);
        return false;
    }
    }
}
package edu.uno.ai.sat.ex;

import java.util.*;

import edu.uno.ai.sat.*;
import edu.uno.ai.util.ImmutableArray;


/**
 * 
 * @author 
 */
public class Smosely extends Solver {
	
	private final Random random = new Random(0);

    public Smosely(){
        super("My Solver");
    }

    @Override
    public boolean solve(Assignment assignment){
    	
         //Perform unit propagation
    	
    	
    		unitPropAndPureLiteral(assignment);
        	// Perform pure literal elimination
    		
        	// Choose a variable whose value will be set using a heuristic
    		Variable chosen = chooseVariable(assignment);
        	// Choose 'true' or 'false' for the variable using a heuristic
    		
        	// Assign the chosen value to the chosen variable.
    		tryValue(assignment, chosen, Value.TRUE);
    		if(solve(assignment)) {
    			return true;
    		}
    		tryValue(assignment, chosen, Value.FALSE);
    		if(solve(assignment)){
    			return true;
    		}
			return false;
    		
    		
    }
    

    public boolean unitPropAndPureLiteral(Assignment assignment) {
        // If the problem has no variables, it is trivially true or false.
        if (assignment.problem.variables.size() == 0) {
            return assignment.getValue() == Value.TRUE;
        } else {
            // Get all the variables in the problem.
            ImmutableArray<Variable> variables = assignment.problem.variables;

            // Keep trying until the assignment is satisfying.
            // Perform unit propagation and pure literal elimination.
            boolean unitPropagation = false;
            for (Variable variable : variables) {
                // Check if the variable appears in any clause.
                boolean variableAppearsInClause = false;
                for (Clause clause : assignment.problem.clauses) {
                    if (clause.literals.contains(variable)) {
                        variableAppearsInClause = true;
                        break;
                    }
                }
                if (!variableAppearsInClause) {
                    // If the variable doesn't appear in any clause, set it to either TRUE or FALSE.
                    tryValue(assignment, variable, Value.TRUE);
                    unitPropagation = true;
                    continue;
                }

                // Perform pure literal elimination.
                boolean positive = false;
                boolean negative = false;
                for (Clause clause : assignment.problem.clauses) {
                    if (clause.literals.contains(variable)) {
                        if (assignment.getValue(clause) == Value.UNKNOWN) {
                            if (variable.equals(Value.TRUE)) {
                                positive = true;
                            } else if (variable.equals(Value.FALSE)) {
                                negative = true;
                            }
                        }
                    }
                }
                if (positive && !negative) {
                    tryValue(assignment, variable, Value.TRUE);
                } else if (negative && !positive) {
                    tryValue(assignment, variable, Value.FALSE);
                }

                // Perform unit propagation.
                for (Clause clause : assignment.problem.clauses) {
                    if (assignment.getValue(clause) == Value.UNKNOWN && assignment.countUnknownLiterals(clause) == 1) {
                        Literal unknown = null;
                        for (Literal literal : clause.literals) {
                            if (assignment.getValue(literal) == Value.UNKNOWN) {
                                if (unknown == null) {
                                    unknown = literal;
                                } else {
                                    unknown = null;
                                    break;
                                }
                            }
                        }
                        if (unknown != null) {
                            assignment.setValue(unknown.variable, Value.TRUE);
                            unitPropagation = true;
                        }
                    }
                }
            }
            return unitPropagation;
        }
    }

    
    private final Variable chooseVariable(Assignment assignment) {
		// This list will hold all variables whose current value is 'unknown.'
		ArrayList<Variable> unknown = new ArrayList<>();
		// Loop through all the variables in the problem and find ones whose
		// current value is 'unknown.'
		for(Variable variable : assignment.problem.variables) {
			if(assignment.getValue(variable) == Value.UNKNOWN) {
				unknown.add(variable);
			}
		}
		// If any variables are 'unknown,' choose one of them randomly.
		//Variable randomVar;
		if(unknown.size() > 0) {
			return unknown.get(random.nextInt(unknown.size()));
		// Otherwise, choose any variable from the problem at random.
		}else {
			return assignment.problem.variables.get(random.nextInt(assignment.problem.variables.size()));
		}
		
		
	}
    
   /*private final Value chooseValue(Variable variable, Assignment assignment) {
		int trueCount = 0;
		int falseCount = 0;
		for (int i = 0; i < variable.problem.clauses.size(); i++) {
			Value value = assignment.getValue(variable.problem.clauses.get(i));
			if (value == Value.TRUE) {
				trueCount++;
			} else if (value == Value.FALSE) {
				falseCount++;
			}
		}
	
		if (trueCount > falseCount) {
			return Value.FALSE;
		} else if (falseCount > trueCount) {
			return Value.TRUE;
		} else {
			return random.nextBoolean() ? Value.TRUE : Value.FALSE;
		}
	}*/
  //Set a variable to a value, and if it doesn’t work, undo it.
    private boolean tryValue(Assignment a, Variable var, Value val){
    	//Back up the variable’s current value.
        Value backup = a.getValue(var);
        //Set the variable to the given value.
        a.setValue(var, val);
        //Try to solve the problem with this new information.
        if(solve(a)){
            return true;
        }
        //If we failed to solve the problem, return the variable to its previous value.
        else{
        	a.setValue(var, backup);
        	return false;
        }
    }
}	