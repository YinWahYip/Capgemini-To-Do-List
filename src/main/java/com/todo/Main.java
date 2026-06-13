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
            System.out.println("4. Exit");
            System.out.print("Enter a number to choose an option: ");

            String choice = scan.nextLine();

        }

        System.out.println("Welcome! What would you like to do today?");

        try {
            FileWriter writer = new FileWriter("file_name.txt");

        } catch (IOException e) {
            System.out.println("Bad input");
        }
        // Write to file
        // Read to file

        // Write NEW file
        // Classify files
    }

    // classify task into different categories.
}