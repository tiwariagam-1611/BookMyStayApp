package com.bookmystay.guest;

// ============== Booking Queue ================
/*
 * BookingQueue manages pending reservation requests in FIFO order.
 *
 * Why this class exists:
 * - Enforces fairness by processing requests in arrival sequence
 * - Prevents race conditions during high-traffic booking
 * - Provides a centralized queue for reservation handling
 *
 * Key Concepts:
 * - Uses LinkedList as a Queue implementation
 * - Demonstrates First-In-First-Out (FIFO) principle
 * - Clean separation of request intake and processing
 */

import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added: " + reservation);
    }

    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("No pending booking requests.");
        } else {
            System.out.println("\n--- Pending Booking Requests ---");
            for (Reservation r : queue) {
                System.out.println(r);
            }
        }
    }

    public Reservation processNext() {
        return queue.poll(); // FIFO
    }
}
