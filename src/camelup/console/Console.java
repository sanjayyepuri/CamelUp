package camelup.console;
import camelup.controller.GameController;
import camelup.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by yepus1 on 4/14/15.
 */

public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private Scanner input = new Scanner(System.in);
    private GameController gameController = new GameController();

    public String[] readPlayers(){
        System.out.println("Enter number of players...");
        int num = 3;
        try {
            do {
                num = input.nextInt();
                if (num <= 0) {
                    System.out.println("Please enter a number greater than 0...");
                }
            } while (num <= 0);
        }
        catch (Exception e){}
        String[] list = new String[num];
        for(int i = 0; i < num; ++i){
            System.out.println("Enter name of player " + (i+1) + " ...");
            list[i] = input.next();
        }
        return list;
    }

    public void run() throws Exception{
        gameController.initGameBoard(readPlayers());
        clear();
        boolean gameWon = false;

        //XD
        loadingScreen();

        String test;
        do{
            update();
            test = input.next();
            if(test.equals("next")){
                gameController.nextPlayer();
                clear();
            }
            switch(test){
                case "next": {
                    gameController.nextPlayer();
                    break;
                }
                case "roll": {
                    printRoll(gameController.rollDie(gameController.getCurrPlayer()));
                    break;
                }
                case "legbet": {
                    System.out.println("Enter Camel 0-3 in order...");
                    int camel = input.nextInt();
                    gameController.takeLegBet(gameController.getCurrPlayer(), new Camel(camel));
                    break;
                }
                case "overallbet":{
                    break;
                }
                case "placetile":{
                    System.out.println("Enter index of tile...");
                    int index = input.nextInt();
                    System.out.println("Desert or Oasis(d/o)");
                    String desert = input.next();
                    gameController.placeTile(gameController.getCurrPlayer(), desert.equals("d"), index);
                    break;
                }
            }
        }while(!test.equals("quit"));
    }
    public void printInstructions(){
        System.out.println("'next' to skip turn...");
        System.out.println("'roll' to roll a die...");
        System.out.println("'legbet' to bet on a leg...");
        System.out.println("'overallbet' to place an Overall bet...");
        System.out.println("'placetile' to place an Oasis or Desert...");
    }
    public void printMap(){
        ArrayList<Block> list = gameController.getGameBoard().getBoard();
        String bar    = "+---------";
        String format = "| %-7s ";
        String format1 = "| %-8d";
        String content = "";
        String content1 = "";
        String content2 = "";
        String border = "";
        for (int i = 0; i <= 16; i++) {
            Block b = list.get(i);
            String camels = "";
            for(Camel camel: b.getCamels()){
                String tmp;
                switch (camel.getColor()){
                    case 0: tmp = "W ";break;
                    case 1: tmp = "Y ";break;
                    case 2: tmp = "G ";break;
                    case 3: tmp = "B ";break;
                    case 4: tmp = "O ";break;
                    default: tmp = "";break;
                }
                camels += tmp;
            }
            content += String.format(format1, i+1);
            content1 += String.format(format, camels);
            String oasis;
            Oasis o = b.getOasis();
            if (o != null) {
                oasis = (o.isDesert())?"Desert":"Oasis";
            }
            else oasis = "None";
            content2 += String.format(format, oasis);
            border += bar;
        }
        System.out.println("MAP");
        System.out.println(border + "+");
        System.out.println(content + "|");
        System.out.println(content1 + "|");
        System.out.println(content2 + "|");
        System.out.println(border + "+");
    }
    public void printLegBets(){
        HashMap<Integer, ArrayList<LegBet>> legbets = gameController.getGameBoard().getLegBets();
        String format = "| %-7s | %-5d |%n";
        System.out.println("AVAILABLE LEGBETS");
        System.out.format("+---------+-------+%n");
        System.out.printf("|  Camel  | Value |%n");
        System.out.format("+---------+-------+%n");
        for(int i : legbets.keySet()){
            for(LegBet bet : legbets.get(i)){
                System.out.format(format, bet.getCamel().getColorString(), bet.getValue());
            }
        }
        System.out.format("+---------+-------+%n");
    }
    public void printPlayer(){
        Player player = gameController.getCurrPlayer();
        System.out.println();
        System.out.println(ANSI_PURPLE+player.getName() + ":" + ANSI_RESET);

        System.out.printf("Gold: %2d    Oasis Placed: %-6s%n", player.getMoney(), player.isOasisPlaced());
        String bar    = "+---------";
        String format = "| %-7s ";
        String border = "";
        String content = "";


        for(OverallBet bet : player.getOverAllBets()){
            border+=bar;
            content+=String.format(format, bet.getCamel().getColorString());
        }
        border += "+";
        content += "|";
        System.out.println("OVER ALL BETS");
        System.out.println(border);
        System.out.println(content);
        System.out.println(border);
        System.out.println("LEGBETS");
        border = "";
        content = "";
        String content1 = "";
        for(LegBet bet : player.getLegBets()){
            border += bar;
            content += String.format(format, bet.getCamel().getColorString());
            content1 += String.format("| %-7d ", bet.getValue());
        }
        border += "+";
        content += "|";
        content1 += "|";
        System.out.println(border);
        System.out.println(content);
        System.out.println(content1);
        System.out.println(border);

    }
    public void printRoll(int[] die) throws InterruptedException{
        clear();
        printPyramid();
        System.out.println(new Camel(die[0]).getColorString());
        System.out.println("+---+");
        System.out.printf("| %d |%n", die[1]);
        System.out.println("+---+");
        Thread.sleep(2000);
    }
    public void printBoard(){
        System.out.println(gameController.getGameBoard());
    }
    public void clear(){
        for(int i = 0; i < 50; ++i)System.out.println();
    }

    //STUFF FOR FUN
    public void loadingDots()throws InterruptedException {
        System.out.printf("%27s", "");
        for(int i = 0; i < 3; ++i){
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println();
    }
    public void printCamel(){
        System.out.println(ANSI_BLUE+"                     .--.      .'  `.");
        System.out.println("                   .' . :\\    /   :  L");
        System.out.println("                   F     :\\  /   . : |        .-._");
        System.out.println("                  /     :  \\/        J      .' ___\\");
                System.out.println("                 J     :   /      : : L    /--'   ``.");
        System.out.println("                 F      : J           |  .<'.o.  `-'>");
        System.out.println("                /        J             L \\_>.   .--w)");
        System.out.println("               J        /              \\_/|   . `-__|");
        System.out.println("               F                        / `    -' /|)");
        System.out.println("              |   :                    J   '        |");
        System.out.println("             .'   ':                   |    .    :  \\");
                System.out.println("            /                          J      :     |L");
        System.out.println("           F                              |     \\   ||");
        System.out.println("          F .                             |   :      |");
        System.out.println("         F  |                             ; .   :  : F");
        System.out.println("        /   |                                     : J");
        System.out.println("       J    J             )                ;        F");
        System.out.println("       |     L           /      .:'                J");
        System.out.println("    .-'F:     L        ./       :: :       .       F");
        System.out.println("    `-'F:     .\\    `:.J         :::.             J");
        System.out.println("      J       ::\\    `:|        |::::\\            |");
        System.out.println("      J        |:`.    J        :`:::\\            F");
        System.out.println("       L   :':/ \\ `-`.  \\       : `:::|        .-'");
        System.out.println("       |     /   L    >--\\         :::|`.    .-'");
        System.out.println("       J    J    |    |   L     .  :::: :`, /");
        System.out.println("        L   F    J    )   |        >::   : /");
        System.out.println("        |  J      L   F   \\     .-.:'   . /");
        System.out.println("        ): |     J   /     `-   | |   .--'");
        System.out.println("        /  |     |: J        L  J J   )");
        System.out.println("        L  |     |: |        L   F|   /");
        System.out.println("        \\: J     \\:  L       \\  /  L |");
        System.out.println("         L |      \\  |        F|   | )");
        System.out.println("         J F       \\ J       J |   |J");
        System.out.println("          L|        \\ \\      | |   | L");
        System.out.println("          J L        \\ \\     F \\   F |");
        System.out.println("           L\\         \\ \\   J   | J   L");
        System.out.println("          /__\\_________)_`._)_  |_/   \\_____"+ANSI_RESET);
    }
    public void printTitle(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(ANSI_PURPLE + "_________                       .__     ____ ___         ");
        System.out.println("\\_   ___ \\_____    _____   ____ |  |   |    |   \\______  ");
        System.out.println("/    \\  \\/\\__  \\  /     \\_/ __ \\|  |   |    |   /\\____ \\ ");
        System.out.println("\\     \\____/ __ \\|  Y Y  \\  ___/|  |__ |    |  / |  |_> >");
        System.out.println(" \\______  (____  /__|_|  /\\___  >____/ |______/  |   __/ ");
        System.out.println("        \\/     \\/      \\/     \\/                 |__|    " + ANSI_RESET);
    }
    public void printLine(){
        System.out.println(ANSI_RED+"==========================================================================================================================================================================="+ANSI_RESET);
    }
    public void printPyramid(){
        System.out.println("               '");
        System.out.println("              /=\\");
        System.out.println("             /===\\ \\");
        System.out.println("            /=====\\' \\");
        System.out.println("           /=======\\'' \\");
        System.out.println("          /=========\\ ' '\\");
        System.out.println("         /===========\\''   \\");
        System.out.println("        /=============\\ ' '  \\");
        System.out.println("       /===============\\   ''  \\");
        System.out.println("      /=================\\' ' ' ' \\");
        System.out.println("     /===================\\' ' '  ' \\");
        System.out.println("    /=====================\\' '   ' ' \\");
        System.out.println("   /=======================\\  '   ' /");
        System.out.println("  /=========================\\   ' /");
        System.out.println(" /===========================\\'  /");
        System.out.println("/=============================\\/");
    }
    public void loadingScreen() throws InterruptedException{
        printCamel();
        printTitle();
        loadingDots();
    }

    public void update(){
        clear();
        printMap();
        printLegBets();
        printLine();
        printInstructions();
        printPlayer();
    }
}
