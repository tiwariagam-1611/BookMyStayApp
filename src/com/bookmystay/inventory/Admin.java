package com.bookmystay.inventory;

import java.util.Scanner;
import com.bookmystay.guest.BookingQueue;
import com.bookmystay.guest.Reservation;
import com.bookmystay.guest.BookingHistory;

public class Admin {
    private RoomInventory inventory;
    private BookingQueue bookingQueue;
    private BookingHistory bookingHistory;
    private Scanner sc;

    public Admin(RoomInventory inventory, BookingQueue bookingQueue, Scanner sc, BookingHistory bookingHistory) {
        this.inventory = inventory;
        this.bookingQueue = bookingQueue;
        this.sc = sc;
        this.bookingHistory = bookingHistory;
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
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Room Type");
            System.out.println("2. Update Room Count");
            System.out.println("3. Update Room Price");
            System.out.println("4. Show Inventory");
            System.out.println("5. Show Booking Queue");
            System.out.println("6. Process Next Booking (Confirm)");
            System.out.println("7. Show Booking History");
            System.out.println("8. Cancel Booking");
            System.out.println("9. Generate Report");
            System.out.println("10. Back");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
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
                case 5 -> bookingQueue.showQueue();
                case 6 -> {
                    Reservation next = bookingQueue.processNext();
                    if (next != null) {
                        if (inventory.isAvailable(next.getRoomType())) {
                            String roomId = inventory.allocateRoom(next.getRoomType());
                            next.assignRoomId(roomId);
                            System.out.println("Booking confirmed: " + next);

                            // UC6: Add to booking history
                            bookingHistory.addConfirmedBooking(next);
                        } else {
                            System.out.println("Booking failed for " + next.getGuestName() +
                                               " (No available " + next.getRoomType() + ")");
                        }
                    } else {
                        System.out.println("No pending requests.");
                    }
                }
                case 7 -> bookingHistory.showHistory();
                case 8 -> {
                    System.out.print("Enter guest name: ");
                    String guestName = sc.nextLine();
                    System.out.print("Enter room ID: ");
                    String roomId = sc.nextLine();
                    bookingHistory.cancelBooking(guestName, roomId);
                }
                case 9 -> bookingHistory.generateReport();
                case 10 -> adminRunning = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
