package com.calendar.event;

import com.calendar.utilisateur.User;
import com.calendar.vo.*;

import java.util.List;

/**
 * Classe représentant un évènement périodique
 */
public class Periodique extends Evenement {

    public Recurrence recurrence;

    public Periodique(TitreEvenement titre, User user, DateEvenement dateDebut, DureeMinute dureeMinutes, Recurrence rec) {
        super(titre, user, dateDebut, dureeMinutes);
        this.recurrence = rec;
    }

    @Override
    public String description() {
        return "Événement périodique : "+ this.titreEvenement.valeur() + " tous les " + recurrence.valeur() + "jours";
    }

    @Override
    public boolean occupeCreneau(DateEvenement debutTest, DateEvenement finTest) {
        // On calcule l'écart en jours et on vérifie si c'est un multiple
        long joursEntre = java.time.temporal.ChronoUnit.DAYS.between(
                java.time.LocalDateTime.of(this.dateDebut.annee(), this.dateDebut.mois(), this.dateDebut.jour(), 0, 0),
                java.time.LocalDateTime.of(debutTest.annee(), debutTest.mois(), debutTest.jour(), 0, 0)
        );
        boolean estLeBonJour = (joursEntre % this.recurrence.valeur() == 0);

        // On crée des dates temporaires qui ont la même année/mois/jour que le début de l'événement périodique
        // mais avec les heures/minutes du créneau à tester.
        DateEvenement debutNormalise = new DateEvenement(
                this.dateDebut.annee(), this.dateDebut.mois(), this.dateDebut.jour(),
                debutTest.heure(), debutTest.minute()
        );
        DateEvenement finNormalise = new DateEvenement(
                this.dateDebut.annee(), this.dateDebut.mois(), this.dateDebut.jour(),
                finTest.heure(), finTest.minute()
        );

        // 3. Comparaison (Logique du conflit temporel classique)
        // (Début1 < FinTest) && (DébutTest < Fin1)
        boolean chevauchementHoraire = this.dateDebut.estAvant(finNormalise)
                && debutNormalise.estAvant(this.getDateFin());

        // Le conflit existe si c'est le bon jour ET que les heures se chevauchent
        return estLeBonJour && chevauchementHoraire;
    }

}
