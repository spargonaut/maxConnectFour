package maxConnectFour.gameboard;

import maxConnectFour.GameBoard;

public class BoardPrinter {
	
	public void printGameBoard(GameBoard gameboard) {
		System.out.println("   1 2 3 4 5 6 7   <---  Column numbers\n -----------------");
		
		int[][] playBoard = gameboard.getGameBoard();

		for( int i = 0; i < 6; i++ ) {
			System.out.print("_| ");
			for( int j = 0; j < 7; j++ ) {
				if( playBoard[i][j] == 0 ) {
					System.out.print("  ");
				} else {
					System.out.print( playBoard[i][j] + " " );
				}
			}
			System.out.print("|_\n");
		}

		System.out.println(" -----------------\n   1 2 3 4 5 6 7   " +
				"<---Column numbers");
	}

}
