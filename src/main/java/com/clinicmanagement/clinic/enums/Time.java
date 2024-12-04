package com.clinicmanagement.clinic.enums;

public enum Time {
    MORNING("08:00 - 12:00"),
    AFTERNOON("13:00 - 17:00"),
    EVENING("18:00 - 21:00");

    private final String slot;

    Time(String slot) {
        this.slot = slot;
    }

    public String getSlot() {
        return slot;
    }
}