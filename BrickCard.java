/** A card that has the resource Brick.
 * @author John Angeles
 */
class BrickCard extends ResourceCard {

    /** Makes a brick card. */
    BrickCard() {
        super(Resource.brick());
    }

    @Override
    public String toString() {
        return "Brick";
    }

}
