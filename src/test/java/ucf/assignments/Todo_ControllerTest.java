/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Todo_ControllerTest {
    @Test
    //Test for both saving to file and retrieving from file
    //Generate file, save todo to the file, read it back, and ensure two lists are equal
    void saveToFile() throws IOException {
        Todo_Controller test_Controller= new Todo_Controller("src/test/java/Resources","Storage_Test");
        ArrayList<Item>test_Items= new ArrayList<Item>();
        test_Items.add(new Item(false,"DESC1",new date("12","23","2001"),"TITLE1"));
        Todo_List test_Todo= new Todo_List("TODO",test_Items);

        test_Controller.saveToFile(test_Todo);
        Todo_List retrievedTodo= test_Controller.readMemory();
        for (int i = 0; i < retrievedTodo.getItems().size(); i++) {
            assertTrue(retrievedTodo.getItems().get(i).getName().equals(test_Todo.getItems().get(i).getName()));
            assertTrue(retrievedTodo.getItems().get(i).getComplete()==(test_Todo.getItems().get(i).getComplete()));
            assertTrue(retrievedTodo.getItems().get(i).getDescription().equals(test_Todo.getItems().get(i).getDescription()));
        }
    }
}