package Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItemConstructor() {
        Item item = new Item("Item1",10,30,5,"Frozen","dskjfkdfj",5);

        assertEquals("Item1", item.getDescription());
        assertEquals(10, item.getWeight());
        assertEquals(30, item.getUnitPrice());
        assertEquals(5,item.getQuantity());
        assertEquals("Frozen", item.getStorageTemperature());
        assertEquals("dskjfkdfj", item.getPhotoUrl());
        assertEquals(5, item.getShelfDesignation());
    }

    @Test
    void testSetters(){
        Item item =new Item("Item1",10,30,5,"Frozen","dskjfkdfj",5);

        item.setDescription("New Description");
        item.setWeight(15);
        item.setUnitPrice(20);
        item.setQuantity(12);
        item.setStorageTemperature("Unrefrigerated");
        item.setPhotoUrl("PhotoUrl");
        item.setShelfDesignation(7);

        assertEquals("New Description", item.getDescription());
        assertEquals(15, item.getWeight());
        assertEquals(20, item.getUnitPrice());
        assertEquals(12, item.getQuantity());
        assertEquals("Unrefrigerated", item.getStorageTemperature());
        assertEquals("PhotoUrl", item.getPhotoUrl());
        assertEquals(7, item.getShelfDesignation());
    }
    @Test
    void testConstraints(){
        String longName = "sissfjsojdosjdosdjosjdosjd";
        String longPhotoURL = "ssljsndfoksfokasnfkosdnflksenfksenflksenflksenfklsenfklsneflknsefklnsefklnselknflskenfklsenfklsenfklsenfsddsdsadasdasdasdasdasdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasddddddddddddddddddddddddddd";


        Item item = new Item(longName,200000,1001,1000,"Frozen",longPhotoURL,5);

        String storedLongName = item.getDescription();
        String storedLongUrl = item.getPhotoUrl();

        assertTrue(storedLongName.length() <= 20);
        assertTrue(longPhotoURL.substring(0,100).equals(storedLongUrl));
        assertEquals("Frozen", item.getStorageTemperature());
        assertEquals(0,item.getWeight());
        assertEquals(0.0,item.getUnitPrice());
        assertEquals(0,item.getQuantity());
        assertEquals("Frozen", item.getStorageTemperature());
        assertEquals(5,item.getShelfDesignation());

    }
}