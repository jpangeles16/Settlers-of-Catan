import java.util.ArrayList;

/** Represents a hex.  Each hex contains six edges and six points
 * where each edge can contain a road, and each point can contain a settlement.
 *
 * Each hex is designated a random number generated from 2 to 12.
 *
 * Each hex is designated a random resource. A resource is either wood, brick,
 * wheat, ore, or sheep.
 *
 * Each hex must have adjacent hexes. This means that hexes act
 * like linked lists.
 *
 * There are 19 hexes total in a regular Settlers game.
 *
 * All of these methods for now assume that we have placed all of the hexes,
 * and then placed the pieces on the hexes, rather than placing pieces on
 * an individual hex and then adding a hex adjacent to the original hex.
 *
 * Lastly, each hex can potentially contain the robber.
 * @author John Angeles
 */
final class Hex {

    /** Generates me with a certain number.
     * @param number Number that I have. When the
     *               sum of the dice rolls is my number,
     *               the settlements adjacent to me get my resource
     *               if the robber is not on me.
     *               Of course, the probability
     *               of rolling a 2 is much lower
     *               than that of a 7.
     *               The desert tile gets a number 0.
     *
     * @param id Each hex has a unique id. We will enumerate each hex starting
     *           from the top-left corner, and move from left to right and from
     *           up to down.  The very first hex (top left) has index 0,
     *           while the very last hex (bottom right) has index 18.
     */
    Hex(int id, int number) {
        assert (number == 0 ||
                (2 <= number && number <= 12)) : "Invalid number!";
        _id = id;
        _number = number;
    }

    /** Returns my id. */
    int id() {
        return _id;
    }

    /** Sets me to produce RESOURCE. */
    void setResource(Resource resource) {
        _resource = resource;
    }

    /** Returns my current resource, but errors if I don't have one set. */
    Resource resource() {
        assert _resource != null : "I have no resource set!";
        return _resource;
    }

    /** Sets my number to be NUMBER. */
    void setNumber(int number) {
        _number = number;
    }

    /** Returns my number. */
    int number() {
        return _number;
    }

    /**

    /** ===== Set of functions that return hexes adjacent to me. =====
     *
     * @return Hex if there is an adjacent hex, or null if there is no adjacent
     *         hex.  For instance, the very first hex returns null if we here to
     *         call its northWest method.
     */
    Hex northEast() {
        return _adjHexes[0];
    }

    Hex east() {
        return _adjHexes[1];
    }

    Hex southEast() {
        return _adjHexes[2];
    }

    Hex southWest() {
        return _adjHexes[3];
    }

    Hex west() {
        return _adjHexes[4];
    }

    Hex northWest() {
        return _adjHexes[5];
    }

    /** Returns hex next to my SIDE.
     * @param side 0 = northeast
     *             1 = east
     *             2 = southeast
     *             3 = southwest
     *             4 = west
     *             5 = northwest
     *
     * @return Hex if there is one, or null otherwise
     */
    Hex adjHex(int side) {
        assert 0 <= side && side <= 5 : "Invalid position!";
        return _adjHexes[side];
    }

    /** ===== Set of functions that set hexes to be adjacent to me. =====
     * Every time I successfully call this function, it will automatically set
     * HEX to be adjacent to me, and HEX will have me be adjacent to it as well.
     * @param hex The hex that will be adjacent to me.
     */
    void setNorthEast(Hex hex) {
        _adjHexes[0] = hex;
        if (hex.southWest() != this) {
            hex.setSouthWest(this);
        }
    }

    void setEast(Hex hex) {
        _adjHexes[1] = hex;
        if (hex.west() != this) {
            hex.setWest(this);
        }
    }

    void setSouthEast(Hex hex) {
        _adjHexes[2] = hex;
        if (hex.northWest() != this) {
            hex.setNorthWest(this);
        }
    }

    void setSouthWest(Hex hex) {
        _adjHexes[3] = hex;
        if (hex.northEast() != this) {
            hex.setNorthEast(this);
        }
    }

