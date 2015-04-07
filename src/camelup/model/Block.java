package camelup.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Block {
    private ArrayList<Camel> camels;
    private boolean isEnd;
    private Oasis oasis;

    public Block(boolean isend){
        isEnd = isend;
        camels = new ArrayList<Camel>();
        oasis = null;
    }

    public void addCamel(Camel x){
        camels.add(x);
    }
    public void addOasis(Oasis o){
        oasis = o;
    }
    public ArrayList<Camel> removeCamel(Camel c){
        ArrayList<Camel> arr = new ArrayList<Camel>();

        for(int i = 0; i < camels.size(); ++i){
            System.out.println(i);
            Camel camel = camels.get(i);
            if(i < camels.size()){
                arr.add(camel);
            }
            camels.remove(camel);
            if(camel.getColor() == c.getColor()){
                break;
            }
        }

        return arr;

    }

    public ArrayList<Camel> getCamels() {
        return camels;
    }

    public void setCamels(ArrayList<Camel> camels) {
        this.camels = camels;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Oasis getOasis() {
        return oasis;
    }

    public void setOasis(Oasis oasis) {
        this.oasis = oasis;
    }

    public Oasis removeOasis() {

        Oasis o = oasis;
        oasis = null;
        return o;
    }

    public String toString(){
        return "\n[IsEnd:" + isEnd + ", Camels: " + camels + ", Oasis: " + oasis + "]"; //TODO
    }
}
