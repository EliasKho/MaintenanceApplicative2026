package com.calendar.event;

import com.calendar.utilisateur.User;
import com.calendar.vo.DateEvenement;
import com.calendar.vo.DureeMinute;
import com.calendar.vo.LieuReunion;
import com.calendar.vo.TitreEvenement;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe représentant une réunion
 */
public class Reunion extends Evenement {

    public LieuReunion lieuReunion;
    public List<User> participants;

    public Reunion(TitreEvenement titre, User user, DateEvenement dateDebut, DureeMinute dureeMinutes, LieuReunion lieu, List<User> users) {
        super(titre, user, dateDebut, dureeMinutes);
        this.lieuReunion = lieu;
        this.participants = users;
    }

    @Override
    public String description() {
        String sortie = "Réunion : "+ this.titreEvenement.valeur() + " à " + this.lieuReunion.valeur() +
                " avec "+this.proprietaire.toString();

        String sortie2 = "";
        for (User user : this.participants) {
            sortie2 += ", "+user.toString();
        }

        return sortie+sortie2;
    }

    @Override
    public boolean occupeCreneau(DateEvenement debutTest, DateEvenement finTest) {
        return this.dateDebut.estAvant(finTest) && debutTest.estAvant(this.getDateFin());
    }

}
