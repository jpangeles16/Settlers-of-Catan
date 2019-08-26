/** A generic resource card. Can be either a wood, brick,
 * wheat, ore, or sheep card.
 * @author John Angeles
 */
abstract class ResourceCard extends Card {

    /** Constructs me as a ResourceCard that has value RESOURCE. */
    ResourceCard(Resource resource) {
        _resource = resource;
    }

    /** Returns my resource. */
    Resource resource() {
        return _resource;
    }

    /** My current resource. */
    private Resource _resource;
}
