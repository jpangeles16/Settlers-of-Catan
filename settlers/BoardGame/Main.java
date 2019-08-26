import java.util.Scanner;
/** Starts the game up
@author John Angeles
 */
public class Main {

    /** Starts up the game.
     *
     * @param args Type in "start" to start the game. Caps sensitive!
     */
    public static void main(String[] args) {
        // Initialize the scanner
        _console = new Scanner(System.in);

        System.out.println("Welcome! All credit goes to the designers of Settlers of Catan.");
        System.out.println("Type in 'start' to start the game!");
        System.out.println("Type in 'quit' to exit the program!");

        // Fetch input from the user until the user inputs something valid
        while (true) {
            String userInput = _console.nextLine();
            if (userInput.equals("quit")) {
                quit();
                System.out.println("Type 'start' to play!");
            } else if (!userInput.equals("start")) {
                incorrectArgs();
            } else {
                startGame();
                break;
            }
        }
    }

    /** If the user does not input start to start up the game,
     * then we print out a message telling the user what to do.
     */
    private static void incorrectArgs() {
        System.out.println("Invalid input!");
        System.out.println("Type in 'start' to start the game!");
        System.out.println("Type in 'quit' to exit the program!");
    }

    /** Upon calling this function from main, we can start the game
     */
    private static void startGame() {
        System.out.println("Starting the game!");
        // First, initialize the board
        System.out.println("Initializing the board... ");
        displayBoard();
        // Then, name all of the players
        namePlayers();
        // Finally, take turns placing settlements
        placeSettlements();
    }

    /** Displays the board.
     */
    private static void displayBoard() {
        System.out.println(Board.dump());
    }

    /** Part where player gets to put the names.
     */
    private static void namePlayers() {
        // First player
        while (true) {
            System.out.println("What will be player one's name?");
            String name = _console.nextLine();
            if (name.equals("quit")) {
                quit();
            } else {
                _player1 = new Player(Color.black(), name);
                break;
            }
        }
        // Second player
        while (true) {
            System.out.println("What will be player two's name?");
            String name = _console.nextLine();
            if (name.equals("quit")) {
                quit();
            } else {
                _player2 = new Player(Color.white(), name);
                break;
            }
        }
        // Third player
        while (true) {
            System.out.println("What will be player three's name?");
            String name = _console.nextLine();
            if (name.equals("quit")) {
                quit();
            } else {
                _player3 = new Player(Color.orange(), name);
                break;
            }
        }
        // Fourth player
        while (true) {
            System.out.println("What will be player four's name?");
            String name = _console.nextLine();
            if (name.equals("quit")) {
                quit();
            } else {
                _player4 = new Player(Color.red(), name);
                break;
            }
        }
    }

    /** Whenever the player types in 'quit', asks the player if they want to quit. Returns nothing
     * if the user did not want to quit.
     */
    private static void quit() {
        System.out.println("Are you sure you want to quit? Type 'yes' to quit or 'no' to keep playing");
        while (true) {
            String next = _console.nextLine();
            if (next.equals("yes")) {
                System.exit(0);
            } else if (!next.equals("no")) {
                System.out.println("Please type in 'yes' or 'no'.");
            } else {
                System.out.println("Let's keep playing!");
                return;
            }
        }
    }

    /** Whenever

    /* The players.
     */
    private static Player _player1;
    private static Player _player2;
    private static Player _player3;
    private static Player _player4;

    /* The console.
     */
    private static Scanner _console;

}
