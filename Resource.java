/** A resource can either be wood, brick, wheat, ore, or
 * sheep. Resources are used to distinguish hexes and cards.
 * @author John Angeles
 */
class Resource {

    /** Resource cannot be instantiated; call
     * the static constructors instead.
     */
    private Resource() {}

    /** === Set of static functions that return resources. */
    static Resource Wood() {
        return RESOURCES[0];
    }

    static Resource Brick() {
        return RESOURCES[1];
    }

    static Resource Wheat() {
        return RESOURCES[2];
    }

    static Resource Ore() {
        return RESOURCES[3];
    }

    static Resource Sheep() {
        return RESOURCES[4];
    }

    static Resource Desert() {
        return RESOURCES[5];
    }

    /** Static array of all resources needed for the game. */
    private static Resource[] RESOURCES
            = new Resource[6];

    /** Sets up RESOURCES array. */
    static {
        RESOURCES[0] = new Wood();
        RESOURCES[1] = new Brick();
        RESOURCES[2] = new Wheat();
        RESOURCES[3] = new Ore();
        RESOURCES[4] = new Sheep();
        RESOURCES[5] = new Desert();
    }

    @Override
    public String toString() {
        if (this == RESOURCES[0]) {
            return "Wood ";
        } else if (this == RESOURCES[1]) {
            return "Brick";
        } else if (this == RESOURCES[2]) {
            return "Wheat";
        } else if (this == RESOURCES[3]) {
            return " Ore ";
        } else if (this == RESOURCES[4]) {
            return "Sheep";
        } else {
            return "  D  ";
        }
    }

    /** Wood. Excellent for building roads and settlements. */
    private static class Wood extends Resource {}

    /** Brick. Great for early game. */
    private static class Brick extends Resource {}

    /** Wheat. A very versatile resource everyone wants. */
    private static class Wheat extends Resource {}

    /** Ore. This is a crucial late-game resource. */
    private static class Ore extends Resource {}

    /** Sheep. Great to have from early to mid-game,
     * sometimes late-game. */
    private static class Sheep extends Resource {}

    /** Desert. Only a single hex and no cards may implement this.
     * Try not to settle next to this one.
     */
    private static class Desert extends Resource {}

}
