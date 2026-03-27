package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.User;
import com.calendar.vo.*;
import java.util.*;

public class CommandeAjouterReunion implements CommandeMenu {
    public void executer(Scanner sc, User session, Agenda agenda, List<User> ann) {
        TitreEvenement titre = new TitreEvenement(SaisieUtils.demander(sc, "Titre"));
        DateEvenement date = SaisieUtils.demanderDate(sc);
        DureeMinute duree = new DureeMinute(Integer.parseInt(SaisieUtils.demander(sc, "Durée (min)")));
        LieuReunion lieu = new LieuReunion(SaisieUtils.demander(sc, "Lieu"));

        List<User> participants = new ArrayList<>();
        agenda.ajouter(new Reunion(titre, session, date, duree, lieu, participants));
    }
}