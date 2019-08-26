
/** A settlement.
 * Each settlement belongs to a certain player.
 * Each settlement can also be adjacent to multiple hexes.
 * Game-wise, a settlement gives one victory point and one
 * of each resource it's adjacent to.
 * @author John Angeles
 */
class Settlement extends Building {

    /** Constructs a generic settlement with a
     * color COLOR.
     * @param color My color.
     */
    Settlement(Color color) {
        super(color);
    }

    /** Constructs a settlement with color COLOR that
     * belongs to player PLAYER.
     *
     * @param color My color.
     * @param player My player that owns me.
     */
    Settlement(Color color, Player player) {
        super(color, player);
    }

    @Override
    void returnToPlayer() {
        if (player() != null) {
            player().takeBackSettlement(this);
        }
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
