package camelup.model;
import java.util.ArrayList;

/**
 * Created by yepus1 on 3/3/15.
 */
public class GameBoard {
    private ArrayList<Block> board;
    private Pyramid pyramid;
    private ArrayList<OverallBet> overallWinBet;
    private ArrayList<OverallBet> overallLostBet;
    private ArrayList<LegBet> legBets;
    private ArrayList<Player> players;
    private int numPlayers;

    public GameBoard(){
        init();
    }
    public GameBoard(int num){
        init();
        numPlayers = num;
        for (int i = 0; i < numPlayers; ++i){
            players.add(new Player("" + i));
        }

    }
    public GameBoard(String[] names){
        init();
        for(String name: names){
            players.add(new Player(name));
        }
    }
    public void init(){
        board = new ArrayList<Block>();
        for(int i = 0; i <= 16; ++i){
            board.add(new Block(false));
        }
        board.get(16).setEnd(true);
        players = new ArrayList<Player>();
        pyramid = new Pyramid();
        overallLostBet = new ArrayList<OverallBet>();
        overallWinBet = new ArrayList<OverallBet>();
        legBets = new ArrayList<LegBet>();
        placeCamels();
        //place legbets
        for(int i = 0; i < 5; ++i){
            int[] vals = {5, 3, 2};
            for(int j = 0; j < 3; ++j){
                //5..3..2
                legBets.add(new LegBet(vals[j], new Camel(i)));
            }
        }

    }
    public ArrayList<Block> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Block> board) {
        this.board = board;
    }

    public Pyramid getPyramid() {
        return pyramid;
    }

    public void setPyramid(Pyramid pyramid) {
        this.pyramid = pyramid;
    }

    public ArrayList<OverallBet> getOverallWinBet() {
        return overallWinBet;
    }

    public void setOverallWinBet(ArrayList<OverallBet> overallWinBet) {
        this.overallWinBet = overallWinBet;
    }

    public ArrayList<OverallBet> getOverallLostBet() {
        return overallLostBet;
    }

    public void setOverallLostBet(ArrayList<OverallBet> overallLostBet) {
        this.overallLostBet = overallLostBet;
    }

    public ArrayList<LegBet> getLegBets() {
        return legBets;
    }

    public void setLegBets(ArrayList<LegBet> legBets) {
        this.legBets = legBets;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void moveCamel(){

    }
    public void moveCamelTo(Camel c, int distance){
        int block = findCamel(c);
        ArrayList<Camel> arr = new ArrayList<Camel>();
        if (block != -1) {
            arr = board.get(block).removeCamel(c);
        }
        block += distance;
        for (Camel camel: board.get(block).getCamels()){
            arr.add(camel);
        }
        board.get(block).setCamels(arr);
    }
    public int findCamel(Camel camel){
        for(int i = 0; i < board.size(); ++i) {
            Block block = board.get(i);
            for (Camel c : block.getCamels()){
                if(c.getColor() == camel.getColor()){
                    return i;
                }
            }
        }
        return -1;
    }
    public void placeCamels(){
        for(int i = 0; i < 4; ++i){
            int position = (int)(Math.random()*3);
            board.get(position).addCamel(new Camel(i));
        }
    }

    public void placeOverAllWin(OverallBet bet){
        if(bet.isWin()){
            overallWinBet.add(bet);
        }
    }
    public void placeOverLose(OverallBet bet) {
        if (!bet.isWin()) {
            overallLostBet.add(bet);
        }
    }
    public String toString(){
        return "[Board: " + board + ",\n" + "Players: " + players + ",\nOver All Winner" + overallWinBet + ",\nOver All Loser" + overallLostBet + ",\nLeg Bets:"+ legBets + ",\nPyramid:" + pyramid + "]"; //TODO
    }

}
