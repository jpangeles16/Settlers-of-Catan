import org.junit.Test;
import static org.junit.Assert.*;


/** Set of tests for the board class.
 * @author John Angeles
 */
public class BoardTest {
    /** Random number generator between 2 and 12. */
    private int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    @Test
    public void randomGenTest() {
        int[] generated = new int[500];
        for (int i = 0; i < 500; i += 1) {
            generated[i] = genRandom(2, 12);
        }
        for (int i = 0; i < 500; i += 1) {
            assertTrue(2 <= generated[i] && generated[i] <= 12);
        }
    }
}
