import java.util.Stack;

/** A player.  There can be up to four players.
 * Each player has 15 roads, 5 settlements and
 * 4 cities.
 *
 * If a player were to upgrade a settlement to a city,
 * then the player is able to take back the settlement
 * to use again.
 *
 * A player wins the game by having 10 victory points.
 *
 * Unlike the other classes, the player cannot place
 * buildings and roads onto a hex unless he or she expends resource
 * cards.
 *
 * @author John Angeles
 */
public class Player {

    /** Instantiates a new player with a unique color.
     *
     * @param color A unique color either black, white,
     *              orange or red.
     * @param name The name of the player.
     */
    Player(Color color, String name) {
        _color = color;
        _name = name;

        for (int i = 0; i < 15; i += 1) {
            _roads.push(new Road(_color));
        }

        for (int i = 0; i < 5; i += 1) {
            _settlements.push(new Settlement(_color));
        }

        for (int i = 0; i < 4; i += 1) {
            _cities.push(new City(_color));
        }
    }

    /** Places the

    /** Current victory points that I have. */
    private int _victoryPoints;

    /** Roads that I haven't placed yet. */
    private Stack<Road> _roads = new Stack<>();

    /** Settlements that I haven't placed yet. */
    private Stack<Building> _settlements = new Stack<>();

    /** Cities that I haven't placed yet. */
    private Stack<Building> _cities = new Stack<>();

    /** My color. */
    private Color _color;

    /** My name. */
    private String _name;

}
