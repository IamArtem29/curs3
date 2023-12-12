package com.example.curs3;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FibonacciView {
    private Label inputLabel;
    private TextField inputField;
    private ComboBox<String> methodComboBox;
    private Button calculateButton;
    private Label resultLabel;

    public FibonacciView() {
        inputLabel = new Label("Введите число:");
        inputField = new TextField();
        methodComboBox = new ComboBox<>();
        methodComboBox.getItems().addAll("Рекурсивный", "Итеративный");
        methodComboBox.setValue("Рекурсивный"); // По умолчанию выбран рекурсивный метод
        calculateButton = new Button("Выполнить");
        resultLabel = new Label("Результат: ");
    }

    public Label getInputLabel() {
        return inputLabel;
    }

    public TextField getInputField() {
        return inputField;
    }

    public ComboBox<String> getMethodComboBox() {
        return methodComboBox;
    }

    public Button getCalculateButton() {
        return calculateButton;
    }

    public Label getResultLabel() {
        return resultLabel;
    }
}
