package maxConnectFour.gameboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import maxConnectFour.GameBoard;



public class BoardReader {

	private int[][] playBoard;
	private int pieceCount;
	private int currentTurn;
	
	public GameBoard readGame(String inputFile) {
		
		this.playBoard = new int[6][7];
		this.pieceCount = 0;
		int counter = 0;
		
		String gameData = null;

		BufferedReader input = openTheInputFile(inputFile);

		//read the game data from the input file
		for(int i = 0; i < 6; i++) {

			try {
				gameData = input.readLine();

				// testing
				//System.out.println("I just read ->" + gameData + "<- outer for loop");

				// read each piece from the input file
				for( int j = 0; j < 7; j++ ) {

					//testing- uncomment the next 3 lines to see each piece that was read in
					//System.out.println("I just read ->" + ( gameData.charAt( counter ) - 48 ) + "<- inner for loop");

					this.playBoard[ i ][ j ] = gameData.charAt( counter++ ) - 48;

					// sanity check
					if( markIsNotValid(i, j) ) {
						System.out.println("\nProblems!\n--The piece read from the input file was not a 1, a 2 or a 0" );
						System.exit(0);
					}

					if( this.playBoard[ i ][ j ] > 0 )
					{
						this.pieceCount++;
					}
				}
			} catch( IOException e ) {
				System.out.println("\nProblem reading the input file!\nTry again.\n");
				e.printStackTrace();
				System.exit(0);
			}

			//reset the counter
			counter = 0;

		}

		// read one more line to get the next players turn
		try {
			gameData = input.readLine();
		} catch( Exception e ) {
			System.out.println("\nProblem reading the next turn!\n" +
					"--Try again.\n");
			e.printStackTrace();
		}

		this.currentTurn = gameData.charAt( 0 ) - 48;

		//testing-uncomment the next 2 lines to see which current turn was read
		//System.out.println("the current turn i read was->" + this.currentTurn );

		// make sure the turn corresponds to the number of pcs played already
		verifyCorrectTurnOrExit();
		
		return new GameBoard(playBoard);
		
	}

	private void verifyCorrectTurnOrExit() {
		if(!( ( this.currentTurn == 1) || ( this.currentTurn == 2 ) ) ) {
			System.out.println("Problems!\n the current turn read is not a 1 or a 2!");
			System.exit(0);
		} else if ( this.getCurrentTurn() != this.currentTurn ) {
			System.out.println("Problems!\n the current turn read does not correspond to the number of pieces played!");
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
			System.out.println("\nProblem opening the input file!\nTry again." +
			"\n");
			e.printStackTrace();
		}
		return input;
	}
	
	public int getCurrentTurn() {
		return ( this.pieceCount % 2 ) + 1 ;
	} 
	

}
