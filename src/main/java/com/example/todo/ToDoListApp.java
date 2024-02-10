package com.example.todo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ToDoListApp {
    public void start(Stage primaryStage) {
        ListView<String> toDoListView = new ListView<>();
        TextField taskTextField = new TextField();
        taskTextField.setPromptText("Yeni görev ekle");

        Button addButton = new Button("Ekle");
        addButton.setOnAction(e -> {
            String task = taskTextField.getText().trim();
            if (!task.isEmpty()) {
                toDoListView.getItems().add(task);
                taskTextField.clear();
            }
        });

        Button deleteButton = new Button("Sil");
        deleteButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                toDoListView.getItems().remove(selectedIndex);
            }
        });
        Button saveButton = new Button("Kaydet");
        saveButton.setVisible(false);
        Button editButton = new Button("Düzenle");
        editButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedTask = toDoListView.getSelectionModel().getSelectedItem();
                taskTextField.setText(selectedTask);
                addButton.setDisable(true);
                deleteButton.setDisable(true);
                editButton.setVisible(false);
                saveButton.setVisible(true);
            }
        });


        saveButton.setVisible(false);
        saveButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            String editedTask = taskTextField.getText().trim();
            if (!editedTask.isEmpty()) {
                toDoListView.getItems().set(selectedIndex, editedTask);
                taskTextField.clear();
                addButton.setDisable(false);
                deleteButton.setDisable(false);
                editButton.setVisible(true);
                saveButton.setVisible(false);
            }
        });

        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.getChildren().addAll(taskTextField, addButton, deleteButton, editButton, saveButton);

        // Başlık etiketi oluştur
        Label titleLabel = new Label("To-Do List App");
        titleLabel.setStyle("-fx-font-size: 20px;"); // Başlık etiketinin font boyutunu ayarla

        // BorderPane için üst kısım
        BorderPane topPane = new BorderPane();
        topPane.setPadding(new Insets(10));
        topPane.setTop(titleLabel);

        BorderPane root = new BorderPane();
        root.setCenter(toDoListView);
        root.setBottom(inputBox);
        root.setTop(topPane);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("To-Do List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



