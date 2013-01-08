package maxConnectFour;

import maxConnectFour.GameBoard;
import java.io.*;

/**
 * 
 * @author James Spargo
 * This class controls the game play for the Max Connect-Four game. <br>
 * To compile the program, use the following command from the parent directory of the maxConnectFour directory:
 * javac maxConnectFour/*.java
 * <br><br>
 * the usage to run the program is as follows:<br>
 * ( again, from the parent directory of the maxConnectFour directory )
 * <br><br>
 *  -- for interactive mode:<br>
 * java maxConnectFour.MaxConnectFour interactive [ /path/to/game/file ] [ computer-next / human-next ] [ search depth]
 * <br><br>
 * -- for one move mode<br>
 * java maxConnectFour.MaxConnectFour one-move [ /path/to/input/game/file ] [ /path/to/output/file ] [ search depth]
 * <br><br>
 * description of arguments:<br> 
 *  [ /path/to/input/game/file ]<br>
 *  -- the path and filename of the input file for the game
 *  <br><br>
 *  [ computer-next / human-next ]<br>
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *  <br><br>
 *  [ /path/to/output/file ]<br>
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *  <br><br>
 *  [ search depth ]<br>
 *  -- the depth of the minimax search algorithm
 * 
 *   
 */

public class MaxConnectFour {
	
	String game_mode;
	String input;
	int depthLevel;

	public static void main(String[] args) {
		MaxConnectFour game = new MaxConnectFour();
		game.play(args);
	}
		
