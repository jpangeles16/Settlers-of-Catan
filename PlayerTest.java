import org.junit.Test;
import static org.junit.Assert.*;

/** Set of tests for the player class.
 * @author John Angeles
 */
public class PlayerTest {

    @Test
    public void giveResourceTest() {
        Board.reset();
        System.out.println(Board.dump());
        Player alice = new Player(Color.white(), "Alice");
        Player bob = new Player(Color.black(), "Bob");
        System.out.println(alice.placeSettlement(5, 0));
        alice.giveResource(new SheepCard());
        alice.giveResource(new WoodCard());
        alice.giveResource(new BrickCard());
        alice.giveResource(new WheatCard());
        System.out.println(alice.placeSettlement(5, 0));
        System.out.println(Board.dump());
        assertTrue(Board.get(1).hasBuilding(2));
        assertTrue(Board.get(2).hasBuilding(4));
        assertTrue(Board.get(5).hasBuilding(0));
        Building curr = Board.get(5).buildings().get(0);
        System.out.println(curr.adjHexes());
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
        System.out.println(Board.dump());
        assertEquals("Alice can't put down a road because" +
                "the adjacent road doesn't belong to anyone.","Nope, can't place it there.",
                alice.placeRoad(9, 1));
        Board.placeRoad(new Road(Color.red(), alice), 9, 0);
        System.out.println(Board.dump());
    }

}
