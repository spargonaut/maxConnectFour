package org.spargonaut.maxConnectFour.players;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;

public interface Player {

	public Integer getBestPlay(GameBoard gameboard, int depthLevel);
}