    void setWest(Hex hex) {
        _adjHexes[4] = hex;
        if (hex.east() != this) {
            hex.setEast(this);
        }
    }

    void setNorthWest(Hex hex) {
        _adjHexes[5] = hex;
        if (hex.southEast() != this) {
            hex.setSouthEast(this);
        }
    }

    /** Returns true if I have a hex in POSN.
     * @param posn 0 = northeast
     *             1 = east
     *             2 = southeast
     *             3 = southwest
     *             4 = west
     *             5 = northwest
     */
    boolean hasAdjacentHex(int posn) {
        return _adjHexes[posn] != null;
    }

    /** Returns true if I have a building in POSN.
     * @param posn 0 = north
     *             1 = northeast
     *             2 = southeast
     *             3 = south
     *             4 = southwest
     *             5 = northwest
     */
    boolean hasBuilding(int posn) {
        assert 0 <= posn && posn < 6 : "Invalid position!";
        return _adjBuildings[posn] != null;
    }

    /** Adds a building to POSN. Any adjacent hexes will have building be
     * added to them as well.
     * Any original building already in POSN will be overridden by BUILDING.
     *
     * @param posn (See hasBuilding)
     * @param building Building to be placed on POSN
     */
    void addBuilding(int posn, Building building) {

        if (_adjBuildings[posn] == building) {
            return;
        }

        _adjBuildings[posn] = building;

        int[] adjHexes = ADJACENT_HEXES[posn];
        int[] adjPoint = POINTS_ON_OTHER_ADJ_HEXES[posn];

        for (int i = 0; i < 2; i += 1) {
            Hex currHex = adjHex(adjHexes[i]);
            if (currHex != null) {
                currHex.addBuilding(adjPoint[i], building);
            }
        }
    }

    /** Returns the building on POSN, or null if there isn't. */
    Building building(int posn) {
        return _adjBuildings[posn];
    }

    /** Returns buildings that I possess in an ArrayList, enumerated in
     * a clockwise fashion.
     */
    ArrayList<Building> buildings() {
        ArrayList<Building> answer = new ArrayList<>();
        for (int i = 0; i < 6; i += 1) {
            if (_adjBuildings[i] != null) {
                answer.add(_adjBuildings[i]);
            }
        }
        return answer;
    }

    /** Returns an ArrayList of hexes adjacent to POSN, including me. */
    ArrayList<Hex> adjacentHexes(int posn) {
        ArrayList<Hex> answer = new ArrayList<>();
        answer.add(this);
        int[] adj = ADJACENT_HEXES[posn];
        int hex1 = adj[0];
        int hex2 = adj[1];
        if (_adjHexes[hex1] != null) {
            answer.add(_adjHexes[hex1]);
        }
        if (_adjHexes[hex2] != null) {
            answer.add(_adjHexes[hex2]);
        }
        return answer;
    }

    /** Returns true if I have a road on SIDE.
     * @param side Index 0 = northeast side
     *             Index 1 = east side
     *             Index 2 = southeast side
     *             Index 3 = southwest side
     *             Index 4 = west side
     *             Index 5 = northwest side
     */
    boolean hasRoad(int side) {
        assert 0 <= side && side <= 5 : "Invalid side!";
        return _roads[side] != null;
    }

    /** Returns the road on SIDE, or null if I don't have one
     * there.
     * @param side (See hasRoad)
     */
    Road getRoad(int side) {
        assert 0 <= side && side <= 5 : "Invalid side!";
        return _roads[side];
    }

    /** Adds a road on SIDE. If I have an adjacent hex touching my
     * side, then I will add ROAD to its side as well. */
    void placeRoad(Road road, int side) {
        if (_roads[side] == road) {
            return;
        }

        _roads[side] = road;

        if (hasAdjacentHex(side)) {
            adjHex(side).placeRoad(road, (side + 3) % 6);
        }
    }

