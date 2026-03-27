package com.calendar.vo;

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
}
