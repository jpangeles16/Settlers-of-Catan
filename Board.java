import afu.org.checkerframework.checker.oigj.qual.O;
import net.sf.saxon.expr.Component;

import java.util.Collections;
import java.util.ArrayList;

/** The game board that consists of 19 hexes.
 * There is only one board in the game.
 * The board is setup like any other Catan game.
 * Also contains some useful random functions.
 * @author John Angeles
 */
final class Board {

    /** Returns an integer either 0 or 1. */
    static int coinFlip() {
        return genRandom(0, 1);
    }

    /** Generates a random number between MIN and MAX, inclusive.
     * Credit: https://www.w3schools.com/js/js_random.asp
     */
    static int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

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
     * It first randomly distributes the resources, then
     * distributes probability tokens from the center
     * of the board.
     * Finally, it sets the desert tile's number to be 0.
     */
    static void reset() {
        Collections.shuffle(RESOURCES);
        for (int i = 0; i < 19; i += 1) {
            BOARD[i].setResource(RESOURCES.get(i));
            if (BOARD[i].resource() == DESERT) {
                BOARD[i].setNumber(0);
            }
        }
        distributeTokens();
    }

    /** Distributes the tokens in a random fashion
     * starting from the center of the board. Note that although the
     * process in which we do so is random, there is a certain
     * algorithm/mechanism that we always follow when we distribute
     * the tokens.
     */
    static void distributeTokens() {
        boolean clockwise = true;
        int currToken = 17;

        if (coinFlip() == 0) {
            clockwise = false;
        }

        if (CENTER.resource() != DESERT) {
            CENTER.setNumber(_tokens[currToken]);
            currToken -= 1;
        }

        int currMiddle = genRandom(0, 5);


        for (int i = 0; i < 6; i += 1) {
            if (MIDDLE.get(currMiddle).resource() != DESERT) {
                MIDDLE.get(currMiddle).setNumber(_tokens[currToken]);
                currToken -= 1;
            }
            if (clockwise) {
                currMiddle = (currMiddle + 1) % 6;
            } else {
                currMiddle -= 1;
                if (currMiddle < 0) {
                    currMiddle += 6;
                }
            }
        }

        Hex outerStarting;

        if (clockwise) {
            outerStarting
                    = clockwiseOuter(MIDDLE.get(currMiddle));
        } else {
            outerStarting
                    = counterClockwiseOuter(MIDDLE.get(currMiddle));
        }

        int currOuter = OUTER.indexOf(outerStarting);

        for (int i = 0; i < 12; i += 1) {
            if (OUTER.get(currOuter).resource() != DESERT) {
                OUTER.get(currOuter).setNumber(_tokens[currToken]);
                currToken -= 1;
            }
            if (clockwise) {
                currOuter = (currOuter + 1) % 12;
            } else {
                currOuter -= 1;
                if (currOuter < 0) {
                    currOuter += 12;
                }
            }
        }
    }

    /** Helper for distribute tokens. Returns the hex that
     * follows after the middle hex.
     */
    private static Hex clockwiseOuter(Hex hex) {
        assert MIDDLE.contains(hex) : "Invalid hex!";
        if (hex == BOARD[4]) {
            return BOARD[3];
        } else if (hex == BOARD[5]) {
            return BOARD[1];
        } else if (hex == BOARD[10]) {
            return BOARD[6];
        } else if (hex == BOARD[14]) {
            return BOARD[15];
        } else if (hex == BOARD[13]) {
            return BOARD[17];
        } else {
            return BOARD[12];
        }
    }

    /** Helper for distribute tokens. Returns the hex that
     * follows after the middle hex when we go counterclockwise
     * while setting up the board.
     */
    private static Hex counterClockwiseOuter(Hex hex) {
        assert MIDDLE.contains(hex) : "Invalid hex!";
        if (hex == BOARD[4]) {
            return BOARD[1];
        } else if (hex == BOARD[5]) {
            return BOARD[6];
        } else if (hex == BOARD[10]) {
            return BOARD[15];
        } else if (hex == BOARD[14]) {
            return BOARD[17];
        } else if (hex == BOARD[13]) {
            return BOARD[12];
        } else {
            return BOARD[3];
        }
    }

