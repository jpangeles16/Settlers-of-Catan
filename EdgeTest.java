import org.junit.Test;
import static org.junit.Assert.*;

/** Tests for Edge class.
 * @author John Angeles
 */
public class EdgeTest {

    @Test
    public void toStringTest() {
        Road road = new Road(Color.red());
        System.out.println(road);
        assertEquals("R", road.toString());
    }

}
