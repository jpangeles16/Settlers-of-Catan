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

    /** Places me on HEX on position POSN. Also sets my adjacent hexes
     * appropriately
     * @param hex Hex to place me on
     * @param posn Position to place me on on hex
     */
    void placeOn(Hex hex, int posn) {
        hex.addBuilding(posn, this);
        storeHexes(hex, posn);
    }

    /** Assumes placeOn has been called. Stores the hex's adjacent hexes
     * on POSN. */
    private void storeHexes(Hex hex, int posn) {
        _adjHexes = hex.adjacentHexes(posn);
    }

    /** Returns an ArrayList of hexes that I am next to. */
    ArrayList<Hex> adjHexes() {
        return _adjHexes;
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
    private ArrayList<Hex> _adjHexes;

    /** True if I am placed on the board, false otherwise. */
    private boolean _placed;

    /** My color. */
    protected Color _color;
}
