package com.calendar.event;

import com.calendar.utilisateur.User;
import com.calendar.vo.DateEvenement;
import com.calendar.vo.DureeMinute;
import com.calendar.vo.TitreEvenement;

import java.time.LocalDateTime;

/**
 * Classe représentant un Rendez-Vous personnel
 */
public class RDV extends Evenement {

    public RDV(TitreEvenement titre, User user, DateEvenement dateDebut, DureeMinute dureeMinutes) {
        super(titre, user, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : "+ this.titreEvenement.valeur() + " à " + this.dateDebut.toString();
    }

    @Override
    public boolean occupeCreneau(DateEvenement debutTest, DateEvenement finTest) {
        return this.dateDebut.estAvant(finTest) && debutTest.estAvant(this.getDateFin());
    }

}
