package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemTest {
    //Test for Requirement 11
    @Test
    void setDescription() {
        Item desc_test= new Item(false,"description_test",new date(00,00,0000),"test1");
        Item toEdit= new Item(false,"description_old",new date(00,00,0000),"test1");
        toEdit.setDescription("description_test");
        assertEquals(desc_test.getDescription(),toEdit.getDescription());
    }

    //Test for Requirement 12
    @Test
    void setDueDate() {
        Item date_test= new Item(false,"desc",new date(12,23,2001),"test1");
        Item toEdit= new Item(false,"desc",new date(11,15,2021),"test1");
        toEdit.setDate(new date(12,23,2001));
        assertEquals(toEdit.getDate().getDateinString(),date_test.getDate().getDateinString());
    }
    //Test for Requirement 13
    @Test
    void setComplete() {
        Item desc_test= new Item(false,"description_test",new date(00,00,0000),"test1");
        Item toEdit= new Item(true,"description_old",new date(00,00,0000),"test1");
        toEdit.setComplete(false);
        assertEquals(toEdit.getComplete(),desc_test.getComplete());
        toEdit.setComplete(true);
        assertNotEquals(toEdit.getComplete(),desc_test.getComplete());
    }
}