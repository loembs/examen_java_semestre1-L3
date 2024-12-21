package com.patrick.entities.Enums;

public enum Role {
    BOUTIQUIER(1),
    ADMIN(2),
    CLIENT(3);

    public int compareTo(String role) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getById(int id) {
        for (Role role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid TypeTransaction id: " + id);
    }
}
