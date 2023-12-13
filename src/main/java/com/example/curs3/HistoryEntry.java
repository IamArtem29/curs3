package com.example.curs3;

public class HistoryEntry {
    private int fibnum;
    private int result;
    private String type;

    public HistoryEntry(int fibnum, int result, String type) {
        this.fibnum = fibnum;
        this.result = result;
        this.type = type;
    }

    // Геттеры (или сеттеры по необходимости)
    public int getFibnum() {
        return fibnum;
    }

    public int getResult() {
        return result;
    }

    public String getType() {
        return type;
    }
}
