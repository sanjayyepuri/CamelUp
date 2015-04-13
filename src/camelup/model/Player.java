package camelup.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by yepus1 on 3/3/15.
 */
public class Player {
    private String name;
    private int rollCount;
    private ArrayList<OverallBet> overAllBets;
    private ArrayList<LegBet> legBets;
    private int money;
    private Oasis oasis;
    private boolean oasisPlaced;


    public void init(){
        for(int i = 0; i < 5; ++i){
            overAllBets.add(new OverallBet(this, 0, new Camel(i), true));
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }

    public ArrayList<OverallBet> getOverAllBets() {
        return overAllBets;
    }

    public void setOverAllBets(ArrayList<OverallBet> overAllBets) {
        this.overAllBets = overAllBets;
    }

    public ArrayList<LegBet> getLegBets() {
        return legBets;
    }

    public void setLegBets(ArrayList<LegBet> legBets) {
        this.legBets = legBets;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Oasis getOasis() {
        return oasis;
    }

    public void setOasis(Oasis oasis) {
        this.oasis = oasis;
    }

    public boolean isOasisPlaced() {
        return oasisPlaced;
    }

    public void setOasisPlaced(boolean oasisPlaced) {
        this.oasisPlaced = oasisPlaced;
    }

    public Player(String name){
        this.name = name;
        rollCount = 0;
        overAllBets = new ArrayList<OverallBet>();
        legBets = new ArrayList<LegBet>();
        money = 3;
        oasis = new Oasis(this);
        oasisPlaced = false;
        init();
    }
    public void putOasis(){}
    public void takeLegBet(LegBet bet){
        legBets.add(bet);
    }
    public OverallBet placeOverallBet(Camel camel, boolean win){
        OverallBet b = null;
        for(OverallBet bet : overAllBets){
            if(bet.getCamel().equals(camel)){
                b = bet;
            }
        }
        b.setWin(win);
        overAllBets.remove(b);
        return b;
    }
    public Oasis placeOasis(){
        if(!oasisPlaced) return oasis;
        return null;
    }

    public String toString(){
        return "\n[Name: " + name + ", Leg Bets: " + legBets + "OverAll Bets: " + overAllBets + ", Oasis: " + oasis + "]"; //TODO
    }



}
