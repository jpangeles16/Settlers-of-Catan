import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Set of tests for the hex class.
 * Also tests a little bit of the Building class and its
 * subclasses.
 * @author John Angeles
 */
public class HexTest {

    @Test
    public void adjacencyTest() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        hex1.setEast(hex2);
        assertEquals(hex2, hex1.east());
        assertEquals(hex1, hex2.west());
    }

    @Test
    public void adjacencyTest2() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        Hex hex3 = new Hex(3, 4);
        Hex hex4 = new Hex(4, 5);
        hex1.setNorthEast(hex4);
        hex1.setSouthEast(hex2);
        hex3.setNorthWest(hex4);
        hex3.setSouthWest(hex2);
        assertEquals(hex3, hex4.southEast());
        assertEquals(hex1, hex4.southWest());
        assertEquals(hex1, hex2.northWest());
        assertEquals(hex3, hex2.northEast());
    }

    @Test
    public void adjacencyTest3() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        Hex hex3 = new Hex(3, 4);
        hex1.setNorthEast(hex2);
        hex1.setSouthEast(hex3);
        assertTrue(hex1.hasAdjacentHex(0));
        assertTrue(hex1.hasAdjacentHex(2));
        assertFalse(hex1.hasAdjacentHex(1));
        assertTrue(hex2.hasAdjacentHex(3));
        assertTrue(hex3.hasAdjacentHex(5));
        assertFalse(hex3.hasAdjacentHex(4));
        assertEquals(hex2, hex1.adjHex(0));
    }

    @Test
    public void addBuildingTest() {
        Hex hex1 = new Hex(1, 2);
        Building building = new Settlement(Color.black());
        hex1.addBuilding(0, building);
        System.out.println(hex1.dump());
        assertTrue(hex1.hasBuilding(0));
    }

    @Test
    public void addBuildingTest2() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        hex1.setSouthWest(hex2);
        hex1.addBuilding(4, new Settlement(Color.black()));
        assertTrue(hex1.hasBuilding(4));
        assertTrue(hex2.hasBuilding(0));
    }

    @Test
    public void addBuildingTest3() {
        Hex hex1 = new Hex(1, 2);
        Hex hex2 = new Hex(2, 3);
        Hex hex3 = new Hex(3, 4);
        hex1.setNorthWest(hex3);
        hex1.setWest(hex2);
        hex2.setNorthEast(hex3);
        hex1.addBuilding(5, new Settlement(Color.black()));
        System.out.println(hex2.dump());
        System.out.println(hex3.dump());
        assertTrue(hex1.hasBuilding(5));
        assertTrue(hex2.hasBuilding(1));
        assertTrue(hex3.hasBuilding(3));
    }

    @Test
    public void placeRoadTest() {
        Hex hex1 = new Hex(1, 10);
        assertFalse(hex1.hasRoad(1));
        hex1.placeRoad(new Road(Color.red()), 1);
        hex1.placeRoad(new Road(Color.red()), 0);
        assertTrue(hex1.hasRoad(1));
        assertTrue(hex1.hasRoad(0));
        System.out.println(hex1.dump());
    }

    @Test
    public void placeRoadTest2() {
        Hex hex1 = new Hex(1, 10);
        Hex hex2 = new Hex(2, 11);
        hex1.setEast(hex2);
        hex1.placeRoad(new Road(Color.red()), 1);
        assertTrue(hex1.hasRoad(1));
        assertTrue(hex2.hasRoad(4));
        System.out.println(hex1.dump() + "\n" + hex2.dump());
    }

    @Test
    public void placeRoadTest3() {
        Hex hex1 = new Hex(1, 10);
        Hex hex2 = new Hex(2, 11);
        Hex hex3 = new Hex(3, 5);
        hex1.setEast(hex2);
        hex1.setNorthEast(hex3);
        hex3.setSouthEast(hex2);
        Road road1 = new Road(Color.red());
        Road road2 = new Road(Color.red());
        hex1.placeRoad(road1, 0);
        hex2.placeRoad(road2, 5);
        assertTrue(hex1.hasRoad(0));
        assertTrue(hex3.hasRoad(3));
        assertTrue(hex3.hasRoad(2));
        assertTrue(hex2.hasRoad(5));
        assertFalse(hex2.hasRoad(4));
        System.out.println(hex1.dump());
        System.out.println(hex2.dump());
        System.out.println(hex3.dump());
        assertTrue(hex3.getRoad(3) != hex3.getRoad(2));
    }

    @Test
    public void adjacentHexesTest() {
        Hex hex1 = new Hex(1, 10);
        Hex hex2 = new Hex(2, 10);
        ArrayList<Hex> adj = hex1.adjacentHexes(0);
        assertEquals("[Hex 1]", adj.toString());
        hex1.setNorthEast(hex2);
        ArrayList<Hex> adj2 = hex1.adjacentHexes(0);
        assertEquals("[Hex 1, Hex 2]", adj2.toString());
    }

    @Test
    public void resourceTest() {
        Hex hex1 = new Hex(1, 10);
        hex1.setResource(Resource.brick());
        Hex hex2 = new Hex(2, 11);
        hex2.setResource(Resource.brick());
        Hex hex3 = new Hex(3, 12);
        hex3.setResource(Resource.brick());
        assertEquals(Resource.brick(), hex1.resource());
        assertEquals("Invalid resources!", hex1.resource(), hex2.resource());
        assertEquals("Invalid resources!", hex1.resource(), hex3.resource());
    }

    @Test
    public void dumpTest() {
        Hex hex1 = new Hex(1, 2);
        hex1.addBuilding(0, new Settlement(Color.black()));
        hex1.addBuilding(2, new City(Color.white()));
        System.out.println(hex1.dump());
    }

}
