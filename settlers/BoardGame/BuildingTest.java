import org.junit.Test;
import static org.junit.Assert.*;

/** Set of tests for the Building class and its
 * subclasses.
 * @author John Angeles
 */
public class BuildingTest {

    @Test
    public void adjacencyTest() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        Hex hex3 = new Hex(3, 4);
        hex1.setNorthEast(hex2);
        hex1.setEast(hex3);
        hex2.setSouthEast(hex3);
        Settlement settlement = new Settlement(Color.red());
        settlement.placeOn(hex1, 1);
        assertEquals("[Hex 1, Hex 2, Hex 3]",
                settlement.adjHexes().toString());
    }
}
