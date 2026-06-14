package com.todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager();

        System.out.println("Welcome to your To-Do List!");
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add a Task");
            System.out.println("3. Remove a Task");
            System.out.println("4. Complete a Task");
            System.out.println("5. Exit");
            System.out.print("Enter a number to choose an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Your Tasks ---");
                    if (manager.getAllTasks().isEmpty()) {
                        System.out.println("Your list is empty!");
                    } else {
                        for (Task t : manager.getAllTasks()) {
                            System.out.println(t);
                        }
                    }

                case "2":
                    System.out.print("Enter task description: ");
                    String desc = scan.nextLine();
                    System.out.print("Enter a category tag (e.g., Work, Personal, School): ");
                    String cat = scan.nextLine();
                    manager.addTask(desc, cat);
                    break;

                case "3":
                    System.out.print("Enter the ID of the task to remove: ");
                    try {
                        int id = Integer.parseInt(scan.nextLine());
                        if (manager.removeTask(id)) {
                            System.out.println("🗑️ Task removed.");
                        } else {
                            System.out.println("Task ID not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number ID.");
                    }
                    break;

                case "4":
                    System.out.print("");

                case "5":
                    System.out.println("Closing shop!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Select a number between 1-4");
            }

        }

        // Write to file
        // Read to file

        // Write NEW file
        // Classify files
    }

    // classify task into different categories.
}