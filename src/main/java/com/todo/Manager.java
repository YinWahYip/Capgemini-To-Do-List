package com.todo;

import java.io.*;
import java.util.*;

public class Manager {
    private List<Task> tasks;

    // safer to just final
    private final String filePath = "todo_list.txt";
    // we could start at zero...
    private int nextId = 1;

    public Manager() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String description, String category) {
        Task newTask = new Task(nextId++, description, category);
        tasks.add(newTask);
        saveTasksToFile();
        System.out.println("Task added! ");
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public boolean removeTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            saveTasksToFile();
        }
        return removed;
    }

    // File I/O Methods
    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    // Create text file. If doesn't exist, exit
    // Read file, read contents line by line
    // Split, delimit categories by 3 - ID, Description / Task, Category hard limit
    // Load the split parts into String array "parts" - convert ID to integer from
    // string]
    // add task from parts array
    // ID Reset at the end if statement
    private void loadTasksFromFile() {
        File file = new File(filePath);
        if (!file.exists())
            return;

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {

                    int id = Integer.parseInt(parts[0]);
                    String desc = parts[1];
                    String cat = parts[2];

                    // id #, description, category
                    tasks.add(new Task(id, desc, cat));

                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading existing tasks. Starting fresh.");
        }
    }
}