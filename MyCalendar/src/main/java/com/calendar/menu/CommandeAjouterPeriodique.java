package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.User;
import com.calendar.vo.*;
import java.util.*;

public class CommandeAjouterPeriodique implements CommandeMenu {
    public void executer(Scanner sc, User session, Agenda agenda, List<User> ann) {
        agenda.ajouter(new Periodique(
                new TitreEvenement(SaisieUtils.demander(sc, "Titre")),
                session,
                SaisieUtils.demanderDate(sc),
                new DureeMinute(Integer.parseInt(SaisieUtils.demander(sc, "Durée (min)"))),
                new Recurrence(Integer.parseInt(SaisieUtils.demander(sc, "Fréquence (jours)")))
        ));
    }
}
