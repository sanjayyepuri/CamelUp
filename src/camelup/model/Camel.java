package camelup.model;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Camel implements Comparable<Camel> {
    private int color;
    public Camel(int color){
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int compareTo(Camel x){
        return this.color - x.color;
    }

    public String toString(){
        String c = "";
        switch (color) {
            case 0:
                c = "WHITE";break;
            case 1:
                c = "YELLOW";break;
            case 2:
                c = "GREEN";break;
            case 3:
                c = "BLUE";break;
            case 4:
                c = "ORANGE";break;
            default: break;
        }
        return "[Color: " + c + "]"; //TODO
    }
}
