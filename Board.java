
/** The game board that consists of 19 hexes.
 * There is only one board in the game.
 * @author John Angeles
 */
class Board {

    /** Generates the board with hexes numbered from 1 to 19.
     * Also distributes probability tokens from the center
     * of the circle.
     */
    static void generateBoard() {
        
    }

    /** Random number generator between min and max inclusive. */
    static int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }


}
