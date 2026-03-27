package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.utilisateur.User;
import com.calendar.vo.*;

import java.util.List;
import java.util.Scanner;

public class CommandeAjouterRDV implements CommandeMenu {
    public void executer(Scanner sc, User session, Agenda agenda, List<User> ann) {
        agenda.ajouter(new RDV(
                new TitreEvenement(SaisieUtils.demander(sc, "Titre")),
                session,
                SaisieUtils.demanderDate(sc),
                new DureeMinute(Integer.parseInt(SaisieUtils.demander(sc, "Durée (min)")))
        ));
    }
}