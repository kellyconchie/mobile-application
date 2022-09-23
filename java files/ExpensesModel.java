package com.example.testingproject;

public class ExpensesModel {

    private int id;
    private String date;
    private double amount;
    private String category;

    public ExpensesModel(int id, String date, double amount, String category) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }


    // toString has been added to print the contents of the class objects
    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Amount spent: " + amount + "\n" +
                "Category: " + category;
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
