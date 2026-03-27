package com.calendar.vo;

/**
 * Valeur représentant la durée en minute d'un évènement
 * @param valeur
 */
public record DureeMinute(int valeur) {
    public DureeMinute {
        if (valeur <= 0) throw new IllegalArgumentException("La durée de l'évènement doit être supérieure à 0.");
    }
}
