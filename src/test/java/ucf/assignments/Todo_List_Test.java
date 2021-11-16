/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Todo_List_Test {
    @Test
    //Test to check the addItem functionality of the code, also verifies ability to remove by simply resetting variable
    void addItem() {
        ArrayList<Item> Test= new ArrayList<Item>();
        Item toAdd=new Item(false, "Test", new date("00","00","00"),"Test");
        Test.add(toAdd);
        Todo_List Todo_test= new Todo_List("Todo_Test");
        Todo_test.addItem(toAdd);
        for (int i = 0; i < Test.size(); i++) {
            assertEquals(Todo_test.getItems().get(i),Test.get(i));
        }
    }

    @org.junit.jupiter.api.Test
        //Test to check for the removing an item to a todo list
        //Create two identical Todo Lists, add an additional item to one, then call removeItem
        //on that item and ensure the two are equal
    void removeItem() {
        {
            ArrayList<Item> Test= new ArrayList<Item>();
            Item toAdd=new Item(false, "Test1", new date("00","00","00"),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test2", new date("00","00","00"),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test3", new date("00","00","00"),"Test");
            Test.add(toAdd);

            Todo_List Todo_test= new Todo_List("Todo_Test",Test);
            Todo_test.removeItem(1);
            Test.remove(1);
            for (int i = 0; i < Test.size(); i++) {
                assertEquals(Todo_test.getItems().get(i),Test.get(i));
            }
        }
    }

    //Test to check for the removing all items in a todo list
    //Create a todo list populated with items, then call removeallItems and ensure the length
    //of the item array list is 0
    @org.junit.jupiter.api.Test
    void removeAllItems(){
        {
            ArrayList<Item> Test= new ArrayList<Item>();
            Item toAdd=new Item(false, "Test1", new date("00","00","00"),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test2", new date("00","00","00"),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test3", new date("00","00","00"),"Test");
            Test.add(toAdd);

            Todo_List Todo_test= new Todo_List("Todo_Test",Test);
            Todo_test.removeAllItems();
            assertEquals(Todo_test.getItems().size(),0);
        }
    }

    //Test for ability to get all items independent of complete/incomplete status
    //Populate TodoList with items of both complete and incomplete status then call getItems()
    //and ensure the generated item array is the same as the items put in
    @org.junit.jupiter.api.Test
    void getItems() {
        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(false,"Desc1",new date("00","00","0000"),"Item1"));
        test_array.add(new Item(true,"Desc2",new date("11","11","1111"),"Item2"));
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);
        assertTrue(test_array==todo_test.getItems());
    }

    //Test for ability to get all items of Incomplete status
    //Populate TodoList with items of both complete and incomplete status then call getIncompleteItems()
    //and ensure the generated item array is the same as the array of incomplete items put in
    @org.junit.jupiter.api.Test
    void getIncompleteItems(){
        Item falseItem=new Item(false,"Desc1",new date("00","00","0000"),"Item1");
        ArrayList<Item> goal_Array= new ArrayList<Item>();

        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(true,"Desc1",new date("00","00","0000"),"Item1"));
        goal_Array.add(falseItem);
        test_array.add(falseItem);
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);

        ArrayList<Item>incompleteArray=todo_test.getIncompleteItems();
        for (int i = 0; i < incompleteArray.size(); i++) {
            assertTrue(goal_Array.get(i)==incompleteArray.get(i));
        }
    }
    //Test for ability to get all items of complete status
    //Populate TodoList with items of both complete and incomplete status then call getCompleteItems()
    //and ensure the generated item array is the same as the array of complete items put in
    @org.junit.jupiter.api.Test
    void getCompleteItems(){
        Item trueItem=new Item(true,"Desc1",new date("00","00","0000"),"Item1");
        ArrayList<Item> goal_Array= new ArrayList<Item>();

        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(false,"Desc1",new date("00","00","0000"),"Item1"));
        goal_Array.add(trueItem);
        test_array.add(trueItem);
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);

        ArrayList<Item>completeArray=todo_test.getCompleteItems();
        for (int i = 0; i < completeArray.size(); i++) {
            assertTrue(goal_Array.get(i)==completeArray.get(i));
        }
    }
}