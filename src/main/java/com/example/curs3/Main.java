package com.example.curs3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private FibonacciController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Числа Фибоначчи");

        // Инициализация компонентов MVC
        RecursiveFibonacciModel recursiveModel = new RecursiveFibonacciModel();
        IterativeFibonacciModel iterativeModel = new IterativeFibonacciModel();

        FibonacciView view = new FibonacciView();
        controller = new FibonacciController(recursiveModel, iterativeModel, view);

        // Установка обработчика события на кнопке
        view.getCalculateButton().setOnAction(event -> controller.calculateFibonacci());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                view.getInputLabel(),
                view.getInputField(),
                view.getMethodComboBox(),
                view.getCalculateButton(),
                view.getResultLabel());

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
