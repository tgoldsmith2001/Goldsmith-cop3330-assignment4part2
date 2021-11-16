/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class Todo_ControllerTest {
    /*Instantiate Todo_Controller_Test (new Todo_Controller)
    Create initialList (TodoList) with title "INITIAL_TITLE"
    Add a TodoList to Todo_Controller_Test.all_todo_lists
     */
    //Test for Requirement 6
    @Test
    void addTodoList() {

    }

    @Test
    void saveToFile() throws IOException {
        //Todo_Controller test_Controller= new Todo_Controller("src/test/java/Resources","Storage_Test");
        //Todo_List todoTest=new Todo_List("Test");
    }
    //Test for Requirement 18
    @Test
    void saveAllToFile(){
                /*
        Define a new array of todo lists
        Add multiple todo list to the array
        Save all todo lists in array to a file
        Call readMemory of the created file
        Assert that initial array equals the call to readMemory
         */
    }
    //Test for Requirement 19 and 20
    @Test
    void searchStorage(){
        /*
        Define a new array of todo lists
        Add multiple todo list to the array
        Save all todo lists in array to a file
        Call searchStorage for one element in memory, and set equal to todo list variable
        Assert that todo list variable equals intended memory access
        Call searchStorage again for another element, and set equal to another variable
        Assert that todo list variable equals intended memory access
         */
    }
}