import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/** Set of tests for the board class.
 * @author John Angeles
 */
public class BoardTest {

    /** Generates a random number between MIN and MAX, inclusive. */
    private int genRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    /** Randomly shuffles an ArrayList. */
    private void shuffle(ArrayList<Integer> arraylist) {
        Collections.shuffle(arraylist);
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

    @Test
    public void shuffleTest() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(0);
        test.add(1);
        for (int i = 0; i < 15; i += 1) {
            shuffle(test);
            System.out.println(test);
        }
    }


}
