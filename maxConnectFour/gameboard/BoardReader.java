package maxConnectFour.gameboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import maxConnectFour.GameBoard;



public class BoardReader {

	private int[][] playBoard;
	private int pieceCount;
//	private int currentTurn;
	
	public GameBoard readGame(String inputFile) {
		
		this.playBoard = new int[6][7];
		this.pieceCount = 0;
		int counter = 0;
		
		String gameData = null;

		BufferedReader input = openTheInputFile(inputFile);

		for(int i = 0; i < 6; i++) {
			try {
				gameData = input.readLine();

				// read each piece from the input file
				for( int j = 0; j < 7; j++ ) {

					this.playBoard[ i ][ j ] = Integer.parseInt(Character.toString(gameData.charAt(counter++)));

					// sanity check
					checkIfMarkIsValidOrExit(i, j);

					if( this.playBoard[ i ][ j ] > 0 )
					{
						this.pieceCount++;
					}
				}
			} catch( IOException e ) {
				System.out.println("\nProblem reading the input file!\nTry again.\n");
				e.printStackTrace();
			}

			//reset the counter
			counter = 0;

		}

		return new GameBoard(playBoard);
		
	}

	private void checkIfMarkIsValidOrExit(int i, int j) {
		if( markIsNotValid(i, j) ) {
			System.out.println("\nProblems!\n--The piece read from the input file was not a 1, a 2 or a 0" );
			System.exit(0);
		}
	}

	private boolean markIsNotValid(int i, int j) {
		return !( ( this.playBoard[ i ][ j ] == 0 ) ||
			   ( this.playBoard[ i ][ j ] == 1 ) ||
			   ( this.playBoard[ i ][ j ] == 2 ) );
	}

	private BufferedReader openTheInputFile(String inputFile) {
		BufferedReader input = null;
		try {
			input = new BufferedReader( new FileReader( inputFile ) );
		} catch( IOException e ) {
			System.out.println("\nProblem opening the input file!\nTry again.\n");
			e.printStackTrace();
		}
		return input;
	}
	
	public int getCurrentTurn() {
		return ( this.pieceCount % 2 ) + 1 ;
	} 
}
