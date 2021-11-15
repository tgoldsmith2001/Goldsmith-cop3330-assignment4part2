package com.example.assignment42_final;/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Todo_Controller TDC= new Todo_Controller("src/main","Storage");

    private String RootText= "Starting_Todo";
    private TreeItem<String>Todo_Root;

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
        System.out.println("IN");
    }
}