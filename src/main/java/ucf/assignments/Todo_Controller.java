package ucf.assignments;
/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import java.io.*;
import java.util.ArrayList;

/*
This Class deals with the functional aspects of a todo list such as adding or deleting todo lists
as well as dealing with memory by creating a xml file to save to at an input path, and having functionality
for saving multiple lists, single lists, and retrieving lists
Additionally this class contains a master list of all active todo lists
 */
public class Todo_Controller {
    private Todo_List Saved_todo=null;
    private Todo_List global_todo=null;
    private Todo_List Stored_todo_lists= null;
    private String file_name;
    private String file_path;
    private File xmlFile;
    public Todo_Controller(String path, String name) throws IOException {
        this.file_name = name;
        this.file_path = path;
        this.xmlFile = new File(this.file_path + "/" + file_name + ".xml");
        if (!this.xmlFile.exists()) {
            this.xmlFile.createNewFile();
            FileWriter FW = new FileWriter(this.xmlFile);
            BufferedWriter BW = new BufferedWriter(FW);
            BW.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            BW.write("<Todos>\n");
            BW.write("</Todos>\n");
            BW.write("</xml>");
            BW.close();
        }
    }
    //Allows the user to input a todo list to add to the master list
    public void setTodoList(Todo_List toAdd){
        this.global_todo=toAdd;
    }
    public void setSavedTodoList(Todo_List toAdd){
        this.Saved_todo=toAdd;
    }
    public Todo_List getTodoList(){
        return this.global_todo;
    }
    public Todo_List getSavedTodoList(){
        return this.Saved_todo;
    }
    public Todo_List getGlobal_todo(){
        return this.global_todo;
    }


    //Allows user to delete a todo list by name from the master list
    public boolean removeTodoList(Todo_List toRemove){
        /*
        Check if toRemove is in all_todo_list
        if this is the case
            remove toRemove from Todo_List
            return true
        else
            return false
         */
        return true;
    }

    //Allows the user to save all active todo lists in the master list to xml file
    public void saveToFile(Todo_List items) throws IOException {
            String fileString = TodotoString(items);
            //Clear the file and rewrite to it
            FileWriter FW = new FileWriter(this.xmlFile, false);
            BufferedWriter BW = new BufferedWriter(FW);
            BW.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            BW.write("<Todo>");
            BW.write(fileString);
            BW.write("\n</Todo>\n");
            //BW.write("</xml>");
            BW.close();
    }
    //Creates a string from Todo List to allow for storage in memory
    public String TodotoString(Todo_List items) throws IOException {
        //String ret= FiletoString();
        String ret= "";
        ret+="\n\t<Title>"+items.getTitle()+"</Title>\n\t<Items>\n\t";
        for (int i = 0; i < items.getItems().size(); i++) {
            ret+="\t<Item>\n\t\t\t";
            Item currItem = items.getItems().get(i);
            ret+="<Name>" + currItem.getName() + "</Name>\n\t\t\t";
            ret+="<Description>" + currItem.getDescription() + "</Description>\n\t\t\t";
            ret+="<Date>" + currItem.getDate().getDateinString() + "</Date>\n\t\t\t";
            ret+="<Complete>" + currItem.getComplete() + "</Complete>\n\t\t";
            ret+="</Item>\n\t";
        }
        ret+="</Items>";
        return ret;
    }
    //Allows the user to search the storage for a todo list by name

    //Reads all todo lists from xml file and returns an arraylist of these lists
    public Todo_List readMemory() throws IOException {
        Todo_List ret = new Todo_List("");
        BufferedReader reader = new BufferedReader(new FileReader(this.file_path + "/" + this.file_name + ".xml"));
        String line;
        boolean item = false;
        int itemCounter=0;

        while ((line = reader.readLine()) != null) {
            //If we are currently adding items
            if (item == true) {
                if(line.contains("<Name>")){
                    String Name= parseSingleLine("Name",line);
                    ArrayList<Item>Todo_Items=ret.getItems();
                    Item currentItem= Todo_Items.get(itemCounter);
                    currentItem.setName(Name);
                }else if(line.contains("<Description>")){
                    String description= parseSingleLine("Description",line);
                    ret.getItems().get(itemCounter).updateDescription(description);
                }else if(line.contains("<Date>")){
                    String date= parseSingleLine("Date",line);
                    int month= Integer.parseInt(date.substring(5,7));
                    int day= Integer.parseInt(date.substring(8,10));
                    int year= Integer.parseInt(date.substring(0,4));
                    ret.getItems().get(itemCounter).setDate(new date(month, day, year));
                }else if(line.contains("<Complete>")){
                    //When we get to name tag we start creating an item
                    String comp= parseSingleLine("Complete",line);
                    boolean complete;
                    if(comp.equals("true"))
                        complete=true;
                    else
                        complete=false;
                    ret.getItems().get(itemCounter).setComplete(complete);
                }
            }
            if (line.contains("<Title>")) {
                String title= parseSingleLine("Title",line);
                ret.updateTitle(title);
            }else if (line.contains("</Item>")) {
                itemCounter++;
            }else if(line.contains("<Item>")) {
                ret.getItems().add(new Item(false,"", null,""));
            }
            else if(line.contains("</Items>")) {
                itemCounter=0;
            }else if(line.contains("<Items")) {
                item = true;
            }
        }
        return ret;
    }
    private String parseSingleLine(String Tag, String line){
        line= line.replace("\\s", "").replace("\t","");
        int end_index = line.indexOf("</"+Tag+">");
        String name = line.substring(Tag.length()+2, end_index);
        return name;
    }
}
