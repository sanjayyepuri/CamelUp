package camelup.controller;
import camelup.model.*;

import java.util.ArrayList;

/**
 * Created by yepus1 on 3/7/15.
 */
public class GameController {

    private GameBoard gameBoard;

    public GameController(){
        gameBoard = new GameBoard();
    }

    public void takeLegBet(Player player, Camel camel){
        player.takeLegBet(gameBoard.getLegBet(camel));
    }
    public void placeOverallWin(Player player, Camel camel){
        gameBoard.placeOverAllWin(player.placeOverallBet(camel, true));
    }
    public void placeOverallLose(Player player, Camel camel){
        gameBoard.placeOverLose(player.placeOverallBet(camel, false));
    }

}
