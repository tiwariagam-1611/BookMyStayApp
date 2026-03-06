package com.bookmystay.guest;

// ============== Reservation ================
/*
 * Reservation represents a booking request made by a guest.
 *
 * Why this class exists:
 * - Encapsulates guest and room type details
 * - Provides a simple, immutable record of a reservation
 * - Acts as a core data model for booking flows
 *
 * Key Concepts:
 * - Encapsulation (private fields with getters)
 * - Composition with RoomInventory (roomType reference)
 * - Readability via toString() override
 */

public class Reservation {
    private String guestName;
    private String roomType;
    private String roomId; // unique room ID assigned at confirmation

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void assignRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }

    @Override
    public String toString() {
        return "Reservation [Guest=" + guestName +
               ", RoomType=" + roomType +
               (roomId != null ? ", RoomID=" + roomId : "") + "]";
    }
}

