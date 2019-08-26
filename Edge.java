/** An edge that may or may not contain a road.
 * @author John Angeles
 */
public class Edge {

    /** Constructs a vacant edge. */
    Edge() {
        _hasRoad = false;
    }

    /** Constructs me that contains ROAD. */
    Edge(Road road) {
        _road = road;
    }

    @Override
    public String toString() {
        if (_hasRoad) {
            return _road.toString();
        } else {
            return "R";
        }
    }

    /** Boolean indicating if I have a road on me. */
    private boolean _hasRoad;

    /** My road, or null if I don't have one. */
    private Road _road;
}
