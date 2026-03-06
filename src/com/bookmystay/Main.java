package com.bookmystay;

// Use Case-1: Room Inventory Setup & Management
// Maintain a single source of truth for hotel room inventory
// @author Developer
// @version 1.0 UC-1: Room Inventory Setup & Management

import java.util.Scanner;

public class Main {
    private static String getRoomTypeFromChoice(int choice) {
        switch (choice) {
            case 1: return "Single";
            case 2: return "Double";
            case 3: return "Suite";
            default: return null;
        }
    }

    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel Admin Menu ---");
            System.out.println("1. Add Room Type (Single/Double/Suite)");
            System.out.println("2. Update Room Count");
            System.out.println("3. Update Room Price");
            System.out.println("4. Show Inventory");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Select room type: 1.Single  2.Double  3.Suite");
                    int typeChoice = sc.nextInt();
                    sc.nextLine();
                    String type = getRoomTypeFromChoice(typeChoice);
                    if (type == null) {
                        System.out.println("Invalid choice. Only 1, 2, or 3 allowed.");
                        break;
                    }
                    System.out.print("Enter count: ");
                    int count = sc.nextInt();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    inventory.addRoomType(type, count, price);
                    break;

                case 2:
                    System.out.println("Select room type to update count: 1.Single  2.Double  3.Suite");
                    int typeCountChoice = sc.nextInt();
                    sc.nextLine();
                    String typeCount = getRoomTypeFromChoice(typeCountChoice);
                    if (typeCount == null) {
                        System.out.println("Invalid choice.");
                        break;
                    }
                    System.out.print("Enter new count: ");
                    int newCount = sc.nextInt();
                    sc.nextLine();
                    inventory.updateRoomCount(typeCount, newCount);
                    break;

                case 3:
                    System.out.println("Select room type to update price: 1.Single  2.Double  3.Suite");
                    int typePriceChoice = sc.nextInt();
                    sc.nextLine();
                    String typePrice = getRoomTypeFromChoice(typePriceChoice);
                    if (typePrice == null) {
                        System.out.println("Invalid choice.");
                        break;
                    }
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    inventory.updateRoomPrice(typePrice, newPrice);
                    break;

                case 4:
                    inventory.showAvailability();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
