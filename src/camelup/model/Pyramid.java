package camelup.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Pyramid {
    private class Die{
        Camel camel;
        int distance;
        public Die(Camel c, int d){
            camel = c;
            distance = d;
        }
        public String toString(){
            return "[Camel: " + camel + ", Distance: " + distance +"]";
        }
    }

    private ArrayList<Integer> die;
    private ArrayList<Die> rolled;
    public Pyramid(){
        rolled = new ArrayList<Die>();
        die = new ArrayList<Integer>();
        for(int i = 0; i < 5; ++i){
            die.add(i);
        }
    }
    public int[] rollDie(){
        int camel = (int) Math.round(Math.random()*5);
        int dice = (int)Math.round(Math.random()*3) + 1;
        die.remove(new Integer(camel));
        int[] arr = new int[2];
        arr[0] = camel;
        arr[1] = dice;
    //INDEX 0 IS THE CAMEL AND 1 IS THE DISTANCE ROLLED
        rolled.add(new Die(new Camel(camel), dice));
        System.err.println("DICE: "  + Arrays.toString(arr));
        return arr;
    }
    public int[] rollDieDebug(int camel, int distance){
        int[] tmp = new int[2];
        tmp[0] = camel;
        tmp[1] = distance;
        return tmp;
    }
    public String toString(){
        return "[Rolled: " + rolled + ", Left: " + die + "]"; //TODO
    }
}
