package ucf.assignments;
/*
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
    public String getTitle(){
        return this.title;
    }
    //Allows user to update title of todo list based on input parameter
    public String updateTitle(String newTitle){
        this.title=newTitle;
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
