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

    Todo_Controller TDC= new Todo_Controller("src/main","Storage");

    private String RootText= "Starting_Todo";
    private TreeItem<String>Todo_Root;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Delete_Button;
    @FXML
    private TreeView<String> Todo_List_Treeview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("IN");
        TDC.setTodoList(new Todo_List(RootText));
        Todo_Root=new TreeItem<String >(RootText);
        Todo_List_Treeview.setRoot(Todo_Root);
        Todo_Root.setExpanded(true);
    }
    public Controller() throws IOException {}

    @FXML
    void Todo_Tree_Selected(MouseEvent event){
        if(Todo_List_Treeview.getSelectionModel().getSelectedItem()!=null){
            TreeItem<String> item = Todo_List_Treeview.getSelectionModel().getSelectedItem();
            current_Todo_Tree_Selection=item;
            if(current_Todo_Tree_Selection.getParent()!=null) {
                //Case where the parent is the root meaning that we have selected an Item
                if (current_Todo_Tree_Selection.getParent().getValue().equals(RootText)) {
                    currentSelection="ITEM";
                    Delete_Button.setOpacity(1.0);
                }
            }else{
                currentSelection="TODO";
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
        //Add item to the tree view list after added to array
        Todo_Root.getChildren().add(new TreeItem<String>(toAdd.getName()));
    }
    @FXML
    void Delete_Item(){
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