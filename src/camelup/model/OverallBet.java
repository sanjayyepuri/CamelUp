package camelup.model;

/**
 * Created by yepus1 on 3/3/15.
 */
public class OverallBet extends Bet{
    private Player player;
    private boolean win;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public OverallBet(Player player, int value, Camel camel, boolean isWin){
        super(value, camel);
        this.player = player;
        win = isWin;
    }

    public boolean isWin() {
        return win;
    }

    public String toString(){
        return "[Player: " + player.getName() + " Camel: " + this.getCamel() + "]"; //TODO
    }
}
