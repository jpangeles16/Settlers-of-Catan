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

    @Test
    public void trivialBoardTest() {
        Hex[] board = Board.hexList();
        Hex[] sameBoard = board;
        assertSame(board, sameBoard);
    }

    @Test
    public void axialCoordinatesTest() {
        Hex[] board = Board.hexList();
        assertTrue(board[0] == Board.get(0, -2));
        assertTrue(board[1] == Board.get(1, -2));
        assertTrue(board[2] == Board.get(2, -2));
        assertTrue(board[3] == Board.get(-1, -1));
        assertTrue(board[4] == Board.get(0, -1));
        assertTrue(board[5] == Board.get(1, -1));
        assertTrue(board[6] == Board.get(2, -1));
        assertTrue(board[7] == Board.get(-2, 0));
        assertTrue(board[8] == Board.get(-1, 0));
        assertTrue(board[9] == Board.get(0, 0));
        assertTrue(board[10] == Board.get(1, 0));
        assertTrue(board[11] == Board.get(2, 0));
        assertTrue(board[12] == Board.get(-2, 1));
        assertTrue(board[13] == Board.get(-1, 1));
        assertTrue(board[14] == Board.get(0, 1));
        assertTrue(board[15] == Board.get(1, 1));
        assertTrue(board[16] == Board.get(-2, 2));
        assertTrue(board[17] == Board.get(-1, 2));
        assertTrue(board[18] == Board.get(0, 2));
    }

    @Test
    public void resourceTest() {
        Board.reset();

    }

}
