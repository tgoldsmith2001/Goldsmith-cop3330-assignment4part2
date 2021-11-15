package ucf.assignments;/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import java.util.ArrayList;

public class Todo_List {
    private String title;
    private ArrayList<Item>items=new ArrayList<Item>();

    public Todo_List(String title){
        this.title=title;
    }
    public Todo_List(String title,ArrayList<Item>items){
        this.title=title;
        this.items=items;
    }

    //Sorts the items list by date (earliest to latest) then returns the resulting Array list of items
    public ArrayList<Item> sortByDate(){
        /*
        For i=0 to i=items length
            For j=i+1 to j=items length
                Call compareTwoItems with input items[i] and items[j]
                    If False
                        Flip items[i] and items[j]
        return items
         */
        return null;
    }
    //Compares item 1 and item 2 if item 1 is earlier return true, otherwise false
    private boolean compareTwoItems(Item item1, Item item2){
        /*
        If date of item1 is earlier than date of item2
            return true
        Else
            Return false
         */
        return false;
    }
    //Allows user to add an item to items arraylist, then returns that item
    public ArrayList<Item> addItem(Item addedElement){
        this.items.add(addedElement);
        return this.items;
    }
    //Allows a user to remove an element in items based on input parameter
    public ArrayList<Item> removeItem(int removeIndex){
        this.items.remove(removeIndex);
        return this.items;
    }
    public ArrayList<Item> removeAllItems(){
        this.items.clear();
        return this.items;
    }
    //Searches for item in Array
    private int searchItem(String name){
        /*
        For each Item in items
            If the name of the item is the same as the input parameter return its index
        If input parameter is not found in list return -1
         */
        return -1;
    }
    //Allows user to update title of todo list based on input parameter
    public String updateTitle(String newTitle){
        /*
        Set the title variable to the input parameter
        Return the title of the Todo list
         */
        return "";
    }
    //Returns all values of items
    public ArrayList<Item>getItems(){
        return this.items;
    }
    //Returns only the items that are incomplete from items
    public ArrayList<Item>getIncompleteItems(){
        ArrayList<Item>return_Array= new ArrayList<Item>();
        for (int i = 0; i < this.items.size(); i++) {
            if(!this.items.get(i).getComplete()){
                return_Array.add(this.items.get(i));
            }
        }
        return return_Array;
    }
    //Returns only the completed items from items
    public ArrayList<Item>getCompleteItems(){
        ArrayList<Item>return_Array= new ArrayList<Item>();
        for (int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getComplete()){
                return_Array.add(this.items.get(i));
            }
        }
        return return_Array;
    }
}
