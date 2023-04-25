package com.stephengware.java.games.chess.bot;

import java.util.*;
import com.stephengware.java.games.chess.bot.*;
import com.stephengware.java.games.chess.state.*;

/**
 * A chess bot which selects its next move at random.
 * 
 * @author Stephen G. Ware
 */
public class smosely1 extends Bot {

	/** A random number generator */
	private int maxDepth;
	private Player player;


	
	/**
	 * Constructs a new chess bot named "My Chess Bot" and whose random  number
	 * generator (see {@link java.util.Random}) begins with a seed of 0.
	 */
	public smosely1() {
		super("Klumsy");
		this.maxDepth = maxDepth;
	}

	/*
	@Override
	protected State chooseMove(State root) {
		// This list will hold all the children nodes of the root.
		ArrayList<State> children = new ArrayList<>();
		// Generate all the children nodes of the root (that is, all the
		// possible next states of the game.  Make sure that we do not exceed
		// the number of GameTree nodes that we are allowed to generate.
		Iterator<State> iterator = root.next().iterator();
		while(!root.searchLimitReached() && iterator.hasNext())
			children.add(iterator.next());
		// Choose one of the children at random.
		return children.get(random.nextInt(children.size()));
	}*/

	@Override
	public State chooseMove(State state) {
		int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int maxEval = Integer.MIN_VALUE;
        State bestMove = null;
        
        Iterator<State> iterator = state.next().iterator();
		ArrayList<State> children = new ArrayList<>();
		
		while (!state.searchLimitReached() && iterator.hasNext()) {
			children.add(iterator.next());
		}
        for (State child : children) {
                        
            int eval = miniMax(maxDepth - 1, alpha, beta, state, false);
            
            if (eval > maxEval) {
                maxEval = eval;
                bestMove = child;
            }
        }
        return bestMove;
    }


	private int miniMax(int depth, int alpha, int beta, State state, boolean maxPlayer) {
		if (depth == 0 || state.over) {
	        return evaluateState(state);
	    }
		 
		//Iterator<State> iterator = state.next().iterator();
		ArrayList<State> children = new ArrayList<>();


	    if (maxPlayer) {
	        int maxEval = Integer.MIN_VALUE;
	        for(State child : children) {
	                
	            int eval = miniMax(depth - 1, alpha, beta, child, false);
	                
	            maxEval = Math.max(maxEval, eval);
	            alpha = Math.max(alpha, eval);
	            if (beta <= alpha) {
	                break;
	            }
	        }
	        return maxEval;
	    } else {
	        int minEval = Integer.MAX_VALUE;
	        for(State child : children) {
	                
	            int eval = miniMax(depth - 1, alpha, beta, child, true);
	                
	            minEval = Math.min(minEval, eval);
	            beta = Math.min(beta, eval);
	            if (beta <= alpha) {
	                break;
	            }
	        }
	        return minEval;
	    }
    }

	private int evaluateState(State state) {
       int score = 0;
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = state.board.getPieceAt(rank, file);
                if (piece != null) {
                    int pieceValue = getPieceValue(piece);
                    if (piece.player == Player.WHITE) {
                        score += pieceValue;
                    } else {
                        score -= pieceValue;
                    }
                }
            }
        }
        return score;
    }

	private int getPieceValue(Piece piece) {
		
		if (piece.getClass() == Pawn.class) {
			return 1;
			
		} else if (piece.getClass() == Knight.class) { 
			return 3;
			
		}else if (piece.getClass() == Bishop.class) {
			return 3;

		}else if (piece.getClass() == Rook.class) {
			return 5;

		}else if (piece.getClass() == Queen.class) {
			return 9;
		
		}else if (piece.getClass() == King.class) {
			return 1000;
		
		}else{
			return 0;
		}
        /*switch (piece.getType()) {
            case Pawn.class:
                return 1;
            case Knight.class:
                return 3;
            case Bishop.class:
                return 3;
            case Rook.class:
                return 5;
            case Queen.class:
                return 9;
            case King.class:
                return 1000;
            default:
                return 0;
        }*/
    }
}