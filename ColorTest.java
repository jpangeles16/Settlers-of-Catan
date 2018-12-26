import org.junit.Test;
import static org.junit.Assert.*;

/** Set of tests for the Color class.
 * @author John Angeles
 */
public class ColorTest {

    @Test
    public void instantiationTest() {
        Color red = Color.Red();
        Color red2 = Color.Red();
        Color black = Color.Black();
        Color black2 = Color.Black();
        Color orange = Color.Orange();
        Color orange2 = Color.Orange();
        assertTrue(red == red2);
        assertTrue(black == black2);
        assertTrue(orange == orange2);
    }

    @Test
    public void toStringTest() {
        Color mystery = Color.White();
        assertEquals("W", mystery.toString());
    }
}
