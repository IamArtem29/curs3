package com.example.curs3;

public class RecursiveFibonacciModel extends FibonacciModel {
    @Override
    public int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}
