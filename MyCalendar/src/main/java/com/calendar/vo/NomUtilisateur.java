package com.calendar.vo;

/**
 * Valeur représentant le username d'un utilisateur
 * @param valeur
 */
public record NomUtilisateur(String valeur) {
    public NomUtilisateur{
        if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être nul.");
    }
}
