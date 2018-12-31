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

}
