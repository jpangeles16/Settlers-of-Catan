import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;

/** The game board that consists of 19 hexes.
 * There is only one board in the game.
 * The board is setup like any other Catan game.
 * @author John Angeles
 */
class Board {

    /** You can't create a new board.
     * This is the only board; you must use the static methods provided.
     */
    private Board() { }

    /** Returns the hex corresponding to the axial coordinates q and r.
     *
     * @param q Number of steps horizontally from the center of the board
     * @param r Number of steps vertically from the center of the board
     * @return Hex corresponding to the axial coordinates q and r
     */
    static Hex get(int q, int r) {
        return AXIAL[q + 2][r + 2];
    }

    /** Returns the board in the form of an array of hexes. */
    static Hex[] hexList() {
        return BOARD;
    }

    /** Generates the board with hexes numbered from 1 to 19.
     * Also distributes probability tokens from the center
     * of the board.
     */
    static void reset() {
        Collections.shuffle(RESOURCES);
        for (int i = 0; i < 19; i += 1) {
            BOARD[i].setResource(RESOURCES.get(i));
        }
    }

    /** Either 0 or 1. */
    static int coinFlip() {
        return genRandom(0, 1);
    }

    /** Generates a random number between MIN and MAX.
     * Credit: https://www.w3schools.com/js/js_random.asp
     */
    static int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    /** List of all resources in the game.
     * In a typical board, there are:
     * 4 wood hexes
     * 4 wheat hexes
     * 4 sheep hexes
     * 3 brick hexes
     * 3 ore hexes
     * 1 desert hex
     */
    static ArrayList<Resource> RESOURCES
            = new ArrayList<>();

    /** Sets up our RESOURCES list. */
    static {
        for (int i = 0; i < 4; i += 1) {
            RESOURCES.add(Resource.wood());
        }

        for (int i = 0; i < 4; i += 1) {
            RESOURCES.add(Resource.wheat());
        }

        for (int i = 0; i < 4; i += 1) {
            RESOURCES.add(Resource.sheep());
        }

        for (int i = 0; i < 3; i += 1) {
            RESOURCES.add(Resource.brick());
        }

        for (int i = 0; i < 3; i += 1) {
            RESOURCES.add(Resource.ore());
        }

        RESOURCES.add(Resource.desert());
    }

    /** Returns a string representation of the board. */
    public static String dump() {
        return null;
    }

    /** The board itself. Indexing returns a specific hex numbered from 1 to
     * 19.
     */
    private static Hex[] BOARD = new Hex[19];

    /** Sets up BOARD. */
    static {
        for (int i = 0; i < 19; i += 1) {
            BOARD[i] = new Hex(i + 1, 2);
        }
    }

    /** The tokens. There are 18 tokens listed alphabetically
     * in increasing order. For example, index one gives
     * the token A.
     * By indexing you return the unique probability number
     * associated with the token.
     * In the following list, I have the index to the
     * very left column, followed by the proper probability
     * numbers in the very right.
     *
     * Credit: https://boardgames.stackexchange.com/questions
     * /2740/distribution-of-tokens-in-standard-4-player-catan
     *
     * 00, A = 5
     * 01, B = 2
     * 02, C = 6
     * 03, D = 3
     * 04, E = 8
     * 05, F = 10
     * 06, G = 9
     * 07, H = 12
     * 08, I = 11
     * 09, J = 4
     * 10, K = 8
     * 11, L = 10
     * 12, M = 9
     * 13, N = 4
     * 14, O = 5
     * 15, P = 6
     * 16, Q = 3
     * 17, R = 11. */
    private int[] _tokens
            = new int[] {5, 2, 6, 3, 8, 10, 9, 12, 11,
            4, 8, 10, 9, 4, 5, 6, 3, 11};

    /** Returns the unique hex by indexing with its axial
     * coordinates.
     * Returns null if the hex doesn't exist.
     */
    private static Hex[][] AXIAL
            = new Hex[][] { {null, null, BOARD[7], BOARD[12], BOARD[16]},
                    {null, BOARD[3], BOARD[8], BOARD[13], BOARD[17]},
                    {BOARD[0], BOARD[4], BOARD[9], BOARD[14], BOARD[18]},
                    {BOARD[1], BOARD[5], BOARD[10], BOARD[15], null},
                    {BOARD[2], BOARD[6], BOARD[11], null, null} };

    /** The centermost hex. */
    private static Hex CENTER = get(0, 0);

    /** List of hexes one step away from the center, enumerated
     * clockwise, starting from hex 5.
     */
    private static ArrayList<Hex> MIDDLE = new ArrayList<>(6);

    /** Sets up MIDDLE. */
    static {
        MIDDLE.add(get(0, 0));
        MIDDLE.add(get(1, -1));
        MIDDLE.add(get(1, 0));
        MIDDLE.add(get(0, 1));
        MIDDLE.add(get(-1, 1));
        MIDDLE.add(get(-1, 0));
    }

    /** List of hexes two steps away from the center, enumerated
     * clockwise, starting from hex 1.
     */
    private static ArrayList<Hex> OUTER = new ArrayList<>(12);

    /** Sets up OUTER. */
    static {
        OUTER.add(get(0, -2));
        OUTER.add(get(1, -2));
        OUTER.add(get(2, -2));
        OUTER.add(get(2, -1));
        OUTER.add(get(2, 0));
        OUTER.add(get(1, 1));
        OUTER.add(get(0, 2));
        OUTER.add(get(-1, 2));
        OUTER.add(get(-2, 2));
        OUTER.add(get(-2, 1));
        OUTER.add(get(-2, 0));
        OUTER.add(get(-1, -1));
    }

}