package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemTest {
    @Test
    //Test to check for the setting of description
    //Create two identical items with the exception of different descriptions call setDescription
    //to make descriptions the same then compare the results
    void setDescription() {
        Item desc_test= new Item(false,"description_test",new date("00","00","0000"),"test1");
        Item toEdit= new Item(false,"description_old",new date("00","00","0000"),"test1");
        toEdit.setDescription("description_test");
        assertEquals(desc_test.getDescription(),toEdit.getDescription());
    }

    //Test to check for the setting of date
    //Create two identical items with the exception of different dates call setDate
    //to make dates the same then compare the results
    @Test
    void setDueDate() {
        Item date_test= new Item(false,"desc",new date("12","23","2001"),"test1");
        Item toEdit= new Item(false,"desc",new date("11","15","2021"),"test1");
        toEdit.setDate(new date("12","23","2001"));
        assertEquals(toEdit.getDate().getDateinString(),date_test.getDate().getDateinString());
    }
    //Test to check for the setting of complete
    //Create two identical items with the exception of different complete tags call setComplete
    //to make complete the same then compare the results
    @Test
    void setComplete() {
        Item desc_test= new Item(false,"description_test",new date("00","00","0000"),"test1");
        Item toEdit= new Item(true,"description_old",new date("00","00","0000"),"test1");
        toEdit.setComplete(false);
        assertEquals(toEdit.getComplete(),desc_test.getComplete());
        toEdit.setComplete(true);
        assertNotEquals(toEdit.getComplete(),desc_test.getComplete());
    }
}