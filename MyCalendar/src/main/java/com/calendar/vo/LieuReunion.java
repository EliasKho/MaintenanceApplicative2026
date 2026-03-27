package com.calendar.vo;

/**
 * Valeur représentant une salle de réunion
 * @param valeur
 */
public record LieuReunion(String valeur) {
    public LieuReunion{
        if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("Le nom du lieu de la réunion ne peut pas être nul.");
    }
}
