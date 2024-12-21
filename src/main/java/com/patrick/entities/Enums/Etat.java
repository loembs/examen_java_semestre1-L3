package com.patrick.entities.Enums;

public enum Etat {
    ACTIVER(1),
    DESACTIVER(2);
    
    private final int id;

    Etat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Etat getById(int id) {
        for (Etat etat : values()) {
            if (etat.getId() == id) {
                return etat;
            }
        }
        throw new IllegalArgumentException("Invalid TypeTransaction id: " + id);
    }
}
