package com.todo;

public class Task {
    private int id;
    private String description;
    private String category;

    public Task(int id, String description, String category) {
        this.id = id;
        this.description = description;
        // "General" as default
        this.category = (category == null || category.trim().isEmpty()) ? "General" : category.trim();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    // just get category
    public String getCategory() {
        return category;
    }

    // formatting text for console
    @Override
    public String toString() {
        return String.format("[%d] %s (Category: %s)", id, description, category);
    }

    // save tasks
    public String toFileString() {
        return id + ": " + description + "," + category;
    }
}