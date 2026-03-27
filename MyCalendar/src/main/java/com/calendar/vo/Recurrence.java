package com.calendar.vo;

/**
 *Valeur représentant la récurrence d'un évènement périodique
 *      Si la valeur est 7, l'évènement a lieu chaque semaine à la même heure.
 * @param valeur
 */
public record Recurrence(int valeur) {
    public Recurrence{
        if (valeur <= 0) throw new IllegalArgumentException("La récurrence de l'évènement périodique doit être supérieur à 0 jours.");
    }
}
