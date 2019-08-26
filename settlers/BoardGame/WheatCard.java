/** A wheat card.
 * @author John Angeles
 */
class WheatCard extends ResourceCard {

    /** Constructs a wheat card. */
    WheatCard() {
        super(Resource.wheat());
    }

    @Override
    public String toString() {
        return "Wheat";
    }
}

