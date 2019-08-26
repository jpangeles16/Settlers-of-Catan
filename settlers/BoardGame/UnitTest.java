import ucb.junit.textui;

/** The suite of all JUnit tests for the BoardGame package.
 *  @author John Angeles
 */
public class UnitTest {

    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(
                BoardTest.class,
                BuildingTest.class,
                ColorTest.class,
                EdgeTest.class,
                HexTest.class,
                PlayerTest.class);
    }
}
