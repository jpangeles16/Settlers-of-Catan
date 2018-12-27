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

    }

    /** Generates a random number between MIN and MAX.
     * Credit: https://www.w3schools.com/js/js_random.asp
     */
    static int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    /** The tokens. There are 18 tokens listed alphabetically
     * in increasing order. For example, index one gives
     * the token A.
     * By indexing you return the unique probability number
     * associated with the token.
     * In the following list, I have the index to the
     * very left column, followed by the proper probability
     * numbers in the very right.
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
