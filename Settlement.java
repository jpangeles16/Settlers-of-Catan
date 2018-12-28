/** A settlement.
 * Each settlement belongs to a certain player.
 * Each settlement can also be adjacent to multiple hexes.
 * Game-wise, a settlement gives one victory point and one
 * of each resource it's adjacent to.
 * @author John Angeles
 */
class Settlement extends Building {

    Settlement(Color color) {
        super(color);
    }


    @Override
    int victoryPoints() {
        return 1;
    }

    @Override
    public String toString() {
        return " " + _color.toString() + " ";
    }

}
