package camelup.model;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Bet implements Comparable<Bet>{
    private int value;
    private Camel camel;

    public Bet(int value, Camel camel){
        this.value = value;
        this.camel = camel;
    }

    public int compareTo(Bet x){
        return value - x.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Camel getCamel() {
        return camel;
    }

    public void setCamel(Camel camel) {
        this.camel = camel;
    }

    public String toString(){
        return "\n[Camel: "+camel.toString() + ", Value: "+ value +"]"; //TODO
    }
}

