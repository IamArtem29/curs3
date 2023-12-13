package com.example.curs3;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private FibonacciController controller;

    private VBox layout;  // Добавление переменной layout как поле класса

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Числа Фибоначчи");

        RecursiveFibonacciModel recursiveModel = new RecursiveFibonacciModel();
        IterativeFibonacciModel iterativeModel = new IterativeFibonacciModel();

        FibonacciView view = new FibonacciView();
        controller = new FibonacciController(recursiveModel, iterativeModel, view);

        view.getCalculateButton().setOnAction(event -> controller.calculateFibonacci());

        Button showHistoryButton = new Button("Показать историю");
        showHistoryButton.setOnAction(event -> showHistory());

        TextArea historyTextArea = new TextArea();
        historyTextArea.setEditable(false);

        layout = new VBox(10);  // Инициализация layout как поле класса
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                view.getInputLabel(),
                view.getInputField(),
                view.getMethodComboBox(),
                view.getCalculateButton(),
                view.getResultLabel(),
                showHistoryButton,
                historyTextArea);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showHistory() {
        List<HistoryEntry> historyEntries = controller.getAllHistoryEntries();

        StringBuilder historyText = new StringBuilder();
        for (HistoryEntry entry : historyEntries) {
            historyText.append(entry.getFibnum())
                    .append(": ")
                    .append(entry.getResult())
                    .append(" (")
                    .append(entry.getType())
                    .append(")\n");
        }

        ((TextArea) layout.getChildren().get(layout.getChildren().size() - 1)).setText(historyText.toString());
    }
}
