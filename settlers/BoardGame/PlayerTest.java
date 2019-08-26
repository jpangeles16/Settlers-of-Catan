import org.junit.Test;
import static org.junit.Assert.*;

/** Set of tests for the player class.
 * @author John Angeles
 */
public class PlayerTest {

    /** Prints a visual representation of the board. */
    private void dump() {
        System.out.println(Board.dump());
    }

    @Test
    public void clearTest() {
        Board.reset();
        Board.placeSettlement(new Settlement(Color.red()), 1, 1);
        Board.placeSettlement(new Settlement(Color.white()), 2, 0);
        Board.reset();
        dump();
    }

    @Test
    public void giveResourceTest() {
        Board.reset();
        System.out.println(Board.dump());
        Player alice = new Player(Color.white(), "Alice");
        System.out.println(alice.placeSettlement(5, 0));
        alice.giveResource(new SheepCard());
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        alice.giveResource(new WheatCard());
        System.out.println(alice.placeSettlement(5, 0));
        assertTrue(Board.get(1).hasBuilding(2));
        assertTrue(Board.get(2).hasBuilding(4));
        assertTrue(Board.get(5).hasBuilding(0));
        dump();
    }

    @Test
    public void flagTest() {
        Player alice = new Player(Color.red(), "Alice");
        System.out.println(alice.flag());
        alice.giveResource(new SheepCard());
        System.out.println(alice.flag());
    }

    @Test
    public void placeRoadTest() {
        dump();
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        assertEquals("Nope, can't place it there.", alice.placeRoad(6, 5));
        System.out.println(Board.dump());
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        Board.placeRoad(new Road(Color.red(), alice), 6, 0);
        System.out.println(Board.dump());
        assertEquals("Nope, can't place it there.", alice.placeRoad(1, 1));
        assertTrue(Board.get(6).hasRoad(0));
        assertTrue(Board.get(3).hasRoad(3));
        alice.placeRoad(6, 1);
        assertTrue(Board.get(6).hasRoad(1));
        assertTrue(Board.get(7).hasRoad(4));
        System.out.println(Board.dump());
        assertEquals("Gonna need more trees and bricks.",
                alice.placeRoad(6, 2));
        alice.giveResource(new BrickCard());
        alice.giveResource(new WoodCard());
        assertEquals("Nope, can't place it there.",
                alice.placeRoad(6, 1));
        assertEquals("Nope, can't place it there.",
                alice.placeRoad(1, 1));
        assertEquals("Alice put down a road!",
                alice.placeRoad(6, 2));
        System.out.println(Board.dump());
    }

    @Test
    public void placeRoadTest2() {
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        Board.placeRoad(new Road(Color.red()), 4, 0);
        assertTrue(Board.get(4).hasRoad(0));
        assertTrue(Board.get(1).hasRoad(3));
        System.out.println(Board.dump());
        Board.placeSettlement(new Settlement(Color.black()), 4, 1);
        System.out.println(Board.dump());
        assertEquals("Nope, can't place it there.",
                alice.placeRoad(4, 1));
        System.out.println(Board.dump());
    }

    @Test
    public void placeRoadTest3() {
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        Board.placeRoad(new Road(Color.black()), 9, 0);
        alice.giveResource(new WoodCard());
        alice.giveResource(new SheepCard());
        assertEquals("Alice can't put down a road because" +
                "the adjacent road doesn't belong to anyone.","Nope, can't place it there.",
                alice.placeRoad(9, 1));
        Board.placeRoad(new Road(Color.red(), alice), 9, 0);
        assertEquals("Still missing bricks!" +
                "there is already an adjacent road that belongs to her.", "Gonna " +
                "need more trees and bricks.", alice.placeRoad(9, 1));
        alice.giveResource(new BrickCard());
        assertEquals("Now she can place a road.", "Alice put down a road!",
                alice.placeRoad(9, 1));
        System.out.println(Board.dump());
    }

    /** This test tests whether or not we can place a road
     * where there is another settlement available. */
    @Test
    public void placeRoadTest4() {
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        Board.placeSettlement(new Settlement(Color.red()),
                14, 5);
        Board.placeRoad(new Road(Color.orange()),
                9, 3);
        assertEquals("Nope, can't place it there.",
                alice.placeRoad(13, 1));
        dump();
    }

