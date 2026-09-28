package Objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AisleTest {

    @Test
    void validConstructor(){
        Aisle aisle = new Aisle("Aisle 1", 1500,100,400,"FROZEN","FloorArea1");

        assertEquals("Aisle 1", aisle.getAisleName());
        assertEquals(1500, aisle.getAisleLength());
        assertEquals(100, aisle.getAisleWidth());
        assertEquals(400, aisle.getAisleHeight());
        assertEquals("FROZEN", aisle.getTemperature());
        assertEquals("FloorArea1", aisle.getFloorAreaDesignation());

    }
    @Test
    void checkMaxDimensions(){
        Aisle aisle = new Aisle("Aisle 2", 2500,300,600,"Frozen","FloorArea1");
        assertEquals(0,aisle.getAisleLength());
        assertEquals(0, aisle.getAisleWidth());
        assertEquals(0,aisle.getAisleHeight());
    }
    @Test
    void settersFunctioning(){
        Aisle aisle = new Aisle("Aisle 3", 1000,150,300,"Unrefrigerated","FloorArea2");

        aisle.setAisleLenght(2000);
        aisle.setAisleWidth(200);
        aisle.setAisleHeight(500);

        assertEquals(2000, aisle.getAisleLength());
        assertEquals(200, aisle.getAisleWidth());
        assertEquals(500,aisle.getAisleHeight());

        aisle.setAisleLenght(3000);
        aisle.setAisleWidth(1000);
        aisle.setAisleHeight(9999);

        assertEquals(2000, aisle.getAisleLength());
        assertEquals(200, aisle.getAisleWidth());
        assertEquals(500, aisle.getAisleHeight());
    }
    @Test
    void testTruncateString(){
        String longName = "oijkasjdoaisjdoaisjdoiasjdoisjdoiasjdoiasjdoiasj";

        Aisle aisle = new Aisle(longName,1000,100,100,"Frozen", "FloorArea1");

        String storedName = aisle.getAisleName();

        assertTrue(storedName.length() <= 20);
        assertEquals(longName.substring(0,20),storedName);
    }
}