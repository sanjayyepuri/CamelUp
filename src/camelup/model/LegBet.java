package camelup.model;

/**
 * Created by yepus1 on 3/3/15.
 */
public class LegBet extends Bet {

    public LegBet(int value, Camel camel){
        super(value, camel);
    }

    public int compareTo(Bet o) {
        return super.getValue() - o.getValue();
    }

    public String toString(){
        return super.toString(); //TODO
    }
}
