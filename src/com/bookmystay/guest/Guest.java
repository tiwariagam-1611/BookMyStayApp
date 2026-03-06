package com.bookmystay.guest;

import java.util.Scanner;

import com.bookmystay.inventory.RoomInventory;

public class Guest {
    private RoomInventory inventory;
    private BookingQueue bookingQueue;
    private Scanner sc;

    public Guest(RoomInventory inventory, BookingQueue bookingQueue, Scanner sc) {
        this.inventory = inventory;
        this.bookingQueue = bookingQueue;
        this.sc = sc;
    }

    private String getRoomTypeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> "Single";
            case 2 -> "Double";
            case 3 -> "Suite";
            default -> null;
        };
    }

    public void showMenu() {
        boolean guestRunning = true;
        while (guestRunning) {
            System.out.println("\n--- Guest Menu ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Check Specific Room Type");
            System.out.println("3. Place Booking Request");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
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
                case 3 -> {
                    System.out.print("Enter your name: ");
                    String guestName = sc.nextLine();
                    System.out.println("Select room type: 1.Single  2.Double  3.Suite");
                    int typeChoice = sc.nextInt();
                    sc.nextLine();
                    String type = getRoomTypeFromChoice(typeChoice);
                    if (type == null) {
                        System.out.println("Invalid choice.");
                        continue;
                    }
                    Reservation reservation = new Reservation(guestName, type);
                    bookingQueue.addRequest(reservation);
                }
                case 4 -> guestRunning = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

