package com.calendar.vo;

import java.time.LocalDateTime;

/**
 * Valeur représentant la date d'un évènement
 * @param annee
 * @param mois
 * @param jour
 * @param heure
 * @param minute
 */
public record DateEvenement(int annee,int mois, int jour, int heure, int minute) {
    public DateEvenement {
        //TODO Restraindre la date (annee debut + différents mois)
        if(annee<2000){
            throw new IllegalArgumentException("La date ne peut pas être inférieure à 2000.");
        }
        if(mois<1 || mois>12){
            throw new IllegalArgumentException("Le mois entré n'est pas entre 1 et 12.");
        }
        if(jour<1 || jour>31){
            throw new IllegalArgumentException("Le jour entré est invalide.");
        }
        if(heure<0 || heure>23){
            throw new IllegalArgumentException("L'heure entrée n'est pas entre 0 et 23.");
        }
        if(minute<0 || minute>59){
            throw new IllegalArgumentException("La minute entrée n'est pas entre 0 et 59.");
        }
    }

    @Override
    public String toString() {
        return String.format("%d/%02d/%02d à %02dh%02d",
                annee, mois, jour, heure, minute);
    }

    public boolean estAvant(DateEvenement autre) {
        LocalDateTime cetteDate = LocalDateTime.of(annee, mois, jour, heure, minute);
        LocalDateTime autreDate = LocalDateTime.of(autre.annee(), autre.mois(), autre.jour(), autre.heure(), autre.minute());
        return cetteDate.isBefore(autreDate);
    }

    public long ecartEnJours(DateEvenement autre) {
        return Math.abs(java.time.temporal.ChronoUnit.DAYS.between(
                LocalDateTime.of(annee, mois, jour, 0, 0),
                LocalDateTime.of(autre.annee(), autre.mois(), autre.jour(), 0, 0)
        ));
    }
}
