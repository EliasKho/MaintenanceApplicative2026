package com.calendar.menu;

import com.calendar.Agenda;
import com.calendar.event.*;
import com.calendar.ServiceSauvegarde;
import com.calendar.utilisateur.*;
import com.calendar.vo.*;
import java.util.*;

public class CommandeAjouterPeriodique implements CommandeMenu {
    private final ServiceSauvegarde sauvegarde;

    public CommandeAjouterPeriodique(ServiceSauvegarde sauvegarde) {
        this.sauvegarde = sauvegarde;
    }

    @Override
    public void executer(Scanner sc, User session, Agenda agenda, List<User> annuaire) {
        System.out.print("Titre de l'événement : ");
        TitreEvenement titre = new TitreEvenement(sc.nextLine());
        DateEvenement date = SaisieUtils.demanderDate(sc);
        System.out.print("Frequence (en jours) : ");
        Recurrence freq = new Recurrence(Integer.parseInt(sc.nextLine()));

        // Note : Durée par défaut 0 ou saisie selon ton modèle Periodique
        agenda.ajouter(new Periodique(titre, session, date, new DureeMinute(0), freq));
        sauvegarde.sauvegarderAgenda(agenda);
        System.out.println("Événement ajouté.");
    }
}