package com.calendar.vo;

/**
 * Valeur représentant le titre d'un évènement
 * @param valeur
 */
public record TitreEvenement(String valeur) {
    public TitreEvenement {
        if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("Le titre de l'évènement ne doit pas être nul.");
    }
}
