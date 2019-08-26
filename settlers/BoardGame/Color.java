import java.util.ArrayList;

/** A class that you cannot instantiate. It
 * contains all of the valid colors.
 *
 * Two players cannot share the same color; hence
 * there is only one of each color available.
 *
 * @author John Angeles
 */
class Color {

    /** A color cannot be instantiated.
     * To construct a color, you must use
     * the static constructors.
     */
    private Color() {}

    /** Returns the unique color black. */
    static Color black() {
        return _colors.get(0);
    }

    /** Returns the unique color white. */
    static Color white() {
        return _colors.get(1);
    }

    /** Returns the unique color orange. */
    static Color orange() {
        return _colors.get(2);
    }

    /** Returns the unique color red. */
    static Color red() {
        return _colors.get(3);
    }

    /** All of the unique colors. */
    private static ArrayList<Color> _colors
            = new ArrayList<>(4);

    /** Adds one of each color to _colors. */
    static {
        _colors.add(new Black());
        _colors.add(new White());
        _colors.add(new Orange());
        _colors.add(new Red());
    }

    /** List of characters of valid colors. */
    static ArrayList<Color> VALID_COLORS
            = _colors;

    @Override
    public String toString() {
        if (this == _colors.get(0)) {
            return "B";
        } else if (this == _colors.get(1)) {
            return "W";
        } else if (this == _colors.get(2)) {
            return "O";
        } else {
            return "R";
        }
    }

    /** The color black. */
    final private static class Black extends Color { }

    /** The color white. */
    final private static class White extends Color { }

    /** The color orange. */
    final private static class Orange extends Color { }

    /** The color red. */
    final private static class Red extends Color { }

    /** Returns true if I am taken by a player. */
    private boolean _taken;

}

