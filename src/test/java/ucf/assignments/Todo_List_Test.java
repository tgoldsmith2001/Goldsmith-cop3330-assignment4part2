/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Todo_List_Test {
    @Test
    void addItem() {
        ArrayList<Item> Test= new ArrayList<Item>();
        Item toAdd=new Item(false, "Test", new date(00,00,00),"Test");
        Test.add(toAdd);
        Todo_List Todo_test= new Todo_List("Todo_Test");
        Todo_test.addItem(toAdd);
        for (int i = 0; i < Test.size(); i++) {
            assertEquals(Todo_test.getItems().get(i),Test.get(i));
        }
    }
    //Test for Requirement 10
    @org.junit.jupiter.api.Test
    void removeItem() {
        {
            ArrayList<Item> Test= new ArrayList<Item>();
            Item toAdd=new Item(false, "Test1", new date(00,00,00),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test2", new date(00,00,00),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test3", new date(00,00,00),"Test");
            Test.add(toAdd);

            Todo_List Todo_test= new Todo_List("Todo_Test",Test);
            Todo_test.removeItem(1);
            Test.remove(1);
            for (int i = 0; i < Test.size(); i++) {
                assertEquals(Todo_test.getItems().get(i),Test.get(i));
            }
        }
    }

    //Test for Requirement 8
    @org.junit.jupiter.api.Test
    void updateTitle() {
        /*
        Define test_8 (a todo list) and define using new using initializer with only title input: new Todo_List("test_before")
        Call updateTitle with input parameter "test_after"
        Check the title of test_1 by using an assert equals to ensure it was changed to "test_before"
         */
    }
    //Test for Requirement 14
    @org.junit.jupiter.api.Test
    void getItems() {
        /*
        Define test_14_items (arraylist of test items)
        Define test_14 (a todo list) and create new todoList with title "Test_14" and test_14_items
        Assert that test_14_items equals a call to getItems
         */
    }
    //Test for Requirement 15
    @org.junit.jupiter.api.Test
    void getIncompleteItems(){
        /*
        Define test_15_items as an arraylist of only incomplete test items
        Define test_15 as a new Todo List with title "Test_15" and an arraylist of items (with the incomplete items matching test_15_items)
        Assert that test_15_items equals a call to getIncompleteItems
         */
    }
    //Test for Requirement 16
    void getCompleteItems(){
        /*
        Define test_16_items as an arraylist of only complete test items
        Define test_16 as a new Todo List with title "Test_16" and an arraylist of items (with the complete items matching test_16_items)
        Assert that test_16_items equals a call to getCompleteItems
         */
    }
    //Test for Extra Credit
    void sortByDate(){
        /*
        Define test_EC as an arrayList of todo_lists (out of order)
        Define Ordered_test_EC as an arrayList of todo_lists (in order)
        Assert that arrat test_EC equals Ordered_test_EC
         */
    }
}