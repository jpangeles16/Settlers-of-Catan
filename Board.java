import java.util.LinkedList;

/** The game board that consists of 19 hexes.
 * There is only one board in the game.
 * The board is setup like any other Catan game.
 * @author John Angeles
 */
class Board {

    /** Generates the board with hexes numbered from 1 to 19.
     * Also distributes probability tokens from the center
     * of the circle.
     */
    static void generateBoard() {
        LinkedList<Resource> resources = new LinkedList<>();


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
    private static LinkedList<Resource> RESOURCES
            = new LinkedList<>();

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



}