	public void play(String[] args) {

		// check for the correct number of arguments
		checkForProperNumberOfArgments(args);
		
		// parse the input arguments
		String game_mode = args[0].toString();				// the game mode
		String input = args[1].toString();					// the input game file
		int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search
		
		// create and initialize the game board
		GameBoard currentGame = new GameBoard( input );
		
		
		// create the Ai Player
		AiPlayer calculon = new AiPlayer();
		
		//  variables to keep up with the game
		int playColumn = 99;				//  the players choice of column to play
		boolean playMade = false;			//  set to true once a play has been made

		if( game_mode.equalsIgnoreCase( "interactive" ) ) {
			/////////////   interactive mode ///////////

			// get the next turn
			char nextTurn = args[2].charAt( 0 );
			
			// some fields for the user input
			BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
			String userInput = null;
			Character userChar = 'x';

			System.out.println( "--------------------------------------------------------------------------------");			
			System.out.println( "Max Connect Four Client\n - Interactive Mode -");

			//print the current game board
			System.out.println("\ncurrent game: - interactive\n");
			currentGame.printGameBoard();
			
			System.out.println( "\nIt is now Player " + currentGame.getCurrentTurn() + "'s Turn" );
			
			if( !( nextTurn == 'c' || nextTurn == 'C' || nextTurn == 'h' || nextTurn == 'H' ) ) {
				// I don't understand whos turn it is next.
				System.out.println( "!!!!!!!--------->     Houston we have a problem.\n" +
						"I can't tell whos turn it is next\n\n" +
						"you're going to have to try again.\n" +
						"next time, please indicate if it is the human's turn next or the computer's turn\n\n\n" );
				exit_function( 0 );
			}
			
			while ( currentGame.getPieceCount() < 42 ) {
				switch ( nextTurn ) {

				// humans turn
				case 'h':
				case 'H':
					while ( !playMade ) {
						System.out.println("\n--------------------------------------------------------------------------------\n");
						System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
						try {
							// get input from the user
							userInput = br.readLine();
							
							// keep asking for input until we receive something
							while( ( userInput.equals("") ) ) {
								System.out.println("\nI didn't understand that input\n--Try again" );
								System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
								// try getting input fromt the user again
								userInput = br.readLine();
							}
							
							// we only care about the first character
							userChar = userInput.charAt( 0 );
							
						} catch (IOException e) {
							System.out.println("I didn't understand that at all.\nI'm leavin' the program.\n");
							System.exit( 0 );
						}
						
						// check to see if the input is a digit						
						if( Character.isDigit( userChar )) {
							// parse the user input into something we can use
							playColumn = Character.getNumericValue( userChar ) - 1;
							
							// check if the play is valid
							if( currentGame.isValidPlay( playColumn ) ) {
								//make the play
								currentGame.playPiece( playColumn );
								playMade = true;								
							} else {
								// that was an invalid play
								System.out.println("That was an invalid play\n--Try again!");
							}
							
						} else {
							System.out.println("\nThat was not a valid digit.\n--Try again");
						} // end digit check
					}
										
					nextTurn = 'c';
						break;
						// end human turn
						
				//Computers turn
				case 'c':
				case 'C':
					System.out.println("\n--------------------------------------------------------------------------------\n");
					
					//Tell the user which player the computer is playing as
					System.out.println("oh, I see that it is my turn. ....  let me think..." +
							"\n I am playing as player: " + currentGame.getCurrentTurn() );

					// AI play - random play
					// playColumn = calculon.findBestPlay( currentGame );
					
					// AI play - solution play
					playColumn = calculon.findBestPlay( currentGame, depthLevel );
					
					//play the piece
					currentGame.playPiece( playColumn );
					
					nextTurn = 'H';
					break;

				//I don't know who's turn it is.  we shouldn't get here.
				default:
					System.out.println( "what the heck am i doing here?\n I lost track of whose turn it is.\n uh oh" );
				    exit_function( 0 );
				}
				
				//testing
				//System.out.println("\nThe last play was made in column: " + ( playColumn + 1 ) );
				
				//print the current game board and the score
				System.out.println( "\nafter the last move, the board currently looks like this:" );
				currentGame.printGameBoard();

				System.out.println( "\nScores:\n Player1: " + currentGame.getScore( 1 ) +
						"\n Player2: " + currentGame.getScore( 2 ) + "\n " );
				
				//reset playColumn and playMade
				playColumn = 99;
				playMade = false;
				
			} // end while
			
			// the game board is full.
			System.out.println("\n\n\n\nThe Board is Full\n\nGame Over");
			System.out.println("here is the final game state\n");
			currentGame.printGameBoard();
			System.out.println( "Scores:\n Player1: " + currentGame.getScore( 1 ) +
					"\n Player2: " + currentGame.getScore( 2 ) + "\n " );
			return;
			
		} else if( game_mode.equalsIgnoreCase( "one-move" ) ) {
			
			/////////////   one-move mode ///////////
			
			// get the output file name
			String output = args[2].toString();				// the output game file

			System.out.println( "--------------------------------------------------------------------------------");
			System.out.println( "\nMax Connect Four Client\n - One Move Mode - ");

			//print the current game board
			System.out.println("\n\ncurrent game: - one-move\n");
			currentGame.printGameBoard();

			// ****************** this chunk of code makes the computer play
			if( currentGame.getPieceCount() < 42 ) {

				// Tell the user which player the computer is playing for
				System.out.println( "\n\n I am playing as player: " + currentGame.getCurrentTurn() +
						"\n  searching for the best play to depth level: " + depthLevel );				

				// AI play - random play
				//playColumn = calculon.findBestPlay( currentGame );
				
				// AI play - solution play
				playColumn = calculon.findBestPlay( currentGame, depthLevel );
				
				// play the piece
				currentGame.playPiece( playColumn );
				
			} else {
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
			}
			//************************** end computer play
			
			//testing
			//System.out.println("\nThe last play was made in column: " + ( playColumn + 1 ) );
			
			// display the current game board
			System.out.println("...and now the board looks like this: \n");
			currentGame.printGameBoard();

			// print the current scores
			System.out.println( "Scores:\n Player1: " + currentGame.getScore( 1 ) +
					"\n Player2: " + currentGame.getScore( 2 ) + "\n " );

			currentGame.printGameBoardToFile( output );

			return;
			
		} else if( !game_mode.equalsIgnoreCase( "one-move" ) ) {
			System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
			return;
		}
	} // end of main()

	private void checkForProperNumberOfArgments(String[] args) {
		if( args.length != 4 ) {
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function( 0 );
		}
	}
	
	/**
	 * This method is used when to exit the program prematurly.
	 * @param value an integer that is returned to the system when the program exits.
	 */
	private static void exit_function( int value ){
		System.out.println("exiting from MaxConnectFour.java!\n\n");
		System.exit( value );
	}
} // end of class connectFour