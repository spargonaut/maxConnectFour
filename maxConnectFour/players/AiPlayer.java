package maxConnectFour.players;

import java.util.*;

import maxConnectFour.gameboard.GameBoard;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 * 
 * @author james spargo
 *
 */

public class AiPlayer {
	
	public int getRandomPlay( GameBoard currentGame ) {
		Random randy = new Random();
		int randomPlay = randy.nextInt( 7 );
		
		while(!currentGame.isValidPlay( randomPlay ))
			randomPlay = randy.nextInt( 7 );
		
		return randomPlay;
	}
	
	/************************ solution methods *******************/

	public int getBestPlay( GameBoard currentGame, int depthLevel ) {
		if (currentGame.getNumberOfPlaysRemaining() > depthLevel) {
			depthLevel = currentGame.getNumberOfPlaysRemaining();
		}
		
		int currentTurn = currentGame.getCurrentTurnBasedOnNumberOfPlays();
		int bestPlay[] = { -1, -1 };
		bestPlay = generateBestMove( depthLevel, 1, currentTurn, currentGame, -333, 444 );
	
		return bestPlay[0];
	}
	
	/**
	 *	This method generates the best move to make from the AiPlayers
	 * perspective. This method, in combination with generateWorstMove(),
	 * implements the minimax algorithm using alpha beta pruning. It is always
	 * called first when trying to decide the best move to make. 
	 * @param maxDepth the maximum search depth to search before giving up.
	 * @param level the current level that the search is being performed at.
	 * @param currentPlayer the players perspective that the algorithm is
	 * looking at
	 * @param lastBoard the state that the GameBoard was in when this method
	 * was called
	 * @param alpha the value of the best move that we have found so far
	 * @param beta the value of the worst move that we have found so far
	 * @return a dual indexed array.<br>index 0 represents the column of the
	 * best move, and index 1 represents the score difference by playing in
	 * that column.
	 */
	private int[] generateBestMove( int maxDepth, int level, int currentPlayer,
			GameBoard lastBoard, int alpha, int beta ) {

		// I need to take into account when two moves are equal
		// i think this is where i need to add in heuristics

		GameBoard testBoard = new GameBoard( lastBoard.getGameBoard() );
		
		 // { column, scoreDiff }    bestMove[ 1 ]  is analagous to v
		int bestMove[] = { -1, -999 };
		int scoreDiff;
		int otherPlayer;

		if( currentPlayer == 1 )
			otherPlayer = 2;
		else
			otherPlayer = 1;

		//terminal condition - find the move that yeilds the highest score
		if( level == maxDepth ) {
			for( int i = 0; i < 7; i++ ) {
				
				if( testBoard.playPiece( i ) ) {
					// count the scores and find the difference between the two
					scoreDiff = testBoard.getScore( currentPlayer ) -
								testBoard.getScore( otherPlayer );
					
					if( scoreDiff > bestMove[ 1 ] ) {
						bestMove[ 0 ] = i;
						bestMove[ 1 ] = scoreDiff;
					} 
						
					if( bestMove[ 1 ] >= beta ) {
						return bestMove;
					}
					
					// minimize alpha and keep checking leaf nodes
					if ( bestMove[ 1 ] > alpha ) {
						alpha = bestMove[ 1 ];
					}
					
					//remove the piece i just played, to continue testing
					testBoard.removePiece( i );
				}
			}	
		} else { //we're not at the max depth yet - need to call geneWorstMove
			int[] nextMove = { 65, -550, -363, 45 };
			
			for( int i = 0; i < 7; i ++ ) {

				if( testBoard.playPiece( i ) ) {
					nextMove = generateWorstMove( maxDepth, level + 1,
							currentPlayer, testBoard, alpha, beta );

					if( nextMove[ 1 ] > bestMove[ 1 ] ) {
						bestMove[ 0 ] = i;
						bestMove[ 1 ] = nextMove[ 1 ];
					}
					
					if( bestMove[ 1 ] >= beta ) {
						return bestMove;
					}
					
					// minimize alpha and keep checking leaf nodes
					if ( bestMove[ 1 ] >  alpha) {
						alpha = bestMove[ 1 ];
					}
					
					testBoard.removePiece( i );
					
				} else {
					//do nothing - that column was full
				}
			}
		} // end else
		
		return bestMove;
	} // end generateBestMove()

	/**
	 *	This method generates the worst move to make from the AiPlayers
	 * perspective. This method, in combination with generateBestMove(),
	 * implements the minimax algorithm using alpha beta pruning. It is always
	 * called second, from generateBestMove() when trying to decide the best
	 * move to make. 
	 * @param maxDepth the maximum search depth to search before giving up.
	 * @param level the current level that the search is being performed at.
	 * @param currentPlayer the players perspective that the algorithm is
	 * looking at
	 * @param lastBoard the state that the GameBoard was in when this method
	 * was called
	 * @param alpha the value of the best move that we have found so far
	 * @param beta the value of the worst move that we have found so far
	 * @return a dual indexed array.<br>index 0 represents the column of the
	 * best move, and index 1 represents the score difference by playing in
	 * that column.
	 */
	
	private int[] generateWorstMove( int maxDepth, int level,
			int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
		// I need to take into account when two moves are equal
		// i think this is where i need to add in heuristics

		GameBoard testBoard = new GameBoard( lastBoard.getGameBoard() );
		
		// { column, scoreDiff }    worstMove is analagous to v
		int worstMove[] = { 70, 700 };  
		int scoreDiff;
		int otherPlayer;

		if( currentPlayer == 1 )
			otherPlayer = 2;
		else
			otherPlayer = 1;

		//terminal condition - find the move that yeilds the highest score
		if( level == maxDepth ) {
			
			for( int k = 0; k < 7; k++ ) {

				if( testBoard.playPiece( k ) ) {
					
					//count the scores and find the difference between the two
					scoreDiff = testBoard.getScore( currentPlayer ) -
								testBoard.getScore( otherPlayer );
					
					if( scoreDiff < worstMove[ 1 ] ) {
						worstMove[ 0 ] = k;
						worstMove[ 1 ] = scoreDiff;
						
						if( worstMove[ 1 ]  <= alpha ) {		
							// minimize alpha and return
							return worstMove;
						}
						
						// minimize beta
						if ( worstMove[ 1 ] < beta ) {
							beta = worstMove[ 1 ];
						}
					}
					
					//remove the piece i just played and continue testing
					testBoard.removePiece( k );
				}
			}			
		} else { // we're not at the max depth yet - need to call genBestMove
			int[] nextMove = { 75, 750 };

			for( int i = 0; i < 7; i ++ ) {
				
				if( testBoard.playPiece( i ) ) {
					
					nextMove = generateBestMove( maxDepth, level + 1,
								currentPlayer, testBoard, alpha, beta );

					if( nextMove[ 1 ] < worstMove[ 1 ] ) {
						worstMove[ 0 ] = i;
						worstMove[ 1 ] = nextMove[ 1 ];
						
						if( worstMove[ 1 ] <= alpha ) {		
							return worstMove;
						} else  if ( worstMove[ 1 ] < beta ) {
							beta = worstMove[ 1 ];
						}
					}
					
					testBoard.removePiece( i );
				}
			}
		} // end else
		
		return worstMove;
	}  // end generateWorstMove()
	
	/******************** end solution methods *******************/

}
