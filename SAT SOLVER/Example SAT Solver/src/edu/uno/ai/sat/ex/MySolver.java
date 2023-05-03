package edu.uno.ai.sat.ex;

import java.util.ArrayList;
import java.util.List;

import edu.uno.ai.sat.*;

public class MySolver extends Solver {
    
    private int maxOperations;
    private long startTime;
    private List<List<Literal>> clauses;
    
    public MySolver() {
        super("smosely1");
        this.maxOperations = 1000000;
        this.startTime = System.currentTimeMillis();
        clauses = new ArrayList<>();

    }
    
    public void addClause(List<Literal> clause) {
       
        clauses.add(clause);
    }
    
    @Override
    public boolean solve(Assignment assignment) {
        List<List<Literal>> clauses = this.clauses;
        return reSolve(clauses, assignment, 0);
    }
    
    private boolean reSolve(List<List<Literal>> clauses, Assignment assignments, int numOperations) {
    	boolean value = true;
        if (numOperations >= maxOperations || System.currentTimeMillis() - startTime >= 5 * 60 * 1000) {
            return false; // exceeded maximum operations or time
        }
        if (clauses.isEmpty()) {
            return true; // all clauses are satisfied
        }
        for (List<Literal> clause : clauses) {
            if (clause.isEmpty()) {
                return false; // an empty clause means the formula is unsatisfiable
            }
            Literal literal;

            if (clause.size() == 1) {
                literal = clause.get(0);
                Variable variable = literal.variable;
                //Literal negatedLiteral = new Literal(variable, !literal.value, false);

                if (assignments.getValue(variable) == Value.UNKNOWN) {
                	
                	//value = !literal.negate();
                	assignments.setValue(variable, value ? Value.TRUE : Value.FALSE);
                    List<List<Literal>> newClauses = simplify(clauses, variable, !value);
                    boolean result = reSolve(newClauses, assignments, numOperations + 1);
                    if (result) {
                        return true;
                    }
                    assignments.setValue(variable, Value.UNKNOWN);
                }
            }
        }
        Variable variable = null;
        int minOccurrences = Integer.MAX_VALUE;
        
        //for (Variable v : variable) {
        	
            Value variableValue = assignments.getValue(variable);
            if (variableValue == Value.UNKNOWN) {
                int occurrences = countOccurrences(clauses, variable);
                if (occurrences < minOccurrences) {
                    //variable = variable;
                    value = true;
                    minOccurrences = occurrences;
                }
                if (occurrences < minOccurrences) {
                    //variable = variable;
                    value = false;
                    minOccurrences = occurrences;
                }
            }
        //}
        //Literal literal = new Literal(variable, value);
        //Literal negatedLiteral = new Literal(variable, !value);
        
        if (assignments.getValue(variable) == Value.UNKNOWN) {
            //Value v = Value.fromBoolean(!literal.negate());
            assignments.setValue(variable, Value.FALSE);
            List<List<Literal>> newClauses = simplify(clauses, variable, value);
            boolean result = reSolve(newClauses, assignments, numOperations + 1);
            if (result) {
                return true;
            }
            assignments.setValue(variable, Value.UNKNOWN);
        }
        return false; // no more unassigned variables, backtrack
    }

    
    @SuppressWarnings("unlikely-arg-type")
	private List<List<Literal>> simplify(List<List<Literal>> clauses, Variable variable, boolean value) {
        List<List<Literal>> newClauses = new ArrayList<List<Literal>>();
        
        for (List<Literal> clause : clauses) {
           
        	if (!clause.contains(variable) != value) {
                List<Literal> newClause = new ArrayList<Literal>();
                
                for (Literal literal : clause) {
                    if (!literal.equals(variable) == value) {
                        newClause.add(literal);
                    }
                }
                if (!newClause.isEmpty()) {
                    newClauses.add(newClause);
                }
            }
        }
        return newClauses;
    }

    @SuppressWarnings("unlikely-arg-type")
	private int countOccurrences(List<List<Literal>> clauses, Variable variable) {
        int count = 0;
        for (List<Literal> clause : clauses) {
            if (clause.contains(variable) == false || clause.contains(variable) == true) {
                count++;
            }
        }
    return count;
    }
    
}