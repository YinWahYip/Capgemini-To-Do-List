package com.todo;

import java.io.*;
import java.util.*;

public class Manager {
    private List<Task> tasks;
    private List<Task> completedTasks;

    // safer to just final
    private final String filePath = "todo_list.txt";
    private final String completedFilePath = "completed_list.txt";

    // we could start at zero...
    private int nextId = 1;

    public Manager() {
        this.tasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
        loadTasksFromFile(filePath, tasks);
        loadTasksFromFile(completedFilePath, completedTasks);
    }

    public void addTask(String description, String category) {
        Task newTask = new Task(nextId++, description, category);
        tasks.add(newTask);
        saveTasksToFile(filePath, tasks);
        System.out.println("Task added! ");
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public boolean completeTask(int id) {
        Task taskToMove = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToMove = task;
                break;
            }
        }

        if (taskToMove != null) {
            tasks.remove(taskToMove);
            completedTasks.add(taskToMove);
            saveTasksToFile(filePath, tasks);
            saveTasksToFile(completedFilePath, completedTasks);
            return true;
        }
        return false;
    }

    public boolean removeTask(int id) {
        boolean removed = tasks.removeIf(
                task -> task.getId() == id);

        if (removed) {
            saveTasksToFile(filePath, tasks);
        }
        return removed;
    }

    // Loop through task object
    // write whatever the addTask parameter has into write
    // -> Write into file
    private void saveTasksToFile(String path, List<Task> targetList) {
        try (FileWriter writer = new FileWriter(path)) {
            for (Task task : targetList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + path);
        }
    }

    // For sorting tasks by category, ignore case
    // prob will not implement misspelling. not sure how too... maybe a similarity
    // comparison?
    public List<Task> getTasksByCategory(String category) {
        List<Task> filteredList = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category.trim())) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    // Create text file. If doesn't exist, exit
    // Read file, read contents line by line
    // Split, delimit categories by 3 - ID, Description / Task, Category hard limit
    // Load the split parts into String array "parts" - convert ID to integer from
    // string]
    // add task from parts array
    // ID Reset at the end if statement

    private void loadTasksFromFile(String path, List<Task> targetList) {
        File file = new File(path); // Use the parameter
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

                    // Add to whichever list was passed in!
                    targetList.add(new Task(id, desc, cat));

                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks from " + path + ". Starting fresh.");
        }
    }
}