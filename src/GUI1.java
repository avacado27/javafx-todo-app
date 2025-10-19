import java.text.ChoiceFormat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GUI1 extends Application {

    private TODO todoList = new TODO();

    @Override
    public void start(Stage primaryStage) {

        //Initial TODOs
        todoList.loadTasks();
        //displayArea.setText(todoList.displayTODO());

        // UI Elements
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a task");

        Button addButton = new Button("Add Task");
        Button removeButton = new Button("Remove Task");
        Button completeButton = new Button("Complete Task");
        Button editNameButton = new Button("Edit Task");
        Button changePriorityButton = new Button("Change Priority");
        ObservableList<Task> observableTasks = FXCollections.observableArrayList(todoList.getTasks());
        ListView<Task> taskListView = new ListView<>(observableTasks);

        removeButton.setOnAction(e -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            //String task = taskInput.getText();
            if (selected != null) {
                todoList.remove(selected.getTitle());
                observableTasks.remove(selected);
                todoList.saveTODOS();
            }
        });

        completeButton.setOnAction(e -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isCompleted()) {
                selected.setCompleted(true);
                taskListView.refresh();
                todoList.saveTODOS();
            }
        });

        // Event handler for Add button
        addButton.setOnAction(e -> {
            String task = taskInput.getText();
            if (!task.isEmpty()) {
                Task newTask = new Task(task);
                todoList.getTasks().add(newTask);
                observableTasks.add(newTask);
                taskInput.clear();
                todoList.saveTODOS();
            }
        });

        editNameButton.setOnAction(e -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog(selected.getTitle());
                dialog.setTitle("Edit Task");
                dialog.setHeaderText("Edit Task Name");
                dialog.setContentText("New name:");
                dialog.showAndWait().ifPresent(newName -> {
                    selected.setTitle(newName);
                    taskListView.refresh();
                    todoList.saveTODOS();
                });
            }
        });

        changePriorityButton.setOnAction(e -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                ChoiceDialog<Integer> dialog = new ChoiceDialog<>(selected.getPriority(), 1, 2, 3);
                dialog.setTitle("Change Priority");
                dialog.setHeaderText("Set new priority (1 = High, 2 = Medium, 3 = Low)");
                dialog.setContentText("Priority:");
                dialog.showAndWait().ifPresent(newPriority -> {
                    selected.setPriority(newPriority);
                    taskListView.refresh();
                    todoList.saveTODOS();
                });
            }
        });

        // Create a layout pane and add the button to it
        VBox root = new VBox(10);
        root.getChildren().addAll(taskInput, addButton, removeButton, completeButton, taskListView,
                                            changePriorityButton, editNameButton);

        // Create a scene with the layout pane as its root
        Scene scene = new Scene(root, 350, 450);

        // Set the scene to the primary stage
        primaryStage.setTitle("My To-Do List");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}