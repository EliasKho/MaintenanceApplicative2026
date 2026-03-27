package com.calendar.vo;

import java.util.UUID;

/**
 * Identifiant unique d'un évènement
 */
public record EventId(UUID valeur) {
    public EventId {
        if (valeur == null) {
            throw new IllegalArgumentException("L'identifiant ne peut pas être nul.");
        }
    }

    /**
     * Méthode utilitaire pour générer un nouvel identifiant aléatoire
     */
    public static EventId generer() {
        return new EventId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return valeur.toString();
    }
}