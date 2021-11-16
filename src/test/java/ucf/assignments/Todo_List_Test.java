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

    @org.junit.jupiter.api.Test
    void removeAllItems(){
        {
            ArrayList<Item> Test= new ArrayList<Item>();
            Item toAdd=new Item(false, "Test1", new date(00,00,00),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test2", new date(00,00,00),"Test");
            Test.add(toAdd);
            toAdd=new Item(false, "Test3", new date(00,00,00),"Test");
            Test.add(toAdd);

            Todo_List Todo_test= new Todo_List("Todo_Test",Test);
            Todo_test.removeAllItems();
            assertEquals(Todo_test.getItems().size(),0);
        }
    }

    @org.junit.jupiter.api.Test
    void updateTitle() {
        /*
        Define test_8 (a todo list) and define using new using initializer with only title input: new Todo_List("test_before")
        Call updateTitle with input parameter "test_after"
        Check the title of test_1 by using an assert equals to ensure it was changed to "test_before"
         */
    }

    @org.junit.jupiter.api.Test
    void getItems() {
        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(false,"Desc1",new date(00,00,0000),"Item1"));
        test_array.add(new Item(true,"Desc2",new date(11,11,1111),"Item2"));
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);
        assertTrue(test_array==todo_test.getItems());
    }

    @org.junit.jupiter.api.Test
    void getIncompleteItems(){
        Item falseItem=new Item(false,"Desc1",new date(00,00,0000),"Item1");
        ArrayList<Item> goal_Array= new ArrayList<Item>();

        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(true,"Desc1",new date(00,00,0000),"Item1"));
        goal_Array.add(falseItem);
        test_array.add(falseItem);
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);

        ArrayList<Item>incompleteArray=todo_test.getIncompleteItems();
        for (int i = 0; i < incompleteArray.size(); i++) {
            assertTrue(goal_Array.get(i)==incompleteArray.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void getCompleteItems(){
        Item trueItem=new Item(true,"Desc1",new date(00,00,0000),"Item1");
        ArrayList<Item> goal_Array= new ArrayList<Item>();

        ArrayList<Item> test_array= new ArrayList<Item>();
        test_array.add(new Item(false,"Desc1",new date(00,00,0000),"Item1"));
        goal_Array.add(trueItem);
        test_array.add(trueItem);
        Todo_List todo_test= new Todo_List("Todo_Test",test_array);

        ArrayList<Item>completeArray=todo_test.getCompleteItems();
        for (int i = 0; i < completeArray.size(); i++) {
            assertTrue(goal_Array.get(i)==completeArray.get(i));
        }
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