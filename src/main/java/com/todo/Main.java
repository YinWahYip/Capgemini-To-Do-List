package com.todo;

import java.util.*;

public class Main {
    // main staging area to move stuff around, and how the string choices work.

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager();

        System.out.println("Welcome to your To-Do List!");
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View Active Tasks");
            System.out.println("2. Add a Task");
            System.out.println("3. Remove a Task");
            System.out.println("4. Complete a Task");
            System.out.println("5. View Tasks by Category"); 
            System.out.println("6. View Archive (Completed Tasks)");
            System.out.println("7. Exit");
            System.out.print("Enter a number to choose an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                // View Tasks
                case "1":
                    System.out.println("\n--- Your Active Tasks ---");
                    if (manager.getAllTasks().isEmpty()) {
                        System.out.println("Your active list is empty!");
                    } else {
                        for (Task t : manager.getAllTasks()) {
                            System.out.println(t);
                        }
                    }
                    break; 

                // Adding task
                case "2":
                    System.out.print("Enter task description: ");
                    String desc = scan.nextLine();
                    System.out.print("Enter a category tag (e.g., Work, Personal, School): ");
                    String cat = scan.nextLine();
                    manager.addTask(desc, cat);
                    break;

                // Remove task
                case "3":
                    if (manager.getAllTasks().isEmpty()) {
                        System.out.println("Your list is empty! Nothing to remove.");
                        break;
                    }
                    System.out.println("\n--- Current Active Tasks ---");
                    for (Task t : manager.getAllTasks()) {
                        System.out.println(t);
                    }
                    System.out.print("\n Enter the ID of the task to remove: ");

                    try {
                        int id = Integer.parseInt(scan.nextLine());

                        if (manager.removeTask(id)) {
                            System.out.println("Task removed.");
                        } else {
                            System.out.println("Task ID not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number ID.");
                    }
                    break;

                // Complete task (like remove), but adds the selected task to separate list
                case "4":
                    System.out.println("\n--- Current Active Tasks ---");
                    for (Task t : manager.getAllTasks()) {
                        System.out.println(t);
                    }
                    System.out.print("Enter the ID of the task you finished: ");
                    try {
                        int id = Integer.parseInt(scan.nextLine());
                        if (manager.completeTask(id)) {
                            System.out.println("Task completed!");
                        } else {
                            System.out.println("Task ID not found in active list.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number ID.");
                    }
                    break;

                // categories
                case "5":
                    System.out.print("What category do you want to see? (e.g., Work, School): ");
                    String targetCategory = scan.nextLine();

                    // Call the manager to filter the data tier
                    List<Task> filteredTasks = manager.getTasksByCategory(targetCategory);

                    System.out.println("\n--- Tasks in Category: " + targetCategory + " ---");
                    if (filteredTasks.isEmpty()) {
                        System.out.println("No tasks found matching that category.");
                    } else {
                        for (Task t : filteredTasks) {
                            System.out.println(t);
                        }
                    }
                    break;

                case "6": // (Old Case 5)
                    System.out.println("\n--- Completed Tasks Archive ---");
                    if (manager.getCompletedTasks().isEmpty()) {
                        System.out.println("No completed tasks yet. Go back to add one!");
                    } else {
                        for (Task t : manager.getCompletedTasks()) {
                            System.out.println(t);
                        }
                    }
                    break;

                // Exit
                case "7":
                    System.out.println("Quitting");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Select a number between 1-7");
            }
        }
        scan.close();
    }
}
