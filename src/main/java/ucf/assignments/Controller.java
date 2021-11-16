package ucf.assignments;/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    TreeItem<String> current_Todo_Tree_Selection=null;
    String currentSelection= "NONE";
    String Items_Shown= "ALL";

    Todo_Controller TDC= new Todo_Controller("src/main","Storage");

    private String RootText= "Todo List";
    private TreeItem<String>Todo_Root;
    private TreeItem<String>Saved_Root;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Delete_Button;
    @FXML
    private Button Description_Button;
    @FXML
    private Button dueDate_Button;
    @FXML
    private Button Complete_Button;
    @FXML
    private Button Incomplete_Button;
    @FXML
    private Button ViewAll_Button;
    @FXML
    private Button ViewCompleted_Button;
    @FXML
    private Button ViewInCompleted_Button;
    @FXML
    private Button Save_Button;
    @FXML
    private Button Load_Button;
    @FXML
    private Button About_Button;
    @FXML
    private TreeView<String> Todo_List_Treeview;
    @FXML
    private TreeView<String> Saved_Treeview;
    @FXML
    private Label Todo_Label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TDC.setTodoList(new Todo_List(RootText));
        Todo_Root=new TreeItem<String >(RootText);
        Todo_List_Treeview.setRoot(Todo_Root);
        Todo_Root.setExpanded(true);
    }
    public Controller() throws IOException {}
    @FXML
    void Information_Page(){

        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Item Prompts");
        dialog.setHeaderText("Welcome! This Program stores and allows organization of a single Todo List\n" +
                "To Create an Item: click the Add Item button and fill in the subsequent text prompts\n" +
                "To see Information about each item: Select the item in the Tree View to display information int he bottom right\n" +
                "To Remove and Item: Select the item to be removed in the Tree View then select the remove Button\n" +
                "To Clear all Todo Lists: Select the Todo List title at the top of the Tree View then select the Delete All Button\n" +
                "To edit an Item: Select the item to edit then select one of the four edit options below and follow the text prompts\n" +
                "To mark and Item as Complete or Incomplete: Select the item to be altered and select the Mark Complete/Incomplete Button\n" +
                "To see only Complete/Incomplete Items: Select the View Completed Items Button or View Incomplete Items Button\n" +
                "To save to Memory: Select the Save to File Button\n" +
                "To Retrieve from Memory: Select the Load File button to retrieve the Todo List from storage to the Saved List section");

        ButtonType enterButtonType = new ButtonType("Return", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enterButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Hello"), 0, 0);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                return null;
            }
            return null;
        });
        //Optional<Pair<String, String>> result = dialog.showAndWait();
        Optional<ArrayList<String>> result = dialog.showAndWait();
    }
    @FXML
    void Todo_Tree_Selected(MouseEvent event){
        if(Todo_List_Treeview.getSelectionModel().getSelectedItem()!=null){
            TreeItem<String> item = Todo_List_Treeview.getSelectionModel().getSelectedItem();
            current_Todo_Tree_Selection=item;
            if(current_Todo_Tree_Selection.getParent()!=null) {
                //Case where the parent is the root meaning that we have selected an Item
                Item currentItem=null;
                if (current_Todo_Tree_Selection.getParent().getValue().equals(RootText)) {
                    for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                        Item temp= TDC.getTodoList().getItems().get(i);
                        if(temp.getName().equals(current_Todo_Tree_Selection.getValue()))
                            currentItem=temp;
                    }
                    Todo_Label.setText("Selected Item Information:\nName: "+currentItem.getName()+"\nDescription: "+ currentItem.getDescription()
                            +"\nDue Date: "+currentItem.getDate().getDateinString()+"\nComplete?: "+currentItem.getComplete());
                    currentSelection="ITEM";
                    Delete_Button.setText("Delete");
                    Delete_Button.setOpacity(1.0);
                    Description_Button.setOpacity(1.0);
                    dueDate_Button.setOpacity(1.0);
                    Complete_Button.setOpacity(1.0);
                    Incomplete_Button.setOpacity(1.0);
                }
            }else{
                currentSelection="TODO";
                Delete_Button.setText("Delete All");
                Delete_Button.setOpacity(1.0);
                Description_Button.setOpacity(0.25);
                dueDate_Button.setOpacity(0.25);
                Complete_Button.setOpacity(0.25);
                Incomplete_Button.setOpacity(0.25);
                }
        }
    }
    @FXML
    void Add_Item(){
        //Optional<Pair<String, String>> Result= itemDialog();
        Optional<ArrayList<String>> Result= itemDialog();
        //Gets the index of the current todo list in array of all todo lists
        String name= Result.get().get(0);
        String description= Result.get().get(1);
        int month= Integer.parseInt(Result.get().get(2));
        int day= Integer.parseInt(Result.get().get(3));
        int year= Integer.parseInt(Result.get().get(4));

        date itemDueDate= new date(month, day, year);

        Item toAdd= new Item(false,description,itemDueDate,name);

            TDC.getTodoList().addItem(toAdd);
        if(Items_Shown.equals("INCOMPLETED")||Items_Shown.equals("ALL")) {
            //Add item to the tree view list after added to array
            Todo_Root.getChildren().add(new TreeItem<String>(toAdd.getName()));
        }
    }
    @FXML
    void Delete_Item(){
        //Deletes only one item
        if(currentSelection.equals("ITEM")){
            ArrayList<Item> itemList= TDC.getTodoList().getItems();
            for (int i = 0; i < itemList.size(); i++) {
                if(itemList.get(i).getName().equals(current_Todo_Tree_Selection.getValue())){
                    TDC.getTodoList().removeItem(i);
                    TreeItem<String>toRemove= getItemFromTree(current_Todo_Tree_Selection.getValue(), Todo_Root);
                    toRemove.getParent().getChildren().remove(toRemove);
                }
            }
            Delete_Button.setOpacity(0.25);
        }
        //Deletes all items in the List
        else if(currentSelection.equals("TODO")){
            for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                TreeItem children = getItemFromTree(TDC.getTodoList().getItems().get(i).getName(),Todo_Root);
                children.getParent().getChildren().remove(children);
            }
            TDC.getTodoList().removeAllItems();
        }
    }
    @FXML
    void Edit_Description(){
        //Checks to ensure that this is an item level selection
        if(currentSelection.equals("ITEM")){
                TextInputDialog textInput = new TextInputDialog();

                textInput.setTitle("Dialogue Box");
                textInput.setHeaderText("");
                textInput.getDialogPane().setContentText("Enter the new Description for the Item");
                Optional<String> result = textInput.showAndWait();
                TextField input = textInput.getEditor();
                for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                    System.out.println();
                    Item currentItem= TDC.getTodoList().getItems().get(i);
                    if(currentItem.getName().equals(current_Todo_Tree_Selection.getValue())){
                        currentItem.setDescription(input.getText().toString());
                        Todo_Label.setText("Selected Item Information:\nName: "+currentItem.getName()+"\nDescription: "+ currentItem.getDescription()
                                +"\nDue Date: "+currentItem.getDate().getDateinString()+"\nComplete?: "+currentItem.getComplete());
                    }
            }
        }
    }
    @FXML
    void Edit_dueDate(){
        //Checks to ensure that this is an item level selection
        if(currentSelection.equals("ITEM")){
            Optional<ArrayList<String>> arrayDate= dateDialog();
            int month=Integer.parseInt(arrayDate.get().get(0));
            int day=Integer.parseInt(arrayDate.get().get(1));
            int year= Integer.parseInt(arrayDate.get().get(2));
            date newDate= new date(month,day,year);
            for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                Item currentItem= TDC.getTodoList().getItems().get(i);
                if(currentItem.getName().equals(current_Todo_Tree_Selection.getValue())){
                    currentItem.setDate(newDate);
                    Todo_Label.setText("Selected Item Information:\nName: "+currentItem.getName()+"\nDescription: "+ currentItem.getDescription()
                            +"\nDue Date: "+currentItem.getDate().getDateinString()+"\nComplete?: "+currentItem.getComplete());
                }
            }
            dueDate_Button.setOpacity(0.25);
        }
    }
    @FXML
    void Mark_Complete(){
        //Checks to ensure that this is an item level selection
        if(currentSelection.equals("ITEM")){
            for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                Item currentItem= TDC.getTodoList().getItems().get(i);
                if(currentItem.getName().equals(current_Todo_Tree_Selection.getValue())){
                    currentItem.setComplete(true);
                    //Removes from view if not proper setting
                    if(Items_Shown.equals("INCOMPLETED")){
                        TreeItem<String>toRemove= getItemFromTree(current_Todo_Tree_Selection.getValue(), Todo_Root);
                        toRemove.getParent().getChildren().remove(toRemove);
                    }
                    Todo_Label.setText("Selected Item Information:\nName: "+currentItem.getName()+"\nDescription: "+ currentItem.getDescription()
                            +"\nDue Date: "+currentItem.getDate().getDateinString()+"\nComplete?: "+currentItem.getComplete());
                }
            }
        }
    }
    @FXML
    void Mark_Incomplete(){
        //Checks to ensure that this is an item level selection
        if(currentSelection.equals("ITEM")){
            for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
                Item currentItem= TDC.getTodoList().getItems().get(i);
                if(currentItem.getName().equals(current_Todo_Tree_Selection.getValue())){
                    currentItem.setComplete(false);
                    //Remove from view if current setting is only Completed Items
                    if(Items_Shown.equals("COMPLETED")){
                        TreeItem<String>toRemove= getItemFromTree(current_Todo_Tree_Selection.getValue(), Todo_Root);
                        toRemove.getParent().getChildren().remove(toRemove);
                    }
                    Todo_Label.setText("Selected Item Information:\nName: "+currentItem.getName()+"\nDescription: "+ currentItem.getDescription()
                            +"\nDue Date: "+currentItem.getDate().getDateinString()+"\nComplete?: "+currentItem.getComplete());
                }
            }
        }
    }
    @FXML
    void setDisplayAll(){
        if(!Items_Shown.equals("ALL")){
        Items_Shown="ALL";
        ViewAll_Button.setOpacity(1);
        ViewCompleted_Button.setOpacity(0.25);
        ViewInCompleted_Button.setOpacity(0.25);
        //Clear out all Items
        while(Todo_Root.getChildren().size()>0){
            System.out.println(Todo_Root.getChildren().size());
            Todo_Root.getChildren().remove(Todo_Root.getChildren().size()-1);
        }
        //Add all Items back to the tree
        for (int i = 0; i < TDC.getTodoList().getItems().size(); i++) {
            TreeItem toAdd = new TreeItem<String>(TDC.getTodoList().getItems().get(i).getName());
            Todo_Root.getChildren().add(toAdd);
        }
        }
    }
    @FXML
    void setDisplayCompleted(){
        if(!Items_Shown.equals("COMPLETED")) {
            Items_Shown = "COMPLETED";
            ViewAll_Button.setOpacity(0.25);
            ViewCompleted_Button.setOpacity(1);
            ViewInCompleted_Button.setOpacity(0.25);
            //Clear out all Items
            while(Todo_Root.getChildren().size()>0){
                System.out.println(Todo_Root.getChildren().size());
                Todo_Root.getChildren().remove(Todo_Root.getChildren().size()-1);
            }
            for (int i = 0; i <TDC.getTodoList().getCompleteItems().size(); i++) {
                TreeItem toAdd = new TreeItem<String>(TDC.getTodoList().getCompleteItems().get(i).getName());
                Todo_Root.getChildren().add(toAdd);
            }
        }
    }
    @FXML
    void setDisplayInCompleted(){
        if(!Items_Shown.equals("INCOMPLETED")) {
            Items_Shown = "INCOMPLETED";
            ViewAll_Button.setOpacity(0.25);
            ViewCompleted_Button.setOpacity(0.25);
            ViewInCompleted_Button.setOpacity(1);
            //Clear out all Items
            while(Todo_Root.getChildren().size()>0){
                System.out.println(Todo_Root.getChildren().size());
                Todo_Root.getChildren().remove(Todo_Root.getChildren().size()-1);
            }
            for (int i = 0; i < TDC.getTodoList().getIncompleteItems().size(); i++) {
                TreeItem toAdd = new TreeItem<String>(TDC.getTodoList().getIncompleteItems().get(i).getName());
                Todo_Root.getChildren().add(toAdd);
            }
        }
    }
    @FXML
    void SaveToFile() throws IOException {
        TDC.saveToFile(TDC.getTodoList());
    }
    @FXML
    void Load_From_Storage() throws IOException {
        Todo_List fromMemory= TDC.readMemory();
        TDC.setSavedTodoList(fromMemory);
        Saved_Root=new TreeItem<String >(fromMemory.getTitle());
        Saved_Treeview.setRoot(Saved_Root);
        Saved_Root.setExpanded(true);
        for (int i = 0; i < TDC.getSavedTodoList().getItems().size(); i++) {
            TreeItem<String> toAdd= new TreeItem<String>(TDC.getSavedTodoList().getItems().get(i).getName());
            Saved_Root.getChildren().add(toAdd);
        }
    }
    private Optional <ArrayList<String>> itemDialog(){
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Item Prompts");
        dialog.setHeaderText("");

        ButtonType enterButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enterButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");
        TextField description = new TextField();
        description.setPromptText("Description");

        TextField month = new TextField();
        month.setPromptText("XX");
        TextField day = new TextField();
        day.setPromptText("XX");
        TextField year = new TextField();
        year.setPromptText("XXXX");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(description, 1, 1);

        grid.add(new Label("Month:"), 0, 2);
        grid.add(month, 1, 2);
        grid.add(new Label("Day:"), 0, 3);
        grid.add(day, 1, 3);
        grid.add(new Label("Year:"), 0, 4);
        grid.add(year, 1, 4);

        Node enterButton = dialog.getDialogPane().lookupButton(enterButtonType);
        enterButton.setDisable(true);

        //Invoked when text is entered to enable the enter button
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            enterButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                //return new Pair<>(name.getText(), description.getText());
                ArrayList<String> ret= new ArrayList<>();
                ret.add(name.getText());
                ret.add(description.getText());
                ret.add(month.getText());
                ret.add(day.getText());
                ret.add(year.getText());
                return ret;
            }
            return null;
        });
        //Optional<Pair<String, String>> result = dialog.showAndWait();
        Optional<ArrayList<String>> result = dialog.showAndWait();
        return result;
    }
    private Optional <ArrayList<String>> dateDialog(){
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Item Prompts");
        dialog.setHeaderText("");

        ButtonType enterButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enterButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField month = new TextField();
        month.setPromptText("XX");
        TextField day = new TextField();
        day.setPromptText("XX");
        TextField year = new TextField();
        year.setPromptText("XXXX");

        grid.add(new Label("Month:"), 0, 0);
        grid.add(month, 1, 0);
        grid.add(new Label("Day:"), 0, 1);
        grid.add(day, 1, 1);
        grid.add(new Label("Year:"), 0, 2);
        grid.add(year, 1, 2);

        Node enterButton = dialog.getDialogPane().lookupButton(enterButtonType);
        enterButton.setDisable(true);

        //Invoked when text is entered to enable the enter button
        month.textProperty().addListener((observable, oldValue, newValue) -> {
            enterButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> month.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                //return new Pair<>(name.getText(), description.getText());
                ArrayList<String> ret= new ArrayList<>();
                ret.add(month.getText());
                ret.add(day.getText());
                ret.add(year.getText());
                return ret;
            }
            return null;
        });
        //Optional<Pair<String, String>> result = dialog.showAndWait();
        Optional<ArrayList<String>> result = dialog.showAndWait();
        return result;
    }
    private TreeItem<String> getItemFromTree(String name, TreeItem<String>root){
        //Retrieves all the children given a Root
        List<TreeItem<String>> children = root.getChildren();
        for(TreeItem<String>child: children) {
            //For each child searches for the child with the input parameter name
            if(child.getValue().toString().equals(name)){
                return child;
            }
        }
        return null;
    }
}