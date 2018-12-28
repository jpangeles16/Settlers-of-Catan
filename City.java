/** A "better" version of a settlement that gives
 * two victory points and double the amount of resources.
 *
 * I marked this as final because according to the game, cities
 * are as advanced as settlements can get.
 *
 * @author John Angeles
 */
final class City extends Settlement {

    City(Color color) {
        super(color);
    }

    @Override
    int victoryPoints() {
        return 2;
    }

    @Override
    public String toString() {
        return "!" + _color.toString() + "!";
    }

}
