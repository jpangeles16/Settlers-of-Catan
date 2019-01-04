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

    /** Initializes a building that is not yet placed with color COLOR
     * and belongs to player PLAYER.
     */
    Building(Color color, Player player) {
        assert Color.VALID_COLORS.contains(color) : "Invalid color!";
        _placed = false;
        _color = color;
        _myOwner = player;
    }

    /** Returns the player that owns me. */
    Player player() {
        return _myOwner;
    }

    /** Returns me to my player that owns me.
     * This function assumes that I have already been removed
     * from the hex that I have been on.
     */
    void returnToPlayer() {
        //FIXME
    }

    /** Settlements give 1 VP, while cities give 2. */
    abstract int victoryPoints();

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

    /** The player that I belong to. */
    private Player _myOwner;

    /** Set of hexes that I am adjacent to. */
    private ArrayList<Hex> _adjHexes;

    /** True if I am placed on the board, false otherwise. */
    private boolean _placed;

    /** My color. */
    protected Color _color;

}
