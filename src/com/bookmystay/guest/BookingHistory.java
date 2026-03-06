package com.bookmystay.guest;

import java.util.ArrayList;
import java.util.List;

public class BookingHistory {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new ArrayList<>();
    }

    // Store confirmed reservation
    public void addConfirmedBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    // Review all confirmed bookings
    public void showHistory() {
        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings yet.");
        } else {
            System.out.println("\n--- Booking History ---");
            for (Reservation r : confirmedBookings) {
                System.out.println(r);
            }
        }
    }

    // Cancel booking by guest name + room ID
    public void cancelBooking(String guestName, String roomId) {
        Reservation toRemove = null;
        for (Reservation r : confirmedBookings) {
            if (r.getGuestName().equalsIgnoreCase(guestName) &&
                r.getRoomId().equalsIgnoreCase(roomId)) {
                toRemove = r;
                break;
            }
        }
        if (toRemove != null) {
            confirmedBookings.remove(toRemove);
            System.out.println("Booking cancelled: " + toRemove);
        } else {
            System.out.println("No matching booking found for cancellation.");
        }
    }

    // Generate simple report
    public void generateReport() {
        System.out.println("\n--- Booking Report ---");
        System.out.println("Total confirmed bookings: " + confirmedBookings.size());

        long singles = confirmedBookings.stream().filter(r -> r.getRoomType().equals("Single")).count();
        long doubles = confirmedBookings.stream().filter(r -> r.getRoomType().equals("Double")).count();
        long suites = confirmedBookings.stream().filter(r -> r.getRoomType().equals("Suite")).count();

        System.out.println("Singles booked: " + singles);
        System.out.println("Doubles booked: " + doubles);
        System.out.println("Suites booked: " + suites);
    }
}
