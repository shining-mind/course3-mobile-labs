package com.example.firstapplication;

public class Calculator {
    public double calculateTip(double amount, double percent) {
            return amount * percent;
    }

    public double calculateTotal(double amount, double percent) {
        return amount + calculateTip(amount, percent);
    }
}
