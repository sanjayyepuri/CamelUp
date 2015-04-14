package camelup.controller;
import camelup.model.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yepus1 on 3/7/15.
 */
public class GameController {

    private int currPlayer = 0;
    private final int PYRAMID_MONEY = 1;
    private GameBoard gameBoard;
    private int numPlayers;

    public GameController(){
        gameBoard = new GameBoard();
    }

    public void takeLegBet(Player player, Camel camel){
        player.takeLegBet(gameBoard.getLegBet(camel));
        currPlayer++;
    }
    public void placeOverallWin(Player player, Camel camel){
        gameBoard.placeOverAllWin(player.placeOverallBet(camel, true));
        currPlayer++;
    }
    public void placeOverallLose(Player player, Camel camel){
        gameBoard.placeOverLose(player.placeOverallBet(camel, false));
        currPlayer++;
    }
    public boolean placeTile(Player player, boolean desert, int ind){
        Oasis o = player.placeOasis();
        player.setOasisPlaced(true);
        if (o != null) {
            o.setDesert(desert);
        }
        currPlayer++;
        return gameBoard.placeOasis(o, ind);
    }
    public boolean rollDie(Player player){
        gameBoard.moveCamel();
        player.setRollCount(player.getRollCount() + PYRAMID_MONEY);
        currPlayer++;
        return gameBoard.winState();//DO SOMETHING ELSE
    }
    public void initGameBoard(String[] list){
        gameBoard = new GameBoard(list);
        Collections.shuffle(gameBoard.getPlayers());
        numPlayers = list.length;
    }
    public GameBoard getGameBoard(){
        return gameBoard;
    }
    public Player getCurrPlayer(){
        return gameBoard.getPlayers().get(currPlayer%numPlayers);
    }
    public void nextPlayer(){
        currPlayer++;
    }
}
