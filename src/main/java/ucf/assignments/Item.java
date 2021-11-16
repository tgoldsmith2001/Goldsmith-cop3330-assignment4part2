package ucf.assignments;/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import java.util.Date;

public class Item {
    private String name;
    private String description;
    private date dueDate;
    private Boolean complete;

    public Item(boolean complete, String description, date dueDate, String name){
        this.complete=complete;
        this.description=description;
        this.dueDate=dueDate;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDate(date dueDate){
        this.dueDate=dueDate;
    }
    public date getDate(){
        return this.dueDate;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
    public Boolean getComplete(){
        return this.complete;
    }

    //Updates current items description based on input parameter
    public String updateDescription(String newDescription){
        this.description=newDescription;
        return "";
    }
    //Updates current items due date based on input parameter
    public String updateDueDate(Date newDueDate){
        /*
        Set due date to the input parameter
        return due date
         */
        return "";
    }
    //Marks the current item complete boolean as True
    public boolean markComplete() {
        /*
        Set complete to true
        return complete
         */
        return true;
    }
}
