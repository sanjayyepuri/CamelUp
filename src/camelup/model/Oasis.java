package camelup.model;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Oasis {
    private Player player;
    private boolean desert;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isDesert() {
        return desert;
    }

    public void setDesert(boolean desert) {
        this.desert = desert;
    }

    public Oasis(){
        player = null;
        desert = false;
    }
    public Oasis(Player p){
        player = p;
        desert = false;
    }

    public String toString(){
        return "[Player: " + player.getName() + ", desert: " + desert +"]"; //TODO
    }
}
