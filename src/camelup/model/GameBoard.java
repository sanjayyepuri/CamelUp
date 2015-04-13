package camelup.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yepus1 on 3/3/15.
 */
public class GameBoard {
    private ArrayList<Block> board;
    private Pyramid pyramid;

    private ArrayList<Camel> rankedCamel;

    private ArrayList<OverallBet> overallWinBet;
    private ArrayList<OverallBet> overallLostBet;
    private HashMap<Camel, ArrayList<LegBet>> legBets;
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
        rankedCamel = new ArrayList<Camel>();
        board = new ArrayList<Block>();
        for(int i = 0; i <= 16; ++i){
            board.add(new Block(false));
        }
        board.get(16).setEnd(true);
        players = new ArrayList<Player>();
        pyramid = new Pyramid();
        overallLostBet = new ArrayList<OverallBet>();
        overallWinBet = new ArrayList<OverallBet>();
        legBets = new HashMap<Camel, ArrayList<LegBet>>();

        placeCamels();
        //place legbets
        for(int i = 0; i < 5; ++i){
            int[] vals = {5, 3, 2};

            for(int j = 0; j < 3; ++j){
                if(j == 0){
                    legBets.put(new Camel(i), new ArrayList<LegBet>() );
                }
                //5..3..2
                LegBet bet = new LegBet(vals[j], new Camel(i));
                legBets.get(new Camel(i)).add(bet);
            }
        }
        rankCamels();

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

    public HashMap<Camel, ArrayList<LegBet>> getLegBets() {
        return legBets;
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
        return "[Board: " + board + ",\n" + "Players: " + players + ",\nOver All Winner" + overallWinBet + ",\nOver All Loser" + overallLostBet + ",\nLeg Bets:"+ legBets + ",\nPyramid:" + pyramid + "\nRanked: " + rankedCamel + "]"; //TODO
    }

    public void rankCamels(){
        for(int i = board.size()-1; i >= 0; --i){
            Block block = board.get(i);
            for(int j =0; j < block.getCamels().size(); ++j){
                rankedCamel.add(block.getCamels().get(j));
            }
        }
    }
    public Player getPlayer(String name){
        for(Player player: players){
            if(player.getName().equals(name))return player;
        }
        return null;
    }
    public LegBet getLegBet(Camel camel){
        return legBets.get(camel).remove(0);
    }

}
