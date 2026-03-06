package com.bookmystay;

// ============== Room Inventory ================
/*
 * RoomInventory manages hotel room types, counts, and prices.
 *
 * Why this class exists:
 * - Provides a single source of truth for room availability
 * - Centralizes inventory data for consistency
 * - Supports dynamic updates to counts and prices
 *
 * Key Concepts:
 * - Uses HashMap for O(1) lookups
 * - Clean separation of room data
 * - Ensures scalability for new room types
 */

import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> roomCounts;
    private Map<String, Double> roomPrices;

    public RoomInventory() {
        roomCounts = new HashMap<>();
        roomPrices = new HashMap<>();
    }

    public void addRoomType(String type, int count, double price) {
        roomCounts.put(type, count);
        roomPrices.put(type, price);
        System.out.println("Added: " + type + " | Count: " + count + " | Price: " + price);
    }

    public void updateRoomCount(String type, int newCount) {
        if (roomCounts.containsKey(type)) {
            roomCounts.put(type, newCount);
            System.out.println("Updated count for " + type + ": " + newCount);
        } else {
            System.out.println("Room type not found: " + type);
        }
    }

    public void updateRoomPrice(String type, double newPrice) {
        if (roomPrices.containsKey(type)) {
            roomPrices.put(type, newPrice);
            System.out.println("Updated price for " + type + ": " + newPrice);
        } else {
            System.out.println("Room type not found: " + type);
        }
    }

    public void showAvailability() {
        System.out.println("\n--- Current Room Inventory ---");
        for (String type : roomCounts.keySet()) {
            System.out.println("Type: " + type +
                               " | Available: " + roomCounts.get(type) +
                               " | Price: " + roomPrices.get(type));
        }
    }

    public boolean isAvailable(String type) {
        return roomCounts.containsKey(type) && roomCounts.get(type) > 0;
    }

    public double getPrice(String type) {
        return roomPrices.getOrDefault(type, 0.0);
    }
}


