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
    public void coinFlipTest() {
        for (int i = 0; i < 100; i += 1) {
            int k = Board.coinFlip();
            assertTrue(k == 0 || k == 1);
        }
    }

    @Test
    public void dumpTest() {
        Board.reset();
        for (int i = 0; i < 20; i += 1) {
            Board.reset();
            System.out.println(Board.dump());
        }
        System.out.println(Board.dump());
    }

    @Test
    public void moduloTest() {
        int curr = Board.genRandom(0, 5);
        for (int i = 0; i < 6; i += 1) {
            curr = (curr - 1);
            if (curr < 0) {
                curr += 6;
            }
            System.out.println(curr);
        }
    }

    @Test
    public void placeSettlementTest() {
        Board.reset();
        Settlement settlement = new Settlement(Color.red());
        Board.placeSettlement(settlement, 5, 1);
        System.out.println(Board.dump());
    }

    @Test
    public void placeRoadTest() {
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        assertEquals("Nope, can't place it there.", alice.placeRoad(6, 5));
        System.out.println(Board.dump());
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        Board.placeRoad(new Road(Color.red()), 6, 0);
        System.out.println(Board.dump());
        assertEquals("Nope, can't place it there.", alice.placeRoad(1, 1));
        assertTrue(Board.get(6).hasRoad(0));
        assertTrue(Board.get(3).hasRoad(3));
        alice.placeRoad(6, 1);
        assertTrue(Board.get(6).hasRoad(1));
        assertTrue(Board.get(7).hasRoad(4));
    }

    /** Returns a new player named alice whose color is red
     * that can build up to x settlements.
     */
    private Player alice(int x) {
        Player alice = new Player(Color.red(), "Alice");
        for (int i = 0; i < x; i += 1) {
            alice.giveResource(new WoodCard());
            alice.giveResource(new BrickCard());
            alice.giveResource(new SheepCard());
            alice.giveResource(new WheatCard());
        }
        return alice;
    }

    /** Returns a new road whose color is red. */
    private Road redRoad() {
        return new Road(Color.red());
    }

    /** Dumps the board. */
    private void dump() {
        System.out.println(Board.dump());
    }

    @Test
    public void clearTest() {
        Board.reset();
        Board.placeRoad(redRoad(), 1, 0);
        Board.placeRoad(redRoad(), 1, 1);
        Board.placeRoad(redRoad(), 2, 0);
        Board.placeRoad(redRoad(), 2, 1);
        dump();
        Board.reset();
        assertFalse(Board.get(1).hasRoad(1));
    }

    @Test
    public void playTest() {
        Board.reset();
        Player brian = new Player(Color.red(), "Brian");
        Player john = new Player(Color.white(), "John");
        System.out.println(Board.dump());
        Board.placeSettlement(new Settlement(Color.red()), 12, 5);
        System.out.println(Board.dump());
    }
}
