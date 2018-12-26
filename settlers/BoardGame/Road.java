/** A road.
 * @author John Angeles
 */
class Road {
    /** Initializes an unplaced road with a certain color. */
    Road(Color color) {
        assert Color.VALID_COLORS.contains(color)
                : "Invalid color!";
        _color = color;
        _isPlaced = false;

    }

    @Override
    public String toString() {
        char uppercase = _color.toString().charAt(0);
        return String.valueOf(Character.toLowerCase(uppercase));
    }

    /** My color. */
    private Color _color;

    /** True if I am placed on the board. */
    private boolean _isPlaced;

}