    /** Returns a string representation of the board. */
    public static String dump() {
        String[] hex1, hex2, hex3;
        String sixBlanks = "      ";
        ArrayList<String> lines = new ArrayList<>();
        hex1 = BOARD[0].dump().split("\\n");
        hex2 = BOARD[1].dump().split("\\n");
        hex3 = BOARD[2].dump().split("\\n");

        for (int i = 0; i < 5; i += 1) {
            lines.add(sixBlanks + sixBlanks + hex1[i].substring(0, 12)
                    + hex2[i].substring(0, 12) + hex3[i]);
        }
        String[] hex4, hex5, hex6, hex7;
        hex4 = BOARD[3].dump().split("\\n");
        hex5 = BOARD[4].dump().split("\\n");
        hex6 = BOARD[5].dump().split("\\n");
        hex7 = BOARD[6].dump().split("\\n");

        for (int i = 1; i < 5; i += 1) {
            lines.add(sixBlanks + hex4[i].substring(0, 12)
                    + hex5[i].substring(0, 12) + hex6[i].substring(0, 12)
                    + hex7[i]);
        }

        String[] hex8, hex9, hex10, hex11, hex12;
        hex8 = BOARD[7].dump().split("\\n");
        hex9 = BOARD[8].dump().split("\\n");
        hex10 = BOARD[9].dump().split("\\n");
        hex11 = BOARD[10].dump().split("\\n");
        hex12 = BOARD[11].dump().split("\\n");

        for (int i = 1; i < 5; i += 1) {
            lines.add(hex8[i].substring(0, 12)
                    + hex9[i].substring(0, 12) + hex10[i].substring(0, 12)
                    + hex11[i].substring(0, 12) + hex12[i]);
        }

        String[] hex13, hex14, hex15, hex16;
        hex13 = BOARD[12].dump().split("\\n");
        hex14 = BOARD[13].dump().split("\\n");
        hex15 = BOARD[14].dump().split("\\n");
        hex16 = BOARD[15].dump().split("\\n");

        for (int i = 1; i < 5; i += 1) {
            lines.add(sixBlanks + hex13[i].substring(0, 12)
                    + hex14[i].substring(0, 12) + hex15[i].substring(0, 12)
                    + hex16[i]);
        }

        String[] hex17, hex18, hex19;
        hex17 = BOARD[16].dump().split("\\n");
        hex18 = BOARD[17].dump().split("\\n");
        hex19 = BOARD[18].dump().split("\\n");

        for (int i = 1; i < 7; i += 1) {
            lines.add(sixBlanks + sixBlanks + hex17[i].substring(0, 12)
                    + hex18[i].substring(0, 12) + hex19[i]);
        }

        String result = "";

        for (int i = 0; i < lines.size(); i += 1) {
            result = result.concat(lines.get(i) + "\n");
        }

        return result;
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
     * in increasing order. For example, index 0 gives
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
    private static final int[] _tokens
            = new int[] {5, 2, 6, 3, 8, 10, 9, 12, 11,
            4, 8, 10, 9, 4, 5, 6, 3, 11};

    /** Returns the unique hex by indexing with its axial
     * coordinates.
     * Returns null if the hex doesn't exist.
     */
    private static final Hex[][] AXIAL
            = new Hex[][] { {null, null, BOARD[7], BOARD[12], BOARD[16]},
                    {null, BOARD[3], BOARD[8], BOARD[13], BOARD[17]},
                    {BOARD[0], BOARD[4], BOARD[9], BOARD[14], BOARD[18]},
                    {BOARD[1], BOARD[5], BOARD[10], BOARD[15], null},
                    {BOARD[2], BOARD[6], BOARD[11], null, null} };

    /** The centermost hex. */
    private static final Hex CENTER = get(0, 0);

    /** List of hexes one step away from the center, enumerated
     * clockwise, starting from hex 5. Keep this in order!
     */
    private static final ArrayList<Hex> MIDDLE
            = new ArrayList<>(6);

    /** Sets up MIDDLE. */
    static {
        MIDDLE.add(get(0, -1));
        MIDDLE.add(get(1, -1));
        MIDDLE.add(get(1, 0));
        MIDDLE.add(get(0, 1));
        MIDDLE.add(get(-1, 1));
        MIDDLE.add(get(-1, 0));
    }

    /** List of hexes two steps away from the center, enumerated
     * clockwise, starting from hex 1.
     */
    private static final ArrayList<Hex> OUTER
            = new ArrayList<>(12);

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

    /** The resource Desert. */
    private static Resource DESERT = Resource.desert();

}