    /** In this test, we can place a road. */
    @Test
    public void placeRoadTest5() {
        Board.reset();
        Player alice = new Player(Color.red(), "Alice");
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        Board.placeSettlement(new Settlement(Color.red()), 18, 0);
        Board.placeSettlement(new Settlement(Color.orange()), 18, 1);
        Board.placeRoad(new Road(Color.red(), alice), 18, 5);
        Board.placeRoad(new Road(Color.red()), 18, 1);
        dump();
        assertEquals("Alice put down a road!", alice.placeRoad(18, 0));
        dump();
    }

    /** Generates a player named Alice that has enough resources to build one settlement.
     * The default color is white. */
    private Player aliceSettlement() {
        Player alice = new Player(Color.white(), "Alice");
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        alice.giveResource(new SheepCard());
        alice.giveResource(new WheatCard());
        return alice;
    }

    /** Trivial test. If there is nothing on the board, then Alice can't place
     * a settlement. */
    @Test
    public void isValidSettlementTest() {
        Board.reset();
        Player alice = aliceSettlement();
        assertFalse(alice.isValidSettlement(9, 0));
        assertFalse(alice.isValidSettlement(9, 1));
    }

    /** Same as the first test, but here we are checking for potential
     * edge cases along the perimeter of the board.
     */
    @Test
    public void isValidSettlementTest2() {
        Board.reset();
        Player alice = aliceSettlement();
        assertFalse(alice.isValidSettlement(7, 1));
        assertFalse(alice.isValidSettlement(4, 5));
    }

    /** Here are some cases where we can place a settlement. */
    @Test
    public void isValidSettlementTest3() {
        Board.reset();
        Player alice = aliceSettlement();
        Board.placeRoad(new Road(Color.white()), 4, 0);
        System.out.println(Board.dump());
        assertTrue(alice.isValidSettlement(4, 0));
        assertTrue(alice.isValidSettlement(4, 1));
        assertFalse(alice.isValidSettlement(4, 5));
    }

    /** We cannot place a settlement next to another settlement. */
    @Test
    public void isValidSettlementTest4() {
        Board.reset();
        Player alice = aliceSettlement();
        Board.placeSettlement(new Settlement(Color.white()), 17, 3);
        assertFalse(alice.isValidSettlement(17, 2));
        assertFalse(alice.isValidSettlement(17, 4));
        assertFalse(alice.isValidSettlement(17, 1));
        Board.placeRoad(new Road(Color.white()), 17, 1);
        assertTrue(alice.isValidSettlement(17, 1));
        alice.placeSettlement(17, 1);
        System.out.println(Board.dump());
    }

    /** Similar test as above. */
    @Test
    public void isValidSettlementTest5() {
        Board.reset();
        Player alice = aliceSettlement();
        Board.placeSettlement(new Settlement(Color.white()), 14, 1);
        Board.placeSettlement(new Settlement(Color.white()), 14, 5);
        Board.placeSettlement(new Settlement(Color.white()), 9, 1);
        Board.placeRoad(new Road(Color.white()), 9, 0);
        System.out.println(Board.dump());
        assertFalse(alice.isValidSettlement(9, 1));
        assertFalse(alice.isValidSettlement(9, 2));
    }

    @Test
    public void isValidSettlementTest6() {
        Board.reset();
        Player alice = aliceSettlement();
        Board.placeSettlement(new Settlement(Color.white()), 14, 1);
        Board.placeSettlement(new Settlement(Color.white()), 14, 5);
        assertFalse(alice.isValidSettlement(9, 1));
        Board.placeRoad(new Road(Color.red()), 9, 1);
        System.out.println(Board.dump());
        assertFalse(alice.isValidSettlement(9, 1));
    }

    @Test
    public void hasSettlementOrCityTest() {
        Board.reset();
        Player alice = aliceSettlement();
        alice.placeSettlement(1, 2);
        System.out.println(Board.dump());
        assertTrue(alice.hasSettlementOrCityOnHex(1));
        assertTrue(alice.hasSettlementOrCityOnHex(2));
        assertTrue(alice.hasSettlementOrCityOnHex(5));
    }

}
