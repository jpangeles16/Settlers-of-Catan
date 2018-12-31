/** A card that has the resource Wood.
 * @author John Angeles
 */
class WoodCard extends ResourceCard{

    /** Creates a wood card. */
    WoodCard() {
        super(Resource.wood());
    }

    @Override
    public String toString() {
        return "Wood";
    }
}
