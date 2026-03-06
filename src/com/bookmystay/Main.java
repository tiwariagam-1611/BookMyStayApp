// Use Case-5: Add-On Service Selection
// Enhance bookings with optional services like breakfast or spa
// @author Developer
// @version 5.0

package com.bookmystay;

import java.util.Scanner;
import com.bookmystay.inventory.Admin;
import com.bookmystay.inventory.RoomInventory;
import com.bookmystay.guest.BookingQueue;
import com.bookmystay.guest.Guest;

public class Main {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingQueue bookingQueue = new BookingQueue();
        Scanner sc = new Scanner(System.in);

        Admin admin = new Admin(inventory, bookingQueue, sc);
        Guest guest = new Guest(inventory, bookingQueue, sc);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel System ---");
            System.out.println("1. Admin Menu");
            System.out.println("2. Guest Menu");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> admin.showMenu();   // UC1–UC4 logic lives here
                case 2 -> guest.showMenu();   // UC2–UC5 logic lives here
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
