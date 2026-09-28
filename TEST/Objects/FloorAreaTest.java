package Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorAreaTest {
@Test
  void validConstructor(){
      FloorArea floorArea = new FloorArea("FloorArea1", 2);
      assertEquals("FloorArea1", floorArea.getFloorAreaTitle());
      assertEquals(2, floorArea.getLevel());
  }
  @Test
  void testFloorAreaConstraints(){
    String longName = "adasdaefsefsefsefsefsefsefsefsefsefsef";

    FloorArea floorArea = new FloorArea(longName, 21);
    String storedName = floorArea.getFloorAreaTitle();
    assertTrue(storedName.length() <= 30);
    assertEquals(longName.substring(0,30),storedName );
    assertEquals(0,floorArea.getLevel());

  }
  @Test
  void settersTestForFloorArea(){
    FloorArea floorArea = new FloorArea("FloorArea1", 2);

    assertEquals("FloorArea1", floorArea.getFloorAreaTitle());
    assertEquals(2, floorArea.getLevel());

    floorArea.setFloorAreaTitle("FloorArea2");
    floorArea.setLevel(6);

    assertEquals("FloorArea2", floorArea.getFloorAreaTitle());
    assertEquals(6, floorArea.getLevel());

    String longName = "FloorAreas222222222222222222222222222222222222222222";
    floorArea.setFloorAreaTitle(longName);
    floorArea.setLevel(30);

    String storedName = floorArea.getFloorAreaTitle();

    assertTrue(storedName.length() <= 30);
    assertEquals(longName.substring(0,30),storedName );
    assertEquals(6,floorArea.getLevel());
  }
}