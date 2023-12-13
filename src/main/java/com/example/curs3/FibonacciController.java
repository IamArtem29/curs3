package com.example.curs3;

import java.util.List;

public class FibonacciController {
    private FibonacciModel model;
    private FibonacciView view;
    private DatabaseHelper dbHelper;

    public FibonacciController(FibonacciModel model, IterativeFibonacciModel iterativeModel, FibonacciView view) {
        this.model = model;
        this.view = view;
        this.dbHelper = new DatabaseHelper();
    }

    public void calculateFibonacci() {
        try {
            int n = Integer.parseInt(view.getInputField().getText());

            // Проверяем, есть ли результат в базе данных
            int resultFromDB = dbHelper.getResultFromDB(n, view.getMethodComboBox().getValue());
            if (resultFromDB != -1) {
                view.getResultLabel().setText("Результат (" + view.getMethodComboBox().getValue() + "): " + resultFromDB + " (из базы данных)");
                return;
            }

            // Выбор метода на основе ComboBox
            String selectedMethod = view.getMethodComboBox().getValue();
            int result = switch (selectedMethod) {
                case "Рекурсивный" -> model.calculateFibonacci(n);
                case "Итеративный" -> new IterativeFibonacciModel().calculateFibonacci(n);
                default -> throw new IllegalArgumentException("Выбран неправильный метод");
            };

            // Сохраняем результат в базе данных
            dbHelper.saveResultToDB(n, result, selectedMethod);

            view.getResultLabel().setText("Результат (" + selectedMethod + "): " + result);
        } catch (NumberFormatException e) {
            view.getResultLabel().setText("Введите число.");
        }
    }

    public List<HistoryEntry> getAllHistoryEntries() {
        return dbHelper.getAllHistoryEntries();
    }
}
