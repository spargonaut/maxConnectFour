package maxConnectFour.players;

import maxConnectFour.gameboard.GameBoard;

public interface Player {

	public Integer getBestPlay(GameBoard gameboard, int depthLevel);
}
