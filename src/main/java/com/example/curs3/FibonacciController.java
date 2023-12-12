package com.example.curs3;

class FibonacciController {
    private FibonacciModel model;
    private FibonacciView view;

    public FibonacciController(FibonacciModel model, IterativeFibonacciModel iterativeModel, FibonacciView view) {
        this.model = model;
        this.view = view;
    }

    public void calculateFibonacci() {
        try {
            int n = Integer.parseInt(view.getInputField().getText());

            // Выбор метода на основе ComboBox
            String selectedMethod = view.getMethodComboBox().getValue();
            int result = switch (selectedMethod) {
                case "Рекурсивный" -> model.calculateFibonacci(n);
                case "Итеративный" -> new IterativeFibonacciModel().calculateFibonacci(n);
                default -> throw new IllegalArgumentException("Выбран неправильный метож");
            };

            view.getResultLabel().setText("Результат (" + selectedMethod + "): " + result);
        } catch (NumberFormatException e) {
            view.getResultLabel().setText("Введите число.");
        }
    }
}