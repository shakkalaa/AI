package edu.uno.ai.sat.ex;

import java.util.*;

import edu.uno.ai.sat.*;
//import edu.uno.ai.util.ImmutableArray;


/**
 * 
 * @author Shakayla Mosely
 */
public class Smosely extends Solver {
	
	//random number generator 
	private final Random random = new Random(0);
	//noise factor
	private final double noise = 0.15;

    public Smosely(){
        super("smosely1");
    }

    @Override
    public boolean solve(Assignment assignment){
    	
    	//Give start value
    	for(Variable variable : assignment.problem.variables) {
    		boolean value = random.nextBoolean();
    		if(value == true) {
    			assignment.setValue(variable, Value.TRUE);
    		}else {
    			assignment.setValue(variable, Value.FALSE);
    		}	
    	}
    	
    	//If solved then return true
    	//if(assignment.problem.equals(Value.TRUE)){
    	if(assignment.getValue() == Value.TRUE) {
    		return true;
    	}
    	
    	boolean solved = false;

    	//while not solved
    	while(!solved) {
    		
        	if(assignment.getValue() == Value.TRUE) {
        		return true;
        	}
    	
    		//generate random number boolean from 0-1
    		double randomNum = random.nextDouble();
    		Literal bestLiteral = null;
    		
    		//find unsolved clause
    		//for each clause in problem
    		List<Clause> unsolvedClauses =  new ArrayList<>();
    		for( Clause clause: assignment.problem.clauses) {
    			//if clause = False
    			if(assignment.getValue(clause)== Value.FALSE) {
    				unsolvedClauses.add(clause);
    				break;
    			}
    		}
    		
    		
    		Clause randomClause = unsolvedClauses.get(random.nextInt(unsolvedClauses.size()));
    		Literal flipRandom;
    		//if random < noise
        	if(randomNum < noise) {
        		/*List<Literal> literals =  new ArrayList<>();
        		//pick a random literal in that clause
        		//for(Literal literal : randomClause.literals) {
        		//literals.add(literal);*/
        			
        		/*flipRandom = randomClause.literals.get(random.nextInt(randomClause.literals.size()));
     			
       		 	flipValue(assignment, flipRandom);
       			//flip it*/
        		//for each literal in clause
        		for(Literal literal : randomClause.literals) {
        				
        			flipValue(assignment, literal);			
        					
        			//count # of true clause
        			int max = 0;
        			int trueClause = assignment.countTrueClauses();
        					
        			//keep best one
        			if(trueClause > max) {
        				max = trueClause;
        				bestLiteral = literal;
        			}
        					
        			flipValue(assignment, literal);
        		}
        		
        		//permanently flip 
        		flipValue(assignment, bestLiteral);
        		//solved = true;
        	
       		
       		
        	}else {
        		flipRandom = randomClause.literals.get(random.nextInt(randomClause.literals.size()));
     			
       		 	flipValue(assignment, flipRandom);
       			//flip it
        		
        		//for each literal in clause
        		/*for(Literal literal : randomClause.literals) {
        				
        			flipValue(assignment, literal);			
        					
        			//count # of true clause
        			int max = 0;
        			int trueClause = assignment.countTrueClauses();
        					
        			//keep best one
        			if(trueClause > max) {
        				max = trueClause;
        				bestLiteral = literal;
        			}
        					
        			flipValue(assignment, literal);
        		}
        		
        		//permanently flip 
        		flipValue(assignment, bestLiteral);
        		//solved = true;*/
        	}
    		
    	}
    	return true;

    }
    public void flipValue(Assignment assignment, Literal literal) {
    	//flip it back
		if(assignment.getValue(literal.variable) == Value.FALSE) {
			
			assignment.setValue(literal.variable, Value.TRUE);
			
		}else {
			assignment.setValue(literal.variable, Value.FALSE);
		}
    }
    

 
}	