    /** Removes all pieces from me.
     * Every piece that I remove removes themselves from
     * the hexes they are adjacent to.
     * This method does not remove pieces from any hex that is adjacent to me.
     * This method also assumes that clear in Board has been called.
     */
    void clear() {
        //FIXME
    }

    /** Useful if you would like to see the hex printed out
     * for testing or sanity purposes.
     *
     * @return A visual representation of me.
     */
    public String dump() {
        String[] buildings = new String[6];
        for (int i = 0; i < 6; i += 1) {
            buildings[i] = (_adjBuildings[i] != null)
                    ? _adjBuildings[i].toString() : "   ";
        }

        String[] roads = new String[6];
        for (int i = 0; i < 6; i += 1) {
            roads[i] = (_roads[i] != null)
                    ? _roads[i].toString() : " ";
        }

        String id = String.valueOf(_id);
        if (_id < 10) {
            id = "0" + _id;
        }

        String number = "0" + String.valueOf(_number);
        if (_number < 10) {
            number = "00" + String.valueOf(_number);
        }

        String resource = " N/A ";
        if (_resource != null) {
            resource = _resource.toString();
        }

        return    "      *" + buildings[0] + "*      \n"
                + "     " + roads[5] + "     " + roads[0] + "     \n"
                + "*" + buildings[5] + "* |" + number + "| *" + buildings[1] + "*\n"
                + "  " + roads[4] + "   " + resource + "   " + roads[1] + "  \n"
                + "*" + buildings[4] + "* Hex" + id + " *" + buildings[2] + "*\n"
                + "     " + roads[3] + "     " + roads[2] + "     \n"
                + "      *" + buildings[3] + "*      ";
    }

    @Override
    public String toString() {
        return "Hex " + _id;
    }

    /** Hex adjacency matrix.
     * The index refers to a certain point on the hex.
     * Ex. Index 0 (Point 0) has adjacent hexes 5 and 0.
     * Index 0 = north point
     * Index 1 = northwest point
     * Index 2 = southeast point
     * Index 3 = south point
     * Index 4 = southwest point
     * Index 5 = northwest point
     */
    static int[][] ADJACENT_HEXES
            = new int[][] { {5, 0}, {0, 1}, {1, 2},
                            {2, 3}, {3, 4}, {4, 5} };

    /** Position adjacency matrix.
     * The index refers to a certain point on the hex,
     * and accessing the index returns an array of positions
     * that refer to the same point on each adjacent hex next to
     * my position.
     * Index 0 = north point
     * Index 1 = northwest point
     * Index 2 = southeast point
     * Index 3 = south point
     * Index 4 = southwest point
     * Index 5 = northwest point
     */
    static int[][] POINTS_ON_OTHER_ADJ_HEXES
            = new int[][] { {2, 4}, {3, 5}, {4, 0},
                            {5, 1}, {0, 2}, {1, 3} };

    /** My unique id. */
    private int _id;

    /** Returns my numeric value. */
    private int _number;

    /** My resource that I produce. */
    private Resource _resource;

    /** Returns true if I have the robber on me. */
    private boolean _hasRobber;

    /** Hexes adjacent to me.
     * Index 0 = hex northeast
     * Index 1 = hex east
     * Index 2 = hex southeast
     * Index 3 = hex southwest
     * Index 4 = hex west
     * Index 5 = hex northwest
     */
    private Hex[] _adjHexes = new Hex[6];

    /** Buildings adjacent to me.
     * Index 0 = north point
     * Index 1 = northeast point
     * Index 2 = southeast point
     * Index 3 = south point
     * Index 4 = southwest point
     * Index 5 = northwest point
     */
    private Building[] _adjBuildings = new Building[6];

    /** Roads on me.
     * Index 0 = northeast side
     * Index 1 = east side
     * Index 2 = southeast side
     * Index 3 = southwest side
     * Index 4 = west side
     * Index 5 = northwest side
     */
    private Road[] _roads = new Road[6];

}