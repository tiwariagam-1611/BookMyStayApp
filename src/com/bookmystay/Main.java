package com.bookmystay;

//Use Case-2: Room Search & Availability Check
//Allow guests to search rooms without altering inventory
//@author Developer
//@version 2.0 UC-2: Room Search & Availability Check

import java.util.Scanner;

public class Main {
    private static String getRoomTypeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> "Single";
            case 2 -> "Double";
            case 3 -> "Suite";
            default -> null;
        };
    }

    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel System ---");
            System.out.println("1. Admin Menu");
            System.out.println("2. Guest Menu");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 1 -> { // Admin Menu
                    boolean adminRunning = true;
                    while (adminRunning) {
                        System.out.println("\n--- Admin Menu ---");
                        System.out.println("1. Add Room Type");
                        System.out.println("2. Update Room Count");
                        System.out.println("3. Update Room Price");
                        System.out.println("4. Show Inventory");
                        System.out.println("5. Back");
                        System.out.print("Enter choice: ");

                        int adminChoice = sc.nextInt();
                        sc.nextLine();

                        switch (adminChoice) {
                            case 1 -> {
                                System.out.println("Select room type: 1.Single  2.Double  3.Suite");
                                int typeChoice = sc.nextInt();
                                sc.nextLine();
                                String type = getRoomTypeFromChoice(typeChoice);
                                if (type == null) {
                                    System.out.println("Invalid choice.");
                                    continue;
                                }
                                System.out.print("Enter count: ");
                                int count = sc.nextInt();
                                System.out.print("Enter price: ");
                                double price = sc.nextDouble();
                                sc.nextLine();
                                inventory.addRoomType(type, count, price);
                            }
                            case 2 -> {
                                System.out.println("Select room type to update count: 1.Single  2.Double  3.Suite");
                                int typeChoice = sc.nextInt();
                                sc.nextLine();
                                String type = getRoomTypeFromChoice(typeChoice);
                                if (type == null) {
                                    System.out.println("Invalid choice.");
                                    continue;
                                }
                                System.out.print("Enter new count: ");
                                int newCount = sc.nextInt();
                                sc.nextLine();
                                inventory.updateRoomCount(type, newCount);
                            }
                            case 3 -> {
                                System.out.println("Select room type to update price: 1.Single  2.Double  3.Suite");
                                int typeChoice = sc.nextInt();
                                sc.nextLine();
                                String type = getRoomTypeFromChoice(typeChoice);
                                if (type == null) {
                                    System.out.println("Invalid choice.");
                                    continue;
                                }
                                System.out.print("Enter new price: ");
                                double newPrice = sc.nextDouble();
                                sc.nextLine();
                                inventory.updateRoomPrice(type, newPrice);
                            }
                            case 4 -> inventory.showAvailability();
                            case 5 -> adminRunning = false;
                            default -> System.out.println("Invalid choice.");
                        }
                    }
                }
                case 2 -> { // Guest Menu
                    boolean guestRunning = true;
                    while (guestRunning) {
                        System.out.println("\n--- Guest Menu ---");
                        System.out.println("1. View Available Rooms");
                        System.out.println("2. Check Specific Room Type");
                        System.out.println("3. Back");
                        System.out.print("Enter choice: ");

                        int guestChoice = sc.nextInt();
                        sc.nextLine();

                        switch (guestChoice) {
                            case 1 -> inventory.showAvailability();
                            case 2 -> {
                                System.out.println("Select room type: 1.Single  2.Double  3.Suite");
                                int typeChoice = sc.nextInt();
                                sc.nextLine();
                                String type = getRoomTypeFromChoice(typeChoice);
                                if (type == null) {
                                    System.out.println("Invalid choice.");
                                    continue;
                                }
                                if (inventory.isAvailable(type)) {
                                    System.out.println(type + " is available at price: " + inventory.getPrice(type));
                                } else {
                                    System.out.println(type + " is NOT available.");
                                }
                            }
                            case 3 -> guestRunning = false;
                            default -> System.out.println("Invalid choice.");
                        }
                    }
                }
                case 3 -> {
                    running = false;
                    System.out.println("Exiting system...");
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}

