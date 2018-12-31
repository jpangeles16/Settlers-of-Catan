/** An ore card.
 * @author John Angeles
 */
class OreCard extends ResourceCard {

    /** Constructs an ore card. */
    OreCard() {
        super(Resource.ore());
    }

    @Override
    public String toString() {
        return "Ore";
    }
}
