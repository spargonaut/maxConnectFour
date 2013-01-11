package maxConnectFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import maxConnectFour.gameboard.BoardPrinter;
import maxConnectFour.gameboard.BoardReader;
import maxConnectFour.gameboard.GameBoard;
import maxConnectFour.players.AiPlayer;
import maxConnectFour.players.PlayerIdentifier;

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
	
	PlayMode playMode;
	String input;
	int depthLevel;

	public static void main(String[] args) {
		MaxConnectFour game = new MaxConnectFour();
		try {
			game.play(args);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
		
	public void play(String[] args) throws IOException {

		checkForProperNumberOfArgments(args);
		
		parseInputArguments(args);
		
		BoardReader boardReader = new BoardReader();
		GameBoard currentGame = boardReader.readGame(input);
		
		BoardPrinter boardPrinter = new BoardPrinter();
		
		// create the Ai Player
		AiPlayer calculon = new AiPlayer();
		
		//  variables to keep up with the game
		int playColumn = 99;				//  the players choice of column to play
		boolean playMade = false;			//  set to true once a play has been made

		switch(playMode) {
		case INTERACTIVE:
			PlayerIdentifier nextTurnEnum = getFirstPlayer(args);

			printInitialGameBoardState(currentGame, boardPrinter);
			
			System.out.println( "\nIt is now Player " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn" );
			
			// some fields for the user input
			BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
			String userInput = null;
			Character userChar = 'x';
			
			while ( currentGame.getCountOfPiecesPlayed() < 42 ) {
				System.out.println("\n--------------------------------------------------------------------------------\n");
				switch ( nextTurnEnum ) {
				case HUMAN:
					while ( !playMade ) {
						
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
								currentGame.playPieceInColumn( playColumn );
								playMade = true;								
							} else {
								// that was an invalid play
								System.out.println("That was an invalid play\n--Try again!");
							}
							
						} else {
							System.out.println("\nThat was not a valid digit.\n--Try again");
						} // end digit check
					}
										
					nextTurnEnum = PlayerIdentifier.COMPUTER;
					break;
						
				case COMPUTER:
					System.out.println( "\n\n I am playing as player: " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + depthLevel );	

					// AI play - solution play
					playColumn = calculon.getBestPlay( currentGame, depthLevel );
					
					System.out.println("and I'm playing in column " + playColumn);
					
					//play the piece
					currentGame.playPieceInColumn( playColumn );
					
					nextTurnEnum = PlayerIdentifier.HUMAN;
					break;

				//I don't know who's turn it is.  we shouldn't get here.
				default:
					System.out.println( "what the heck am i doing here?\n I lost track of whose turn it is.\n uh oh" );
				    System.exit(0);
				}
				
				printCurrentGameBoardAndScores(currentGame, boardPrinter);

				//reset playColumn and playMade
				playColumn = 99;
				playMade = false;
				
			} // end while
			
			// the game board is full.
			printTheFinalGameState(currentGame, boardPrinter);
			break;
			
		case ONE_MOVE:
			// get the output file name
			String output = args[2].toString();				// the output game file

			printInitialGameBoardState(currentGame, boardPrinter);

			// ****************** this chunk of code makes the computer play
			if( currentGame.getCountOfPiecesPlayed() < 42 ) {

				// Tell the user which player the computer is playing for
				System.out.println( "\n\n I am playing as player: " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + depthLevel );				
				playColumn = calculon.getBestPlay( currentGame, depthLevel );
				currentGame.playPieceInColumn( playColumn );
				
			} else {
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
			}
			
			printCurrentGameBoardAndScores(currentGame, boardPrinter);
			
			boardPrinter.printGameBoardToFile(output, currentGame);

			break;
		}
	}

	protected void printInitialGameBoardState(GameBoard currentGame, BoardPrinter boardPrinter) {
		System.out.println( "--------------------------------------------------------------------------------");
		System.out.println( "\nMax Connect Four Client\n - " + playMode + " Mode");
		boardPrinter.printGameBoard(currentGame);
	}

	protected void printCurrentGameBoardAndScores(GameBoard currentGame, BoardPrinter boardPrinter) {
		System.out.println("\n...and now the board looks like this: \n");
		boardPrinter.printGameBoard(currentGame);
		printCurrentScores(currentGame);
	}

	protected void printCurrentScores(GameBoard currentGame) {
		System.out.println( "Scores:\n Player1: " + currentGame.getScore( 1 ) + "\n Player2: " + currentGame.getScore( 2 ) + "\n " );
	}

	private void printTheFinalGameState(GameBoard currentGame, BoardPrinter boardPrinter) {
		System.out.println("Here is the final game state\n");
		boardPrinter.printGameBoard(currentGame);
		printCurrentScores(currentGame);
	}

	private void parseInputArguments(String[] args) {
		String game_mode = args[0].toString();				// the game mode
		playMode = parsePlayMode(game_mode);
		input = args[1].toString();					// the input game file
		depthLevel = Integer.parseInt( args[3] );  		// the depth level of the ai search
	}

	private void checkForProperNumberOfArgments(String[] args) {
		if( args.length != 4 ) {
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			System.exit(0);
		}
	}
	
	private PlayerIdentifier getFirstPlayer(String[] args) {
		char firstPlayerParamater = args[2].charAt(0);
		
		validateFirstPlayerParameter(firstPlayerParamater);
		PlayerIdentifier firstPlayer = null;
		if ("c".equalsIgnoreCase(Character.toString(firstPlayerParamater))) {
			firstPlayer = PlayerIdentifier.COMPUTER;
		} else {
			firstPlayer = PlayerIdentifier.HUMAN;
		}
		return firstPlayer;
	}
	
	private void validateFirstPlayerParameter(char nextTurn) {
		if( !( nextTurn == 'c' || nextTurn == 'C' || nextTurn == 'h' || nextTurn == 'H' ) ) {
			// I don't understand whos turn it is next.
			System.out.println( "!!!!!!!--------->     Houston we have a problem.\n" +
					"I can't tell whos turn it is next\n\n" +
					"you're going to have to try again.\n" +
					"next time, please indicate if it is the human's turn next or the computer's turn\n\n\n" );
			System.exit(0);
		}
	}
	
	protected PlayMode parsePlayMode(String input) {
		validateGameModeArgument(input);
		PlayMode mode = (input.equalsIgnoreCase( "interactive" )) ? PlayMode.INTERACTIVE : PlayMode.ONE_MOVE;
		return mode;
	}
	
	protected void validateGameModeArgument(String playMode) {
		if (!(playMode.equalsIgnoreCase("interactive") || playMode.equalsIgnoreCase( "one-move" ))) {
			throw new IllegalArgumentException(playMode);
		}
	}
}