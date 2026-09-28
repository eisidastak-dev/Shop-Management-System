package Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

@Test
    void testShelfConstructor(){
        Shelf shelf = new Shelf(2,"Aisle1");

        assertEquals(2, shelf.getShelfNumber());
        assertEquals("Aisle1", shelf.getShelfDesignation());
    }

    @Test
    void testShelfConstraints(){
    Shelf shelf = new Shelf(1000,"Aisle1");

    assertEquals(0, shelf.getShelfNumber());
    assertEquals("Aisle1", shelf.getShelfDesignation());

    shelf.setShelfNumber(100);
    shelf.setShelfDesignation("Aisle20");

    assertEquals(100, shelf.getShelfNumber());
    assertEquals("Aisle20", shelf.getShelfDesignation());
    }

}