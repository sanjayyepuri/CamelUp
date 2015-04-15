package camelup.model;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yepus1 on 3/3/15.
 */
public class GameBoard {
    private final int NUM_CAMELS = 4;
    private final int OASIS_MONEY = 1;
    private final int LAST_BLOCK = 16;
    private ArrayList<Block> board;
    private Pyramid pyramid;
    private ArrayList<Camel> rankedCamel;
    private ArrayList<OverallBet> overallWinBet;
    private ArrayList<OverallBet> overallLostBet;
    private HashMap<Integer, ArrayList<LegBet>> legBets;
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
        for(int i = 0; i <= LAST_BLOCK; ++i){
            board.add(new Block(false));
        }
        board.get(LAST_BLOCK).setEnd(true);
        players = new ArrayList<Player>();
        pyramid = new Pyramid();
        overallLostBet = new ArrayList<OverallBet>();
        overallWinBet = new ArrayList<OverallBet>();
        legBets = new HashMap<Integer, ArrayList<LegBet>>();
        placeCamels();
        //place legbets
        for(int i = 0; i < NUM_CAMELS+1; ++i){
            int[] vals = {5, 3, 2};

            for(int j = 0; j < 3; ++j){
                if(j == 0){
                    legBets.put(i, new ArrayList<LegBet>() );
                }
                //5..3..2
                LegBet bet = new LegBet(vals[j], new Camel(i));
                legBets.get(i).add(bet);
            }
        }
        rankCamels();

    }
    public ArrayList<Block> getBoard() {
        return board;
    }
    //Getters and Setters
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

    public HashMap<Integer, ArrayList<LegBet>> getLegBets() {
        return legBets;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
;
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int[] rollDie(){
        return pyramid.rollDie();
    }
    public int[] moveCamel(){
        int[] die  = rollDie();
        moveCamelTo(new Camel(die[0]), die[1]);
        return die;
    }

    public void moveCamelDebug(int camel, int distance){
        int[] die = pyramid.rollDieDebug(camel, distance);
        moveCamelTo(new Camel(die[0]), die[1]);
    }

    public void moveCamelTo(Camel c, int distance){
        int block = findCamel(c);
        ArrayList<Camel> arr = new ArrayList<Camel>();
        if (block != -1) {
            arr = board.get(block).removeCamel(c);
        }
        block += distance;
        System.err.println(distance);
        if(board.get(block).getOasis() ==  null) {
            for (Camel camel : board.get(block).getCamels()) {
                arr.add(camel);
            }
            board.get(block).setCamels(arr);
        }
        else{
            board.get(block).getOasis().getPlayer().setMoney(board.get(block).getOasis().getPlayer().getMoney()+OASIS_MONEY);
            if(board.get(block).getOasis().isDesert()){
                block--;
                for(Camel camel:arr){
                    board.get(block).getCamels().add(camel);
                }
            }
            else{
                block++;
                for (Camel camel : board.get(block).getCamels()) {
                    arr.add(camel);
                }
                board.get(block).setCamels(arr);
            }

        }
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
        return "[Board: " + board +
                ",\nPlayers: " + players +
                ",\nOver All Winner" + overallWinBet +
                ",\nOver All Loser" + overallLostBet +
                ",\nLeg Bets:"+ legBets +
                ",\nPyramid:" + pyramid +
                ",\nRanked: " + rankedCamel + "]"; //TODO
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
        return legBets.get(camel.getColor()).remove(0);
    }
    public boolean placeOasis(Oasis oasis, int index){
        if(board.get(index).getOasis() == null && board.get(index-1).getOasis() == null && board.get(index + 1).getOasis() == null) {
            if(!board.get(index).isEnd()) {
                board.get(index).setOasis(oasis);
                return true;
            }
        }
        return false;
    }
    public boolean winState(){
        if(board.get(LAST_BLOCK).isEnd()){
            if(board.get(LAST_BLOCK).getCamels().size() > 0){
                return true;
            }
            return false;
        }return false;
    }
}
