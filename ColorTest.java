import org.junit.Test;
import static org.junit.Assert.*;

/** Set of tests for the Color class.
 * @author John Angeles
 */
public class ColorTest {

    @Test
    public void instantiationTest() {
        Color red = Color.red();
        Color red2 = Color.red();
        Color black = Color.black();
        Color black2 = Color.black();
        Color orange = Color.orange();
        Color orange2 = Color.orange();
        assertTrue(red == red2);
        assertTrue(black == black2);
        assertTrue(orange == orange2);
    }

    @Test
    public void toStringTest() {
        Color mystery = Color.white();
        assertEquals("W", mystery.toString());
    }
}
