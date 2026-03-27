package com.calendar.event;

import com.calendar.Event;
import com.calendar.utilisateur.User;
import com.calendar.vo.DateEvenement;
import com.calendar.vo.DureeMinute;
import com.calendar.vo.EventId;
import com.calendar.vo.TitreEvenement;

import java.time.LocalDateTime;

/**
 * Classe représentant un évènement
 */
public abstract class Evenement {
    public final EventId eventId;
    public TitreEvenement  titreEvenement;
    public User proprietaire;
    public DateEvenement dateDebut;
    public DureeMinute dureeMinutes;

    public Evenement(TitreEvenement titre, User user, DateEvenement  dateDebut, DureeMinute dureeMinutes) {
        this.eventId = EventId.generer();
        this.titreEvenement = titre;
        this.proprietaire = user;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    abstract public String description();

    public DateEvenement getDateFin() {
        LocalDateTime dateFin = LocalDateTime.of(
                        this.dateDebut.annee(),this.dateDebut.mois(),this.dateDebut.jour(),
                        this.dateDebut.heure(),this.dateDebut.minute(), 0)
                .plusMinutes(this.dureeMinutes.valeur());

        return new DateEvenement(dateFin.getYear(),dateFin.getMonthValue(),dateFin.getDayOfMonth(),dateFin.getHour(),dateFin.getMinute());
    }

    public abstract boolean occupeCreneau(DateEvenement debutTest, DateEvenement finTest);

    public boolean conflit(Evenement autre) {
        return autre.occupeCreneau(this.dateDebut, this.getDateFin())
                || this.occupeCreneau(autre.dateDebut, autre.getDateFin());
    }

}
