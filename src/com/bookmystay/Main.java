// Use Case-4: Payslip Print / Download
// Create a downloadable payslip copy ensuring data integrity and immutability
// @author Developer
// @version 4.0 UC-4: Payslip Print / Download

package com.bookmystay;

import java.util.Scanner;

import com.bookmystay.guest.BookingQueue;
import com.bookmystay.guest.Guest;
import com.bookmystay.inventory.Admin;
import com.bookmystay.inventory.RoomInventory;

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
                case 1 -> admin.showMenu();
                case 2 -> guest.showMenu();
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

