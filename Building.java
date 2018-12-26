import java.util.ArrayList;

/** A building can either be a settlement or a city a player owns.
 *
 * @author John Angeles
 */
abstract class Building {

    /** Initializes a building that is not yet placed with color COLOR. */
    Building(Color color) {
        assert Color.VALID_COLORS.contains(color) : "Invalid color!";
        _placed = false;
        _color = color;
    }

    /** Settlements give 1 VP, while cities give 2. */
    abstract int victoryPoints();

//    /** Collect resources from each of my adjacent settlements. */
//    abstract void collect();

//    /** A building must be placed onto POSN on HEX.
//     *
//     * Once we place the building on HEX,
//     *
//     * Raises an assertion error if we place a building on a position that
//     * already contains a building.
//     *
//     * @param hex The id of the hex in which we put this building on.
//     *
//     * @param posn 0 - 5, where 0 is position north, 1 is northwest, etc
//     *             (see _adjBuildings in Hex for more details)
//     */
//    Building(char color, int hex, int posn) {
//
//    }
//
//    /** Places a building onto POSN on HEX. This time hex is an actual hex obj. */
//    Building(char color, Hex hex, int posn) {
//        hex.addBuilding(posn, this);
//    }

    /** As soon as
    void addAdjHex() {

    }

    /** Marks me as placed or not. */
    void setPlacedTo(boolean placed) {
        _placed = placed;
    }

    /** Returns true if I am placed. */
    boolean placed() {
        return _placed;
    }

    /** Set of hexes that I am adjacent to. */
    private ArrayList<Hex> _adjHexes = new ArrayList<>(3);

    /** True if I am placed on the board, false otherwise. */
    private boolean _placed;

    /** My color. */
    protected Color _color;
}
