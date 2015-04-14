package camelup.console;

import camelup.controller.GameController;

import java.util.Scanner;


/**
 * Created by yepus1 on 4/14/15.
 */
public class Console {
    private Scanner input = new Scanner(System.in);
    private GameController gameController = new GameController();

    public String[] readPlayers(){
        System.out.println("Enter number of players...");
        int num = input.nextInt();
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

        clear();
        printBoard();

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
        System.out.println("                     .--.      .'  `.");
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
        System.out.println("          /__\\_________)_`._)_  |_/   \\_____");
    }
    public void printTitle(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("_________                       .__     ____ ___         ");
        System.out.println("\\_   ___ \\_____    _____   ____ |  |   |    |   \\______  ");
        System.out.println("/    \\  \\/\\__  \\  /     \\_/ __ \\|  |   |    |   /\\____ \\ ");
        System.out.println("\\     \\____/ __ \\|  Y Y  \\  ___/|  |__ |    |  / |  |_> >");
        System.out.println(" \\______  (____  /__|_|  /\\___  >____/ |______/  |   __/ ");
        System.out.println("        \\/     \\/      \\/     \\/                 |__|    ");
    }
    public void loadingScreen() throws InterruptedException{
        printCamel();
        printTitle();
        loadingDots();
    }

}
