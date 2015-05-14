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
    //Player Options
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
    public int[] rollDie(Player player){
        int[] die = gameBoard.moveCamel();
        System.out.println("STEVE" + player.getMoney());
        player.setMoney(player.getMoney() + PYRAMID_MONEY);
        currPlayer++;
        return die;
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
    public boolean isGameOver(){
        Block lastBlock = gameBoard.getBoard().get( gameBoard.getBoard().size()-1);
        if(lastBlock.getCamels().size() > 0){
            return true;
        }
        return false;
    }
    public boolean isRoundOver(){
        if(gameBoard.getPyramid().isEmpty()){
            return true;
        }
        return false;
    }
    public void resetPyramid(){
        gameBoard.setPyramid(new Pyramid());
    }
    public void roundEnd(){
        gameBoard.rankCamels();
        System.out.println("RANK" + gameBoard.rankedCamel);
        Camel win = gameBoard.firstPlace();
        ArrayList<Player> players = gameBoard.getPlayers();
        for(Player player: players){
            for(LegBet legBet: player.getLegBets()){
                if(legBet.getCamel().getColor() == win.getColor()){
                    player.setMoney(player.getMoney() + legBet.getValue());
                }
            }
            player.setLegBets(new ArrayList<LegBet>());
        }
        gameBoard.resetLegBets();
    }
}
