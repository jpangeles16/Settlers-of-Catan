/** A road.
 * @author John Angeles
 */
class Road {

    /** Initializes an unplaced road with a certain color. */
    Road(Color color) {
        assert Color.VALID_COLORS.contains(color)
                : "Invalid color!";
        _color = color;
    }

    /** Initializes an unplaced road that belongs to a
     * certain player. */
    Road(Color color, Player player) {
        this(color);
        _player = player;
    }

    /** Returns me to my player that owns me.
     * This function assumes that I have already been removed
     * from the hex that I have been on.
     */
    void returnToPlayer() {
        //FIXME
    }

    /** Returns my color. */
    Color color() {
        return _color;
    }

    /** Returns the player that owns me, or null if I am not
     * owned by anyone.
     */
    Player player() {
        return _player;
    }

    @Override
    public String toString() {
        char uppercase = _color.toString().charAt(0);
        return String.valueOf(Character.toLowerCase(uppercase));
    }

    /** My color. */
    private Color _color;

    /** My player that I belong to. */
    private Player _player;

}
